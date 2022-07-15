package tests;

import com.google.gson.Gson;
import models.parsedHL7;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;

import java.util.HashSet;
import java.util.List;

public class idTest {

    @Test
    public void idTest() {

        baseClass base = new baseClass();

        List<Document> sortedDocs = base.retrieveDocuments();
        HashSet<String> idToStringList = new HashSet();

        Log.info("Verify _id is not empty");
        Log.info("Verify _id is unique");

        for(Document sortedDoc : sortedDocs )
        {
            String sortedDocToJson = sortedDoc.toJson();
            Gson gson = new Gson();
            parsedHL7 sortedDocToPOJO = gson.fromJson(sortedDocToJson, parsedHL7.class);

            ObjectId id = sortedDocToPOJO.getId();
            String idToString = id.toString();
            //System.out.println(idToString);
            Assert.assertFalse(idToString.isEmpty());

            idToStringList.add(idToString);

        }
        System.out.println(idToStringList.size());
        Assert.assertEquals(idToStringList.size(), 50);
    }

}
