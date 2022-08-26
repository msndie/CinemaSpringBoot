package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long>, CustomizedSessionRepository<Session> {
}
