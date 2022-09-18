package edu.school21.cinema.repositories;

import java.util.List;

public interface CustomizedMessageRepository<T> {
    List<T> findLast20ByFilmId(Long filmId);
}
