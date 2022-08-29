package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Avatar;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.AvatarService;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.PosterService;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/images")
public class Images {

    private final String path;
    private final PosterService posterService;
    private final FilmService filmService;
    private final AvatarService avatarService;
    private final UserService userService;

    @Autowired
    public Images(String path, PosterService posterService,
                  FilmService filmService, AvatarService avatarService,
                  UserService userService) {
        this.path = path;
        this.posterService = posterService;
        this.filmService = filmService;
        this.avatarService = avatarService;
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<byte[]> get(@PathVariable String id) {
        UUID uuid = null;
        Optional<Poster> poster = Optional.empty();
        Optional<Avatar> avatar = Optional.empty();
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ignored) {}
        if (uuid != null) {
            avatar = avatarService.findByUuid(uuid);
            if (!avatar.isPresent()) {
                poster = posterService.findByUuid(uuid);
            }
        }
        if (poster.isPresent()) {
            try {
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf("image/" + poster.get().getExtension()))
                        .body(FileUtils.readFileToByteArray(
                                new File(path + "/" + uuid + "." + poster.get().getExtension())));
            } catch (IOException e) {
                System.err.println(e.getMessage());
                Film film = filmService.findByPosterId(poster.get().getId());
                film.setPoster(null);
                filmService.update(film);
                posterService.delete(poster.get());
                return ResponseEntity.status(HttpStatus.GONE)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("Poster not found, probably it doesn't exist anymore!".getBytes(StandardCharsets.UTF_8));
            }
        } else if (avatar.isPresent()) {
            try {
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf(avatar.get().getMime()))
                        .body(FileUtils.readFileToByteArray(
                                new File(path + "/" + uuid + "." + avatar.get().getMime().split("/")[1])
                        ));
            } catch (IOException e) {
                System.err.println(e.getMessage());
                avatarService.delete(avatar.get());
                return ResponseEntity.status(HttpStatus.GONE)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("Avatar not found, probably it doesn't exist anymore!".getBytes(StandardCharsets.UTF_8));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Image not found!".getBytes(StandardCharsets.UTF_8));
    }

    @PostMapping
    public String submit(@RequestParam("file") MultipartFile file,
                         @RequestParam("id") String id,
                         @RequestParam("type") String type,
                         HttpServletRequest request,
                         Authentication authentication) {
        String name = file.getOriginalFilename();
        String[] extension = file.getContentType().split("/");
        UUID uuid = UUID.randomUUID();
        if (type.equals("poster")) {
            Optional<Film> film = filmService.findById(Long.parseLong(id));
            if (Utils.createFile(file, path, extension[1], uuid) && film.isPresent()) {
                Poster poster = new Poster();
                poster.setExtension(extension[1]);
                poster.setName(name);
                poster.setUuid(uuid);
                posterService.add(poster);
                film.get().setPoster(poster);
                filmService.update(film.get());
            }
            return "redirect:/admin/panel/films";
        } else if (type.equals("avatar")) {
            User user = (User) request.getSession().getAttribute("UserAttributes");
            if (user == null) {
                user = userService.findByEmail(authentication.getName()).get();
                request.getSession().setAttribute("UserAttributes", user);
            }
            if (Utils.createFile(file, path, extension[1], uuid)) {
                Avatar avatar = new Avatar();
                avatar.setMime(file.getContentType());
                avatar.setName(name);
                avatar.setUuid(uuid);
                avatar.setUserId(user.getId());
                avatar.setSize(file.getSize());
                avatarService.add(avatar);
            }
            return "redirect:/profile";
        }
        return "/";
    }
}
