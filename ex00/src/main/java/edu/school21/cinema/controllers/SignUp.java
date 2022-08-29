package edu.school21.cinema.controllers;

import edu.school21.cinema.models.User;
import edu.school21.cinema.security.Role;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/signUp")
public class SignUp {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String get(@ModelAttribute("model") ModelMap model,
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
    public String post(HttpServletRequest req, @ModelAttribute("model") ModelMap model) {
        User user = new User(null,
                req.getParameter("fname"),
                req.getParameter("lname"),
                req.getParameter("email"),
                req.getParameter("phone"),
                req.getParameter("pass"),
                Role.USER);
        if (!userService.signUp(user)) {
            model.addAttribute("error", "User with this email already exists");
            return "signUp";
        }
        return "redirect:/signIn";
    }
}
