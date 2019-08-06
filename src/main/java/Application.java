import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Application {

    public static String targetSSID = "";
    public static Network net = new Network(targetSSID);

    public static void main(String[] args) {
        try {
            //process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testMethod () throws Exception {

        String path = getParentDir();
        FileWriter fw=new FileWriter(path+"\\loc.txt");
        fw.write(path);
        fw.close();
    }

    public static void process() throws Exception {
        while(true){
            if(!net.alreadyConnected() && net.isAvailable()){
                net.connect();

                }
            TimeUnit.MINUTES.sleep(1);
            }
        }

    public static void loadProperies() throws Exception {
        String propPath = "config.properties";
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propPath));
    }

    public static String getParentDir() throws Exception {
        String path = Application.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        return (new java.io.File(path)).getParentFile().getPath();
    }
}
