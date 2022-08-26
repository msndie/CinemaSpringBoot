package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Avatar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvatarRepository extends CrudRepository<Avatar, Long> {
    List<Avatar> findAllByUserId(Long userId);
    Optional<Avatar> getByUuid(UUID uuid);
}
