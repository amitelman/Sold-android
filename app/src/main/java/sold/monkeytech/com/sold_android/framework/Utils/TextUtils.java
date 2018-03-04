package sold.monkeytech.com.sold_android.framework.Utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by MonkeyFather on 24/09/2017.
 */

public class TextUtils {

    public static boolean isEmpty(String str){
        return str == null || str.equals("") || str.equals("NONE");
    }

    public static String getShorterDouble(Double number, boolean isDollar) {
        if(isDollar)
            return new DecimalFormat("#,###.##").format(number);
        return new DecimalFormat("##.##").format(number);
    }

    public static String getShorterDouble(int number, boolean isDollar) {
        if(isDollar)
            return new DecimalFormat("#,###.##").format(number);
        return new DecimalFormat("##.##").format(number);
    }

    public static String getParsedDate(String dateString){
        if(TextUtils.isEmpty(dateString))
            return "";
        String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.US).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String reportDate = df.format(date);
        return reportDate;
    }

    public static Date getDateFromString(String dateString){
        if(TextUtils.isEmpty(dateString))
            return new Date();
        String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.US).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getCountOfDays(Date date) {
        long msDiff = Calendar.getInstance().getTimeInMillis() - date.getTime();
        long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
        return " (" + daysDiff + " days ago)";
    }


}
