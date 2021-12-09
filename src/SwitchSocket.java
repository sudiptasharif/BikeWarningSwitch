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
            return checkServerOnState(in.readLine());
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
        long t2 = System.currentTimeMillis();
        String t3;
        try {
            System.out.println(TAG + ": Sending alert to app");
            out.println("4");
            t3 = in.readLine();
            if(t3 != null && !t3.equalsIgnoreCase(Integer.toString(SUtil.INVALID_SIGNAL_CODE))){
                trial.setT2(t2);
                trial.setT3(t3);
                trial.printTsInSecAndMillis();
                return true;
            }else {
                System.out.println("Invalid Signal Sent\n");
                return false;
            }
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

    public boolean checkServerOnState(String state) {
        if(state != null && state.equalsIgnoreCase(Integer.toString(SUtil.SERVER_ON))) {
            return true;
        }else {
            return false;
        }
    }
}
