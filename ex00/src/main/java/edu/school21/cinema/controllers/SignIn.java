package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.User;
import edu.school21.cinema.security.Role;
import edu.school21.cinema.services.SessionService;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Optional;

@Controller
@RequestMapping("/signIn")
public class SignIn {

//    private final UserService userService;
//    private final SessionService sessionService;
//
//    @Autowired
//    public SignIn(UserService userService, SessionService sessionService) {
//        this.userService = userService;
//        this.sessionService = sessionService;
//    }

    @GetMapping
    public String get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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

//    @PostMapping
//    public String post(HttpServletRequest req) {
//        User user = new User();
//        user.setEmail(req.getParameter("email"));
//        user.setPassword(req.getParameter("pass"));
//        if (userService.signIn(user)) {
//            req.getSession().setAttribute("UserAttributes", user);
//            String remoteAddr = req.getRemoteAddr();
//            if (remoteAddr.equals("0:0:0:0:0:0:0:1")) {
//                InetAddress localip = java.net.InetAddress.getLocalHost();
//                remoteAddr = localip.getHostAddress();
//            }
//            Session session = new Session(new Timestamp(System.currentTimeMillis()),
//                    remoteAddr, user.getId());
//            sessionService.addSession(session);
//            req.getSession().setAttribute("SessionAttributes", sessionService.getAllSessionsByUserId(user.getId()));
//            res.sendRedirect("profile");
//        } else {
//            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/html/signIn.html");
//            view.forward(req, res);
//        }
//        return "profile";
//        return "signIn";
//    }
}
