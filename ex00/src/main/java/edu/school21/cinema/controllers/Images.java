package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.PosterService;
import edu.school21.cinema.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    public Images(String path, PosterService posterService, FilmService filmService) {
        this.path = path;
        this.posterService = posterService;
        this.filmService = filmService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<byte[]> get(@PathVariable String id) {
        UUID uuid = null;
        Poster poster = null;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ignored) {}
        if (uuid != null) {
            poster = posterService.findByUuid(uuid);
        }
        if (poster != null) {
            try {
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf("image/" + poster.getExtension()))
                        .body(FileUtils.readFileToByteArray(new File(path + "/" + uuid + "." + poster.getExtension())));
            } catch (IOException e) {
                System.err.println(e.getMessage());
                Film film = filmService.findByPosterId(poster.getId());
                film.setPoster(null);
                filmService.update(film);
                posterService.delete(poster);
                return ResponseEntity.status(HttpStatus.GONE)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("Poster not found, probably it doesn't exist anymore!".getBytes(StandardCharsets.UTF_8));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Poster not found!".getBytes(StandardCharsets.UTF_8));
    }

    @PostMapping
    public String submit(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {
        String name = file.getOriginalFilename();
        String[] extension = file.getContentType().split("/");
        UUID uuid = UUID.randomUUID();
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
    }
}
