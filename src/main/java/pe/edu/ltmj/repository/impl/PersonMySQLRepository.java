package pe.edu.ltmj.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.ltmj.domain.Person;
import pe.edu.ltmj.repository.PersonRepository;
import pe.edu.ltmj.repository.jdbc.ResultSetMappers;
import pe.edu.ltmj.repository.jdbc.SQLExecutor;

@Repository("personMySQLRepository")
public class PersonMySQLRepository implements PersonRepository {

    @Autowired
    private DataSource dataSource;

    private static final String SELECT_ALL_QUERY = "SELECT SQL_NO_CACHE id, name, birthday FROM person";
    private static final String SELECT_BY_ID_QUERY = "SELECT SQL_NO_CACHE id, name, birthday FROM person WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO person (name, birthday) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE person SET name = ?, birthday = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM person WHERE id = ?";

    @Override
    public List<Person> findAll() {
        try (Connection con = dataSource.getConnection()) {
            return SQLExecutor.executeQuery(con, SELECT_ALL_QUERY,
                    ResultSetMappers.PERSON_MAPPER);
        } catch (SQLException e) {
            throw new RuntimeException("Problema en la búsqueda", e);
        }
    }

    @Override
    public List<Person> findAll(int from, int to) {
        try (Connection con = dataSource.getConnection()) {
            from = from < 0 ? 0 : from;
            to = to < from ? from : to;
            String sql = String.format(SELECT_ALL_QUERY + " LIMIT %d OFFSET %s",
                    to - from, from);
            return SQLExecutor.executeQuery(con, sql,
                    ResultSetMappers.PERSON_MAPPER);
        } catch (SQLException e) {
            throw new RuntimeException("Problema en la búsqueda", e);
        }
    }

    @Override
    public Person create(Person person) {
        try (Connection con = dataSource.getConnection()) {
            Long generatedId = SQLExecutor.executeUpdate(con, INSERT_QUERY, person.getName(), person.getBirthday());
            Person created = new Person(person);
            created.setId(generatedId);
            return created;
        } catch (SQLException e) {
            throw new RuntimeException("Problema en la búsqueda", e);
        }
    }

    @Override
    public Person update(Person person) {
        try (Connection con = dataSource.getConnection()) {
            SQLExecutor.executeUpdate(con, UPDATE_QUERY, person.getName(), person.getBirthday(), person.getId());
            return findById(person.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Problema en la búsqueda", e);
        }
    }

    @Override
    public Person findById(Long id) {
        try (Connection con = dataSource.getConnection()) {
            return SQLExecutor.executeQuerySingleResult(con, SELECT_BY_ID_QUERY,
                    ResultSetMappers.PERSON_MAPPER, id);
        } catch (SQLException e) {
            throw new RuntimeException("Problema en la búsqueda", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection con = dataSource.getConnection()) {
            SQLExecutor.executeUpdate(con, DELETE_QUERY, id);
        } catch (SQLException e) {
            throw new RuntimeException("Problema en la búsqueda", e);
        }
    }
}
