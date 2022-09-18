package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;

import java.util.Optional;

public interface HallService extends Service<Hall> {
    Optional<Hall> findById(Long id);
}
