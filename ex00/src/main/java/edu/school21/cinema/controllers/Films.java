package edu.school21.cinema.controllers;

import com.github.javafaker.Faker;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;

@Controller
public class Films {

    private final FilmService filmService;
    private final MessageService messageService;
    private final Faker faker;

    @Autowired
    public Films(FilmService filmService, MessageService messageService, Faker faker) {
        this.filmService = filmService;
        this.messageService = messageService;
        this.faker = faker;
    }

    @GetMapping(value = "/films")
    public String getFilms(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("Films", filmService.findAll());
        return "/films/allFilms";
    }

    @GetMapping(value = "/films/{id}")
    public String getFilm(@PathVariable String id, @ModelAttribute("model") ModelMap model) {
        Optional<Film> film = validateId(id);
        if (film.isPresent()) {
            model.addAttribute("Film", film.get());
        } else {
            return "notFound";
        }
        return "/films/id";
    }

    @GetMapping(value = "/films/{id}/chat")
    public String getChat(@ModelAttribute("model") ModelMap model, @PathVariable String id,
                          HttpServletRequest request, HttpServletResponse response) {
        Optional<Film> film = validateId(id);
        if (film.isPresent()) {
            model.addAttribute("Messages", messageService.findLast20ByFilmId(film.get().getId()));
            model.addAttribute("Film", film.get());
            model.addAttribute("Id", film.get().getId());
            boolean isCookie = false;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (Objects.equals(cookie.getName(), "Name")) {
                        model.addAttribute("Name", cookie.getValue());
                        isCookie = true;
                        break;
                    }
                }
            }
            if (!isCookie) {
                Cookie cookie = new Cookie("Name", faker.witcher().character().replaceAll(" ", "_"));
                response.addCookie(cookie);
                model.addAttribute("Name", cookie.getValue());
            }
        } else {
            response.setStatus(404);
            return "notFound";
        }
        return "/films/chat";
    }

    @MessageMapping(value = "/films/{id}/chat/send")
    @SendTo(value = "/films/{id}/chat/messages")
    public Message getMessage(@Payload Message message, @DestinationVariable Long id) {
        System.out.println(message);
        messageService.add(message);
        return message;
    }

    private Optional<Film> validateId(String id) {
        try {
            Long idValue = Long.parseLong(id);
            return filmService.findById(idValue);
        } catch (NumberFormatException ignored) {}
        return Optional.empty();
    }
}
