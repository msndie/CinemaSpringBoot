package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends CrudRepository<Hall, Long> {
    Hall findHallBySerialNumber(Long serialNumber);
    boolean existsBySerialNumber(Long serialNumber);
}
