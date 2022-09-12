package *.tests.MongoDB_S3CrossCheck.DataFromS3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import dpautomation.tests.MongoDB_S3CrossCheck.TestBase.extractObjectKeyFromMongoDB;
import dpautomation.tests.S3.TestBase.displayClientId;
import dpautomation.tests.S3.TestBase.s3Connection;
import dpautomation.utilities.ReadConfig;

public class clientIdFromS3 {

    public List<String> extractClientIdFromS3() throws IOException
    {
    
        ReadConfig readConfig = new ReadConfig();
        extractObjectKeyFromMongoDB objExtractor = new extractObjectKeyFromMongoDB();
       
        String accessKey = readConfig.getAccessKey();
        String secretKey = readConfig.getSecretKey();
        String sessionToken = readConfig.getSessionToken();
        s3Connection connector = new s3Connection();
        AmazonS3 s3 = connector.s3Connection(accessKey, secretKey, sessionToken);
        String bucketName = readConfig.getBucketName();

        List<String> objectKeyLst = objExtractor.extractObjectKey();
        List<String> s3ClientIdLst = new ArrayList<>();
        
        
        for(String objectKey: objectKeyLst){
     
        
            S3Object fullObject = null;
            
            fullObject = s3.getObject(new GetObjectRequest(bucketName, objectKey));
            
            String clientId = displayClientId.extractClientId(fullObject.getObjectContent());

            //System.out.println(clientId);

            s3ClientIdLst.add(clientId);
                     
                
        }
        return s3ClientIdLst;
    } 

    
}
