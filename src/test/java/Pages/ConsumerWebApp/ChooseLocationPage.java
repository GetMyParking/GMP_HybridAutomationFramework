package Pages.ConsumerWebApp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;

public class ChooseLocationPage {
	WebDriver driver;
	
	@FindBy(css="input[placeholder='Search for a location or city...']")
	WebElement locationel;
	
	@FindBy(className="MuiInputBase-input")
	WebElement enter;
	
	public ChooseLocationPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterLocation(String location) {
		ApcoaListeners.logInfo("Going to enter location");
		highLightElement(locationel);
		locationel.sendKeys(location);
		ApcoaListeners.logInfo("Sucessfully Entered location: "+location);
		highLightElement(enter);
		enter.sendKeys(Keys.RETURN);
	}
	 public static void highLightElement(WebElement element){
		   JavascriptExecutor js = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;   
		   js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);   
		 }
	
}