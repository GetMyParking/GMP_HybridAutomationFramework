package Pages.ConsumerWebApp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import CommonUtility.CreateSession;
import CommonUtility.GenericMethods;
import TestNGListeners.ApcoaListeners;

public class LoginPage {

	WebDriver driver;
	
	@FindBy(xpath="//h1[text()='Welcome Back']")
	WebElement text;
	
	@FindBy(id="email")
	WebElement emailele;
	
	@FindBy(id="password")
	WebElement passwordele;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginbtn;
	
	public LoginPage(WebDriver driver)	{
		this.driver = driver;
	}
	
	public boolean validateText() {
		ApcoaListeners.logInfo("Checking the text");
		return text.isDisplayed();
	}
	
	public void enterCredentials(String email, String password){
		ApcoaListeners.logInfo("Going to enter credentials");
		highLightElement(emailele);
		emailele.sendKeys(email); 
		highLightElement(passwordele);
		passwordele.sendKeys(password);
		ApcoaListeners.logInfo("Sucessfully Entered email: "+email);
		ApcoaListeners.logInfo("Sucessfully Entered password: "+password);
	}
	
	public void clickLoginBtn(){
		ApcoaListeners.logInfo("Going to click Continure ");
		GenericMethods.explicitWait(this.driver, By.xpath("//button[@type='submit']"), 10);
		highLightElement(loginbtn);
		loginbtn.sendKeys(Keys.RETURN);
		ApcoaListeners.logInfo("Clicked on Continue Sucessfully");
	}
	
	 public static void highLightElement(WebElement element){
		   JavascriptExecutor js = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;   
		   js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);   
		 }
	
}

