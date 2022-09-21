package edu.school21.cinema.controllers;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/confirm")
public class Confirm {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String get(@PathVariable String id, HttpServletResponse res) {
        try {
            UUID uuid = UUID.fromString(id);
            Optional<User> user = userService.findByUniqueIdentifier(uuid);
            if (user.isPresent()) {
                user.get().setUniqueCode(null);
                user.get().setEnabled(true);
                userService.update(user.get());
                return "confirmationSuccess";
            }
        } catch (IllegalArgumentException ignored) {}
        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "notFound";
    }
}
