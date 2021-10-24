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
    public static final String ASK_START_EXP = "Start new experiment? (y/n)";
    public static final String ASK_SEND_ALART_SIGNAL = "Send alert signal? (y/n)";
    public static final String ASK_SAVE_EXPERIMENT = "Save experiment? (y/n)";
    public static final String ASK_T3 = "Please enter T3";
    public static final String INVALID_Y_N_INPUT = "Invalid input. Please enter y/n";
    public static final String INVALID_T3_USER_INPUT = "Invalid T3. Must be decimal.";
    public static final String APP_NAME = "SwitchBikeApp";
    public static final String CLOSE_APP_MSG = "Closing " + SUtil.APP_NAME + "...";
    public static final String SERVER_CONNECTED = "Connected to server.";
    public static final String SERVER_UNABLE_CONNECTION = "Unable to connect to server.";


    public static String formatDate(Calendar calendar, String dateFormatPattern){
        String formattedDate = "";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatPattern);
        Date date = calendar.getTime();
        formattedDate = formatter.format(date);
        return formattedDate;
    }
}
