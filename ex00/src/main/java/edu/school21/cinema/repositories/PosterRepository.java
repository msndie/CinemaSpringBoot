package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Poster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PosterRepository extends CrudRepository<Poster, Long> {
    Poster findByUuid(UUID uuid);
}
