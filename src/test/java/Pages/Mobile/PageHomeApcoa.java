package Pages.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import CommonUtility.GenericMethods;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageHomeApcoa {
	
	WebDriver driver;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	WebElement btnMenu;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action')]")
	WebElement acceptPushNotificationBtn;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_name')]")
	WebElement usernametxt;
	
	public String Username="";
	
	public PageHomeApcoa(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}

	public void acceptPushNotification(){	
		GenericMethods.explicitWaitForWebElementOnly(driver, acceptPushNotificationBtn,100);
		ApcoaListeners.logInfo("Going to click Accept Push Notification ");
		acceptPushNotificationBtn.click();
		ApcoaListeners.logInfo("Clicked Accept Push Notification button sucessfully.");
	}

	public void checkUserName(){
		GenericMethods.explicitWaitForWebElementOnly(driver, btnMenu,75);
		btnMenu.click();
		GenericMethods.explicitWaitForWebElementOnly(driver, usernametxt,75);
		ApcoaListeners.logInfo("username is: "+ usernametxt.getText());
		Username = usernametxt.getText();
		PageAddVehicle.goBack();
	}
}
