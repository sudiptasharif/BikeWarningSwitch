import java.io.*;
import java.net.*;

public class SwitchSocket {
    private static final String TAG = "SwitchSocket";
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String hostName;
    private int portNumber;

    public SwitchSocket(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    public boolean connectToServer() {
        try  {
            socket = new Socket(hostName, portNumber);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return true;
        } catch(UnknownHostException e) {
            System.err.println("Unknown host: " + hostName);
            System.err.println("Exception msg: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("I/O connection exception to: " + hostName);
            System.err.println("Exception msg: " + e.getMessage());
            return false;
        }
    }

    public boolean sendAlertSignal(Trial trial) {
        trial.setT2(System.currentTimeMillis());
        try {
            System.out.println(TAG + ": Sending alert to app");
            out.println("alert");
            while(in.readLine() != null) {
                trial.setT3(in.readLine());
            }
            trial.printTsInSecAndMillis();
            return true;
        } catch (IOException e) {
            System.err.println("Couldn't send alert signal " + hostName);
            System.err.println("Exception msg: " + e.getMessage());
            //e.printStackTrace();
            return false;
        }
    }

    public boolean closeSocket() {
        try {
            if(in != null)
                in.close();
            if(out != null)
                out.close();
            if(socket != null)
                socket.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error closing socket resources");
            System.err.println("Exception msg: " + e.getMessage());
            return false;
        }
    }
}
