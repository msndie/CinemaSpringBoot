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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
                       Model model,
                       HttpServletRequest req) {
//        User user = new User(null,
//                req.getParameter("fname"),
//                req.getParameter("lname"),
//                req.getParameter("email"),
//                req.getParameter("phone"),
//                req.getParameter("pass"),
//                Role.USER);
        System.out.println(user);
        user.setRole(Role.USER);
        if (bindingResult.hasErrors()) {
            return "signUp";
        }
        if (!userService.signUp(user, req.getRequestURL().toString().replace(req.getServletPath(), ""))) {
            model.addAttribute("error", "User with this email already exists, or we can't send you an email");
            return "signUp";
        }
        return "redirect:/signIn";
    }
}
