package pe.edu.ltmj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.ltmj.domain.Person;
import pe.edu.ltmj.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    @Qualifier("personMongoRepository")
    private PersonRepository personRepository;

    @GetMapping(value= {"", "/"})
    public List<Person> findAll(
            @RequestParam(required=false) Integer from,
            @RequestParam(required=false) Integer to) {
        return (from == null && to == null) ? personRepository.findAll() : personRepository.findAll(from, to);
    }

    @GetMapping(value= {"/{id}", "/{id}/"})
    public Person findById(@PathVariable("id") Long id) {
        return personRepository.findById(id);
    }
}
