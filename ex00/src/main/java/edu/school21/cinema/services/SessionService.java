package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService extends Service<Session> {
    List<Session> findByFilmNameContains(String filmName);
    Optional<Session> findById(Long id);
}
