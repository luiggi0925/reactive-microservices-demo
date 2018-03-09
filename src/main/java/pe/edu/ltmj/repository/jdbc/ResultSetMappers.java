package pe.edu.ltmj.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

import pe.edu.ltmj.domain.Person;

public class ResultSetMappers {

    public static final Function<ResultSet, Person> PERSON_MAPPER = (
            ResultSet rs) -> {
        Person person = new Person();
        try {
            person.setId(rs.getLong("id"));
            person.setName(rs.getString("name"));
            person.setBirthday(rs.getDate("birthday"));
        } catch (SQLException e) {
            throw new RuntimeException("Error al mapear datos", e);
        }
        return person;
    };
}
