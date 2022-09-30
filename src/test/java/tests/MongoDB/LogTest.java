import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dpautomation.models.parsedHL7;
import dpautomation.tests.MongoDB.TestBase.baseClass;
import dpautomation.tests.MongoDB.TestBase.timeStampProcessor;
import dpautomation.utilities.Log;

public class LogTest {

    @Test
    public void LogTest(){
        
        baseClass base = new baseClass();
        timeStampProcessor timeProcessor = new timeStampProcessor();
        
        List<Document> sortedDocs = base.retrieveDocuments();

        Log.info("Verify log.timestamp is not null/empty, and in correct format");
        Log.info("Verify log.function is not null/empty, and contains : scm-pipeline");
        Log.info("Verify Log.results is not null/empty, and value is OK");
       

        for (Document sortedDoc : sortedDocs)
        {
            //convert every document to json format
            
            String sortedDocToJson = sortedDoc.toJson();

            Gson gson = new Gson();
            //using gson to convert every json format to parsedHL7
            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);
            
            //System.out.println(sortedDocToJson);
            List<Map> Log = sortedDocToPOJO.getLog();
            
            Assert.assertFalse(Log.isEmpty());
            
            //System.out.println(Log);
            for(Map log : Log)
            {
                String result = (String) log.get("result");
                //System.out.println(result);
                Assert.assertFalse(result.isEmpty());
                Assert.assertTrue(result.equals("OK"));

                String function = (String) log.get("function");
                Assert.assertFalse(function.isEmpty());
                Assert.assertTrue(function.contains("scm-oi-lamb-useast1-qa-msk-to-mongo-writer"));
                Assert.assertTrue(function.contains("scm-oi-lamb-useast1-qa-msk-to-msk-parser"));


                String timeStamp = (String) log.get("timestamp");
                Assert.assertFalse(timeStamp.isEmpty());
                Assert.assertTrue(timeProcessor.isValidISO8601(timeStamp));


            }

            

        }

    }
    
}
