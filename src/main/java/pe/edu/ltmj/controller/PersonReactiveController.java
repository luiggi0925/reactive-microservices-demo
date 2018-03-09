package pe.edu.ltmj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.ltmj.domain.Person;
import pe.edu.ltmj.repository.PersonReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/peopleReactive")
public class PersonReactiveController {

    @Autowired
    @Qualifier("personMongoReactiveRepository")
    private PersonReactiveRepository personRepository;

    @GetMapping(value= {"", "/"})
    public Flux<Person> findAll(
            @RequestParam(required=false) Integer from,
            @RequestParam(required=false) Integer to) {
        return (from == null && to == null) ? personRepository.findAll() : personRepository.findAll(from, to);
    }

    @GetMapping(value= {"/{id}", "/{id}/"})
    public Mono<Person> findById(@PathVariable("id") Long id) {
        return personRepository.findById(id);
    }
}
