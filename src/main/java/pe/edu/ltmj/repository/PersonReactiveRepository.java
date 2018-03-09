package pe.edu.ltmj.repository;

import pe.edu.ltmj.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonReactiveRepository {

    Flux<Person> findAll();

    Flux<Person> findAll(int from, int to);

    Mono<Person> findById(Long id);
}
