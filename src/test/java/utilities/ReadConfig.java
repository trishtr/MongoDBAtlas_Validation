package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ReadConfig {

    Properties prop;

    public ReadConfig(){

        File src = new File("src/test/java/configuration/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            prop = new Properties();
            prop.load(fis);

        } catch (IOException e)
        {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public String getConnectionString()
    {
        String connectionString = prop.getProperty("connectionString");
        return connectionString;
    }

    public String getSCMDataLakeDatabaseName()
    {
        String databaseName = prop.getProperty("scmDataLakeDataBase");
        return databaseName;
    }

    public String getparsedHL7CollectionName()
    {
        String parsedHL7Collection = prop.getProperty("parsedHL7Coll");
        return parsedHL7Collection;
    }

    public String getEventType()
    {
        String eventType = prop.getProperty("eventType");
        return eventType;
    }

    public String getEventSubType()
    {
        String eventSubType = prop.getProperty("eventSubType");
        return eventSubType;
    }



}
