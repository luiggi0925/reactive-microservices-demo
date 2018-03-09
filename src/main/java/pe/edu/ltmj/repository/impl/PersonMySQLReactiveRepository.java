package pe.edu.ltmj.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.ltmj.domain.Person;
import pe.edu.ltmj.repository.PersonReactiveRepository;
import pe.edu.ltmj.repository.jdbc.ResultSetMappers;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository("personMySQLReactiveRepository")
public class PersonMySQLReactiveRepository implements PersonReactiveRepository {

    @Autowired
    private DataSource dataSource;

    private static final String SELECT_ALL_QUERY = "SELECT SQL_NO_CACHE id, name, birthday FROM person";
    // private static final String SELECT_BY_ID_QUERY = "SELECT SQL_NO_CACHE id, name,
    // birthday FROM person WHERE id = ?";

    @Override
    public Flux<Person> findAll() {
        try {
            Connection con = dataSource.getConnection();

            Stream<Person> personStream = StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(new Iterator<Person>() {
                        Connection _con = con;
                        PreparedStatement pstmt;
                        ResultSet rs;

                        {
                            pstmt = con.prepareStatement(SELECT_ALL_QUERY);
                            rs = pstmt.executeQuery();
                        }

                        @Override
                        public boolean hasNext() {
                            try {
                                if (rs.next()) {
                                    return true;
                                } else {
                                    rs.close();
                                    pstmt.close();
                                    _con.close();
                                    return false;
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(
                                        "Error al obtener elemento.", e);
                            }
                        }

                        @Override
                        public Person next() {
                            return ResultSetMappers.PERSON_MAPPER.apply(rs);
                        }
                    }, Spliterator.IMMUTABLE), false);
            return Flux.fromStream(personStream);
        } catch (Exception e) {
            throw new RuntimeException("X la ptm", e);
        }
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
