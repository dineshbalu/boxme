package android.db.boxme.utils;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Caritor on 12/16/2016.
 */
public class DateTime {

    public static String getPrettyTime( String datetime ) {
// it comes out like this 2013-08-31 15:55:22 so adjust the date format
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = df.parse(datetime);
            long epoch = date.getTime();
            String timePassedString = DateUtils.getRelativeTimeSpanString(epoch, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            return timePassedString;

        } catch (ParseException e) {
            e.printStackTrace();

        }

        return "--";
    }
}
