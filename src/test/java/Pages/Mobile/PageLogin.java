package Pages.Mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PageLogin {

	WebDriver driver;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/email')]")
	WebElement edtxtEmail;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_password')]")
	WebElement edtxtPassword;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CONTINUE']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_continue')]")
	WebElement btnLogin;

	public PageLogin(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}

	/**
	 * method to enter credentials in login page
	 *
	 * @param email, password to enter into login page
	 */
	public void enterCredentials(String email, String password) throws InterruptedException{
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
			Thread.sleep(15000);
		}
		ApcoaListeners.logInfo("Going to Login with credentials: "+ email+" -> "+ password);
		CommonUtility.GenericMethods.waitForElement(driver,edtxtEmail,50);
		CommonUtility.GenericMethods.waitForElement(driver,edtxtPassword,10);
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
			Thread.sleep(5000);
			CommonUtility.GenericMethods.waitForElement(driver,edtxtEmail,8);
			Thread.sleep(5000);
			CommonUtility.GenericMethods.waitForElement(driver,edtxtEmail,8);
		}
		try {
			edtxtEmail.clear();
			edtxtEmail.sendKeys(email);
			edtxtPassword.clear();
			edtxtPassword.sendKeys(password);
		}catch(Exception e) {
			for(int i=0;i<5;i++) {
				try {
					Thread.sleep(2000);
					edtxtPassword.clear();
					edtxtPassword.sendKeys("Nopassowrd");
					CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeTextField")).clear();
					CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeTextField")).sendKeys(email);
					edtxtPassword.clear();
					edtxtPassword.sendKeys(password);	
					break;
				}catch(Exception el) {
					Thread.sleep(1500);
				}
			}
		}
		ApcoaListeners.logInfo("Credentials entered successfully ");
	}

	/**
	 * method to click on Continue button after entering the credentials
	 *
	 */
	public void clickContinue(){
		ApcoaListeners.logInfo("Going to click Login button ");
		CommonUtility.GenericMethods.waitForElement(driver,btnLogin,20);
		btnLogin.click();
		ApcoaListeners.logInfo("Clicked Login button sucessfully.");
	}


}
