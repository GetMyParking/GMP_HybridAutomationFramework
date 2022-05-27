package Pages.Mobile;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import CommonUtility.CreateSession;
import CommonUtility.GenericMethods;
import Mobile.Utils.DialerRotation;
import Mobile.ObjectMapper.FreeParkingMapper;
import Mobile.ObjectMapper.ParkingMapper;
import TestNGListeners.ApcoaListeners;
import Utility.ImageComparison;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PageStartSession {

	WebDriver driver;
	public String Parkingprice = "";
	public String ActualInitialParkingPrice = "";
	public String ActualPaymentSuccess = "";
	public String ActualExtendedParkingPrice = "";

	By Profile = By.xpath("//android.widget.TextView[contains(@resource-id,':id/tv_profile_title')]");

	By DefaultMark = By.xpath("//android.widget.ImageView[contains(@resource-id,':id/iv_default')]");

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_apply_promo_code')]")
	private WebElement btnApplyPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	private WebElement btnclikAddPromo;


	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement btnConfirmPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_edit_discount')]")
	private WebElement btnEditPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_remove_promotion')]")
	private WebElement btnRemovePromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement btnProceedRemovePromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement btnConfirm2Promo;

	@AndroidFindBy(id="com.apcoaflow.consumer:id/tv_positive_action_button]")
	private WebElement btnConfirm3Promo;

	@iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[contains(@name,'Price')]/parent::XCUIElementTypeOther//XCUIElementTypeStaticText)[2]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_price_with_units')]")
	private WebElement ParkingPriceItaly;

	@iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[contains(@name,'Price')]/parent::XCUIElementTypeOther//XCUIElementTypeStaticText)[3]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_price_with_units')]")
	private WebElement ParkingPrice;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='Hours']/parent:: XCUIElementTypeOther/XCUIElementTypeStaticText")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_hours')]")
	private WebElement ParkingHour;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='mins']/parent:: XCUIElementTypeOther/XCUIElementTypeStaticText")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_minutes')]")
	private WebElement ParkingMinute;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='START' or contains(@name,'CONFIRM')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/csb_time_dialer')]")
	private WebElement clickOnDialer;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='EXTEND']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/csb_time_dialer')]")
	private WebElement clickOnDialerExtend;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'Ok')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/half_interstitial_button1')]")
	private WebElement btnCancelNotification;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[contains(@name,'Confirm & Pay')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm_pay')]")
	private WebElement payAmount; 

	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[contains(@name,'Confirm and pay')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm_pay')]")
	private WebElement payAmountAnd; 

	//@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cover_button2')]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'Ok')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/half_interstitial_button1')]")
	private WebElement btnCancelNotification2;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='icn close']")
	private WebElement closepaymentIOS;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='Payment Successful']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_success_heading')]")
	private WebElement paySuccessMsg;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='filter icon' or @name='icn close']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close')]")
	private WebElement closePayment;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='EXTEND SESSION' or @name='EXTEND PARKING SESSION']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_extend_parking_session')]")
	private WebElement ExtendSession;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'PAY')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_action_button')]")
	private WebElement confirmpaymentforextendsession;


	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/actv_positive_button')]")
	private WebElement confirm;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_error_desc')]")
	private WebElement errorMsg;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_payment_heading')]")
	private WebElement PaymentProfile;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_discount_name')]")
	private WebElement isDiscountApplied;


	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.TextView")
	private WebElement TarrifText1;

	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.TextView")
	private WebElement btnTarrif2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ivTariffInfo')]")
	private WebElement tariffInfo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/txtTariffSelection')]")
	private WebElement btnTariffSelection;
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/txtTotalCost')]")
	private WebElement UpdatedTariff;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tvBottomPriceBreakdownLabel')]")
	private WebElement btnOkGotIt;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_tariff_type')]")
	private WebElement tariffText1;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_back')]")
	private WebElement GoBacktoSideBar;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_address')]")
	private WebElement desiredLocation;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_display_name')]")
	private WebElement parkingDetailScreen;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_display_name_main')]")
	private WebElement DisplayName;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_access_type_desc')]")
	private WebElement parkAndGoDes;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_collapse')]")
	private WebElement backbtn;



	public PageStartSession(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}


	/**
	 * method to find dialer in the page can call rotate dialer
	 *
	 * @param percent you want to ratate the dialer
	 */
	public void findDialerAndRotate(int percent) throws InterruptedException {
		ApcoaListeners.logInfo("Dialer Rotation start for percent: "+percent);
		String xpath="";
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
			xpath= "//XCUIElementTypeStaticText[contains(@name,'Spin the dial') or contains(@name,'START') or contains(@name,'CONFIRM') or contains(@name,'SPIN')]/parent:: XCUIElementTypeOther";
		}else {
			xpath="//*[contains(@resource-id,':id/csb_time_dialer')]";
		}
		WebElement element = null;
		for(int i=0;i<20;i++) {
			try {
				element = CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath(xpath));
				break;
			}catch(Exception e) {Thread.sleep(1500);}
		}
		DialerRotation dr = new DialerRotation();
		dr.rotateDialer(percent,element);
		ApcoaListeners.logInfo("Dialer Rotation end for percent: "+percent);
	}

	/**
	 * method to apply promocode and rotate dialer
	 *
	 * @param tariffname is the name of the tariff
	 */
	public void dialerMovement(String currency, String promocode, String dialerpercent) {
		try{

			ApcoaListeners.logInfo("Session Creation Page: start");
			Thread.sleep(2000);

			//production popup ITALY(Ok button)
			if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Italy")) {
				try {
					ApcoaListeners.logInfo("Going to click on Ok btn (POP-UP Italy)");
					CommonUtility.GenericMethods.waitForElementAndClick(driver,btnCancelNotification2,15);
					ApcoaListeners.logInfo("Successfully clicked on Ok btn (POP-UP Italy)");
				}catch(Exception e) {
					ApcoaListeners.logInfo("Button not found. (POP-UP Italy)");
				}
			}
			
			//Thread.sleep(2000);
			if(!promocode.equalsIgnoreCase("null")) {
				try {
					
					PageBuyPass pbp = new PageBuyPass(CreateSession.getAutomationConfiguration().AppiumDriver);
					pbp.applyPromoCode(promocode);
				}catch(Exception e) {
					System.out.println("Error in  apply promocode: "+ e.toString());
				}
			}

			ApcoaListeners.logInfo("Dialer Movement for start Session: start");
			
			this.findDialerAndRotate(Integer.parseInt(dialerpercent));

			ApcoaListeners.logInfo("Dialer Rotatation Complete.");

			if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
				WebElement priceforparking = null;
				Thread.sleep(5000);
				priceforparking = CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+currency+"')]"));
				Parkingprice = priceforparking.getText();
			}
			else {
				Parkingprice = ParkingPrice.getText();
			}
			Parkingprice = Parkingprice.replaceAll(",","").replaceAll(" ","");
			int len=currency.length();
			this.ActualInitialParkingPrice = Parkingprice.substring(len);

			if(this.ActualInitialParkingPrice.contains(currency)) {
				this.ActualInitialParkingPrice = this.ActualInitialParkingPrice.split(currency)[0];
			}
			if(this.ActualInitialParkingPrice.contains(currency)) {
				this.ActualInitialParkingPrice = this.ActualInitialParkingPrice.split("\\"+currency)[0];
			}

			if(this.ActualInitialParkingPrice.contains("R$")){
				this.ActualInitialParkingPrice = this.ActualInitialParkingPrice.split("R")[0];
			}


			//Thread.sleep(3000);
			CommonUtility.GenericMethods.waitForElementAndClick(driver,clickOnDialer,20);
			ApcoaListeners.logInfo("dialerMovement End.");
		}catch(Exception e){
			ApcoaListeners.logInfo("dialerMovement End with error: "+e.toString());
		}
	}


	/**
	 * method to check payment confimation  pop up
	 *
	 * @param parkingtype is type of parking
	 */
	public void PaymentConfirmation(String parkingtype) {
		try{

			ApcoaListeners.logInfo("PaymentConfirmation start");
			try {
				CommonUtility.GenericMethods.waitForElement(driver,payAmount,30);
				ApcoaListeners.logInfo("payAmount.getText(): "+payAmount.getText());
				CommonUtility.GenericMethods.waitForElementAndClick(driver,payAmount,15);
			}catch(Exception e) {
				CommonUtility.GenericMethods.waitForElement(driver,payAmountAnd,30);
				ApcoaListeners.logInfo("payAmount.getText(): "+payAmountAnd.getText());
				CommonUtility.GenericMethods.waitForElementAndClick(driver,payAmountAnd,15);
			}


			//production popup code
			if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Italy")) {
				try {CommonUtility.GenericMethods.waitForElementAndClick(driver,btnCancelNotification2,15);}catch(Exception e) {}
			}

			// cancel payment successfull popup

			if(!parkingtype.equalsIgnoreCase("postpay")) {
				Thread.sleep(5000);
				CommonUtility.GenericMethods.waitForElement(driver,paySuccessMsg,30);
				ActualPaymentSuccess=paySuccessMsg.getText();
				System.out.println("acutal payment"+ActualPaymentSuccess);
				if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
					CommonUtility.GenericMethods.waitForElement(driver,closePayment,10);
					closePayment.click();
				}else {
					try {
							Actions action = new Actions(CreateSession.getAutomationConfiguration().AppiumDriver);
							action.moveToElement(closePayment).doubleClick().perform();
					}catch(Exception e) {
						System.out.println("Not able to find x on payment confirmation first time.");
					}
					try {
						CommonUtility.GenericMethods.waitForElement(driver,closepaymentIOS,10);
						Actions action = new Actions(CreateSession.getAutomationConfiguration().AppiumDriver);
						action.moveToElement(closepaymentIOS).doubleClick().perform();
					}catch(Exception e) {
						System.out.println("Not able to find x on payment confirmation final time as well.");
					}
				}
				ApcoaListeners.logInfo("PaymentConfirmation end. ActualPaymentSuccess msg is: " + ActualPaymentSuccess);
			}
			ApcoaListeners.logInfo("PaymentConfirmation end");

		}catch(Exception e) {
			ApcoaListeners.logInfo("PaymentConfirmation end error: "+e.toString());
		}
	}


	/**
	 * method to extend session
	 *
	 * @param currency is currency symbol of country
	 * @param parkingtype is type of parking
	 * @param drpercent is the percentage you want to rotate dialer
	 */
	public void ExtendSession(String currency, String parkingtype,String drpercent){
		try{
			ApcoaListeners.logInfo("Extend Session start");
			CommonUtility.GenericMethods.waitForElement(driver,ExtendSession,30);
			ExtendSession.click();
			Thread.sleep(4000);
			ApcoaListeners.logInfo("Extend Session Dialer Movement start");
			Thread.sleep(4000);

			this.findDialerAndRotate(Integer.parseInt(drpercent));


			ApcoaListeners.logInfo("Extend Session Dialer Movement ends.");
			Thread.sleep(7000);
			try {
				if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
					Thread.sleep(5000);
					WebElement priceforparking = CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+currency+"')]"));
					Parkingprice = priceforparking.getText();
				}
				else {
					if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Italy")) {
						CommonUtility.GenericMethods.waitForElement(driver,ParkingPriceItaly,20);
						Parkingprice=ParkingPriceItaly.getText();
					}else {
						CommonUtility.GenericMethods.waitForElement(driver,ParkingPrice,20);
						Parkingprice = ParkingPrice.getText();
					}
				}
				//String currency=parkingMapper.getCurrency();
				int len=currency.length();
				ActualExtendedParkingPrice = Parkingprice.substring(len);
			}catch(Exception e) {
				ApcoaListeners.logInfo("Not able to find parking price.");
			}

			if(ActualExtendedParkingPrice.contains(currency)) {
				ActualExtendedParkingPrice = ActualExtendedParkingPrice.split(currency)[0];
			}
			if(ActualExtendedParkingPrice.contains(currency)) {
				ActualExtendedParkingPrice = ActualExtendedParkingPrice.split("\\"+currency)[0];
			}
			ApcoaListeners.logInfo("Parkingprice " + Parkingprice );
			ApcoaListeners.logInfo("Actual Extended Parking Price: " + ActualExtendedParkingPrice );



			//click on dialer extend
			if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
				CommonUtility.GenericMethods.waitForElementAndClick(driver,clickOnDialerExtend,20);
			}else {
				for(int i=0;i<20;i++) {
					try {
						try {
							CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeOther//XCUIElementTypeStaticText[@name='EXTEND' or @name='START' or @name='CONFIRM']")).click();
						}catch(Exception e) {
							CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeOther//XCUIElementTypeStaticText[@name='START' or @name='EXTEND' or @name='CONFIRM']")).click();
						}
						try {
							CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'PAY')]"));
							break;
						}catch(Exception e) {}
						try {Thread.sleep(1500);}catch(Exception el) {}
					}catch(Exception e) {}
				}
			}


			//Payment confirmation popup

			if(!parkingtype.equalsIgnoreCase("postpay"))
			{
				if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
					Thread.sleep(2000);
					CommonUtility.GenericMethods.waitForElement(driver,confirmpaymentforextendsession,30);
					Actions action= new Actions(CreateSession.getAutomationConfiguration().AppiumDriver);
					action.moveToElement(confirmpaymentforextendsession).doubleClick().perform();
				}
				else {
					CommonUtility.GenericMethods.waitForElement(driver,confirmpaymentforextendsession,30);
					confirmpaymentforextendsession.click();
				}

			}
			ApcoaListeners.logInfo("ExtendSession end.");
		}catch(Exception e){
			ApcoaListeners.logInfo("ExtendSession end error: "+e.toString());
		}
	}

	/**
	 * method to confirm payment confirmation after extending the session
	 *
	 */
	public void ExtendPaymentConfirmation(){
		try{
			ApcoaListeners.logInfo("Extend Payment Confirmation start.");
			try {
				CommonUtility.GenericMethods.waitForElement(driver,closePayment,20);
			}catch(Exception e) {}
			if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
				closePayment.click();
			}else {
				Actions action = new Actions(CreateSession.getAutomationConfiguration().AppiumDriver);
				action.moveToElement(closePayment).doubleClick().perform();
			}

			try {
				Actions action = new Actions(CreateSession.getAutomationConfiguration().AppiumDriver);
				action.moveToElement(closepaymentIOS).doubleClick().perform();
			}catch(Exception e) {}

			ApcoaListeners.logInfo("Extend Payment Confirmation end.");
		}catch(Exception e){
			ApcoaListeners.logInfo("ExtendPaymentConfirmation end error: "+e.toString());
		}
	}


	public void CheckPriceInFreeParking(FreeParkingMapper parkingMapper ) throws InterruptedException{
		CommonUtility.GenericMethods.explicitWait(driver,ParkingPrice,30);
		Parkingprice= ParkingPrice.getText();
		String currency=parkingMapper.getcurrency();
		int len=currency.length();
		ActualInitialParkingPrice = Parkingprice.substring(len);
		ApcoaListeners.logInfo("Free Parking Price     "+ActualInitialParkingPrice);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(ActualInitialParkingPrice,"0.00");
		softAssert.assertAll();
		CommonUtility.GenericMethods.explicitWait(driver,clickOnDialer,10);
		clickOnDialer.click();
		PaymentConfirmation("prepay");
	}


	public void SessionCheck() throws InterruptedException
	{
		Thread.sleep(3000);
		confirm.click();
		Thread.sleep(2000);
		SoftAssert softAssert = new SoftAssert();
		ApcoaListeners.logInfo("Rotating the dailer");
		this.findDialerAndRotate(Integer.parseInt("100"));
		Thread.sleep(3000);
		ApcoaListeners.logInfo("error message----->"+errorMsg.getText());
		softAssert.assertEquals(errorMsg.getText(),"Please enter payment details");
		Thread.sleep(2000);
		clickOnDialer.click();
		try{
			ApcoaListeners.logInfo("Checking the confirm pay button");
			softAssert.assertFalse(payAmount.isDisplayed());
		}
		catch(Exception e){}
		softAssert.assertAll();
		ApcoaListeners.logInfo("Unable to start the session without Payment Method");
	}


	public void  ProfileAutoSwitchCheck(String SwitchedProfile ,String startSession,SoftAssert softAssert) throws InterruptedException
	{
		String promocode = "100PERCENT";
		String paymentProfile;

		CommonUtility.GenericMethods.explicitWait(driver,PaymentProfile,10);
		paymentProfile=PaymentProfile.getText();

		ApcoaListeners.logInfo("PaymentProfile in Start Session   ------>"+paymentProfile);

		softAssert.assertEquals(paymentProfile,SwitchedProfile);

		Thread.sleep(4000);
		ApcoaListeners.logInfo("Applying The PromoCode");


		if(!promocode.equalsIgnoreCase("null")) {
			try {
				PageBuyPass pbp = new PageBuyPass(CreateSession.getAutomationConfiguration().AppiumDriver);

				pbp.addAnotherPromoCode(promocode);
			}catch(Exception e) {
				System.out.println("Error in  apply promocode: "+ e.toString());
			}
		}
		Thread.sleep(3000);

		this.findDialerAndRotate(Integer.parseInt("25"));

		ApcoaListeners.logInfo("Checking Start Session");
		softAssert.assertEquals(checkSessionStart(),startSession);
		//softAssert.assertAll();
		CommonUtility.GenericMethods.explicitWait(driver,payAmount,15);
		payAmount.click();

		if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Italy")){
			CommonUtility.GenericMethods.explicitWait(driver,btnCancelNotification2,15);
			btnCancelNotification2.click();
		}
		if(!(CreateSession.getAutomationConfiguration().Country.equals("Denmark")||CreateSession.getAutomationConfiguration().Country.equals("Sweden"))) {
			Thread.sleep(5000);
			CommonUtility.GenericMethods.explicitWait(driver,paySuccessMsg,30);
			String ActualPaymentSuccess=paySuccessMsg.getText();
			CommonUtility.GenericMethods.explicitWait(driver,closePayment,10);
			closePayment.click();
			ApcoaListeners.logInfo("PaymentConfirmation end: ActualPaymentSuccess " + ActualPaymentSuccess);
		}

	}


	public void checkProfileInExtend(String SwictedProfile, SoftAssert softAssert) throws InterruptedException{
		ApcoaListeners.logInfo("Extend Session start");
		CommonUtility.GenericMethods.explicitWait(driver,ExtendSession,30);
		ExtendSession.click();
		Thread.sleep(4000);//8000
		ApcoaListeners.logInfo("Going To click on PaymentProfile");
		CommonUtility.GenericMethods.explicitWait(driver,PaymentProfile,30);
		PaymentProfile.click();
		Thread.sleep(3000);
		List<WebElement> profile = this.driver.findElements(Profile);
		List<WebElement> defaultmark = this.driver.findElements(DefaultMark);
		System.out.println(profile.size());
		for(int i=0;i<profile.size();i++){
			ApcoaListeners.logInfo(profile.get(i).getText());
			if(profile.get(i).getText().equalsIgnoreCase(SwictedProfile)){
				softAssert.assertEquals(TakeScreenShotAndCompare(defaultmark.get(i)),true);
				break;
			}
		}
		Thread.sleep(4000);
		((AndroidDriver<WebElement>) CreateSession.getAutomationConfiguration().AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(4000);
		((AndroidDriver<WebElement>) CreateSession.getAutomationConfiguration().AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		ApcoaListeners.logInfo("Payment Profile in Both Start Session and Extend Session Are Same");
	}

	public boolean TakeScreenShotAndCompare(WebElement element) throws InterruptedException{
		ImageComparison IC=new ImageComparison();
		String p1=GenericMethods.elementScreenshot(element);
		String p2="/Users/karankumaragarwal/Downloads/checksymbol.png";
		double diff=IC.comapareImages(p1,p2,false);
		if(diff>=0.0&&diff<=0.5){
			ApcoaListeners.logInfo("Images Are Same");
			Thread.sleep(4000);
			removeScreenshot(p1);
			return true;
		}else{
			ApcoaListeners.logInfo("Images Are Different");
			Thread.sleep(4000);
			removeScreenshot(p1);
			return false;
		}
	}

	public void removeScreenshot(String path){
		String command ="rm "+path;
		try{
			Process p = Runtime.getRuntime().exec(command);
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		ApcoaListeners.logInfo("ScreenShot Deleted");
	}


	public String checkSessionStart() throws InterruptedException{
		CommonUtility.GenericMethods.explicitWait(driver,clickOnDialer,10);
		clickOnDialer.click();
		Thread.sleep(4000);
		try{
			if(payAmount.isDisplayed()){
				return "true";
			}else {
				return "false";
			}
		}catch(Exception e){}
		return "false";
	}

	public void PolandTarrif(String Country,ParkingMapper parkingMapper, SoftAssert softAssert) throws InterruptedException
	{
		ApcoaListeners.logInfo("going to click Start Session button");

		Thread.sleep(5000);
		try{
			ApcoaListeners.logInfo("Country: "+Country);
			Thread.sleep(10000);//15000
			CommonUtility.GenericMethods.explicitWait(driver,ParkingPrice,30);
			Parkingprice= ParkingPrice.getText();
			String currency=parkingMapper.getCurrency();
			int len=currency.length();
			ActualInitialParkingPrice = Parkingprice.substring(len);
			Thread.sleep(3000);
			CommonUtility.GenericMethods.explicitWait(driver,clickOnDialer,10);
			clickOnDialer.click();
			ApcoaListeners.logInfo("dialerMovement end");
		}catch(Exception e){
			ApcoaListeners.logInfo("dialerMovement end error: "+e.toString());
		}
		PaymentConfirmation("prepay");
		ApcoaListeners.logInfo("Extend Session start");
		CommonUtility.GenericMethods.explicitWait(driver,ExtendSession,100);
		ExtendSession.click();
		Thread.sleep(4000);//8000
		ApcoaListeners.logInfo("Extend Session Dialer Movement start:");
		ApcoaListeners.logInfo("Country: " +Country);
		Thread.sleep(4000);
		String TarrifName=isDiscountApplied.getText();
		ApcoaListeners.logInfo("Tarrif Name in Extend Session  --------->"+TarrifName);
		String ParkingHour1=ParkingHour.getText();
		ApcoaListeners.logInfo("Intial ParkingHour in Extend Session  --------->"+ParkingHour1);
		String ParkingMin=ParkingMinute.getText();
		ApcoaListeners.logInfo("Intial ParkingMin in Extend Session  --------->"+ParkingMin);
		String ParkingPrice1=ParkingPrice.getText();
		ApcoaListeners.logInfo("Intial Parking price in Extend Session  --------->"+ParkingPrice1);
		softAssert.assertEquals(TarrifName,"QAPOLANDFREETIME");
		softAssert.assertEquals(ParkingHour1,"00");
		softAssert.assertEquals(ParkingMin,"00");
		softAssert.assertEquals(ParkingPrice1,"PLN0.00");
		//SA.assertAll();
		((AndroidDriver<WebElement>) CreateSession.getAutomationConfiguration().AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}

	public void check_multiple_tarrif(ParkingMapper parkingMapper,SoftAssert softAssert) throws InterruptedException{
		ApcoaListeners.logInfo("going to click Start Session button");

		ApcoaListeners.logInfo("Selected Tariff   "+TarrifText1.getText());
		TarrifText1.click();
		Thread.sleep(2000);
		if(isDiscountApplied.isDisplayed()){
			removeDiscount();
		}
		Thread.sleep(3000);
		ApcoaListeners.logInfo("Rotating the Dialer");

		this.findDialerAndRotate(Integer.parseInt("25"));
		String oldtarrif,updatedtarrif,TariffText1,TarrifText2,iTarrifText2;
		CommonUtility.GenericMethods.explicitWait(driver,ParkingPrice,30);
		Parkingprice= ParkingPrice.getText();
		String currency=parkingMapper.getCurrency();
		int len=currency.length();
		oldtarrif = Parkingprice.substring(len);
		TariffText1=tariffText1.getText();
		ApcoaListeners.logInfo(TariffText1+"----->"+Parkingprice);
		CommonUtility.GenericMethods.explicitWait(driver,tariffInfo,30);
		ApcoaListeners.logInfo("Going to click on Tariff info ");
		tariffInfo.click();
		Thread.sleep(2000);
		CommonUtility.GenericMethods.explicitWait(driver,btnTariffSelection,30);
		ApcoaListeners.logInfo("Going to Select Different Tariff");
		btnTariffSelection.click();
		Thread.sleep(3000);
		iTarrifText2=btnTarrif2.getText();
		ApcoaListeners.logInfo("Second Tariff Selected"+" "+iTarrifText2);
		btnTarrif2.click();
		Thread.sleep(2000);
		btnOkGotIt.click();
		Thread.sleep(2000);
		updatedtarrif=ParkingPrice.getText().substring(len);
		TarrifText2=tariffText1.getText();
		ApcoaListeners.logInfo(TarrifText2+"----> "+updatedtarrif);
		softAssert.assertEquals(TarrifText2,iTarrifText2);
		softAssert.assertNotEquals(TarrifText2,TariffText1);
		softAssert.assertNotEquals(oldtarrif,updatedtarrif);
	}

	public void removeDiscount() throws InterruptedException{
		CommonUtility.GenericMethods.explicitWait(driver,btnEditPromo,200);
		btnEditPromo.click();
		Thread.sleep(2000);
		CommonUtility.GenericMethods.explicitWait(driver,btnRemovePromo,200);
		btnRemovePromo.click();
		CommonUtility.GenericMethods.explicitWait(driver,btnProceedRemovePromo,200);
		btnProceedRemovePromo.click();
		Thread.sleep(2000);
		GoBacktoSideBar.click();
	}


	public Boolean parkAndGoCheck(String carpark) throws InterruptedException {
		ApcoaListeners.logInfo("Now searching our epmp carpark : "+carpark);
		desiredLocation.click();
		Thread.sleep(3000);
		ApcoaListeners.logInfo("Now going to detail section  of : "+carpark);
		parkingDetailScreen.click();
		Thread.sleep(3000);
		String name = DisplayName.getText();
		if(parkAndGoDes.isDisplayed()) {
			ApcoaListeners.logInfo("(Park & Go) visible");
			assert true:"text (Park & Go) visible";
		}
		else {
			return false;
		}
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Now back to main screen");
		backbtn.click();
		return true;
	}


}
