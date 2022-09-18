package edu.school21.cinema.services;

import edu.school21.cinema.models.Film;

import java.util.Optional;

public interface FilmService extends Service<Film> {
    Film findByImageId(Long id);
    Optional<Film> findById(Long id);
}
