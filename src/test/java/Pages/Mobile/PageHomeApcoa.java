package Pages.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import CommonUtility.CreateSession;
import CommonUtility.GenericMethods;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PageHomeApcoa {
	//XCUIElementTypeStaticText[@name="Customer ID:"]//parent:: XCUIElementTypeCell/XCUIElementTypeStaticText[2]
	WebDriver driver;

	/*try {Thread.sleep(2000);}catch(Exception e) {}
		AutomationConfiguration.AppiumDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='icn menu']")).click();
		//*[@name="Vehicles"]
		try {Thread.sleep(2000);}catch(Exception e) {}
		AutomationConfiguration.AppiumDriver.findElement(By.xpath("//*[@name='Vehicles']")).click();
		//*[@name="add icon"]
		try {Thread.sleep(12000);}catch(Exception e) {}
		AutomationConfiguration.AppiumDriver.findElement(By.xpath("//*[@name='add icon']")).click();

		try {Thread.sleep(2000);}catch(Exception e) {}
		AutomationConfiguration.AppiumDriver.findElement(By.xpath("(//XCUIElementTypeTextField)[2]")).sendKeys("W 3342 R");
		//*[@name="SAVE & CONTINUE"]

		try {Thread.sleep(2000);}catch(Exception e) {}
		AutomationConfiguration.AppiumDriver.findElement(By.xpath("//*[@name='SAVE & CONTINUE']")).click();
		//XCUIElementTypeButton[@name="icn back"]
		try {Thread.sleep(2000);}catch(Exception e) {}
		AutomationConfiguration.AppiumDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='icn back']")).click();*/

	@iOSXCUITFindBy(xpath = "//*[@name='Allow Once']")
	WebElement iOSAllowLocation;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='icn menu']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	WebElement btnMenu;

	@iOSXCUITFindBy(xpath = "//*[contains(@name,'t Allow')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_negative_action')]")
	WebElement acceptPushNotificationBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Customer ID:']//parent:: XCUIElementTypeCell/XCUIElementTypeStaticText[3]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_name')]")
	WebElement usernametxt;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Button']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_order_dismiss')]")
	WebElement btnDismissFoodAlert;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ub_page_button_cancel')]")
	WebElement cancelQuestionPopUpButton;  //to cancel question pop up 

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='icn close']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close_activation_reminder')]")
	WebElement cancelActivatePopUpButton;  


	public String Username="";

	public PageHomeApcoa(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}

	/**
	 * method to dismiss Food alert
	 *
	 */
	public void dismissFoodAlert(){	
		try {
			GenericMethods.waitForElement(driver, btnDismissFoodAlert,2);
			ApcoaListeners.logInfo("Going to click Food Alert ");
			btnDismissFoodAlert.click();
			ApcoaListeners.logInfo("Clicked Food alert button sucessfully.");
		}catch(Exception e) {}
	}

	/**
	 * method to deny accept push notifications
	 *
	 */
	public void acceptPushNotification(){
		ApcoaListeners.logInfo("Going to click Allow Permissions");
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
			GenericMethods.waitForElement(driver, iOSAllowLocation,100);
			iOSAllowLocation.click();
		}
		GenericMethods.waitForElement(driver, acceptPushNotificationBtn,100);
		ApcoaListeners.logInfo("Going to click Accept Push Notification ");
		acceptPushNotificationBtn.click();
		ApcoaListeners.logInfo("Clicked Accept Allow Permissions buttons sucessfully.");
		dismissFoodAlert();
	}

	/**
	 * method to click on menu button
	 *
	 */
	public void clickMenuBtn() {
		ApcoaListeners.logInfo("Going to click menu btn");
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
			GenericMethods.waitForElement(driver, btnMenu,30);
			btnMenu.click();
		}else {
			try {Thread.sleep(2000);}catch(Exception e) {}
			for(int i=0;i<2;i++) {
				Actions action = new Actions(CreateSession.getAutomationConfiguration().AppiumDriver);
				action.moveToElement(btnMenu).doubleClick().perform();
				//System.out.println(i);
				if(btnMenu.isDisplayed()) {
					btnMenu.click();
					break;
				}
			}
		}	
		ApcoaListeners.logInfo("Menu btn clicked successfully");
	}

	/**
	 * method to check the specific username is present or not
	 *
	 */
	public void checkUserName(){
		ApcoaListeners.logInfo("Going to check username");
		clickMenuBtn();
		GenericMethods.waitForElement(driver, usernametxt,20);
		ApcoaListeners.logInfo("username is: "+ usernametxt.getText());
		Username = usernametxt.getText();
		ApcoaListeners.logInfo("Username found: "+Username);
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
			GenericMethods.waitForElement(driver, usernametxt,20);
			usernametxt.click();
			PageAddVehicle pav = new PageAddVehicle(CreateSession.getAutomationConfiguration().AppiumDriver);
			pav.goback();
		}else {
			PageAddVehicle.goBack();
		}
		ApcoaListeners.logInfo("Checking Username Done");
	}

	/**
	 * method to cancel the Question pop up
	 *
	 */
	public void cancelQuestionPopUp() {
		try {
			cancelQuestionPopUpButton.click();
		}catch(Exception e) {}

	}

	/**
	 * method to cancel activate pop up
	 *
	 */
	public void cancelActivatePopUp() {
		try {
			cancelActivatePopUpButton.click();
		}catch(Exception e) {}

	}


}
