package edu.school21.cinema.controllers;

import edu.school21.cinema.models.User;
import edu.school21.cinema.security.Role;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/signUp")
public class SignUp {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String get(@ModelAttribute("userForm") User user,
                      Authentication authentication) {
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken
                || !authentication.isAuthenticated()) {
            return "signUp";
        }
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.name()))) {
            return "redirect:/admin/panel/halls";
        } else {
            return "redirect:/profile";
        }
    }

    @PostMapping
    public String post(@ModelAttribute("userForm") @Valid User user,
                       BindingResult bindingResult,
                       Model model) {
        user.setRole(Role.USER);
        if (bindingResult.hasErrors()) {
            return "signUp";
        }
        if (!userService.signUp(user)) {
            model.addAttribute("error", "User with this email already exists");
            return "signUp";
        }
        return "redirect:/signIn";
    }
}
