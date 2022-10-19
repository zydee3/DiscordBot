package Tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author vince zydea
 */
public class DateTime {
    public static String getDateTime(){
        return new SimpleDateFormat("EEEE MMM d, h:m aa.").format(new Date());
    }

    public static String getTimeDiscriminator(){
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(hour < 12){
            return "Good Morning";
        } else if (hour < 16){
            return "Good Afternoon";
        } else {
            return "Good Evening";
        }
    }
}
