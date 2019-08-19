import java.io.BufferedReader;
import java.net.InetAddress;

public final class Network {

    private final String targetSSID;

    public Network(String targetSSID) {
        this.targetSSID = targetSSID;
    }

    /**
     * Checks to see if known network SSID is within range and available to be connected
     */
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

    /**
     * Checks if this device is already connected to known SSID, used to ensure connection is not attempted if the
     * device is already connected to a desired network
     */
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

    /**
     * connect to the SSID
     */
    public void connect() throws Exception {
        String commandStr = "wlan connect name=\""+this.targetSSID+"\"";
        Process p = Runtime.getRuntime().exec(commandStr);
    }

    /**
     * Verifies that there is a valid internet connection by pinging Google's servers
     */
    public static boolean internetConnection() throws Exception {
        String testUrl = "8.8.8.8";
        return(InetAddress.getByName(testUrl).isReachable(1000));
    }

}
