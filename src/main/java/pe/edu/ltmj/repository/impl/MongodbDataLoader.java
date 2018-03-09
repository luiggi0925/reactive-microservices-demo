package pe.edu.ltmj.repository.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongodbDataLoader {

    public static void main(String[] args) throws Exception {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase database = mongoClient.getDatabase("store");
        MongoCollection<Document> personCollection = database.getCollection("person");
        
        File csvFile = new File("/home/luiggi_mendoza/Downloads/mock-eff.csv");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int counter = 0;
        try (Scanner scanner = new Scanner(csvFile)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                Long id = Long.valueOf(data[0]);
                String name = data[1];
                String birthday = data[2];

                Document document = new Document();
                document.put("id", id);
                document.put("name", name);
                document.put("birthday", sdf.parse(birthday));

                personCollection.insertOne(document);
                counter++;
                
                if (counter % 500 == 0) {
                    System.out.println("Added " + counter + " documents");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mongoClient.close();
        System.out.println("Finished.");
    }
}
