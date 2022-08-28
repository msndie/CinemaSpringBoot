package edu.school21.cinema.services;

import edu.school21.cinema.models.Poster;

import java.util.Optional;
import java.util.UUID;

public interface PosterService extends Service<Poster> {
    Optional<Poster> findByUuid(UUID uuid);
}
