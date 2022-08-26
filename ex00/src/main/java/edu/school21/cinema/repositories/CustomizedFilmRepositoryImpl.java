package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomizedFilmRepositoryImpl implements CustomizedFilmRepository<Film> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Film> getAll() {
        return entityManager.createQuery(
                "SELECT f FROM Film f LEFT JOIN FETCH f.poster", Film.class)
                .getResultList();
    }
}
