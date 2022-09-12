package dp*.tests.MongoDB_S3CrossCheck.MetadataTest;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import dpautomation.tests.MongoDB_S3CrossCheck.DataFromMongo.clientIdFromMongo;
import dpautomation.tests.MongoDB_S3CrossCheck.DataFromS3.clientIdFromS3;

public class clientIdTest {
    
    @Test
    public void clientIdTest() throws IOException{

        clientIdFromMongo mongoExtractor = new clientIdFromMongo();
        clientIdFromS3 S3Extractor = new clientIdFromS3();

        List<String> clientIdMongoLst = mongoExtractor.extractClientIdFromMongo();
        List<String> clientIdS3Lst = S3Extractor.extractClientIdFromS3();

        Assert.assertTrue(clientIdMongoLst.equals(clientIdS3Lst));


        
    }
}
