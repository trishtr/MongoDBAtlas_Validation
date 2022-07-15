package configuration;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class connectionSetup {

    public static MongoCollection<Document> connectToCollection(String connectionString, String databaseName, String collectionName){

        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase sampleTrainingDb = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> desiredCollection = sampleTrainingDb.getCollection(collectionName);
        return desiredCollection;

    }
}
