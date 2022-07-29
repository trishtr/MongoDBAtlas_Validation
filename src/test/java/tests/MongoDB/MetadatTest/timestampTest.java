package tests.MongoDB.MetadatTest;

import com.google.gson.Gson;
import models.parsedHL7;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.MongoDB.TestBase.baseClass;
import tests.MongoDB.TestBase.timeStampProcessor;
import utilities.Log;
import utilities.ReadConfig;

import java.text.ParseException;
import java.util.List;

public class timestampTest {

    @Test
    public void timeStampTest() throws ParseException {

        baseClass base = new baseClass();
        ReadConfig readConfig = new ReadConfig();
        timeStampProcessor timeProcessor = new timeStampProcessor();

        List<Document> sortedDocs = base.retrieveDocuments();

        Log.info("Verify timeStamp is String");
        Log.info("Verify timeStamp is not null/empty");
        Log.info("Verify timeStamp is in ISO 8601 (UTC) format");
        Log.info("Verify timeStamp is updated in 4hrs ");

        for (Document sortedDoc : sortedDocs)
        {
            //convert every document to json format

            String sortedDocToJson = sortedDoc.toJson();

            Gson gson = new Gson();

            //using gson to convert every json format to parsedHL7
            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);

            //retrieve eventSubType from sorted document
            String timeStamp = sortedDocToPOJO.getTimestamp();

            //System.out.println(timeStamp);

            Assert.assertTrue(timeStamp.getClass().getSimpleName().equals("String"));

            Assert.assertFalse(timeStamp.isEmpty());

            //check timestamp format

            Assert.assertTrue(timeProcessor.isValidISO8601(timeStamp));

            //check refresh interval for 4hrs
            Assert.assertTrue(timeProcessor.dataRefreshInterval(timeStamp)<= 4);

        }
    }
}
