package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSessionRepository extends CrudRepository<UserSession, Long> {
    List<UserSession> getAllByUserId(Long id);
}
