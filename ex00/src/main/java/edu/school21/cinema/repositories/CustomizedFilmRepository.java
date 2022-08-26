package edu.school21.cinema.repositories;

import java.util.List;

public interface CustomizedFilmRepository<T> {
    List<T> getAll();
}
