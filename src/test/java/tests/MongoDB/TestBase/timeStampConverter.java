package tests.MongoDB.TestBase;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class timeStampConverter {
    //correct
    public static boolean isValidIOS8601(String timeStamp){

        try{
            Instant stringToDateFormat = Instant.parse(timeStamp);
            //System.out.println(stringToDateFormat);
        }
        catch (DateTimeParseException e) {

            // Throws DateTimeParseException
            // if the string cannot be parsed
            System.out.println("Exception: " + e);

        }
        return true;
    }

    public void convertTimeStampToEpochTime(String timeStamp) {


    }

    public boolean dataRefreshInterval(long timeStampToEpoch){

        String currentTimeInDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now())
                .toString();

        return true;
    }

    public static void main(String[] args) {
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now()).toString());

        System.out.println(isValidIOS8601("2022-07-21T22:57:21Z"));
    }
}
