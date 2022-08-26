package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public boolean signUp(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean signIn(User user) {
        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());
        if (userFromDb.isPresent() && passwordEncoder.matches(user.getPassword(), userFromDb.get().getPassword())) {
            user.setId(userFromDb.get().getId());
            user.setFirstName(userFromDb.get().getFirstName());
            user.setLastName(userFromDb.get().getLastName());
            user.setPhoneNumber(userFromDb.get().getPhoneNumber());
            return true;
        }
        return false;
    }
}
