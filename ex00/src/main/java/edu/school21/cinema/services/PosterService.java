package edu.school21.cinema.services;

import edu.school21.cinema.models.Poster;

import java.util.UUID;

public interface PosterService extends Service<Poster> {
    Poster findByUuid(UUID uuid);
}
