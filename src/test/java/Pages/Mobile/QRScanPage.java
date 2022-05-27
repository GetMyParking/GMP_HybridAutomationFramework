package Pages.Mobile;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class QRScanPage {

	WebDriver driver;
	String ActiveSessionID = "";
	String ExpiredSessionID = "";
	String AppliedPromoByQRInExpire = "";
	String AppliedPromoByQR = "";

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session_with_buy_pass')]")
	private WebElement startSessionwithpass;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	private WebElement ClickOnsideBar;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_sessions')]")
	private WebElement MenuMySessions;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_session_id')]")
	private WebElement MySessionsSessionID;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_back')]")
	private WebElement GoBacktoSideBar;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/clQrScan')]")
	private WebElement ScanQRButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_message')]")
	private WebElement PromoName;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_validation_title')]")
	private WebElement PromoCheck;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement btnConfirmPromo;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_discount_title')]")
	private WebElement AppliedPromoInExpire;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_tap_to_access')]")
	private WebElement TaptoExit;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	private WebElement btnclikAddPromo;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_apply_promo_code')]")
	private WebElement ApplyPromoBtn;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_cost')]")
	private WebElement MySessionsTotalCost;
	
	public QRScanPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}


	public String StartPostpaySession() throws IOException, InterruptedException{   
		ApcoaListeners.logInfo("Going to click on TAP TO ACCESS");
		CommonUtility.GenericMethods.explicitWait(driver,startSessionwithpass,30);
		ApcoaListeners.logInfo("Clicked on TAP TO ACCESS");
		removeQR();
		Thread.sleep(4000);
		excCommand("EntryQR");
		Thread.sleep(4000);
		startSessionwithpass.click();
		ApcoaListeners.logInfo("Going to Scan Entry QR");
		CommonUtility.GenericMethods.explicitWait(driver,ClickOnsideBar,30);
		ClickOnsideBar.click();
		CommonUtility.GenericMethods.explicitWait(driver,MenuMySessions,30);
		MenuMySessions.click();
		CommonUtility.GenericMethods.explicitWait(driver,MySessionsSessionID,30);
		ApcoaListeners.logInfo("Postpay Session Started");
		ActiveSessionID=MySessionsSessionID.getText();
		ApcoaListeners.logInfo("Active Session Id :    "+ActiveSessionID);
		CommonUtility.GenericMethods.explicitWait(driver,GoBacktoSideBar,30);
		GoBacktoSideBar.click();
		return ActiveSessionID;
	}

	public void excCommand(String check) throws IOException{
		String command ="cp "+System.getProperty("user.dir")+"/"+check+"/poster.png /home/ritik/Android/Sdk/emulator/resources";
		try{
			Process p = Runtime.getRuntime().exec(command);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	public void removeQR()
	{
		String command ="rm /home/ritik/Android/Sdk/emulator/resources/poster.png";
		try{
			Process p = Runtime.getRuntime().exec(command);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	public String  AddDiscountByQRScan(int promoNum,SoftAssert softAssert) throws InterruptedException, IOException
	{
		if (promoNum==1)
			ApcoaListeners.logInfo("Going to Add Discount by Scanning QR");
		else if(promoNum==2)
		{
			ApcoaListeners.logInfo("Going to Change the Promo by Scanning QR");
		}
		removeQR();
		System.out.println("Remove the QR");
		if(promoNum==1)
			excCommand("DiscountQR");
		else
			excCommand("DiscountQR2");	
		Thread.sleep(4000);
		CommonUtility.GenericMethods.explicitWait(driver,ScanQRButton,30);
		ScanQRButton.click();
		if(promoNum==2)
		{
			CommonUtility.GenericMethods.explicitWait(driver,btnConfirmPromo,50);
			softAssert.assertEquals(PromoName.getText(),"Are you sure you want to remove the discount applied ?");
			btnConfirmPromo.click();
		}
		CommonUtility.GenericMethods.explicitWait(driver,btnConfirmPromo,50);
		btnConfirmPromo.click();
		AppliedPromoByQR=PromoName.getText();
		int idx = AppliedPromoByQR.indexOf(":");
		AppliedPromoByQR = AppliedPromoByQR.substring(idx+2);
		softAssert.assertEquals("OKAY, GOT IT!",btnConfirmPromo.getText());
		btnConfirmPromo.click();
		CommonUtility.GenericMethods.waitAndClick(driver,PromoCheck,50);
		softAssert.assertEquals(PromoCheck.getText(),AppliedPromoByQR);
		ApcoaListeners.logInfo("Promo Applied Successfully     "+AppliedPromoByQR);
		//softAssert.assertAll();
		//TestLogin.AppliedPromo=AppliedPromoByQR;
		return AppliedPromoByQR;
	}
	
	public void StopPostpaySession(String SessionId,String AppliedPromo, SoftAssert softAssert) throws IOException, InterruptedException{
		ActiveSessionID=SessionId;
		ApcoaListeners.logInfo("Going to Exit the Session");
		removeQR();
		Thread.sleep(4000);
		excCommand("ExitQR");
		TaptoExit.click();
		ApcoaListeners.logInfo("Scanning The EXIT QR");
		CommonUtility.GenericMethods.explicitWait(driver,MySessionsSessionID,30);
		ExpiredSessionID=MySessionsSessionID.getText();
		AppliedPromoByQRInExpire=AppliedPromoInExpire.getText();
		ApcoaListeners.logInfo("Past Session Id :    "+ExpiredSessionID);
		System.out.println(ExpiredSessionID);
		System.out.println(ActiveSessionID);
		softAssert.assertEquals(AppliedPromoByQRInExpire,AppliedPromo);
		softAssert.assertEquals(ActiveSessionID,ExpiredSessionID);
		//softAssert.assertAll();
		ApcoaListeners.logInfo("Exit Session Complete");
	}
	
	
	
	public String StopPostpaySessionNew(String SessionId,String AppliedPromo) throws IOException, InterruptedException
	{  SoftAssert SA = new SoftAssert();
		ActiveSessionID=SessionId;
		ApcoaListeners.logInfo("Going to Exit the Session");
		String TotalCost="";
		removeQR();
		Thread.sleep(4000);
		excCommand("ExitQR");
		TaptoExit.click();
		ApcoaListeners.logInfo("Scanning The EXIT QR");
		
		CommonUtility.GenericMethods.explicitWait(driver,MySessionsSessionID,30);
		ExpiredSessionID=MySessionsSessionID.getText();
		if (AppliedPromo=="NULL") {
			TotalCost = MySessionsTotalCost.getText();
			ApcoaListeners.logInfo("Past Session Id :    "+ExpiredSessionID);
			System.out.println(ExpiredSessionID);
			System.out.println(ActiveSessionID);
			System.out.println("No Discounts");
			SA.assertEquals(ActiveSessionID,ExpiredSessionID);
			SA.assertAll();
			
		}else {
			AppliedPromoByQRInExpire=AppliedPromoInExpire.getText();
			TotalCost = MySessionsTotalCost.getText();
			ApcoaListeners.logInfo("Past Session Id :    "+ExpiredSessionID);
			System.out.println(ExpiredSessionID);
			System.out.println(ActiveSessionID);
			SA.assertEquals(AppliedPromoByQRInExpire,AppliedPromo);
			SA.assertEquals(ActiveSessionID,ExpiredSessionID);
			SA.assertAll();
		}
		
		ApcoaListeners.logInfo("Exit Session Complete");
		
		return TotalCost;
		
	}



	
	public String GmpPostpayWithDiscounts(String DiscountToSend) throws InterruptedException {	
//		String DiscountToSend = "SWITPP";
		Thread.sleep(2000);
		if(DiscountToSend=="NULL") {
			System.out.println("No Discounts");
		}else {
			ApcoaListeners.logInfo("Going to  Click on Add Validations ");
			PromoCheck.click();
			Thread.sleep(4000);
			ApcoaListeners.logInfo("Going to Add Discount Coupon");
			btnclikAddPromo.sendKeys(DiscountToSend);;
			Thread.sleep(4000);
			ApplyPromoBtn.click();
		}
		
		return DiscountToSend;	
	}









}
