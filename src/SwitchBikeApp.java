import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SwitchBikeApp {
    public static void main(String[] args) {
        if (!validInput(args))
            System.exit(1);

        SwitchSocket switchClient = getServerBikeAppConn(args[0], Integer.parseInt(args[1]));
        if (switchClient != null)
            startApp(switchClient);

        System.out.println(SUtil.CLOSE_MSG);
        System.exit(1);
    }

    private static boolean validInput(String[] args) {
        if(args.length != 2) {
            System.err.println(SUtil.INVALID_APP_USAGE);
            return false;
        }
        return true;
    }

    private static SwitchSocket getServerBikeAppConn(String serverHostName, int portNumber) {
        SwitchSocket switchSocket = new SwitchSocket(serverHostName, portNumber);
        System.out.println("Connecting to server...");
        if(switchSocket.connectToServer()) {
            System.out.println(SUtil.CONNECTION_SUCCESS);
            return switchSocket;
        } else {
            System.err.println(SUtil.CONNECTION_FAILURE);
            return null;
        }
    }

    private static void startApp(SwitchSocket switchSocket) {
        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.println(SUtil.START_EXP);
            String userInput = stdIn.readLine();
            while ((userInput.equalsIgnoreCase("y") || (!userInput.equalsIgnoreCase("n")))) {
                if (!userInput.equalsIgnoreCase("y")) {
                    System.out.println(SUtil.INVALID_YN);
                    System.out.println(SUtil.START_EXP);
                    userInput = stdIn.readLine();
                } else if (userInput.equalsIgnoreCase("y")) {
                    Experiment experiment = new Experiment(switchSocket);
                    experiment.startExperiment(stdIn);
                    System.out.println(SUtil.START_EXP);
                    userInput = stdIn.readLine();
                }
            }
            switchSocket.closeSocket();
        }catch (IOException e) {
            System.err.println("I/O exception: " + e.getMessage());
        }
    }
}
