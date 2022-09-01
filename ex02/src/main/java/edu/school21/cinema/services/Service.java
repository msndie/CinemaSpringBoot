package edu.school21.cinema.services;

import java.util.List;

public interface Service<T> {
    List<T> findAll();
    boolean add(T entity);
    void delete(T entity);
    void update(T entity);
}
