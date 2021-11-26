package Pages.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class PageLogin {
	WebDriver driver;
	
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/email")
	WebElement edtxtEmail;
	
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/edt_password")
	WebElement edtxtPassword;
	
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/btn_continue")
	WebElement btnLogin;
	
	
	
	
	public PageLogin(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}
	
	
	
	public void enterCredentials(String email, String password)
	{
		ApcoaListeners.logInfo("Going to Login with credentials: "+ email+" -> "+ password);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,edtxtEmail,150);
		//System.out.println("Going to enter credentials:"+email+" -> "+ password);
		edtxtEmail.sendKeys(email);
		edtxtPassword.sendKeys(password);
		ApcoaListeners.logInfo("Credentials entered successfully ");
	}

	public void clickContinue()
	{
		ApcoaListeners.logInfo("Going to click Login button ");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnLogin,150);
		btnLogin.click();
		ApcoaListeners.logInfo("Clicked Login button sucessfully.");
	}
}
