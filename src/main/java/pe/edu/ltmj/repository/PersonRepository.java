package pe.edu.ltmj.repository;

import java.util.List;

import pe.edu.ltmj.domain.Person;

public interface PersonRepository {

    List<Person> findAll();

    List<Person> findAll(int from, int to);

    Person create(Person person);

    Person update(Person person);

    Person findById(Long id);

    void delete(Long id);
}
