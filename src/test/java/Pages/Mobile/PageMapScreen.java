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
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PageMapScreen {

	WebDriver driver;
	public String ActualParkingName = "";
	public String FullParkingName = "None";
	public String ActualSessionEndMsg = "";

	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='icn target']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	private WebElement clearlocationbtn;	


	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='Where do you want to park?']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	private WebElement ParkingSearchbar2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	private WebElement ParkingSearchbartv;

	@AndroidFindBy(xpath="(//android.widget.RelativeLayout)[7]")
	private WebElement ParkingSearchbar;  

	@iOSXCUITFindBy(xpath="//XCUIElementTypeTextField")
	@AndroidFindBy(xpath=".//android.widget.EditText[contains(@resource-id,':id/edt_Search')]")
	private WebElement searchParking;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeCell")
	@AndroidFindBy(xpath="(//android.widget.LinearLayout)[4]")
	private WebElement selectParking;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeCell")
	@AndroidFindBy(xpath="(//android.view.ViewGroup)[2]")
	private WebElement selectParking2;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'START') or contains(@name,'Start')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session')]")
	private WebElement gmpstartsession;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[3]")
	private WebElement tarrifSelection;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[2]")
	private WebElement bactarrifSelection;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[4]")
	private WebElement oneParkingtarrifSelection;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[1]")
	private WebElement gmptarrifSelection;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='Standardtarif']/parent:: XCUIElementTypeCell/XCUIElementTypeImage")
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[4]")
	private WebElement gmpAustriatarrifSelection;

	@iOSXCUITFindBy(xpath="//*[@name='Park Now']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_park_now')]")
	private WebElement btnParkNow;


	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[3]")
	private WebElement elitetarrifSelection;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='STOP']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_stop_parking_session')]")
	private WebElement stopSession;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'Session') or contains(@name,'Payment')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_desc')]")
	private WebElement SessionStopMsg;  

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='CONFIRM']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm')]")
	private WebElement Confirm;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='Session Ended']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_success_sub_heading')]")
	private WebElement SessionEndSuccessMsg;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='icn close']")
	private WebElement closepaymentIOS;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='filter icon']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close')]")
	private WebElement closePayment;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='EV Charging']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_ev_charging_buy_pass')]")
	WebElement btnEVcharging;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='green cross icon']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_proceed')]")
	WebElement btnProceed;

	public PageMapScreen(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}


	/**
	 * method to search for specific parking
	 *
	 * @param ParkingName is the parking name
	 */
	public String GettheParking(String ParkingName){
		try{
			ActualParkingName = "";
			ApcoaListeners.logInfo("GettheParking start");

			if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
				CommonUtility.GenericMethods.waitForElement(driver,ParkingSearchbar2,30);
				ParkingSearchbar2.click();				
			}else {
				for(int i=0;i<1;i++) {
					try {
						GenericMethods.tapByElement(CreateSession.getAutomationConfiguration().AppiumDriver,clearlocationbtn);
					}catch(Exception e) {}
				}

				for(int i=0;i<3;i++) {
					try {
						GenericMethods.tapByElement(CreateSession.getAutomationConfiguration().AppiumDriver,ParkingSearchbar2);
					}catch(Exception e) {}
				}
			}
			CommonUtility.GenericMethods.waitForElement(driver,searchParking,30);
			searchParking.sendKeys(ParkingName);

			Thread.sleep(6000);
			boolean temp = selectParking2.isDisplayed();
			if(temp){
				selectParking2.click();
			}else{
				selectParking.click(); 
			}



			Thread.sleep(4000);
			//check for EV charging pop up
			try {
				CommonUtility.GenericMethods.explicitWait(driver,btnProceed,7);
				btnProceed.click();
			}catch(Exception e) {}


			if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
				CommonUtility.GenericMethods.waitForElement(driver,ParkingSearchbar,30);
				ApcoaListeners.logInfo("parking name: "+ParkingSearchbartv.getText());
				ActualParkingName = ParkingSearchbartv.getText();
			}
			else {
				WebElement parkingname = null;
				if(this.FullParkingName.equalsIgnoreCase("None")){
					parkingname =CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+ParkingName+"')]"));
				}else {
					parkingname =CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+this.FullParkingName+"')]"));
				}
				ApcoaListeners.logInfo("parking name: "+parkingname.getText());
				ActualParkingName = parkingname.getText();
			}
			ApcoaListeners.logInfo("GettheParking  end");
			return ActualParkingName;
		}catch(Exception e){

			ApcoaListeners.logInfo("GettheParking start: error:"+ e.toString());
			return "";
		}
	}

	/**
	 * method to select specific tariff in IOS
	 *
	 * @param tariffname is the name of the tariff
	 */
	public void tariffSelctionIos(String tariffname) throws InterruptedException {
		try {
			String xpath = "";
			if(tariffname.equalsIgnoreCase("Null") || tariffname.length()==0) {
				xpath = "(//XCUIElementTypeImage)[last()]";
			}else {
				xpath="//XCUIElementTypeStaticText[contains(@name,'"+tariffname+"')]/parent:: XCUIElementTypeCell/XCUIElementTypeImage";
			}
			Thread.sleep(5000);
			CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath(xpath)).click();
		}catch(Exception e) {
			System.out.println("Error in tariff Selection: " + e.toString());
		}
	}

	/**
	 * method to select specific tariff in Android
	 *
	 * @param tariffname is the name of the tariff
	 */
	public void tariffSelectionAndroid(String tariffname) {
		try {
			List<WebElement> tname = CreateSession.getAutomationConfiguration().AppiumDriver.findElements(By.xpath("//*[contains(@resource-id,'id/tv_tariff_type')]"));
			List<WebElement> tbtn = CreateSession.getAutomationConfiguration().AppiumDriver.findElements(By.xpath("//*[contains(@resource-id,':id/iv_selected_mode')]"));
			int flag = 0;
			for(int i=0;i<tname.size();i++) {
				System.out.println(tname.get(i).getText());
				if(tariffname.equalsIgnoreCase("Null") || tariffname.length()==0) {
					tbtn.get(i).click();
					flag = 1;
					break;
				}
				if(tname.get(i).getText().contains(tariffname)) {
					System.out.println("Tariffname found");
					tbtn.get(i).click();
					flag = 1;
					break;
				}
			}
			if(flag==0) {
				System.out.println("Tariff not found" + tariffname);
			}
		}catch(Exception e) {
			System.out.println("Error in tariff Selection: " + e.toString());
		}
	}

	/**
	 * method to click on start button and select tariff
	 *
	 * @param tariffname is the name of the tariff
	 */
	public void StartsessionforParkingwithPass(String tariffname) throws InterruptedException{

		Thread.sleep(2000);
		ApcoaListeners.logInfo("StartsessionforParkingwithPass start");
		
		
		if(CreateSession.getAutomationConfiguration().Environment.equalsIgnoreCase("Production")) {
			ApcoaListeners.logInfo("Scroll page starts");
			Thread.sleep(1000);	
			PageBuyPass.scrollPage();
			ApcoaListeners.logInfo("Scroll page ends");
			Thread.sleep(2000);
		}

		//click on start session button
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
			CommonUtility.GenericMethods.waitForElementAndClick(driver,gmpstartsession,30);
		}else {
			try {CommonUtility.GenericMethods.waitForElement(driver,gmpstartsession,30);}catch(Exception e) {}
			try {GenericMethods.tapByElement(CreateSession.getAutomationConfiguration().AppiumDriver,gmpstartsession);}catch(Exception e) {}
		}



		/*
	    // Handling Park Now Button
		//btnParkNow
		try {
			CommonUtility.GenericMethods.waitForElementAndClick(driver,btnParkNow,10);
		}catch(Exception e) {

		}
		 */

		//tariff selection 
		this.selectTariff(tariffname);

		ApcoaListeners.logInfo("StartsessionforParkingwithPass end");
	}


	public void selectTariff(String tariffname) throws InterruptedException {
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
			this.tariffSelctionIos(tariffname);
		}else {
			this.tariffSelectionAndroid(tariffname);
		}	
	}
	/**
	 * method to stop session and check payment confirmation page
	 *
	 * @param parkingtype is type of parking
	 */
	public void StopSession(String parkingtype) {
		try{
			ApcoaListeners.logInfo("StopSession start");
			CommonUtility.GenericMethods.waitForElement(driver,stopSession,30);
			stopSession.click();
			ApcoaListeners.logInfo("Click on Stop button successfully.");
			/*
			try {
				if(!parkingtype.equalsIgnoreCase("postpay"))
					ActualSessionEndMsg = SessionStopMsg.getText();
			}catch(Exception e){}
			 */
			CommonUtility.GenericMethods.waitForElement(driver,Confirm,30);
			Confirm.click();
			ApcoaListeners.logInfo("Click on confirm button successfully.");
			Thread.sleep(4000);
			try {
				PageAddVehicle av= new PageAddVehicle(CreateSession.getAutomationConfiguration().AppiumDriver);
				if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")){
					av.goBack();
				}else {
					av.goback();
				}
			}catch(Exception e) {}

			//get msg and try to close the POP up
			try {
				try {
					CommonUtility.GenericMethods.waitForElement(driver,SessionStopMsg,30);
					ActualSessionEndMsg = SessionStopMsg.getText();
				}catch(Exception e){}

				try {
					if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
						Actions action = new Actions(CreateSession.getAutomationConfiguration().AppiumDriver);
						action.moveToElement(closepaymentIOS).doubleClick().perform();
					}else {
						closePayment.click();
					}
				}catch(Exception e) {}
				try {
					Actions action = new Actions(CreateSession.getAutomationConfiguration().AppiumDriver);
					action.moveToElement(closepaymentIOS).doubleClick().perform();
				}catch(Exception e) {}

			}catch(Exception e){}

			ApcoaListeners.logInfo("StopSession end");
		}catch(Exception e){
			ApcoaListeners.logInfo("StopSession end error: "+e.toString());
		}
	}


	public SoftAssert EpmpCheck()throws InterruptedException{
		SoftAssert softAssert = new SoftAssert();
		String[] EpmpParkingIdentifier= new String[] {"3040","84010","6495","46010","2891","46079","7001","2932","32872","84012","84013","84011","8401","72600","7255","2780","2790","84016","712"};
		String[] EpmpParkingName = new String[] {"Milleniumgaraget (Park & Go)","Centralsjukhuset parkeringsgarage P60","Kolmårdens Djurpark","Göteborg Centralstation (Park & Go)","P-hus Malmö C","Stockholms Centralstation","P-hus City, Göteborg","Skånes djurpark","Kista One","Centralsjukhuset P6","Centralsjukhuset P8","Centralsjukhuset Parkering P5","Centralsjukhuset parkeringshus P12","Frölunda Torg (Park & Go)","Solkatten (Park & Go)","Turning Torso","Malmö Entré","Centralsjukhuset Älvgatan P7","Platinan 1"};

		for(int i=0;i<EpmpParkingIdentifier.length;i++){
			this.GettheParking(EpmpParkingIdentifier[i]);
			softAssert.assertEquals(EpmpParkingName[i], this.ActualParkingName, "Parking Not Found: "+EpmpParkingName[i]);
			softAssert.assertEquals(this.checkEpmpStartButton(EpmpParkingName[i]),"True" , "Test-FAILED: "+EpmpParkingName[i]);
		}

		return softAssert;
	}

	public String checkEpmpStartButton(String Parking)throws InterruptedException{  
		Thread.sleep(5000);
		ApcoaListeners.logInfo("Checking for Start Button in EPMP");
		try {
			if (gmpstartsession.isDisplayed()){
				ApcoaListeners.logInfo("Start Session Button Found -->"+Parking);
				return "False";
			}else{
				ApcoaListeners.logInfo("Start Session Button Not Found -->"+Parking);
				return "True";
			}
		}
		catch (Exception e){
			ApcoaListeners.logInfo("Start Session Button Not Found -->"+Parking);
			return "True";
		}
	}


	public String checkEVChargingButton(String Parking)throws InterruptedException{

		ApcoaListeners.logInfo("Checking for EV Charging Button in EPMP");

		try {
			System.out.println("checking isDisplayed: "+btnEVcharging.isDisplayed());
			System.out.println("checking isEnabled: "+btnEVcharging.isEnabled());

			if (CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android") && btnEVcharging.isDisplayed()){
				ApcoaListeners.logInfo("EV Charging Button Found -->"+Parking);
				return "True";
			}else if (CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS") && btnEVcharging.isEnabled()) {
				ApcoaListeners.logInfo("EV Charging Button Found -->"+Parking);
				return "True";
			}else{
				ApcoaListeners.logInfo("EV Charging Button Not Found -->"+Parking);
				return "False";
			}
		}catch (Exception e){
			ApcoaListeners.logInfo("EV Charging Button Not Found -->"+Parking);
			return "False";
		}

	}



}
