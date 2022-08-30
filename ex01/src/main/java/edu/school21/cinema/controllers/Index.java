package edu.school21.cinema.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Index {

    @GetMapping
    public String get(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            model.addAttribute("Authenticated", false);
        } else {
            model.addAttribute("Authenticated", true);
        }
        return "index";
    }
}
