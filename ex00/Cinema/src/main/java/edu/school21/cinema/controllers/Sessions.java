package edu.school21.cinema.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.SessionService;
import edu.school21.cinema.utils.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/sessions")
public class Sessions {

    private SessionService sessionService;

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String get() {
        return "sessions/search";
    }

    @ResponseBody
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Search.class)
    public ResponseEntity<Object> getFilms(@RequestParam String filmName) {
        Map<String, List<Session>> map = new HashMap<>();
        map.put("sessions", sessionService.findByFilmNameContains(filmName));
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/{id}")
    public String getIdLink(@PathVariable String id, @ModelAttribute("model") ModelMap model) {
        Long idValue = null;
        try {
            idValue = Long.parseLong(id);
        } catch (NumberFormatException ignored) {}
        if (idValue != null) {
            Optional<Session> session = sessionService.findById(idValue);
            session.ifPresent(value -> model.addAttribute("Session", value));
        }
        return "sessions/sessionId";
    }
}
