package tests.MongoDB.PayloadTest;

import com.google.gson.Gson;
import models.parsedHL7;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.MongoDB.TestBase.baseClass;
import utilities.Log;
import utilities.ReadConfig;

import java.util.List;
import java.util.Map;

public class ADTTypeTest {

    @Test
    public void ADTTypeTest()
    {
        Log.info("Verify ADT is not empty/null");
        Log.info("Verify ADT contains 'type'");
        Log.info("Verify ADT.type value is constant : ADT");

        baseClass base = new baseClass();
        ReadConfig readConfig = new ReadConfig();

        List<Document> sortedDocs = base.retrieveDocuments();

        for(Document sortedDoc : sortedDocs )
        {
            String sortedDocToJson = sortedDoc.toJson();
            Gson gson = new Gson();
            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);
            Map payload = sortedDocToPOJO.getPayload();

            Map eventData = (Map) payload.get("eventData");

            //System.out.println(eventData);
            //Cast value of ADT  to Map

            Map ADT = (Map) eventData.get("ADT");

            //extract type value from ADT

            String ADT_type = (String) ADT.get("type");
            //System.out.println(ADT_type);
            Assert.assertFalse(ADT_type.isEmpty());
            Assert.assertTrue(ADT_type.equals("ADT"));

        }
    }
}
