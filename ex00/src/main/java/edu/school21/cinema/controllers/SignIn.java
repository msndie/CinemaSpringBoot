package edu.school21.cinema.controllers;

import edu.school21.cinema.security.Role;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signIn")
public class SignIn {

    @GetMapping
    public String get(Authentication authentication) {
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken
            || !authentication.isAuthenticated()) {
            return "signIn";
        }
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.name()))) {
            return "redirect:/admin/panel/halls";
        } else {
            return "redirect:/profile";
        }
    }
}
