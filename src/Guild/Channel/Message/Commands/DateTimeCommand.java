package Guild.Channel.Message.Commands;

import Tools.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author vince zydea
 */
public class DateTimeCommand {
    public static String handle(){
        return new StringBuilder().append("It is currently ").append(DateTime.getDateTime()).toString();
    }
}
