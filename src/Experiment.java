import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Experiment {
    private static final String TAG = "Experiment";
    private Calendar expDatetime;
    private ArrayList<Trial> trialList;
    private SwitchSocket switchSocket;

    public Experiment(SwitchSocket switchSocket) {
        this.switchSocket = switchSocket;
        expDatetime = Calendar.getInstance();
        trialList = new ArrayList<>();
    }

    public void printExperiment() {
        System.out.println("Experiment Date: " + SUtil.formatDate(expDatetime, SUtil.DATE_FORMAT_USER_FRIENDLY));
        System.out.format("%10s %10s %20s%n", "Trial #", "T2", "T3");
        for (int i = 0; i < trialList.size(); i++) {
            trialList.get(i).printTrial(i);
        }
    }

    public void startExperiment(BufferedReader stdIn) throws IOException {
        System.out.println(SUtil.SEND_ALART_SIGNAL);
        String userInput = stdIn.readLine();
        boolean isAlertSentSuccessfully = true;
        while(isAlertSentSuccessfully && (userInput.equalsIgnoreCase("y") || (!userInput.equalsIgnoreCase("n")))) {
            if(!userInput.equalsIgnoreCase("y")){
                System.out.println(SUtil.INVALID_YN);
                System.out.println(SUtil.SEND_ALART_SIGNAL);
                userInput = stdIn.readLine();
            } else {
                Trial trial = new Trial();
                isAlertSentSuccessfully = switchSocket.sendAlertSignal(trial);
                if(isAlertSentSuccessfully) {
                    trialList.add(trial);
                    System.out.println(SUtil.SEND_ALART_SIGNAL);
                    userInput = stdIn.readLine();
                }
            }
        }
        if(isAlertSentSuccessfully) {
            printExperiment();
            saveExperiment(stdIn);
        }
    }

    private void saveExperiment(BufferedReader stdIn) throws IOException {
        System.out.println(SUtil.SAVE_EXPERIMENT);
        String userInput = stdIn.readLine();
        while((userInput.equalsIgnoreCase("y") || (!userInput.equalsIgnoreCase("n")))) {
            if(!userInput.equalsIgnoreCase("y")) {
                System.out.println(SUtil.SAVE_EXPERIMENT);
                userInput = stdIn.readLine();
            } else {
                System.out.println("TODO: Will have to write to DB later.");
            }
        }
    }
}
