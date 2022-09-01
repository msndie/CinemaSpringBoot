package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    Optional<Image> findByUuid(UUID uuid);
    List<Image> findAllByUserId(Long userId);
}
