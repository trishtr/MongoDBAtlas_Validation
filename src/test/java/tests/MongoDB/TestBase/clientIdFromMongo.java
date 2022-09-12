package *.tests.MongoDB_S3CrossCheck.DataFromMongo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;

import dpautomation.models.parsedHL7;
import dpautomation.tests.MongoDB.TestBase.baseClass;
import dpautomation.utilities.ReadConfig;

public class clientIdFromMongo {
    
    public static List<String> extractClientIdFromMongo() throws IOException
    {
        baseClass base = new baseClass();
        ReadConfig readConfig = new ReadConfig();
        String fromtime = readConfig.getFromTime();
        String totime = readConfig.getToTime();
        List<Document> docs = base.retrieveDocForS3CrossCheck(fromtime, totime);
    

        List<String> clientIdLst = new ArrayList<>();
       

        for (Document doc: docs ){
            
            //System.out.println(doc.toJson());
            String sortedDocToJson = doc.toJson();

            Gson gson = new Gson();

            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);

            String clientId = sortedDocToPOJO.getClientId();
            
            System.out.println(clientId);

            clientIdLst.add(clientId);
        }
        
        return clientIdLst;
    
       
    }
}
