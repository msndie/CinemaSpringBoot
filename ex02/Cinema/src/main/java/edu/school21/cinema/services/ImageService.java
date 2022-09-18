package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageService extends Service<Image> {
    Optional<Image> findByUuid(UUID uuid);
    List<Image> findAllByUserId(Long userId);
}
