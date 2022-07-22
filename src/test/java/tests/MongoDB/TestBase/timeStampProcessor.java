package tests.MongoDB.TestBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class timeStampProcessor {
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


    public long dataRefreshInterval(String timeStamp) throws ParseException {

        String currentTimeInStringFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now())
                .toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

        //Parse timestamp into date format
        Date d1 = sdf.parse(timeStamp);
        Date d2 = sdf.parse(currentTimeInStringFormat);

        long difference_In_Time = d2.getTime() - d1.getTime();

        long difference_In_Hours = (difference_In_Time/ (1000 * 60 * 60)) % 24;

        //System.out.println(difference_In_Hours);
        return difference_In_Hours;

    }


}
