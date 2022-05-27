package Pages.Dashboard;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;

public class ActiveandInactivePage {
	WebDriver driver;
	
	@FindBy(xpath="(//span[@class='ant-select-arrow'])[2]")
	WebElement dropdown;
	
	@FindBy(xpath="(//span[starts-with(text(),'1') or starts-with(text(),'3') or starts-with(text(),'2')])[1]")
	WebElement activeid;
	
	@FindBy(xpath="(//span[starts-with(text(),'1') or starts-with(text(),'3') or starts-with(text(),'2')])[1]")
	WebElement inactiveid;
	
	@FindBy(xpath="(//li[text()='Inactive']")
	WebElement inactive;
	
	@FindBy(xpath="//button[@class='ant-btn sc-dnqmqq iPGAyJ ant-btn-primary']")
	WebElement apply;
	
	public ActiveandInactivePage(WebDriver driver){
		this.driver = driver;
	}
	
	public void selectactive(){	
		highLightElement(dropdown);
		dropdown.click();
		highLightElement(apply);
		apply.click();
		
	}
	public String validateId() {
		ApcoaListeners.logInfo("Geting the total price of the permit");
		highLightElement(activeid);
		return activeid.getText();
	}
	
	public void selectinactive(){
		highLightElement(dropdown);
		dropdown.click();
		Actions action =new Actions(driver);
		action.sendKeys(Keys.DOWN).build().perform();
		action.sendKeys(Keys.RETURN).build().perform();
		highLightElement(apply);
		apply.click();
		
	}
	
	public String verifyId() {
		ApcoaListeners.logInfo("Geting the total price of the permit");
		highLightElement(inactiveid);
		return inactiveid.getText();	
	}
	
	 public static void highLightElement(WebElement element){
		   JavascriptExecutor js = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;   
		   js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);   
		 }

}
