package tests.MongoDB;

import configuration.connectionSetup;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mongodb.client.MongoCollection;
import utilities.Log;
import utilities.ReadConfig;


public class connectToParsedHL7ColTest {

    ReadConfig readConfig = new ReadConfig();
    connectionSetup setup = new connectionSetup();
    private String connectionString = readConfig.getConnectionString();
    private String databaseName = readConfig.getSCMDataLakeDatabaseName();
    private String parsedHL7 = readConfig.getparsedHL7CollectionName();

    @Test
    public void extractedEventColConnectionTest(){

        MongoCollection<Document> parsedHL7Collection =
                setup.connectToCollection(connectionString, databaseName, parsedHL7);

        Log.info("Verify that parsed_HL7 does not empty");
        //using countDocuments() to count Documents in Collection
        Assert.assertTrue(parsedHL7Collection.countDocuments() > 0);

    }
}
