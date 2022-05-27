package Pages.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PagePersonalDetails {

	WebDriver driver;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_name')]")
	private WebElement ProfileInfo;


	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_edit_info')]")
	private WebElement editProfileInfo;

	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_first_name')]")
	private WebElement FirstName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_last_name')]")
	private WebElement LastName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/email')]")
	private WebElement Email;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_name')]")
	private WebElement UserName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_save')]")
	private WebElement ConfirmChange;
	
	
	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.ImageView[1]")
	private WebElement btnMenu;
	
	public PagePersonalDetails(WebDriver appiumDriver){
		this.driver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator (appiumDriver), this);
	}	



	public void editpersonalDetails(String first,String last, SoftAssert softAssert) throws InterruptedException{
		ApcoaListeners.logInfo("Going to Edit the User Name");
		CommonUtility.GenericMethods.explicitWait(driver,btnMenu,30);
		btnMenu.click();

		String ActualFirstName,ActualLastName,ActualEmail;
		CommonUtility.GenericMethods.explicitWait(driver,ProfileInfo,30);
		ProfileInfo.click();
		CommonUtility.GenericMethods.explicitWait(driver,editProfileInfo,30);
		editProfileInfo.click();

		ActualFirstName=FirstName.getText();

		ActualLastName=LastName.getText();
		ApcoaListeners.logInfo("ActualFirstName  :"+ActualFirstName);

		ApcoaListeners.logInfo("ActualLastName  :"+ActualLastName);

		changeNameandEmail(first,last,softAssert);
		Thread.sleep(4000);
		CommonUtility.GenericMethods.explicitWait(driver,editProfileInfo,30);
		editProfileInfo.click();
		changeNameandEmail(ActualFirstName,ActualLastName,softAssert);


	}

	public void changeNameandEmail(String firstname,String lastname, SoftAssert softAssert) throws InterruptedException{
		ApcoaListeners.logInfo("Changing First Name to   :"+firstname);

		CommonUtility.GenericMethods.explicitWait(driver,FirstName,30);
		FirstName.sendKeys(firstname);
		Thread.sleep(4000);
		ApcoaListeners.logInfo("Changing Last Name to   :"+lastname);
		CommonUtility.GenericMethods.explicitWait(driver,LastName,30);
		LastName.sendKeys(lastname);
		Thread.sleep(4000);
		ApcoaListeners.logInfo("Confirm the Change ");
		CommonUtility.GenericMethods.explicitWait(driver,ConfirmChange,30);
		ConfirmChange.click();

		Thread.sleep(4000);
		((AndroidDriver<WebElement>) CreateSession.getAutomationConfiguration().AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));

		softAssert.assertEquals(UserName.getText(),firstname+" "+lastname);
		//SA.assertAll();
	}


}
