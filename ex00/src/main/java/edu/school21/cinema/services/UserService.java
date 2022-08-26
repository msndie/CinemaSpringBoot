package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

public interface UserService extends Service<User> {
    boolean signUp(User user);
    boolean signIn(User user);
}
