package dpautomation.tests.MongoDB.TestBase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import dpautomation.configuration.connectionSetup;

public class testFilter {
    public static void main(String[] args) {
        connectionSetup setup = new connectionSetup();
       
        String connectionString = "";
        
        String databaseName = "";
        String parsedHL7 = "";
        
        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoCollection<Document> parsedHL7Collection = 
        setup.connectToCollection(connectionString, databaseName, parsedHL7);
    
        Bson filter = Filters.and(Filters.gte("eventId", "testqa-T4"), Filters.lte("eventId", "testqa-T4z"));
        List<Document> sortedDocs = new ArrayList<>();
        parsedHL7Collection.find(filter).into(sortedDocs);

        if (sortedDocs.size() > 0){
            System.out.println("Succesfully filter dataset");
    
            for (Document sortedDoc : sortedDocs)
            {
            //convert every document to json format
            
            String sortedDocToJson = sortedDoc.toJson();
       
            System.out.println(sortedDocToJson);
            
            }
        }
        else {
    
        System.out.println("No dataset is found");}
    
        System.out.println(sortedDocs.size());
}
}

