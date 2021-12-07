package CommonUtility;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class AutomationConfiguration {
    public static WebDriver Driver;
    public static AppiumDriver<WebElement> AppiumDriver;
    public static Properties PropertyFile;
    public static DesiredCapabilities DesiredCap;
    public static Logger Log = (Logger) LogManager.getLogger(CreateSession.class.getName());
    public static String Environment;
    public static String Country;
    public static SoftAssert SoftAsserts;
    public static String AppiumServerURL;
    public static String URL;
    public static String BrowserName;
    public static String LaunchBrowser;
    public static String LaunchMobileApp;
    public static String ScreenshotFor;
    public static String Platform;

    public static void logInfo(String info) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  ").format(new Date()).toString() + info);
        AutomationConfiguration.Log.info(info);
        //	ApcoaListeners.extentTest.get().log(Status.INFO, info);
    }
}
