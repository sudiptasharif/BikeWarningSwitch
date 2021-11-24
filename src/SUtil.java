import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SUtil {
    public static final String DATE_FORMAT_DATABASE = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_USER_FRIENDLY = "yyyy-MM-dd hh:mm a";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_ss_SSS = "ss.SSS";
    public static final String DATE_FORMAT_HH_MM_ss_SSS = "hh:mm:ss.SSS";
    public static final long DEFAULT_MILLISECOND = 0;
    public static final String START_EXPERIMENT = "Start new experiment? (y/n)";
    public static final String SEND_ALART_SIGNAL = "Send alert signal? (y/n)";
    public static final String SAVE_EXPERIMENT = "Save experiment? (y/n)";
    public static final String INVALID_YN = "Invalid input. Please enter y/n";
    public static final String APP_NAME = "Bike Warning Switch Client";
    public static final String APP_CLOSE_MSG = "Closing: " + SUtil.APP_NAME + "\n";
    public static final String CONNECTION_SUCCESS = "Connection SUCCESSFUL\n";
    public static final String CONNECTION_FAILURE = "Connection FAILED\n\nMake sure server is on\nIn App Press Button: START LISTENING\n";
    public static final String INVALID_APP_USAGE = "Usage: java BikeWarningSwitch <host name> <port number>";
    public static final String CONNECTING = "Connecting to server...";
    public static final int SERVER_ON = 0;
    public static final int SERVER_OFF = 1;
    public static final String APP_START_MSG = "Starting: " + SUtil.APP_NAME + "\n";
    public static final int INVALID_SIGNAL_CODE = -1;



    public static String formatDate(Calendar calendar, String dateFormatPattern){
        String formattedDate = "";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatPattern);
        Date date = calendar.getTime();
        formattedDate = formatter.format(date);
        return formattedDate;
    }

    public  static String formatDate(long timeInMillis, String dateFormatPattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatPattern);
        return formatter.format(new Date(timeInMillis));
    }
}
