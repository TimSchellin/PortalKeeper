import java.io.BufferedReader;
import java.net.InetAddress;

public final class Network {

    private final String targetSSID;

    public Network(String targetSSID) {
        this.targetSSID = targetSSID;
    }

    public boolean isAvailable() throws Exception {
        String commandStr = "netsh wlan show networks";
        Process p = Runtime.getRuntime().exec(commandStr);
        BufferedReader in = new BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
        String line = null;
        String message = "";
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            message += line;
        }
        return message.toLowerCase().contains(this.targetSSID.toLowerCase());
    }

    public boolean alreadyConnected() throws Exception {
        String commandStr = "netsh WLAN show interfaces";
        Process p = Runtime.getRuntime().exec(commandStr);
        BufferedReader in = new BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
        String line = null;
        String message = "";
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            message += line;
        }
        return message.toLowerCase().contains(this.targetSSID.toLowerCase());
    }

    public void connect() throws Exception {
        String commandStr = "wlan connect name=\""+this.targetSSID+"\"";
        Process p = Runtime.getRuntime().exec(commandStr);
    }

    public static boolean internetConnection() throws Exception {
        String testUrl = "8.8.8.8";
        return(InetAddress.getByName(testUrl).isReachable(1000));
    }

}
