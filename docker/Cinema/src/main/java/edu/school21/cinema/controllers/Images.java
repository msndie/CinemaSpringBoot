package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.ImageService;
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
    private final FilmService filmService;
    private final UserService userService;
    private final ImageService imageService;

    @Autowired
    public Images(String path,
                  FilmService filmService,
                  UserService userService,
                  ImageService imageService) {
        this.path = path;
        this.filmService = filmService;
        this.imageService = imageService;
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<byte[]> get(@PathVariable String id) {
        UUID uuid = null;
        Optional<Image> image = Optional.empty();
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ignored) {}
        if (uuid != null) {
            image = imageService.findByUuid(uuid);
        }
        if (image.isPresent()) {
            try {
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf(image.get().getMime()))
                        .body(FileUtils.readFileToByteArray(
                                new File(path + "/" + uuid + "." + image.get().getMime().split("/")[1])));
            } catch (IOException e) {
                System.err.println(e.getMessage());
                if (image.get().getType().equals("poster")) {
                    Film film = filmService.findByImageId(image.get().getId());
                    film.setImage(null);
                    filmService.update(film);
                }
                imageService.delete(image.get());
                return ResponseEntity.status(HttpStatus.GONE)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("Image not found, probably it doesn't exist anymore!".getBytes(StandardCharsets.UTF_8));
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
        if (Utils.createFile(file, path, extension[1], uuid)) {
            Image image = new Image();
            image.setMime(file.getContentType());
            image.setName(name);
            image.setUuid(uuid);
            image.setSize(file.getSize());
            if (type.equals("poster")) {
                Optional<Film> film = filmService.findById(Long.parseLong(id));
                image.setType("poster");
                imageService.add(image);
                film.get().setImage(image);
                filmService.update(film.get());
                return "redirect:/admin/panel/films";
            } else {
                User user = (User) request.getSession().getAttribute("UserAttributes");
                if (user == null) {
                    user = userService.findByEmail(authentication.getName()).get();
                    request.getSession().setAttribute("UserAttributes", user);
                }
                image.setType("avatar");
                image.setUserId(user.getId());
                imageService.add(image);
                return "redirect:/profile";
            }
        }
        return "/";
    }
}
