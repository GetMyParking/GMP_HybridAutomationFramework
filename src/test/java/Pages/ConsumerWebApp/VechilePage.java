package Pages.ConsumerWebApp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;

public class VechilePage{
	WebDriver driver;
	
	@FindBy(xpath="//p[text()='Vehicle(s)']")
	WebElement vehicle;
	
	@FindBy(xpath="//span[text()='Add a Vehicle']")
	WebElement addVehicle;
	
	@FindBy(xpath="//li[@data-value='Alabama']")
	WebElement state;
	
	@FindBy(id="mui-component-select-state")
	WebElement dropdown;
	
	@FindBy(name="vehicleNumber")
	WebElement vehicleNumber;
	
	@FindBy(name="displayName")
	WebElement nickName;
	
	public VechilePage(WebDriver driver){
		this.driver = driver;
	}
	
	public void addVechile() {
		ApcoaListeners.logInfo("Clicking on Add vechile ");
		highLightElement(vehicle);
		vehicle.click();
		highLightElement(addVehicle);
		addVehicle.click();
		highLightElement(dropdown);
		dropdown.click();
		highLightElement(state);
		state.click();
		highLightElement(vehicleNumber);
		vehicleNumber.sendKeys("BT2000");
		highLightElement(nickName);
		nickName.sendKeys("Car");		
	}
	 public static void highLightElement(WebElement element){
		   JavascriptExecutor js = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;   
		   js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);   
		 }
}
