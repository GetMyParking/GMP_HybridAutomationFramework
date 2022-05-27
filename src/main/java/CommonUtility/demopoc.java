package CommonUtility;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class demopoc {
	public static void main(String[] args) throws MalformedURLException
	{
		DemoAutomationConfiguration automationConfiguration = new DemoAutomationConfiguration();
		DesiredCapabilities DesiredCap  = new DesiredCapabilities();
		DesiredCap = new DesiredCapabilities();
		DesiredCap.setCapability("platformName","Android");
		DesiredCap.setCapability("appActivity", "com.consumer.consumerApp.modules.splash.SplashActivity");
		DesiredCap.setCapability("app",new File("/tmp/sample_apk.apk"));
		DesiredCap.setCapability(MobileCapabilityType.FULL_RESET, true);
		DesiredCap.setCapability("automationName", "UiAutomator2");
		DesiredCap.setCapability("uiautomator2ServerLaunchTimeout",80000);
		DesiredCap.setCapability("appWaitDuration",5000);
		//automationConfiguration.DesiredCap.setCapability("udid", "");
		DesiredCap.setCapability("adbExecTimeout", 25000);
		DesiredCap.setCapability("autoGrantPermissions", true);
		
		AppiumDriver<WebElement>  appiumDriver = (AppiumDriver<WebElement>) new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), DesiredCap);
		
		
	}

	



}
