package tests.MongoDB.PayloadTest;

import com.google.gson.Gson;
import models.parsedHL7;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.MongoDB.TestBase.baseClass;
import utilities.Log;
import utilities.ReadConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PatientTest {
    @Test
    public void PatientTest(){

        baseClass base = new baseClass();
        ReadConfig readConfig = new ReadConfig();

        Log.info("Verify payload.eventData.ADT.fields.PatientType is not empty/null");
        Log.info("Verify payload.eventData.ADT.fields.PatientType is on the list [ER, OPD, MED, PAT, SERIES]");

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

            //extract field value from ADT and cast it as Map
            Map fields = (Map) ADT.get("fields");

            //extract fields.PatientType
            String PatientType = (String) fields.get("PatientType");

            //System.out.println(PatientType);
            //Check patientType is in the certain list
            String[] patientTypeArray = new String[] {"A","B","C","D","E"};
            List<String> patientTypeList = new ArrayList<>(Arrays.asList(patientTypeArray));
            Assert.assertTrue(patientTypeList.contains(PatientType));

            //Check PatientType is not empty
            Assert.assertFalse(PatientType.isEmpty());

        }
    }
}
