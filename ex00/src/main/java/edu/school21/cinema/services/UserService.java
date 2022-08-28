package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

import java.util.Optional;

public interface UserService extends Service<User> {
    Optional<User> findByEmail(String email);
    boolean signUp(User user);
}
