package tests.MongoDB.MetadatTest;

import com.google.gson.Gson;
import models.parsedHL7;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.MongoDB.TestBase.baseClass;
import utilities.Log;
import utilities.ReadConfig;

import java.util.List;

public class eventTypeTest {

    @Test
    public void eventTypeTest() {

        baseClass base = new baseClass();
        ReadConfig readConfig = new ReadConfig();

        List<Document> sortedDocs = base.retrieveDocuments();

        Log.info("Verify eventType type is String");
        Log.info("Verify eventType value is not empty");
        Log.info("Verify eventType value is constant - INTERNAL");


        for (Document sortedDoc : sortedDocs) {
            String sortedDocToJson = sortedDoc.toJson();
            Gson gson = new Gson();
            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);

            String eventType = sortedDocToPOJO.getEventType();

            Assert.assertTrue(eventType.getClass().getSimpleName().equals("String"));

            Assert.assertFalse(eventType.isEmpty());

            //Check eventType values of all documents will be the same, and are INTERNAL
            Assert.assertTrue(eventType.equals(readConfig.getEventType()));

        }
    }
}
