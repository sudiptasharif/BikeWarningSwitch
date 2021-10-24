import java.io.*;
import java.net.*;

public class SwitchSocket {
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
        try {
            trial.setT1(System.currentTimeMillis());
            // Note: this "alert" msg in checked/verified in the server
            // as it's understood, given the context of this app, only one
            // signal/msg will be sent from the switch client. And
            // there can only be one switch client.
            out.println("alert");
            trial.setT2(in.readLine());
            trial.printT1T2InSecAndMillis();
            return true;
        } catch (IOException e) {
            System.err.println("Couldn't send alert signal " + hostName);
            System.err.println("Exception msg: " + e.getMessage());
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
