package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.PosterService;
import edu.school21.cinema.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin/panel/films")
public class FilmsManage {

    private final FilmService filmService;
    private final PosterService posterService;
    private final String path;

    @Autowired
    public FilmsManage(FilmService filmService, PosterService posterService, String path) {
        this.filmService = filmService;
        this.posterService = posterService;
        this.path = path;
    }

    @GetMapping
    public String get(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("FilmsList", filmService.findAll());
        return "admin/panel/films";
    }

    @PostMapping
    public String post(@ModelAttribute("model") ModelMap model,
                       HttpServletRequest request,
                       @RequestParam(value = "file") MultipartFile file) {
        Optional<Film> film = Film.validateFilm(request.getParameter("title"),
                request.getParameter("description"),
                request.getParameter("year"),
                request.getParameter("age"));
        if (film.isPresent()) {
            if (filmService.add(film.get())) {
                if (!file.isEmpty()) {
                    imageHelper(file, film.get());
                }
            }
        }
        return "redirect:/admin/panel/films";
    }

    private void imageHelper(MultipartFile file, Film film) {
        String name = file.getOriginalFilename();
        String[] extension = file.getContentType().split("/");
        UUID uuid = UUID.randomUUID();
        if (Utils.createFile(file, path, extension[1], uuid)) {
            Poster poster = new Poster();
            poster.setName(name);
            poster.setUuid(uuid);
            poster.setExtension(extension[1]);
            posterService.add(poster);
            film.setPoster(poster);
            filmService.update(film);
        }
    }
}
