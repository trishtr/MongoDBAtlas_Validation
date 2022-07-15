package tests;
import configuration.connectionSetup;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import utilities.Log;
import utilities.ReadConfig;


import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.descending;
public class baseClass {

    public List<Document> retrieveDocuments(){


        //connect to the database
        Log.info("Connect to database");

        ReadConfig readConfig = new ReadConfig();
        connectionSetup setup = new connectionSetup();
        String connectionString = readConfig.getConnectionString();
        String databaseName = readConfig.getSCMDataLakeDatabaseName();
        String parsedHL7 = readConfig.getparsedHL7CollectionName();

        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoCollection<Document> parsedHL7Collection =
                setup.connectToCollection(connectionString, databaseName, parsedHL7);


        //sort by timestamp descending, limit to 50
        Log.info("sort 50 latest documents");

        List<Document> sortedDocuments = new ArrayList<>();
        parsedHL7Collection.find().sort(descending("timestamp")).limit(50).into(sortedDocuments);
        return sortedDocuments;


    }
}
