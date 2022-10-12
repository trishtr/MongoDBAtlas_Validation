import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import dpautomation.configuration.connectionSetup;
import dpautomation.utilities.Log;
public class searchDocByText{

public static void main(String[] args) {

        Log.info("Connect to database");

     
        connectionSetup setup = new connectionSetup();
       
        String connectionString = "";
        
        String databaseName = "";
        String parsedHL7 = "";
        
        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoCollection<Document> parsedHL7Collection = 
        setup.connectToCollection(connectionString, databaseName, parsedHL7);

        //parsedHL7Collection.createIndex(Indexes.text("eventId"));

    
        Bson filter = Filters.text("test");
        List<Document> sortedDocs = new ArrayList<>();
        parsedHL7Collection.find(filter).limit(5).into(sortedDocs);

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
    
    
}
}
    
