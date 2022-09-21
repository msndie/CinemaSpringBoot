package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;

import java.util.List;

public interface CustomizedSessionRepository<T> {
    List<T> getAll();
    List<Session> findByFilmTitleContainsIgnoreCase(String title);
}
