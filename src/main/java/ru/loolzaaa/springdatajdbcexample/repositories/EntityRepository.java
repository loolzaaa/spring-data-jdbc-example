package ru.loolzaaa.springdatajdbcexample.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.loolzaaa.springdatajdbcexample.model.Entity;

@Repository
public interface EntityRepository extends CrudRepository<Entity, Long> {
}
