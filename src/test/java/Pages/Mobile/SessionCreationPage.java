package Pages.Mobile;

import java.time.Duration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import CommonUtility.AutomationConfiguration;
import MobileObjectMapper.ParkingMapper;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SessionCreationPage {

	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	TouchAction action;
	public String Parkingprice;
	public String ActualInitialParkingPrice;
	public String ActualParkingHour;
	public String ActualParkingMin;
	public String ActualPaymentConfirmMsg;
	public String ActualPaymentSuccess;
	public String ActualSessionEndMsg;
	public String ActualSessionEndSuccessmsg;
	public String ActualExtendedParkingPrice;
	public String ActualParkingName;
	public String ExpectedPaymentConfirmMsg="You will be charged " +Parkingprice+ " before the session starts";
	public String ActiveSessionID;
	public String ActiveSessionCost;
	public String ExpiredSessionID;
	public String ExpiredSessionCost;

	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.ImageView[1]")
	private WebElement btnMenu;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_sessions')]")
	private WebElement MenuMySessions;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_session_id')]")
	private WebElement MySessionsSessionID;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_cost')]")
	private WebElement MySessionsTotalCost;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc='Expired']")
	private WebElement MySessionsGotoExpiredSessions;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc=\"Past\"]/android.widget.TextView")//here
	private WebElement GMPMySessionsGotoExpiredSessions;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	private WebElement ParkingSearchbartv;

	@AndroidFindBy(xpath="(//android.widget.RelativeLayout)[7]")
	private WebElement ParkingSearchbar;  

	@AndroidFindBy(xpath=".//android.widget.EditText[contains(@resource-id,':id/edt_Search')]")
	private WebElement searchParking;

	@AndroidFindBy(xpath="(//android.widget.LinearLayout)[4]")
	private WebElement selectParking;

	@AndroidFindBy(xpath="(//android.view.ViewGroup)[2]")
	private WebElement selectParking2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_setting_parent')]")
	public WebElement scrollStartPointInMap;

	@AndroidFindBy(xpath="(//android.view.View)[5]") 
	public WebElement clickOnParking;

	@AndroidFindBy(xpath="(//android.view.View)[10]")
	public WebElement clickOnParkingGermany;

	@AndroidFindBy(xpath="(//android.view.View)[4]") 
	public WebElement clickOnParkingAustria_Poland;    //zamek ujazdowski

	@AndroidFindBy(xpath="(//android.view.View)[18]")
	public WebElement clickOnParkingItaly; 

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_display_name')]")
	private WebElement parkingDetailScreen;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_flow_benefits_header')]")
	private WebElement scrollStartPoint;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/vehicle_type_header')]")
	private WebElement scrollEndPoint;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_favourite')]")
	private WebElement SetfavParking;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session_with_buy_pass')]")
	private WebElement startSessionwithpass;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_price_with_units')]")
	private WebElement ParkingPrice;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_hours')]")
	private WebElement ParkingHour;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_minutes')]")
	private WebElement ParkingMinute;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/csb_time_dialer')]")
	private WebElement clickOnDialer;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_message')]")
	private WebElement paymentConfirmMsg;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm_pay')]")
	private WebElement payAmount; 

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_success_heading')]")
	private WebElement paySuccessMsg;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close')]")
	private WebElement closePayment;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_login')]")
	private WebElement Login;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/email')]")
	private WebElement email;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_password')]")
	private WebElement password;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_continue')]")
	private WebElement continuelogin;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_stop_parking_session')]")
	private WebElement stopSession;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_desc')]")
	private WebElement SessionStopMsg;  

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm')]")
	private WebElement SessionStopConfirm;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_success_sub_heading')]")
	private WebElement SessionEndSuccessMsg; 

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_extend_parking_session')]")
	private WebElement ExtendSession;

	@AndroidFindBy(xpath=".//android.widget.TextView[contains(@resource-id,':id/tv_garage_access_door')]")
	private WebElement opengaragedoor;

	@AndroidFindBy(xpath="(.//android.widget.RelativeLayout[contains(@resource-id,':id/rl_garage_door_item')])[1]")
	private WebElement selectFirstdoor;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_action_button')]")
	private WebElement openaccessdoor;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_action_button')]")
	private WebElement confirmpaymentforextendsession;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_negative_action_button')]")
	private WebElement goback;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_start_session')]")
	private WebElement StartSession;

	@AndroidFindBy(xpath="(.//android.widget.ImageView[contains(@resource-id,':id/iv_selected_mode')])[2]")
	private WebElement SelectSecondTariffMode;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ub_page_button_cancel')]")
	private WebElement feedbackPopUp;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_area_code_search')]")
	private WebElement SearchAreaCode;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_search')]")
	private WebElement AreaCodeSearchBar;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cl_parent')]")
	private WebElement Selectparking;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_select_parking_lot')]")
	private WebElement SelectparkingLot;

	@AndroidFindBy(xpath="(//android.widget.ImageView[contains(@resource-id,':id/iv_selected_mode')])[2]")
	private WebElement SelectSecondLot;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session')]")
	private WebElement StartSessionafterSelectinglot;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_header')]")
	public WebElement DetailScreenscrollStartPoint;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/service_header')]")
	public WebElement DetailScreenscrollEndPoint;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	private WebElement ClickOnsideBar;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_sessions')]")
	private WebElement My_Sessions;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_name')]")
	private WebElement ActiveParking;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_back')]")
	private WebElement GoBacktoSideBar;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_view_parking_session')]")
	private WebElement emptyActiveSession;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_extend_session_option')]")
	private WebElement btnselectfirsttariff;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session')]")
	private WebElement gmpstartsession;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[3]")
	private WebElement tarrifSelection;
//	(//*[contains(@resource-id,':id/cl_nearby_parking')]//android.widget.LinearLayout)[3]
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/cl_nearby_parking')]//android.widget.LinearLayout)[3]")
	private WebElement openstartsession;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[2]")
	private WebElement bactarrifSelection;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[4]")
	private WebElement oneParkingtarrifSelection;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[4]")
	private WebElement gmptarrifSelection;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[3]")
	private WebElement elitetarrifSelection;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_park_now')]")
	private WebElement btnParkNow;

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SessionCreationPage(AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait=new WebDriverWait(driver, 100);
		action=new TouchAction((PerformsTouchActions) driver);
	}
	
	public void GotoMyActiveSessions() {
		try{
			Thread.sleep(2000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,100);
			btnMenu.click();

			Thread.sleep(2000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MenuMySessions,100);
			MenuMySessions.click();
			Thread.sleep(2000);

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsSessionID,100);
			ActiveSessionID = MySessionsSessionID.getText();
			System.out.println("Active Session ID: "+ ActiveSessionID);

			action.press(PointOption.point(505, 1870)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(528, 784)).release().perform();
			Thread.sleep(3000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsTotalCost,100);
			ActiveSessionCost = MySessionsTotalCost.getText();
			System.out.println("Active Session cost : "+ ActiveSessionCost);
		}catch(Exception e){
			System.out.println("PaymentConfirmation end error: "+e.toString());

		}
	}

	public void GotoMyExpiredSessions() {
		try{
			Thread.sleep(5000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,100);
			btnMenu.click();
			Thread.sleep(3000);

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MenuMySessions,100);
			MenuMySessions.click();
			Thread.sleep(8000);
			if(AutomationConfiguration.Tenant.equalsIgnoreCase("GMP")) {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,GMPMySessionsGotoExpiredSessions,30);
				GMPMySessionsGotoExpiredSessions.click();
				Thread.sleep(3000);
			}else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsGotoExpiredSessions,30);
				MySessionsGotoExpiredSessions.click();
				Thread.sleep(3000);
			}
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsSessionID,100);
			ExpiredSessionID = MySessionsSessionID.getText();
			System.out.println("Expired Session ID: "+ ExpiredSessionID);

			Thread.sleep(3000);
			ExpiredSessionCost = MySessionsTotalCost.getText();
			System.out.println("Expired Session Cost: "+ ExpiredSessionCost);
		}catch(Exception e){
			System.out.println("PaymentConfirmation end error: "+e.toString());
		}
	}

	public void GettheParking(String ParkingName){
		try{
			ApcoaListeners.logInfo("GettheParking start");

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingSearchbar,30);
			ParkingSearchbar.click();

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,searchParking,30);
			searchParking.sendKeys(ParkingName);

			Thread.sleep(8000);
			boolean temp = selectParking2.isDisplayed();
			if(temp){
				selectParking2.click();
			}else{
				selectParking.click(); 
			}

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingSearchbar,30);
			ApcoaListeners.logInfo("parking name: "+ParkingSearchbartv.getText());
			ActualParkingName = ParkingSearchbartv.getText();
			ApcoaListeners.logInfo("GettheParking  end");
		}catch(Exception e){
			ApcoaListeners.logInfo("GettheParking start: error:"+ e.toString());
		}
	}

	public void scrolldown(WebElement pageStart, WebElement PageEnd){
		try{
			pageStart.isDisplayed();

			Thread.sleep(3);
			Dimension dim= driver.manage().window().getSize();
			int x=dim.getWidth()/2;
			int startY=(int) (dim.getHeight()*0.2);
			int endY=(int) (dim.getHeight()*0.8);
			action.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, endY)).release().perform();
			boolean scrolled=PageEnd.isDisplayed();
			Thread.sleep(4000);
			System.out.println(scrolled);
		}catch(Exception e) {
			e.printStackTrace(); 
		}

	}

	public void scrollUp(WebElement pageStart, WebElement PageEnd){
		try{
			pageStart.isDisplayed();
			Thread.sleep(3);
			Dimension dim= driver.manage().window().getSize();
			int x=dim.getWidth()/2;
			int startY=(int) (dim.getHeight()*0.8);
			int endY=(int) (dim.getHeight()*0.2);
			action.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, endY)).release().perform();
			boolean scrolled=PageEnd.isDisplayed();
			Thread.sleep(4000);
			System.out.println(scrolled);
		}catch(Exception e) {
			e.printStackTrace(); 
		}

	}

	public void favPark(){
		try{
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnParkingGermany,100);	
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,parkingDetailScreen,100);	  
			SetfavParking.click();
		}catch(Exception e) {
			e.printStackTrace(); 
		}

	}

	
	
	
	public void dialerMovement(String Country, ParkingMapper parkingMapper) {
		try{
			ApcoaListeners.logInfo("Dialer Movement for start Session: start");
			ApcoaListeners.logInfo("Country: "+Country);
			Thread.sleep(15000);
			if(AutomationConfiguration.Environment.equalsIgnoreCase("Production") && AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {
				action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(602, 1444)).release().perform();
			}else {
				if(Country.equalsIgnoreCase("Sweden")){
					dailerRotationControl(25);
					//action.press(PointOption.point(361, 891)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(520, 1165)).release().perform();	
				}else  if(Country.equalsIgnoreCase("Austria")){
					dailerRotationControl(25);
					//action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(847, 1640)).release().perform();
				}else if(Country.equalsIgnoreCase("Poland")) {
					//action.press(PointOption.point(538, 1173)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(689, 1653)).release().perform(); //emulator
					dailerRotationControl(25);
					//action.press(PointOption.point(359, 891)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(532, 1136)).release().perform();
				}else if(Country.equalsIgnoreCase("India")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("Brazil")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("Dubai")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("United States")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("United States Elite")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("New India")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("Australia")){
				    dailerRotationControl(25);
				}else{
					dailerRotationControl(25);
				}
			}
			ApcoaListeners.logInfo("Dialer Movement for start Session: ends");
			if(AutomationConfiguration.Environment.equalsIgnoreCase("Production") && AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {
				PageBuyPass pbp = new PageBuyPass(driver);
				pbp.applyPromoCode("REGTESTAT");
			}
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingPrice,30);
			Parkingprice= ParkingPrice.getText();
			String currency=parkingMapper.getCurrencysymbol();
			int len=currency.length();
			ActualInitialParkingPrice = Parkingprice.substring(len);
			
			Thread.sleep(3000);
			ActualParkingHour=ParkingHour.getText();
			ActualParkingMin=ParkingMinute.getText();

			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnDialer,100);
			ApcoaListeners.logInfo("dialerMovement end");
		}catch(Exception e){
			ApcoaListeners.logInfo("dialerMovement end error: "+e.toString());
		}
	}

	public void ExtendPaymentConfirmation(){
		try{
			ApcoaListeners.logInfo("ExtendPaymentConfirmation start");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,closePayment,100);
			closePayment.click();
			ApcoaListeners.logInfo("ExtendPaymentConfirmation end: ");
		}catch(Exception e){
			ApcoaListeners.logInfo("ExtendPaymentConfirmation end error: "+e.toString());
		}
	}

	public void PaymentConfirmation() {
		try{
			ApcoaListeners.logInfo("PaymentConfirmation start");

			Thread.sleep(5000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,payAmount,30);
			ApcoaListeners.logInfo("payAmount.getText(): "+payAmount.getText());
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,payAmount,15);

			Thread.sleep(10000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,paySuccessMsg,100);
			ActualPaymentSuccess=paySuccessMsg.getText();
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,closePayment,100);
			closePayment.click();
			ApcoaListeners.logInfo("PaymentConfirmation end: ActualPaymentSuccess " + ActualPaymentSuccess);
		}catch(Exception e) {
			ApcoaListeners.logInfo("PaymentConfirmation end error: "+e.toString());
		}
	}

	public void StopSession() {
		try{
			ApcoaListeners.logInfo("StopSession start");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,stopSession,100);
			stopSession.click();
			ActualSessionEndMsg=SessionStopMsg.getText();

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,SessionStopConfirm,100);
			SessionStopConfirm.click();
			Thread.sleep(8000);
			((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
			//ActualSessionEndSuccessmsg=SessionEndSuccessMsg.getText();
			//closePayment.click();
			ApcoaListeners.logInfo("StopSession end");
		}catch(Exception e){
			ApcoaListeners.logInfo("StopSession end error: "+e.toString());
		}
	}

	public void ExtendSession(String Country, ParkingMapper parkingMapper){
		try{
			ApcoaListeners.logInfo("Extend Session start");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ExtendSession,100);
			ExtendSession.click();
			Thread.sleep(8000);
			ApcoaListeners.logInfo("Extend Session Dialer Movement start:");
			ApcoaListeners.logInfo("Country: " +Country);
			if(AutomationConfiguration.Environment.equalsIgnoreCase("Production") && AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {
				action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(602, 1444)).release().perform();
			}else {
				if(Country.equalsIgnoreCase("Austria")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("India")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("New India")){
					 dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("Italy")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("Brazil")){
					dailerRotationControl(100);
				}else if(Country.equalsIgnoreCase("Australia")){
					 dailerRotationControl(25);
			    }else if(Country.equalsIgnoreCase("United States")){
					dailerRotationControl(100);		
				}else if(Country.equalsIgnoreCase("United States Elite")){
					dailerRotationControl(50);		
				}else if(Country.equalsIgnoreCase("Dubai")){
	            	dailerRotationControl(100);
	            }else if(Country.equalsIgnoreCase("Poland")){
					dailerRotationControl(25);
				}else {
					dailerRotationControl(25);
				}
			}
			ApcoaListeners.logInfo("Extend Session Dialer Movement ends:");
			Thread.sleep(8000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingPrice,100);
			Parkingprice= ParkingPrice.getText();
			//ActualExtendedParkingPrice=Parkingprice.substring(1);
			String currency=parkingMapper.getCurrencysymbol();
			int len=currency.length();
			ActualExtendedParkingPrice=Parkingprice.substring(len);
			
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnDialer,100);
			ApcoaListeners.logInfo("ExtendSession end:Parkingprice " + Parkingprice );

			ApcoaListeners.logInfo("ExtendSession end: ActualExtendedParkingPrice" + ActualExtendedParkingPrice );
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,confirmpaymentforextendsession,100);
			confirmpaymentforextendsession.click();
		}catch(Exception e){
			ApcoaListeners.logInfo("ExtendSession end error: "+e.toString());
		}
	}

	public void OpenGarageDoor() {
		try{
			opengaragedoor.click();
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,selectFirstdoor,100);
			openaccessdoor.click();
			goback.click();
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	public void Startsession(String Country){
		try{
			ApcoaListeners.logInfo("Startsession start");
			if(Country.equalsIgnoreCase("Italy")){
				Thread.sleep(4000);
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnParkingItaly,100);	
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,StartSession,100);
			}else if(Country.equalsIgnoreCase("Austria")) {
				ApcoaListeners.logInfo("Startsession start Austria");
				Thread.sleep(8000);
				//CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnParking,100);
				// GenericMethods.explicitWait1(startSessionwithpass);
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,StartSession,100);
			}else if(Country.equalsIgnoreCase("Poland")){
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnParking,100);
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver, StartSession,100);
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,SelectSecondTariffMode,100);
			}else{
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnParking,100);
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,StartSession,100);
			}
			ApcoaListeners.logInfo("Startsession end");
		}catch(Exception e){
			ApcoaListeners.logInfo("Startsession end error: "+e.toString());
		}
	}

	public void StartsessionforParkingwithPass() throws InterruptedException{
		ApcoaListeners.logInfo("StartsessionforParkingwithPass start");
		if(AutomationConfiguration.Environment.equalsIgnoreCase("Production")) {
			ApcoaListeners.logInfo("Scroll page starts");
			Thread.sleep(5000);	
			PageBuyPass.scrollPage();
			ApcoaListeners.logInfo("Scroll page ends");
		}
		if(AutomationConfiguration.Tenant.equalsIgnoreCase("Elite")) {		
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,tarrifSelection,100);
		}
		
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("GMP")) {	
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmptarrifSelection,100);
		}else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {	
			Thread.sleep(5000);
			try {
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,startSessionwithpass,100);
			}catch(Exception e) {}
			try {
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,startSessionwithpass,30);
			}catch(Exception e) {}
			try {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,btnselectfirsttariff,30);	
			}catch(Exception e) {}
		}else if(AutomationConfiguration.Tenant.equalsIgnoreCase("OneParking")) {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,oneParkingtarrifSelection,100);
		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("BAC")) {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,bactarrifSelection,100);
	
		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("GarageInn")) {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
		//	CommonUtility.GenericMethods.explicitWaitForWebElement(driver,tarrifSelection,100);
	
		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Reef")) {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
		//	CommonUtility.GenericMethods.explicitWaitForWebElement(driver,tarrifSelection,100);
	
		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("GreenParking")) {
			
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,btnParkNow,100);
	
		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Elite")) {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,elitetarrifSelection,100);
	
		}
		ApcoaListeners.logInfo("StartsessionforParkingwithPass end");
	}

	public void StartSessionforParkingwithTariff(){
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,SelectSecondTariffMode,100);
	}

	public void SelectParkinglot_startSession() {
		try{
			SelectparkingLot.click();
			SelectSecondLot.click();
			int attempts = 0;
			while(attempts < 2) {
				try {
					SelectSecondLot.click();
					break;
				} 
				catch(Exception e) 
				{}
				attempts++;
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
	}

	public void SearchParkingUsingAreaCode(String areacode) {
		try{
			SearchAreaCode.click();
			AreaCodeSearchBar.sendKeys(areacode);
			Selectparking.click();

		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	public void DailerMovement_AutoCheckout(int x,int y,int a,int b) {
		try {
			Thread.sleep(10000);
			action.press(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(a, b)).release().perform();
			//action.press(PointOption.point(538, 1169)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(612, 1181)).release().perform();
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnDialer,100);
			payAmount.click();
			ActualPaymentSuccess=paySuccessMsg.getText();
			closePayment.click();
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	public void ActiveSessions_List() {
		try {
			ClickOnsideBar.click();
			My_Sessions.click();
			Thread.sleep(7000);
			boolean SessionGoingOn=ActiveParking.isDisplayed();
			while(SessionGoingOn) {
				GoBacktoSideBar.click();
				ClickOnsideBar.click();
				My_Sessions.click();
				Thread.sleep(5000);
				SessionGoingOn=ActiveParking.isDisplayed();
				//    	if(ActiveParking.isDisplayed()){
				//    	
				//    	System.out.println(SessionGoingOn);
				//    }
			}
		}catch(Exception e){
			e.printStackTrace(); 
			if(emptyActiveSession.isDisplayed()) {
				System.out.println("Auto checkout is successfull");
			}
		}
	}
	
	public void dailerRotationControl(int perc) throws InterruptedException
	{
	  if(perc==25)
	  {
		  action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(849, 1646)).release().perform();
		  Thread.sleep(2000);
      }else if(perc==50)
      {
    	  dailerRotationControl(25);
    	  action.press(PointOption.point(849, 1646)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(545, 1968)).release().perform();
    	  Thread.sleep(2000);
      }
      else if(perc==75)
      {
    	  dailerRotationControl(50);
    	  action.press(PointOption.point(545, 1968)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(206, 1643)).release().perform();
    	  Thread.sleep(2000);
      }
      else if(perc==100)
      {
    	  dailerRotationControl(75);
    	  action.press(PointOption.point(206, 1643)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(528, 1314)).release().perform();
    	  Thread.sleep(2000);
      }
	}
}