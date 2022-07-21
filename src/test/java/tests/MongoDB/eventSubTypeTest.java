package tests.MongoDB;

import com.google.gson.Gson;
import models.parsedHL7;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;
import utilities.ReadConfig;

import java.util.List;

public class eventSubTypeTest {


    @Test
    public void eventSubTypeTest()
    {
        baseClass base = new baseClass();
        ReadConfig readConfig = new ReadConfig();

        List<Document> sortedDocs = base.retrieveDocuments();

        Log.info("Verify eventSubType type is String");
        Log.info("Verify eventSubType is not null/empty");
        Log.info("Verify eventSubType is constant - PARSEDHL7");

        for (Document sortedDoc : sortedDocs)
        {
            //convert every document to json format
            String sortedDocToJson = sortedDoc.toJson();

            Gson gson = new Gson();
            //using gson to convert every json format to parsedHL7
            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);

            //retrieve eventSubType from sorted document
            String eventSubType = sortedDocToPOJO.getEventSubType();

            Assert.assertTrue(eventSubType.getClass().getSimpleName().equals("String"));

            Assert.assertFalse(eventSubType.isEmpty());

            Assert.assertTrue(eventSubType.equals(readConfig.getEventSubType()));

        }

    }
}
