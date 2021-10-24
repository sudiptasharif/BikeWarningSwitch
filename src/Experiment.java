import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Experiment {
    private Calendar expDatetime;
    private ArrayList<Trial> trialList;

    public Experiment() {
        expDatetime = Calendar.getInstance();
        trialList = new ArrayList<>();
    }

    public void printExperiment() {
        System.out.println("Experiment Date: " + SUtil.formatDate(expDatetime, SUtil.USER_FRIENDLY_DATE_TIME_FORMAT));
        for (int i = 0; i < trialList.size(); i++) {
            trialList.get(i).printTrial(i);
        }
    }

    public void startExperiment(String hostName, int portNumber, BufferedReader stdIn) throws IOException {
        SwitchSocket switchSocket = new SwitchSocket(hostName, portNumber);
        if (switchSocket.connectToServer()) {
            System.out.println(SUtil.SERVER_CONNECTED);
            System.out.println(SUtil.ASK_SEND_ALART_SIGNAL);
            String userInput = stdIn.readLine();
            while((userInput.equalsIgnoreCase("y") || (!userInput.equalsIgnoreCase("n")))) {
                if(!userInput.equalsIgnoreCase("y")) {
                    System.out.println();
                    System.out.println(SUtil.INVALID_Y_N_INPUT);
                    System.out.println(SUtil.ASK_SEND_ALART_SIGNAL);
                    userInput = stdIn.readLine();
                } else {
                    Trial trial = new Trial();
                    if(switchSocket.sendAlertSignal(trial)) {
                        trial.setT3(getT3FromUser(stdIn));
                        trialList.add(trial);
                        System.out.println();
                        System.out.println(SUtil.ASK_SEND_ALART_SIGNAL);
                        userInput = stdIn.readLine();
                    }
                }
            }
            printExperiment();
            saveExperiment(stdIn);
        } else {
            System.out.println(SUtil.SERVER_UNABLE_CONNECTION);
        }
        switchSocket.closeSocket();
        System.out.println(SUtil.CLOSE_APP_MSG);
    }

    private void saveExperiment(BufferedReader stdIn) throws IOException {
        System.out.println(SUtil.ASK_SAVE_EXPERIMENT);
        String userInput = stdIn.readLine();
        while((userInput.equalsIgnoreCase("y") || (!userInput.equalsIgnoreCase("n")))) {
            if(!userInput.equalsIgnoreCase("y")) {
                System.out.println();
                System.out.println(SUtil.ASK_SAVE_EXPERIMENT);
                userInput = stdIn.readLine();
            } else {
                System.out.println("TODO: Will have to write to DB later.");
            }
        }
    }

    private double getT3FromUser(BufferedReader stdIn) throws IOException {
        double t3 = 0.0;
        System.out.println(SUtil.ASK_T3);
        String userInput = stdIn.readLine();
        try {
            t3 = Double.parseDouble(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println(SUtil.INVALID_T3_USER_INPUT);
            getT3FromUser(stdIn);
        }
        return t3;
    }

}
