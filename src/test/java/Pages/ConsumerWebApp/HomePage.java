package Pages.ConsumerWebApp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(id="headingTextBig")
	WebElement text;
	
	@FindBy(xpath="//p[contains(text(),'Login')]")
	WebElement login;
	
	public HomePage(WebDriver driver)	{
		this.driver = driver;
	}
	
	public boolean validateText() {
		ApcoaListeners.logInfo("Checking the text");
		return text.isDisplayed();
	}
	
	public void clickLogin() {
		ApcoaListeners.logInfo("Going to click login button");
		highLightElement(login);
		login.click();
		ApcoaListeners.logInfo("Login button clicked sucessfully");
	}
	public static void highLightElement(WebElement element){
		   JavascriptExecutor js = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;   
		   js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);   
		 }

}
