package pe.edu.ltmj.repository.impl;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.async.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;

import pe.edu.ltmj.domain.Person;
import pe.edu.ltmj.repository.PersonReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository("personMongoReactiveRepository")
public class PersonMongoReactiveRepository implements PersonReactiveRepository {

    @Autowired
    private MongoClient mongoClient;

    private MongoCollection<Document> personCollection;

    @PostConstruct
    public void init() {
        personCollection = MongoClients.create(mongoClient).getDatabase("store")
                .getCollection("person");
    }

    @Override
    public Flux<Person> findAll() {
        System.out.println("Mongo implementation");
        return Flux.from(personCollection.find()).map(doc -> {
            Person person = new Person();
            person.setId(doc.getLong("id"));
            person.setName(doc.getString("name"));
            person.setBirthday(doc.getDate("birthday"));
            return person;
        });
    }

    @Override
    public Flux<Person> findAll(int from, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Person> findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
