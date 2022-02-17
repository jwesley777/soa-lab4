package soa.repository;

import soa.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Optional<Person> findById(Integer id);

    ArrayList<Person> findAll();

}
