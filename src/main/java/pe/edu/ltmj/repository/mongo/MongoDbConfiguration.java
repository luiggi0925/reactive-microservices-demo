package pe.edu.ltmj.repository.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;

@Configuration
public class MongoDbConfiguration {

    @Bean
    public MongoClient asyncMongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }
}
