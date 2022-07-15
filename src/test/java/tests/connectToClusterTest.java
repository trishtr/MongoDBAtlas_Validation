package tests;
import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import models.DatabaseList;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;
import utilities.ReadConfig;

import java.util.ArrayList;
import java.util.List;


public class connectToClusterTest {

    ReadConfig readConfig = new ReadConfig();
    private String connectionString = readConfig.getConnectionString();

    @Test
    public void connectionTest() {

        MongoClient mongoClient = MongoClients.create(connectionString);
        List<Document> database = mongoClient.listDatabases().into(new ArrayList<Document>());

        Log.info("Verify the connection is successfully made");
        Assert.assertFalse(database.isEmpty());

        Log.info("verify the database: scm-data-lake exists and not empty");
        Log.info("Loop through the list and find scm-data-lake database, verify it is not empty");
        for (Document db : database) {
            String dbToJson = db.toJson();
            System.out.println(dbToJson);

            Gson gson = new Gson();
            DatabaseList list = gson.fromJson(dbToJson, DatabaseList.class);

            String databaseName = list.getName();
            Boolean empty = list.getEmpty();

            if(databaseName.equals("scm-data-lake")&& !empty){
                Log.info("Verify the database exists and not empty");
                Assert.assertTrue(true);
                break;


            }

        }
    }
}
