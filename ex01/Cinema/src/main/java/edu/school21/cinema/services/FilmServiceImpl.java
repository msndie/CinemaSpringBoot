package edu.school21.cinema.services;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    @Autowired
    public void setFilmRepository(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> findAll() {
        return filmRepository.getAll();
    }

    public boolean add(Film entity) {
        if (!filmRepository.existsByTitleAndYearAndDescription(entity.getTitle(),
                entity.getYear(), entity.getDescription())) {
            filmRepository.save(entity);
        }
        return true;
    }

    public void delete(Film entity) {
        filmRepository.delete(entity);
    }

    public void update(Film entity) {
        filmRepository.save(entity);
    }

    public Film findByImageId(Long id) {
        return filmRepository.findByImageId(id);
    }

    public Optional<Film> findById(Long id) {
        return filmRepository.findById(id);
    }
}
