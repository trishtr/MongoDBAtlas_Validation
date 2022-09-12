package dp*.tests.MongoDB_S3CrossCheck.TestBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;

import dpautomation.models.parsedHL7;
import dpautomation.tests.MongoDB.TestBase.baseClass;
import dpautomation.utilities.ReadConfig;

public class extractObjectKeyFromMongoDB {
    
    public static List<String> extractObjectKey() throws IOException
    {
        baseClass base = new baseClass();
        ReadConfig readConfig = new ReadConfig();
        String fromtime = readConfig.getFromTime();
        String totime = readConfig.getToTime();
        List<Document> docs = base.retrieveDocForS3CrossCheck(fromtime, totime);
    

        List<String> objectKeyLst = new ArrayList<>();
        String year = null;
        String month = null;
        String day = null;

        for (Document doc: docs ){
            
            //System.out.println(doc.toJson());
            String sortedDocToJson = doc.toJson();

            Gson gson = new Gson();

            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);

            String eventId = sortedDocToPOJO.getEventId();
            String timestamp = sortedDocToPOJO.getTimestamp();
            
            year = timestamp.split("-")[0];
            String rawmonth = timestamp.split("-")[1];
        
            if (rawmonth.charAt(0) == '0')
            {
                month = rawmonth.replace("0", "");
            }
            else{
                month = rawmonth;
            }
            
            String rawday = timestamp.split("-")[2].split("T")[0];
          
            if (rawday.charAt(0) == '0')
            {
                day = rawday.replace("0", "");
                
            }
            else{
                day = rawday;
            }

            String objectKey = "APP/" + year + "/" + month + "/" + day + "/" +  eventId;
            objectKeyLst.add(objectKey);
            System.out.println("Object Key : " + objectKey);
           
            

        }
        
        return objectKeyLst;
    
       
    }
}
