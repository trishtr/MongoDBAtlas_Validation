package tests.MongoDB.PayloadTest;

import java.util.List;
import java.util.Map;

import models.parsedHL7;
import org.bson.Document;

import com.google.gson.Gson;
import tests.MongoDB.TestBase.baseClass;
import utilities.Log;

public class extractedPayload {

    public extractedPayload() {


        baseClass base = new baseClass();

        List<Document> sortedDocs = base.retrieveDocuments();

        Log.info("Convert doc to POJO");
        for(Document sortedDoc : sortedDocs )
        {
            String sortedDocToJson = sortedDoc.toJson();
            Gson gson = new Gson();
            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);
            Map payload = sortedDocToPOJO.getPayload();
            System.out.println(payload);
        }



    }

}
