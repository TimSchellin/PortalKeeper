import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Collections;
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

    private static void propTest() {
        String propPath = getPropPath();
        Properties prop = new Properties();
    }

    private static String getPropPath(){
        String prefix = "\\";
        String filename = "config.properties";
        return prefix + filename;
    }

    /**
     * this is the main proccess loop for continously monitoring for available SSIDs, and logging into to known networks
     */
    public static void process() throws Exception {
        while(true){
            if(!net.alreadyConnected() && net.isAvailable()){
                net.connect();

                }
            TimeUnit.MINUTES.sleep(1);
            }
        }

    /**
     * loads SSID, username, and password from the config.properties files
     */
    public static void loadProperies() throws Exception {
        String propPath = "config.properties";
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propPath));
    }

    /**
     * finds the root directory of the compiled jar file, used for locating the relative location of the config file
     */
    public static String getParentDir() throws Exception {
        String path = Application.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        return (new java.io.File(path)).getParentFile().getPath();
    }

    /**
     * creates and returns a web driver object instantiated with custom configuration options
     */
    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("-incognito");

        //PLEASE CHANGE PATH TO CHROME DRIVER FOR REMOTE DEPLOYMENT
        System.setProperty("webdriver.chrome.driver", "C://chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();//setSize(new Dimension(1366,768)); //
        return driver;
    }
}
