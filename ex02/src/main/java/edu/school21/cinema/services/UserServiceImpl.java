package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final String email;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JavaMailSender mailSender,
                           String email) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        this.email = email;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public boolean add(User entity) {
        userRepository.save(entity);
        return true;
    }

    public void delete(User entity) {
        userRepository.delete(entity);
    }

    public void update(User entity) {
        userRepository.save(entity);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean signUp(User user, String url) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }
        user.setUniqueCode(UUID.randomUUID());
        try {
            sendVerificationEmail(user, url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            return false;
        }
        user.setEnabled(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public Optional<User> findByUniqueIdentifier(UUID uuid) {
        return userRepository.findByUniqueCode(uuid);
    }

    private void sendVerificationEmail(User user, String url) throws MessagingException, UnsupportedEncodingException {
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Cinema-spring-boot project.";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(email, "Cinema-spring-boot");
        helper.setTo(user.getEmail());
        helper.setSubject("Please verify your account");
        content = content.replace("[[name]]", user.getFullName());
        content = content.replace("[[URL]]",  url +"/confirm/" + user.getUniqueCode());
        helper.setText(content, true);
        mailSender.send(message);
    }
}
