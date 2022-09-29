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
        //CollectionName
        String parsedHL7 = "";
        
        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoCollection<Document> parsedHL7Collection = 
        setup.connectToCollection(connectionString, databaseName, parsedHL7);
         
        //if we havent created a text indexes, first need to create text Index and pass the field want to sort
        //parsedHL7Collection.createIndex(Indexes.text("eventId"));

        Bson filter = Filters.text("4b2c");
        List<Document> sortedDocs = new ArrayList<>();
        parsedHL7Collection.find(filter).limit(10).into(sortedDocs);
        
        for (Document sortedDoc : sortedDocs)
        {
            //convert every document to json format
            
            String sortedDocToJson = sortedDoc.toJson();
       
            System.out.println(sortedDocToJson);
        
    }
    }
}
    
