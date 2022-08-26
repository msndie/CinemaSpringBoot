package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long>, CustomizedFilmRepository<Film> {
    boolean existsByTitleAndYearAndDescription(String title, int year, String description);
    Film findByPosterId(Long id);
}
