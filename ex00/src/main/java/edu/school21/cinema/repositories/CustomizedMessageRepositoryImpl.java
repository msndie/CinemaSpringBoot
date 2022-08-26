package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

public class CustomizedMessageRepositoryImpl implements CustomizedMessageRepository<Message> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Message> findLast20ByFilmId(Long filmId) {
        List<Message> messages = entityManager.createQuery(
                "SELECT m FROM Message m WHERE m.filmId = ?1 ORDER BY m.id DESC",
                Message.class)
                .setParameter(1, filmId)
                .setMaxResults(20)
                .getResultList();
        Collections.reverse(messages);
        return messages;
    }
}
