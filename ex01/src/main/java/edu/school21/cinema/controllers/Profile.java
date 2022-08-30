package edu.school21.cinema.controllers;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    private final ImageService imageService;
    private final UserService userService;
    private final UserSessionService userSessionService;

    @Autowired
    public Profile(ImageService imageService, UserService userService, UserSessionService userSessionService) {
        this.imageService = imageService;
        this.userService = userService;
        this.userSessionService = userSessionService;
    }

    @GetMapping
    public String get(HttpServletRequest request, @ModelAttribute("model") ModelMap model, Authentication authentication) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("UserAttributes");
        if (user == null) {
            user = userService.findByEmail(authentication.getName()).get();
            session.setAttribute("UserAttributes", user);
        }
        model.addAttribute("Images", imageService.findAllByUserId(user.getId()));
        model.addAttribute("User", user);
        Object object = session.getAttribute("SessionAttributes");
        if (object == null) {
            object = userSessionService.getAllByUserId(user.getId());
            session.setAttribute("SessionAttributes", object);
        }
        model.addAttribute("Sessions", object);
        return "profile";
    }
}
