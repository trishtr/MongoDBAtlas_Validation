package dpautomation.tests.MongoDB.LogTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dpautomation.models.LogContainer;
import dpautomation.utilities.Log;
import dpautomation.models.parsedHL7;
import dpautomation.tests.MongoDB.TestBase.baseClass;
import dpautomation.tests.MongoDB.TestBase.timeStampProcessor;


public class LogTest {

    @Test
    public void LogTest(){
        
        baseClass base = new baseClass();
        timeStampProcessor timeProcessor = new timeStampProcessor();
        
        List<Document> sortedDocs = base.retrieveDocuments();

    
        Log.info("Verify log.timestamp is not null/empty, and in correct format");
        Log.info("Verify log.function is not null/empty, and contains : ");
        Log.info("Verify log.function is not null/empty, and contains : ");
        Log.info("Verify Log.results is not null/empty, and value is OK");
       

        for (Document sortedDoc : sortedDocs)
        {
            //convert every document to json format
            
            String sortedDocToJson = sortedDoc.toJson();

            Gson gson = new Gson();
            //using gson to convert every json format to parsedHL7
            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);
            
            //System.out.println(sortedDocToJson);
            List<LogContainer> LogContainerLst = sortedDocToPOJO.getLog();
            List resultLst = new ArrayList<>();
            List functionLst = new ArrayList<>();
            List timeStampLst = new ArrayList<>();
            
            
            Assert.assertFalse(LogContainerLst.isEmpty());
            
            //System.out.println(LogContainerLst);
            for(LogContainer log : LogContainerLst)
            {
    
                String result = log.getResult();
                resultLst.add(result);

                String function = log.getFunction();
                functionLst.add(function);
        
                String timeStamp = log.getTimestamp();
                timeStampLst.add(timeStamp);
               

            }

            Assert.assertFalse(resultLst.isEmpty());
            Assert.assertTrue(resultLst.contains("OK"));

            Assert.assertFalse(functionLst.isEmpty());
            Assert.assertTrue(functionLst.contains(""));
            Assert.assertTrue(functionLst.contains(""));

            Assert.assertFalse(timeStampLst.isEmpty());
            Assert.assertTrue(timeProcessor.isValidISO8601((String) timeStampLst.get(0)));
            Assert.assertTrue(timeProcessor.isValidISO8601((String) timeStampLst.get(1)));





            

        }

    }
    
}

