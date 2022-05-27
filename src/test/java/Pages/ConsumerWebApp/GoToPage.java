package Pages.ConsumerWebApp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import CommonUtility.CreateSession;

public class GoToPage{
	WebDriver driver;
	
	@FindBy(xpath="(//*[name()='svg'][@height='16'])[1]")
	WebElement name;
	
	@FindBy(xpath="//span[text()='My Profile']")
	WebElement profile;
	
	public GoToPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void name() {
		highLightElement(name);
		name.click();
	
	}
	public void profile() {
		highLightElement(profile);
		profile.click();
	
	}
	public static void highLightElement(WebElement element){
		   JavascriptExecutor js = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;   
		   js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);   
		 }

}

