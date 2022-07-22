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

public class VersionIDTest {

    @Test

    public void VersionIDTest() {

        Log.info("Verify payload.eventData.ADT.fields.VersionID is not empty/null");
        Log.info("Verify payload.eventData.ADT.fields.VersionID format : [0-99].[0-99]");

        baseClass base = new baseClass();
        ReadConfig readConfig = new ReadConfig();

        List<Document> sortedDocs = base.retrieveDocuments();

        for (Document sortedDoc : sortedDocs) {
            String sortedDocToJson = sortedDoc.toJson();
            Gson gson = new Gson();
            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);
            Map payload = sortedDocToPOJO.getPayload();

            Map eventData = (Map) payload.get("eventData");

            //System.out.println(eventData);
            //Cast value of ADT  to Map
            Map ADT = (Map) eventData.get("ADT");

            //extract field value from ADT and cast it as Map
            Map fields = (Map) ADT.get("fields");

            //extract fields.VersionID
            String VersionID = (String) fields.get("VersionID");

            //System.out.println(VersionID);
            Assert.assertFalse(VersionID.isEmpty());
            Assert.assertTrue(VersionID.matches("[0-99].[0-99]"));

        }
    }
}
