package edu.school21.cinema.controllers;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class Profile {

    private AvatarService avatarService;

    @Autowired
    public void setAvatarService(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping
    public String get(HttpServletRequest request, @ModelAttribute("model") ModelMap model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("UserAttributes");
        model.addAttribute("Images", avatarService.findAllByUserId(user.getId()));
        model.addAttribute("User", user);
        model.addAttribute("Sessions", session.getAttribute("SessionAttributes"));
        return "profile";
    }
}
