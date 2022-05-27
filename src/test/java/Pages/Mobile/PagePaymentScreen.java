package Pages.Mobile;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PagePaymentScreen {

	WebDriver driver;

	By ProfileList = By.className("android.widget.TextView");
	By ProfileName = By.xpath("//android.widget.TextView[contains(@text,'Profile')]");
	By SubCorporate = By.className("android.widget.TextView");

	@iOSXCUITFindBy(xpath = "//XCUIElementTypePicker")
	WebElement iOSSelectCoorperate;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
	WebElement iOSDoneBtn;

	//@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	//private WebElement ClickOnsideBar;

	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Payment') or contains(@name,'payment')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_payments_nav')]")
	private WebElement paymentNav;


	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/sc_make_default')]")
	private WebElement makingDefault;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='icn back']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_back')]")
	private WebElement GoBacktoSideBar;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Business Profile')]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Business Profile')]")
	private WebElement selectBusinessProfile;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='trash icon']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_delete')]")
	private WebElement deleteProfile;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='PROCEED']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement ProceedBtn;


	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Add a Parking Profile']")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Add a parking profile')]")
	private WebElement AddBuisnessProfile;


	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Add a Business Profile']")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'id/tv_parking_type')]")
	private WebElement SelectBuisnessProfile;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CONTINUE']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_action')]")
	private WebElement confirmProfile;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField)[2]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_corporate_id')]")
	private WebElement CorporateId;


	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Add a Corporate Profile') or contains(@name,'Add a Parking Profile')]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Add a parking profile')]")
	private WebElement SelectAddProfile;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Add a Corporate Profile']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/sp_subcorporates')]")
	private WebElement SelectSubCorporate;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField)[1]")
	private WebElement IOSSelectSubCorporate;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField)[3]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/email')]")
	private WebElement email;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='PROCEED']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement Proceed;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_email')]")
	private WebElement enterEmail;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='checkbox unchecked icon']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cb_terms')]")
	private WebElement AcceptTerms;


	public PagePaymentScreen(WebDriver appiumDriver){
		this.driver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator (appiumDriver), this);
	}	


	public void changingDefaultProfile(String Profile) throws InterruptedException {
		ApcoaListeners.logInfo("Profile to be set Default  ------>"+Profile);
		//CommonUtility.GenericMethods.explicitWait(driver,ClickOnsideBar,30);
		//ClickOnsideBar.click();
		PageHomeApcoa pha = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
		pha.clickMenuBtn();

		CommonUtility.GenericMethods.explicitWait(driver,paymentNav,30);
		paymentNav.click();
		Thread.sleep(4000);
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
			List<WebElement> profile = this.driver.findElements(ProfileList);
			for(WebElement singleProfile : profile){
				if(singleProfile.getText().equalsIgnoreCase(Profile)){	
					ApcoaListeners.logInfo("Profile Selected :"+singleProfile.getText());
					singleProfile.click();
					break;
				}
			}
		}else {
			driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+Profile+"')]")).click();

		}
		CommonUtility.GenericMethods.explicitWait(driver,makingDefault,30);
		makingDefault.click();
		ApcoaListeners.logInfo("Set to default profile");
		Thread.sleep(2000);
		GoBacktoSideBar.click();
		Thread.sleep(2000);
		GoBacktoSideBar.click();
		ApcoaListeners.logInfo("Setting up  Default profile  done");
	}


	public void DeleteBuisnessProfile() throws InterruptedException{
		try {
			ApcoaListeners.logInfo("Going to Delete buisness profile");
			//CommonUtility.GenericMethods.explicitWait(driver,ClickOnsideBar,30);
			//ClickOnsideBar.click();
			PageHomeApcoa pha = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
			pha.clickMenuBtn();
			Thread.sleep(2000);
			CommonUtility.GenericMethods.explicitWait(driver,paymentNav,30);
			paymentNav.click();
			Thread.sleep(2000);
			selectBusinessProfile.click();
			CommonUtility.GenericMethods.explicitWait(driver,makingDefault,10);
			ApcoaListeners.logInfo("Making Default profile ");
			makingDefault.click();
			CommonUtility.GenericMethods.explicitWait(driver,deleteProfile,10);
			deleteProfile.click();
			Thread.sleep(2000);
			ProceedBtn.click();
			ApcoaListeners.logInfo("Profile Deleted");
		}catch(Exception e) {

		}
	}


	public void AddBuisnessprofile() throws InterruptedException{
		ApcoaListeners.logInfo("Going Add Buisness Profile");
		CommonUtility.GenericMethods.explicitWait(driver,AddBuisnessProfile,30);
		AddBuisnessProfile.click();
		if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Sweden")){
			try {
				Thread.sleep(2000);
				SelectBuisnessProfile.click();
			} catch(Exception e) {}
		}
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Entering the Email id :  spoorthi@getmyparking.com");
		enterEmail.sendKeys("spoorthi@getmyparking.com");
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Confirm the Profile Creation");
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
			//AutomationConfiguration.AppiumDriver.getKeyboard().pressKey(Keys.RETURN);
			CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("(//XCUIElementTypeTextField)[2]")).click();
		}
		
		confirmProfile.click();
		Thread.sleep(2000);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(selectBusinessProfile.getText(),"Business Profile");
		softAssert.assertAll();
		ApcoaListeners.logInfo("Buisness profile created Successfully!!");
		Thread.sleep(4000);
		CommonUtility.GenericMethods.explicitWait(driver,GoBacktoSideBar,30);
		GoBacktoSideBar.click();
	}


	public boolean DeleteCorporateProfile(String CorporateName) throws InterruptedException{ 
		try {
			ApcoaListeners.logInfo("Going to Delete Corporate profile");
			ApcoaListeners.logInfo("Corporate Profile to be Deleted   :"+CorporateName);
			//CommonUtility.GenericMethods.explicitWait(driver,ClickOnsideBar,30);
			//ClickOnsideBar.click();
			PageHomeApcoa pha = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
			pha.clickMenuBtn();
			Thread.sleep(2000);
			CommonUtility.GenericMethods.explicitWait(driver,paymentNav,30);
			paymentNav.click();
			Thread.sleep(2000);

			if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
				List<WebElement> profile = this.driver.findElements(ProfileName);
				for(int i=0;i<profile.size();i++){
					if(profile.get(i).getText().equalsIgnoreCase(CorporateName)){
						ApcoaListeners.logInfo("Got the Profile  :"+CorporateName);
						profile.get(i).click();
						break;
					}
				}
			}else {
				driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+CorporateName+"')]")).click();
			}
			CommonUtility.GenericMethods.explicitWait(driver,deleteProfile,10);
			deleteProfile.click();

			Thread.sleep(2000);
			ProceedBtn.click();
			ApcoaListeners.logInfo("Profile Deleted");

			Thread.sleep(6000);
			if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
				List<WebElement> profile1 = this.driver.findElements(ProfileName);
				for(int i=0;i<profile1.size();i++){
					if(profile1.get(i).getText().equalsIgnoreCase(CorporateName)){
						return false;
					}
				}
			}else {
				if(driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+CorporateName+"')]")).getText().length()>0) {
					return false;
				}
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	public boolean AddCorporateprofile(String CorporateProfile,String corporateId,String EmailId) throws InterruptedException{
		ApcoaListeners.logInfo("Going Add Corporate Profile");
		CommonUtility.GenericMethods.explicitWait(driver,SelectAddProfile,30);
		SelectAddProfile.click();
		CommonUtility.GenericMethods.explicitWait(driver,SelectSubCorporate,10);
		SelectSubCorporate.click();
		Thread.sleep(4000);
		ApcoaListeners.logInfo("Going To Select SubCorporate   :"+CorporateProfile);
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
			List<WebElement> SP = this.driver.findElements(SubCorporate);
			for(int i=0;i<SP.size();i++){
				if(SP.get(i).getText().equalsIgnoreCase(CorporateProfile)){
					System.out.println(SP.get(i).getText());
					SP.get(i).click();
					break;
				}
			}
		}else {
			CommonUtility.GenericMethods.explicitWait(driver,iOSSelectCoorperate,10);
			IOSSelectSubCorporate.click();
			CommonUtility.GenericMethods.explicitWait(driver,iOSSelectCoorperate,10);
			//CorporateProfile = CorporateProfile.replaceAll(" ", "\\uFEFF");
			System.out.println(CorporateProfile);
			
			iOSSelectCoorperate.sendKeys(CorporateProfile);
			Thread.sleep(2000);
			iOSDoneBtn.click();
		}
		CommonUtility.GenericMethods.explicitWait(driver,CorporateId,30);
		CorporateId.sendKeys(corporateId);
		ApcoaListeners.logInfo("Corporate Id  :"+corporateId);

		CommonUtility.GenericMethods.explicitWait(driver,email,30);
		email.sendKeys(EmailId);
		ApcoaListeners.logInfo("Email Id  :"+EmailId);

		CommonUtility.GenericMethods.explicitWait(driver,AcceptTerms,30);
		AcceptTerms.click();
		ApcoaListeners.logInfo("Terms Accepted");

		CommonUtility.GenericMethods.explicitWait(driver,confirmProfile,30);
		confirmProfile.click();

		CommonUtility.GenericMethods.explicitWait(driver,Proceed,30);
		Proceed.click();


		Thread.sleep(8000);
		ApcoaListeners.logInfo("Checking Corporate Profile Added");
		List<WebElement> profile1 = this.driver.findElements(ProfileName);
		for(int i=0;i<profile1.size();i++){
			if(profile1.get(i).getText().equalsIgnoreCase(CorporateProfile+" Profile")){
				return true;
			}
		}
		return false;
	}
}
