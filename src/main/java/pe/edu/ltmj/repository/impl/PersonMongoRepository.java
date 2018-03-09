package pe.edu.ltmj.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import pe.edu.ltmj.domain.Person;
import pe.edu.ltmj.repository.PersonRepository;

@Repository("personMongoRepository")
public class PersonMongoRepository implements PersonRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    static final String COLLECTION_NAME = "person";

    @Override
    public List<Person> findAll() {
        System.out.println("Mongo implementation");
        Query query = new Query();
        List<Person> results = new LinkedList<>();
        mongoTemplate.executeQuery(query, COLLECTION_NAME, doc -> {
            Person person = new Person();
            person.setId(doc.getLong("id"));
            person.setName(doc.getString("name"));
            person.setBirthday(doc.getDate("birthday"));
            
            results.add(person);
        });
        return results;
    }

    @Override
    public List<Person> findAll(int from, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Person create(Person person) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Person update(Person person) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Person findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

}
