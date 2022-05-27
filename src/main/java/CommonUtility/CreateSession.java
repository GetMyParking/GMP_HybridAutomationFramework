/*
-------------------------------------------------------------
Author Name: Karan Kumar Agarwal

Date:24-Sep-2021

Purpose /Description: This Class for loading config file and
	creating the using of selenium and appium.
-------------------------------------------------------------

 */
package CommonUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSession {

	DemoAutomationConfiguration automationConfiguration;
	public static ThreadLocal<DemoAutomationConfiguration> tac = new ThreadLocal<DemoAutomationConfiguration>();

	
	/**
	 * method to read the Config File and launch drivers according to configuration
	 * @throws InterruptedException 
	 *
	 */
	public void readConfigFile(String path,String Platform) throws IOException{
		automationConfiguration = new DemoAutomationConfiguration();
		automationConfiguration.Platform = Platform;
		System.out.println("Start reading config file");

		String pathforconfig=System.getProperty("user.dir").toString()+path.toString();

		FileInputStream fis = new FileInputStream(pathforconfig.toString());
		Properties PropertyFile = new Properties();
		PropertyFile.load(fis);
		automationConfiguration.DesiredCap = new DesiredCapabilities();

		automationConfiguration.ScreenshotFor = PropertyFile.getProperty("screenshotfor").toString();
		automationConfiguration.LaunchBrowser = PropertyFile.getProperty("launchbrowser").toString();
		automationConfiguration.LaunchMobileApp = PropertyFile.getProperty("launchmobileapp").toString();
		System.out.println("Read Complete property file.");

		if(automationConfiguration.LaunchBrowser.toUpperCase().equals("TRUE")){
			//AutomationConfiguration.logInfo("Starting launching web for testing.");
			launchWebDriver(PropertyFile);
		}if(automationConfiguration.LaunchMobileApp.toUpperCase().equals("TRUE")){
			System.out.println("Starting launching mobile app ");
			if(Platform.toUpperCase().equals("ANDROID")){
				System.out.println("Platform: Android");
				launchAndroidDriver(PropertyFile);
			}else if(Platform.toString().toUpperCase().equals("IOS")){
				System.out.println("Platform: IOS");
				launchiOSDriver(PropertyFile);
			}else{
				//AutomationConfiguration.logInfo("Platform name is invalid cannot launch any test.");
			}
		}
		tac.set(automationConfiguration);
		
	}
	
	public static synchronized DemoAutomationConfiguration getAutomationConfiguration() {
		return tac.get();
	}


	/**
	 * method to launch the android driver
	 *
	 *@param capabilities to give the desiredcapabilities
	 */

	public synchronized void launchAndroidDriver(Properties PropertyFile) throws MalformedURLException{		
		try {
			System.out.println("Inside the launchandroid");
			automationConfiguration.DesiredCap.setCapability("deviceName", PropertyFile.getProperty("deviceName").toString());
			automationConfiguration.DesiredCap.setCapability("platformName",PropertyFile.getProperty("platformName").toString());
			automationConfiguration.DesiredCap.setCapability("appActivity", PropertyFile.getProperty("appActivity").toString());
			automationConfiguration.DesiredCap.setCapability("app",new File( PropertyFile.getProperty("app").toString()).getAbsolutePath());
			automationConfiguration.DesiredCap.setCapability(MobileCapabilityType.FULL_RESET, true);
			automationConfiguration.DesiredCap.setCapability("automationName", PropertyFile.getProperty("automationName").toString());
			automationConfiguration.DesiredCap.setCapability("uiautomator2ServerLaunchTimeout",Integer.parseInt(PropertyFile.getProperty("uiautomator2ServerLaunchTimeout")));
			automationConfiguration.DesiredCap.setCapability("appWaitDuration",Integer.parseInt(PropertyFile.getProperty("appWaitDuration")));
			automationConfiguration.DesiredCap.setCapability("udid", PropertyFile.getProperty("udid").toString());
			automationConfiguration.DesiredCap.setCapability("adbExecTimeout", 25000);
			automationConfiguration.DesiredCap.setCapability("autoGrantPermissions", true);

			System.out.println("before launchandroid");
			automationConfiguration.AppiumServerURL = PropertyFile.getProperty("appiumserverurl").toString();
			System.out.println("before launchandroid");
			for(int i=0;i<5;i++) {
				try {
					automationConfiguration.AppiumDriver = new AndroidDriver<>(new URL(automationConfiguration.AppiumServerURL), automationConfiguration.DesiredCap);
					//AndroidDriver driver = (AndroidDriver) new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),
					//		AutomationConfiguration.DesiredCap);
					break;
				}catch(Exception e) {
					System.out.println(e.toString());
					automationConfiguration.DesiredCap.setCapability("appWaitDuration",Integer.parseInt((String) automationConfiguration.DesiredCap.getCapability("appWaitDuration"))+3000);
					Thread.sleep(3000);
				}
			}
			automationConfiguration.AppiumDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			//AutomationConfiguration.logInfo("Successfully launched android app");	
		}catch(Exception e){
			System.out.println(e.toString());
			//AutomationConfiguration.logInfo("Error in launching android app. Exception: "+e.toString());
		}
	}
	
//	public static void main(String[] args) throws InterruptedException, IOException {
//		AutomationConfiguration.Tenant = "Apcoa";
//		AutomationConfiguration.Environment = "Staging";
//		AutomationConfiguration.Country = "Austria";
//		AutomationConfiguration.Platform = "IOS";
//		CreateSession.readConfigFile("/src/test/java/resources/"+AutomationConfiguration.Platform+AutomationConfiguration.Tenant+".properties");
//			
//	}
	/**
	 * method to launch the IOS driver
	 *
	 *@param capabilities to give the desiredcapabilities
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public synchronized void launchiOSDriver(Properties PropertyFile) throws IOException {
		
		
		automationConfiguration.DesiredCap = new DesiredCapabilities();
		
		automationConfiguration.DesiredCap.setCapability("udid", PropertyFile.getProperty("iosudid").toString());
		automationConfiguration.DesiredCap.setCapability("deviceName", PropertyFile.getProperty("iosdeviceName").toString());
		automationConfiguration.DesiredCap.setCapability("platformName",PropertyFile.getProperty("iosplatformName").toString());
		automationConfiguration.DesiredCap.setCapability("automationName", PropertyFile.getProperty("iosautomationName").toString());
		automationConfiguration.DesiredCap.setCapability("app",new File( PropertyFile.getProperty("iosapp").toString()).getAbsolutePath());
		
		automationConfiguration.AppiumDriver  = new AppiumDriver<WebElement>( new URL("http://127.0.0.1:4723/wd/hub"), automationConfiguration.DesiredCap);
		
	
	}
	
	/**
	 * method to launch the Webdriver for Web App
	 *
	 */
	public void launchWebDriver(Properties PropertyFile){
		try {
			automationConfiguration.BrowserName = PropertyFile.getProperty("browser").toString();
			automationConfiguration.URL = PropertyFile.getProperty("url").toString();
			if(automationConfiguration.BrowserName.toUpperCase().equals("CHROME")){
				//automationConfiguration.logInfo("Launching Chrome browser");
				WebDriverManager.chromedriver().setup();
				automationConfiguration.Driver = new ChromeDriver();
				automationConfiguration.Driver.manage().window().maximize();
				//automationConfiguration.logInfo("Sucessfully launched Chrome Browser");
			}else if (automationConfiguration.BrowserName.toUpperCase().equals("FIREFOX")){
				//automationConfiguration.logInfo("Launching Firefox browser");
				WebDriverManager.firefoxdriver().setup();
				automationConfiguration.Driver.manage().window().maximize();
				//automationConfiguration.logInfo("Sucessfully launched Firefox Browser");
			}else if (automationConfiguration.BrowserName.toUpperCase().equals("IE")){
				//automationConfiguration.logInfo("Launching Internet Explorer browser");
				WebDriverManager.iedriver().setup();
				automationConfiguration.Driver.manage().window().maximize();
				//AutomationConfiguration.logInfo("Sucessfully launched Internet Explorer Browser");
			}else{
				//AutomationConfiguration.logInfo("Invalid browser type. Cannot launch.");
			}
		}catch(Exception e){
			//AutomationConfiguration.logInfo("Exception in launching browser: "+ e.toString());
		}	
		
	}
}



















