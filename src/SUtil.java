import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SUtil {
    public static final String DATABASE_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String USER_FRIENDLY_DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm a";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final int DEFAULT_TRIAL_NUMBER = -1;
    public static final long DEFAULT_MILLISECOND = 0;
    public static final int FACTOR_SECOND = 60;
    public static final int FACTOR_MILLISECOND = 1000;
    public static final String START_EXPERIMENT = "Start new experiment? (y/n)";
    public static final String SEND_ALART_SIGNAL = "Send alert signal? (y/n)";
    public static final String SAVE_EXPERIMENT = "Save experiment? (y/n)";
    public static final String ASK_T3 = "Please enter T4:";
    public static final String INVALID_YN = "Invalid input. Please enter y/n";
    public static final String INVALID_T3 = "Invalid T4. Must be decimal.";
    public static final String APP_NAME = "SwitchBikeSignal";
    public static final String CLOSE_MSG = "Closing " + SUtil.APP_NAME + " Client.";
    public static final String CONNECTION_SUCCESS = "Connected to server.";
    public static final String CONNECTION_FAILURE = "Unable to connect to server.";
    public static final String INVALID_APP_USAGE = "Usage: java SwitchBikeSignal <host name> <port number>";
    public static final String CONNECTING = "Connecting to server...";


    public static String formatDate(Calendar calendar, String dateFormatPattern){
        String formattedDate = "";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatPattern);
        Date date = calendar.getTime();
        formattedDate = formatter.format(date);
        return formattedDate;
    }
}
