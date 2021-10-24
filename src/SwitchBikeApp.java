import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SwitchBikeApp {
    public static void main(String args[]) {
        if(args.length != 2) {
            System.err.println("Usage: java SwitchBikeApp <host name> <port number>");
            System.exit(1);
        }
        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.println(SUtil.ASK_START_EXP);
            String userInput = stdIn.readLine();
            while((userInput.equalsIgnoreCase("y") || (!userInput.equalsIgnoreCase("n")))){
                if(!userInput.equalsIgnoreCase("y")) {
                    System.out.println(SUtil.INVALID_Y_N_INPUT);
                    System.out.println(SUtil.ASK_START_EXP);
                    userInput = stdIn.readLine();
                }else {
                    Experiment experiment = new Experiment();
                    experiment.startExperiment(args[0], Integer.parseInt(args[1]), stdIn);
                }
            }
            System.out.println(SUtil.CLOSE_APP_MSG);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O exception: " + e.getMessage());
        }
    }
}
