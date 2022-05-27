package Mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import CommonUtility.CreateSession;
import CommonUtility.GenericMethods;
import DataDriven.ExcelDriven;
import Mobile.ObjectMapper.BuyPassMapper;
import Mobile.ObjectMapper.CorporateProfileMapper;
import Mobile.ObjectMapper.FreeParkingMapper;
import Mobile.ObjectMapper.LoginMapper;
import Mobile.ObjectMapper.OperationalHrsMapper;
import Mobile.ObjectMapper.ParkingMapper;
import Mobile.ObjectMapper.VehicleMapper;
import Pages.Mobile.LaunchQrReader;
import Pages.Mobile.OperationalHrs;
import Pages.Mobile.PageAddVehicle;
import Pages.Mobile.PageBuyPass;
import Pages.Mobile.PageHomeApcoa;
import Pages.Mobile.PageLogin;
import Pages.Mobile.PageMapScreen;
import Pages.Mobile.PagePaymentScreen;
import Pages.Mobile.PagePersonalDetails;
import Pages.Mobile.PageSelectCountry;
import Pages.Mobile.PageSessionDetails;
import Pages.Mobile.PageStartSession;
import Pages.Mobile.QRScanPage;
import TestNGListeners.ApcoaListeners;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SmokePnd {

	WebDriver driver;
	float finalcalculatedprice = 0;
	PageStartSession pss = null;
	PageSessionDetails psd = null;
	float activesessionid = 0;
	float activesessionprice = 0 ;
	String AmountPaid = "";

	/**
	 * method to initialize the appium and launch application
	 *  
	 */
	@Parameters({ "Environment", "Country","Tenant","Platform" })
	@BeforeClass
	public void initializeDriver(String ennv, String country,String tenant, String platform) throws IOException{
		//AutomationConfiguration.Tenant = tenant;
		//AutomationConfiguration.Environment = ennv;
		//AutomationConfiguration.Country = country;
		//AutomationConfiguration.Platform = platform;
		//AutomationConfiguration.extent.setSystemInfo("Tenant",AutomationConfiguration.Tenant);
		//AutomationConfiguration.extent.setSystemInfo("Environment", AutomationConfiguration.Environment);
		//AutomationConfiguration.extent.setSystemInfo("Country", AutomationConfiguration.Country);
		//AutomationConfiguration.extent.setSystemInfo("Platform", AutomationConfiguration.Platform);
		CreateSession cs = new CreateSession();
		cs.readConfigFile("/src/test/java/resources/Mobile/ConfigFiles/"+tenant+".properties",platform);
		CreateSession.getAutomationConfiguration().Country = country;
		CreateSession.getAutomationConfiguration().Tenant = tenant;
		CreateSession.getAutomationConfiguration().Environment = ennv;
		CreateSession.getAutomationConfiguration().Platform = platform;
	}

	/**
	 * method to end the automation
	 *
	 */
	@AfterClass
	public void Teardown()
	{
		CreateSession.getAutomationConfiguration().AppiumDriver.quit();
	}

	/**
	 * method to give data for login
	 *
	 * @return object that contains the user credentials
	 */
	@DataProvider
	public LoginMapper[] getLoginData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/Mobile/Dataset/"+CreateSession.getAutomationConfiguration().Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Country,"Login");	
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);

		return login;	
	}

	/**
	 * method to give data for vehicle addition and deletion
	 * 
	 * @return object that contains the vehicle lpr data
	 */
	@DataProvider
	public VehicleMapper[] getVehicleData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/Mobile/Dataset/"+CreateSession.getAutomationConfiguration().Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Country,"Add Vehicle");
		ObjectMapper mapper = new ObjectMapper();
		VehicleMapper []vehicle = new VehicleMapper[1];
		vehicle[0] = mapper.readValue(data, VehicleMapper.class);

		return vehicle;	
	}

	/**
	 * method to data for making session and extending session
	 * 
	 * @return Object that contains information of parking and other related stuffs.
	 */
	@DataProvider
	public ParkingMapper[] getParkingData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/Mobile/Dataset/"+CreateSession.getAutomationConfiguration().Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Country,"Session");		
		ObjectMapper mapper = new ObjectMapper();
		ParkingMapper []parking = new ParkingMapper[1];
		parking[0] = mapper.readValue(data, ParkingMapper.class);
		return parking;	
	}



	/**
	 * method to Select country from the select country page
	 * 
	 */
	@Test(priority=1)
	public void selectCountry() throws Exception{
		System.out.println(CreateSession.getAutomationConfiguration().AppiumDriver.getPageSource());
		Thread.sleep(2000);
		PageSelectCountry selectcountry = new PageSelectCountry(CreateSession.getAutomationConfiguration().AppiumDriver);
		SoftAssert softAssert = new SoftAssert();
		if(CreateSession.getAutomationConfiguration().Tenant.equalsIgnoreCase("Apcoa") || CreateSession.getAutomationConfiguration().Tenant.equalsIgnoreCase("GMP")) {
			//System.out.println(CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//*[contains(@resource-id,':id/text')]")).getText());
			selectcountry.selectCountryClick();
			Thread.sleep(2000);
			selectcountry.selectCountry(CreateSession.getAutomationConfiguration().Country);
			softAssert.assertEquals(CreateSession.getAutomationConfiguration().Country.toUpperCase(), selectcountry.CountrySelected.toUpperCase(),"Country not selected" );		
		}
		Thread.sleep(3000);
		selectcountry.btnLoginClick();
		softAssert.assertAll();
	}


	/**
	 * method to enter credentials and check whether user is able to login or not
	 * @param loginMapper contains information of user credentials and username
	 * 
	 */
	@Test(priority=2,dataProvider="getLoginData")
	public void loginAppcoa(LoginMapper loginMapper) throws InterruptedException{
		PageLogin login = new PageLogin(CreateSession.getAutomationConfiguration().AppiumDriver);
		Thread.sleep(3000);
		login.enterCredentials(loginMapper.getEmail(), loginMapper.getPassword());
		Thread.sleep(2000);
		login.clickContinue();

		PageHomeApcoa home = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
		home.acceptPushNotification();
		home.checkUserName();
		home.cancelActivatePopUp();
		home.cancelQuestionPopUp();

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(loginMapper.getUsername().toUpperCase(),home.Username.toUpperCase() );
		softAssert.assertAll();	

	}

	/**
	 * method to add vehicle and check whether vehicle is added or not.
	 * @param vehicleMapper contains information of LPR
	 * 
	 */
	//@Test(priority=3,dataProvider="getVehicleData")
	public void addVehicle(VehicleMapper vehicleMapper) throws InterruptedException{
		PageHomeApcoa home = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
		home.dismissFoodAlert();
		String vehicleno = vehicleMapper.getLpr();
		SoftAssert softAssert = new SoftAssert();
		PageAddVehicle addvehicle = new PageAddVehicle(CreateSession.getAutomationConfiguration().AppiumDriver);
		addvehicle.addVehicle(vehicleno);
		Thread.sleep(2000);

		softAssert.assertTrue(addvehicle.chechVehiclePresent(vehicleno),"Vehicle LPR not added");
		addvehicle.goback();
		softAssert.assertAll();
		//XCUIElementTypeStaticText[@name="Park Now"]
	}

	/**
	 * method to delete the specific LPR and check whether vehicle is deleted or not.
	 * @param vehicleMapper contains information of LPR
	 * 
	 */
	//@Test(priority=6,dataProvider="getVehicleData")
	public void deleteVehicle(VehicleMapper vehicleMapper) throws InterruptedException{
		PageHomeApcoa home = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
		home.dismissFoodAlert();
		Thread.sleep(2000);
		String vehicleno = vehicleMapper.getLpr();
		SoftAssert softAssert = new SoftAssert();
		PageAddVehicle delvehicle = new PageAddVehicle(CreateSession.getAutomationConfiguration().AppiumDriver);
		delvehicle.deletelpr(vehicleMapper.getLpr());
		Thread.sleep(2000);
		softAssert.assertFalse(delvehicle.chechVehiclePresent(vehicleno),"Vehicle LPR not added");
		delvehicle.goback();
		softAssert.assertAll();
	}

	/**
	 * method to search the parking and select it.
	 * @param parkingMapper contains information of parking name and parking type.
	 * 
	 */
	@Test(priority = 4, dataProvider = "getParkingData")
	public void searchParking(ParkingMapper parkingMapper) throws InterruptedException {
		String parkingName = parkingMapper.getParkingidentifier();
		String fullParkingName = parkingMapper.getParkingname();
		SoftAssert softAssert = new SoftAssert();

		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.FullParkingName = fullParkingName;
		pms.GettheParking(parkingName);

		softAssert.assertEquals(fullParkingName, pms.ActualParkingName, "Parking Name Not Found.");
		softAssert.assertAll();
	}



	/**
	 * method to click on start button and select the tariff.
	 * @param parking Mapper contains information of tariffname
	 * 
	 */
	@Test(priority = 5, dataProvider = "getParkingData")
	public void startParking(ParkingMapper parkingMapper) throws InterruptedException {
		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.StartsessionforParkingwithPass(parkingMapper.getTariffname());
	}

	/**
	 * method to apply the promocode and rotate the dialer and click on start button
	 * @param parkingMapper contains information of promocode, dialer rotation percentage
	 * 
	 */
	@Test(priority = 6, dataProvider = "getParkingData")
	public void dialerRotationAndStartSession(ParkingMapper parkingMapper) {
		SoftAssert softAssert = new SoftAssert();
		this.pss = new PageStartSession(CreateSession.getAutomationConfiguration().AppiumDriver);
		this.pss.dialerMovement(parkingMapper.getCurrency(),parkingMapper.getPromocode(),parkingMapper.getDrstart());

		ApcoaListeners.logInfo("Main ActualInitialParkingPrice:" + this.pss.ActualInitialParkingPrice);
		softAssert.assertAll();
	}

	/**
	 * method to click on PAY button and check for the Payment confirmation Page
	 * @param parkingMapper contains information of parking type
	 * 
	 */
	@Test(priority = 7, dataProvider = "getParkingData")
	public void startSessionAndPaymentConfirmation(ParkingMapper parkingMapper) {
		SoftAssert softAssert = new SoftAssert();
		this.pss.PaymentConfirmation(parkingMapper.getType());

		if(!parkingMapper.getType().equalsIgnoreCase("postpay")){
			System.out.println("Main ActualPaymentSuccess:" + this.pss.ActualPaymentSuccess);
			softAssert.assertEquals(this.pss.ActualPaymentSuccess, "Payment Successful", "Payment Successful message fail");
		}
		softAssert.assertAll();
	}



	/**
	 * method to extend the session.
	 * @param parkingMapper contains information of currency and parking type
	 * 
	 */
	@Test(priority = 8, dataProvider = "getParkingData")
	public void extendSession(ParkingMapper parkingMapper) throws InterruptedException {

		this.pss.ExtendSession(parkingMapper.getCurrency(),parkingMapper.getType(),parkingMapper.getDrextend());

		ApcoaListeners.logInfo("Actual Initial Parking Price:" + this.pss.ActualInitialParkingPrice);
		ApcoaListeners.logInfo("Actual Extended Parking Price:" + this.pss.ActualExtendedParkingPrice);



		this.pss.ActualInitialParkingPrice = GenericMethods.handlePrice(this.pss.ActualInitialParkingPrice);
		this.pss.ActualExtendedParkingPrice = GenericMethods.handlePrice(this.pss.ActualExtendedParkingPrice);

		if(this.pss.ActualInitialParkingPrice.contains(parkingMapper.getCurrency())) {
			this.pss.ActualInitialParkingPrice = this.pss.ActualInitialParkingPrice.split("\\"+parkingMapper.getCurrency())[0];
		}
		if(this.pss.ActualExtendedParkingPrice.contains(parkingMapper.getCurrency())) {
			this.pss.ActualExtendedParkingPrice = this.pss.ActualExtendedParkingPrice.split("\\"+parkingMapper.getCurrency())[0];
		}
		if(this.pss.ActualInitialParkingPrice.contains("R$")){
			this.pss.ActualInitialParkingPrice = this.pss.ActualInitialParkingPrice.split("R")[0];
		}
		if(this.pss.ActualExtendedParkingPrice.contains("R$")){
			this.pss.ActualExtendedParkingPrice = this.pss.ActualExtendedParkingPrice.split("R")[0];
		}


		float initialparkingprice = Float.parseFloat(this.pss.ActualInitialParkingPrice.trim());
		float extendedParkingPrice = Float.parseFloat(this.pss.ActualExtendedParkingPrice.trim());
		System.out.println("Parsing of Price is Done successfully.");

		Thread.sleep(3000);
		if(!parkingMapper.getType().equalsIgnoreCase("postpay")){
			this.pss.ExtendPaymentConfirmation();
		}

		if(parkingMapper.getType().equalsIgnoreCase("postpay")){
			this.finalcalculatedprice =initialparkingprice;
		}else{
			this.finalcalculatedprice = extendedParkingPrice + initialparkingprice;
		}

		//softAssert.assertAll();
	}

	/**
	 * method to goto Active Session Page and collect information
	 * @param parkingMapper contains information of parking type
	 * 
	 */
	@Test(priority = 9, dataProvider = "getParkingData")
	public void ActiveSesssionDetails(ParkingMapper parkingMapper) throws InterruptedException {

		this.psd = new PageSessionDetails(CreateSession.getAutomationConfiguration().AppiumDriver);
		psd.GotoMyActiveSessions(parkingMapper.getType());
		SoftAssert softAssert = new SoftAssert();
		activesessionid = Float.parseFloat(this.psd.ActiveSessionID.split("#")[1]);
		activesessionprice =SmokePnd.getprice(psd.ActiveSessionCost, parkingMapper.getCurrency()) ;
		if(!parkingMapper.getType().equalsIgnoreCase("postpay")) {
			softAssert.assertEquals(activesessionprice, finalcalculatedprice, "Final price in active session fail");
		}
		ApcoaListeners.logInfo("Main ActiveSessionID:" + psd.ActiveSessionID);
		ApcoaListeners.logInfo("Main ActiveSessionCost:" + psd.ActiveSessionCost);
		Thread.sleep(3000);
		softAssert.assertAll();
	}


	/**
	 * method to stop the session and check the payment confirmation page
	 * @param parkingMapper contains information of parking type
	 * 
	 */
	@Test(priority = 10, dataProvider = "getParkingData")
	public void StopSession(ParkingMapper parkingMapper) {

		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.StopSession(parkingMapper.getType());

	}

	/**
	 * method to goto expired Session page and verify the details
	 * @param parkingMapper contains information of currency, parking type 
	 * 
	 */
	@Test(priority = 11, dataProvider = "getParkingData")
	public void GotoExpiredSessions(ParkingMapper parkingMapper) {
		SoftAssert softAssert = new SoftAssert();
		String currency = parkingMapper.getCurrency();
		//this.psd = new PageSessionDetails(AutomationConfiguration.AppiumDriver);
		this.psd.GotoMyExpiredSessions();

		float expiredsessionid = Float.parseFloat(this.psd.ExpiredSessionID.split("#")[1]);
		float expiredsessionprice = getPriceExpiredSession(this.psd.ExpiredSessionCost, currency);

		if(!parkingMapper.getType().equalsIgnoreCase("postpay")) {
			softAssert.assertEquals(expiredsessionprice, this.finalcalculatedprice, "Final price in expired session fail");
			softAssert.assertEquals(activesessionprice, expiredsessionprice, "Total amount is not matching in expired sessions in active and expired session.");
		}
		softAssert.assertEquals(expiredsessionid, activesessionid, "Session ID is not matching in expired sessions.");
		ApcoaListeners.logInfo("Expired Session ID:" + this.psd.ExpiredSessionID);
		ApcoaListeners.logInfo("Expired Session Cost:" + this.psd.ExpiredSessionCost);
		softAssert.assertAll();
	}


	//@Test(priority = 15)
	public void EpmpStartButtonCheck() throws InterruptedException {
		Thread.sleep(2000);
		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		SoftAssert softAssert = pms.EpmpCheck();
		softAssert.assertAll();

	}

	//@Test(priority=16)
	public void EVchargingButtonCheck() throws InterruptedException{   
		
		SoftAssert softAssert=new SoftAssert();
		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);

		String[] ParkingIdentifier= new String[] {"7002","3299","8391","6605"};
		String[] ParkingName = new String[] {"P-Hus Avenyn","Torsplan 2","P-Hus Kanikenäsbanken","Doktor Butlers gata 3-5"};
		for(int i=0;i<ParkingIdentifier.length;i++){
			ApcoaListeners.logInfo("Parking Area Code ------> "+ParkingIdentifier[i]);
			ApcoaListeners.logInfo("Parking Name          ------> "+ParkingName[i]);

			pms.GettheParking(ParkingIdentifier[i]);

			//softAssert.assertEquals(ParkingName[i], ActualParkingName, "Parking Not Found: "+ParkingName[i]);

			softAssert.assertEquals(pms.checkEVChargingButton(ParkingName[i]),"True" ,"Test-FAILED: "+ParkingName[i]);
		}
		softAssert.assertAll();
	}


	//@Test(priority=17)
	public void ChangingDefaultProfile() throws InterruptedException{
		//changingDefaultProfile("Corporate");
		PagePaymentScreen pps = new PagePaymentScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pps.changingDefaultProfile("Personal Profile");//Personal Profile Business
	}

	@DataProvider
	public FreeParkingMapper[] getFreeParkingData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/Mobile/Dataset/"+CreateSession.getAutomationConfiguration().Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Country,"FreeParking");		
		ObjectMapper mapper = new ObjectMapper();
		FreeParkingMapper []promo = new FreeParkingMapper[1];
		promo[0] = mapper.readValue(data, FreeParkingMapper.class);
		return promo;	
	}

	//@Test(priority=18,dataProvider = "getFreeParkingData")
	public void freeParkingCheck(FreeParkingMapper parkingMapper) throws InterruptedException{
		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		String parkingName = parkingMapper.getParkingidentifier();

		pms.GettheParking(parkingName);

		PageMapScreen pmss = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pmss.StartsessionforParkingwithPass("null");

		PageBuyPass pbp = new PageBuyPass(CreateSession.getAutomationConfiguration().AppiumDriver);
		pbp.addAnotherPromoCode(parkingMapper.getpromo());

		PageStartSession pss = new PageStartSession(CreateSession.getAutomationConfiguration().AppiumDriver);
		pss.CheckPriceInFreeParking(parkingMapper);

		pmss.StopSession("prepay");

	}



	//@Test(priority=19)
	public void BuisnessProfileCreation() throws InterruptedException
	{//login
		PagePaymentScreen pps = new PagePaymentScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		//SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		pps.DeleteBuisnessProfile();
		pps.AddBuisnessprofile();
	}


	//@Test(priority=20)
	public void NoSessionWithoutPaymentMethod() throws InterruptedException
	{
		Thread.sleep(4000);
		PageLogin login = new PageLogin(CreateSession.getAutomationConfiguration().AppiumDriver);
		Thread.sleep(3000);
		login.enterCredentials("automation_newuser112333@yopmail.com"," testing");
		Thread.sleep(2000);//8000
		login.clickContinue();
		PageHomeApcoa home = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
		home.acceptPushNotification();
		home.checkUserName();
		home.cancelActivatePopUp();
		home.cancelQuestionPopUp();

		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.GettheParking("kommune");

		PageMapScreen pmss = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pmss.StartsessionforParkingwithPass("null");

		PageStartSession psss = new PageStartSession(CreateSession.getAutomationConfiguration().AppiumDriver);
		psss.SessionCheck();
	}

	@DataProvider
	public BuyPassMapper[] getBuyPassData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/Mobile/Dataset/"+CreateSession.getAutomationConfiguration().Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Country,"BuyPass");		
		ObjectMapper mapper = new ObjectMapper();
		BuyPassMapper []pass = new BuyPassMapper[1];
		pass[0] = mapper.readValue(data, BuyPassMapper.class);
		return pass;	
	}

	//@Test(priority=21,dataProvider = "getBuyPassData")
	public void BuyPass(BuyPassMapper passMapper)throws InterruptedException{
		Thread.sleep(4000);
		PageBuyPass PB= new PageBuyPass(CreateSession.getAutomationConfiguration().AppiumDriver);
		String Parking =passMapper.getParkingidentifier();
		String PassPromo=passMapper.getpromo();
		PB.searchAreaAndBuyPass(Parking,PassPromo);
	}


	//	@Test(priority=22)
	public void ProfileAutoSwitch() throws InterruptedException
	{
		/*
		 *  FOR SWEDEN -----------------------------------------------------
		 */
		// Case 1: Both Personal and Corporate , Profile Selected-Personal , Tarrif-Regular , Expected Behavior-Able to start the parking ,Profile_switch-- Personal
		Map<String,String> myMap1 = new HashMap<String, String>();
		Map<String,String> myMap2 = new HashMap<String, String>();
		Map<String,String> myMap3 = new HashMap<String, String>();
		Map<String,String> myMap4 = new HashMap<String, String>();
		Map<String,String> myMap5 = new HashMap<String, String>();
		Map<String,String> myMap6 = new HashMap<String, String>();
		Map<String,String> myMap7 = new HashMap<String, String>();
		Map<String,String> myMap8 = new HashMap<String, String>();
		List<Map<String , String>> myMap  = new ArrayList<Map<String,String>>();
		myMap.add(0,myMap1);
		myMap.add(1,myMap2);
		myMap.add(2,myMap3);
		myMap.add(3,myMap4);
		myMap.add(4,myMap5);
		myMap.add(5,myMap6);
		myMap.add(6,myMap7);
		myMap.add(7,myMap8);

		myMap1.put("DefaultProfile", "Personal Profile");
		myMap1.put("parking", "3450");                       // Case1
		myMap1.put("SwitchedProfile", "Personal");
		myMap1.put("startsession", "true");

		myMap2.put("DefaultProfile", "Personal Profile");
		myMap2.put("parking", "50224");                     // Case2
		myMap2.put("SwitchedProfile", "Region Stockholm Profile");
		myMap2.put("startsession", "true");

		myMap3.put("DefaultProfile", "Personal Profile");
		myMap3.put("parking", "50220");                      //Case3
		myMap3.put("SwitchedProfile", "Region Stockholm Profile");
		myMap3.put("startsession", "true");

		myMap4.put("DefaultProfile", "Personal Profile");
		myMap4.put("parking", "7385");                       //Case4
		myMap4.put("SwitchedProfile", "Personal");
		myMap4.put("startsession", "true");

		myMap5.put("DefaultProfile", "Region Stockholm Profile");
		myMap5.put("parking", "3450");                    //Case5
		myMap5.put("SwitchedProfile", "Personal");
		myMap5.put("startsession", "true");

		myMap6.put("DefaultProfile", "Region Stockholm Profile");
		myMap6.put("parking", "50224");
		myMap6.put("SwitchedProfile", "Region Stockholm Profile");
		myMap6.put("startsession", "true");

		myMap7.put("DefaultProfile", "Region Stockholm Profile");
		myMap7.put("parking", "50220");
		myMap7.put("SwitchedProfile", "Region Stockholm Profile");
		myMap7.put("startsession", "true");

		myMap8.put("DefaultProfile", "Region Stockholm Profile");
		myMap8.put("parking", "7385");
		myMap8.put("SwitchedProfile", "Personal");
		myMap8.put("startsession", "true");

		PageMapScreen pmss = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		SoftAssert softAssert = new SoftAssert();

		for (Map<String, String> map : myMap)
		{   ApcoaListeners.logInfo("Searching the parking"+map);
		ApcoaListeners.logInfo("Default profile ");

		PagePaymentScreen pps = new PagePaymentScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pps.changingDefaultProfile(map.get("DefaultProfile"));

		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.GettheParking(map.get("parking"));
		pms.StartsessionforParkingwithPass("null");

		PageStartSession pss = new PageStartSession(CreateSession.getAutomationConfiguration().AppiumDriver);
		pss.ProfileAutoSwitchCheck(map.get("SwitchedProfile"),map.get("startsession"),softAssert);

		pss.checkProfileInExtend(map.get("SwitchedProfile"),softAssert);
		pmss.StopSession("prepay");
		}
		softAssert.assertAll();
	}


	//@Test(priority=23,dataProvider = "getParkingData")
	public void TarrifAfterFreeParkingHrsInExtendSession(ParkingMapper parkingMapper) throws InterruptedException{

		SoftAssert softAssert = new SoftAssert();
		PageHomeApcoa home = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
		home.dismissFoodAlert();

		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.GettheParking(parkingMapper.getParkingidentifier());
		pms.StartsessionforParkingwithPass("null");

		PageStartSession pss = new PageStartSession(CreateSession.getAutomationConfiguration().AppiumDriver);
		pss.PolandTarrif(CreateSession.getAutomationConfiguration().Country, parkingMapper,softAssert);

		pms.StopSession("prepay");
		softAssert.assertAll();
	}


	//@Test(priority = 24, dataProvider = "getParkingData")
	public void multipleTarrifCheck(ParkingMapper parkingMapper) throws InterruptedException
	{
		SoftAssert softAssert = new SoftAssert();
		String parkingName = parkingMapper.getParkingidentifier();
		//SC.GettheParking(parkingName);
		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.GettheParking(parkingName);
		pms.StartsessionforParkingwithPass("null");
		PageStartSession pss = new PageStartSession(CreateSession.getAutomationConfiguration().AppiumDriver);
		pss.check_multiple_tarrif(parkingMapper, softAssert);
		softAssert.assertAll();
	}


	//@Test(priority=25)
	public void parkAndGoCheck() throws InterruptedException {
		PageStartSession SC = new PageStartSession(CreateSession.getAutomationConfiguration().AppiumDriver);
		String[]  mycarpark = {"84016","712","6495","46010","84010","46079","7001","2932","32872","84012","84013","84011","8401","72600","7255","2780","2790","3040","2891"};
		Boolean check = true;
		for(String e:mycarpark) {
			PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
			pms.GettheParking(e);
			// SC.SearchParkingUsingAreaCode(e);
			check = SC.parkAndGoCheck(e);
			if(!check) {
				assert false:"park and go description not visible at :"+e;
			}
			Thread.sleep(3000);
		}
	}



	//@Test(priority=26)
	public void passPromoToastCheck() throws InterruptedException {
		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.GettheParking("skara");
		PageBuyPass SC = new PageBuyPass(CreateSession.getAutomationConfiguration().AppiumDriver);
		Boolean check = SC.passPromoToastCheck();
		System.out.println(check);
		if(!check) {
			assert false:"Toast is visble";
		}
	}

	//@Test(priority=27)
	public void  elementAlignment() throws InterruptedException, IOException {
		PageSessionDetails SC = new PageSessionDetails(CreateSession.getAutomationConfiguration().AppiumDriver);
		Boolean check = SC.elementAlignment();
		if(!check) {
			assert false:"Image alignment is different";
		}
	}

	//@Test(priority=28)
	public void renewPass() throws InterruptedException {
		PageBuyPass SC = new PageBuyPass(CreateSession.getAutomationConfiguration().AppiumDriver);
		Boolean check = SC.renewPass("test_pass_demo","2412");
		if(!check) {
			assert false:"testcase is failed";
		}
	}

	String ActiveSessionId="";
	String AppliedPromo = "";

	//@Test(priority=29,dataProvider = "getReverseQRParkingData")
	public void StartPostpay(ParkingMapper parkingMapper) throws InterruptedException, IOException
	{
		QRScanPage SC = new QRScanPage(CreateSession.getAutomationConfiguration().AppiumDriver);
		String parkingName = parkingMapper.getParkingidentifier();

		PagePaymentScreen pps = new PagePaymentScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pps.changingDefaultProfile("Business Profile");//Personal Profile
		//SC.changingDefaultProfile("adfs");//AutomationConfiguration.Profile
		Thread.sleep(3000);
		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.GettheParking(parkingName);
		//SC.GettheParking(parkingName);
		ActiveSessionId= SC.StartPostpaySession();
	}

	//@Test(priority=30)
	public void DiscountByQRScan() throws InterruptedException, IOException
	{
		QRScanPage SC = new QRScanPage(CreateSession.getAutomationConfiguration().AppiumDriver);
		SoftAssert softAssert = new SoftAssert();
		AppliedPromo = SC.AddDiscountByQRScan(1,softAssert);
		AppliedPromo = SC.AddDiscountByQRScan(2,softAssert);
		softAssert.assertAll();
	}

	//@Test(priority=31)
	public void StopPostpay() throws InterruptedException, IOException{
		QRScanPage SC = new QRScanPage(CreateSession.getAutomationConfiguration().AppiumDriver);
		SoftAssert softAssert = new SoftAssert();
		SC.StopPostpaySession(ActiveSessionId,AppliedPromo,softAssert);
		softAssert.assertAll();
	}


	//@Test(priority=32,dataProvider = "getParkingData")
	public void directLink(ParkingMapper parkingMapper) throws IOException, InterruptedException{
		//SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		LaunchQrReader LR = new LaunchQrReader(CreateSession.getAutomationConfiguration().AppiumDriver);
		QRScanPage SC = new QRScanPage(CreateSession.getAutomationConfiguration().AppiumDriver);
		SC.removeQR();
		SC.excCommand("DirectLinkQr");
		LR.launchQrReader();
		Thread.sleep(5000);

		//PageStartSession pss = new PageStartSession(CreateSession.getAutomationConfiguration().AppiumDriver);
		this.pss.dialerMovement(parkingMapper.getCurrency(),parkingMapper.getPromocode(),parkingMapper.getDrstart());
		//SC.dialerMovement(AutomationConfiguration.Country,parkingMapper);
		LR.KillQrScanner();
	}


	@DataProvider
	public OperationalHrsMapper[] getOperationalHrsMapper() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+CreateSession.getAutomationConfiguration().Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Country,"operationalHrs");		
		ObjectMapper mapper = new ObjectMapper();
		OperationalHrsMapper []hrs = new OperationalHrsMapper[1];
		hrs[0] = mapper.readValue(data, OperationalHrsMapper.class);

		return hrs;	
	}


	//@Test(priority=33,dataProvider = "getOperationalHrsMapper")
	public void SessionAfterOperationalHrs(OperationalHrsMapper parkingMapper) throws Exception{   
		//SoftAssert softAssert = new SoftAssert();
		//SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		OperationalHrs op= new OperationalHrs(CreateSession.getAutomationConfiguration().AppiumDriver);
		String  parkingName=parkingMapper.getParkingidentifier();
		ApcoaListeners.logInfo("Searching the parking");
		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.GettheParking(parkingName);
		//SC.GettheParking(parkingName);
		Thread.sleep(5000);
		//PageMapScreen pms = new PageMapScreen(AutomationConfiguration.AppiumDriver);
		pms.StartsessionforParkingwithPass("null");
		op.CheckOperationalHrs2(parkingMapper);
	}

	@DataProvider
	public CorporateProfileMapper[] getCorporateProfileMapper() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/Mobile/Dataset/"+CreateSession.getAutomationConfiguration().Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Country,"corporateprofile");		
		ObjectMapper mapper = new ObjectMapper();
		CorporateProfileMapper []hrs = new CorporateProfileMapper[1];
		hrs[0] = mapper.readValue(data, CorporateProfileMapper.class);

		return hrs;	
	}


	//@Test(priority=34,dataProvider = "getCorporateProfileMapper")
	public void CorporateProfileCreation(CorporateProfileMapper mapper) throws InterruptedException{
		SoftAssert SA=new SoftAssert();
		PagePaymentScreen SC = new PagePaymentScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		boolean Temp1=SC.DeleteCorporateProfile(mapper.getcorporate()+" Profile");//Region Värmland Profile
		SA.assertEquals(Temp1, true);
		boolean Temp2=SC.AddCorporateprofile(mapper.getcorporate(),mapper.getcorporateid(),mapper.getemail());
		SA.assertEquals(Temp2,true);
		SA.assertAll();

	}


	//@Test(priority=35)
	public void EditPersonalDetails() throws InterruptedException{
		SoftAssert SA=new SoftAssert();
		PagePersonalDetails SC = new PagePersonalDetails(CreateSession.getAutomationConfiguration().AppiumDriver);
		SC.editpersonalDetails("ABC","CDEF",SA);
		SA.assertAll();

	}



	/**
	 * method to get the actual float price value from string
	 * @param price contains string price
	 * @param currency 
	 * 
	 * @return floating point price value
	 */
	public static float getPriceExpiredSession(String price, String currency) {
		try {
			String symbol;
			float mainprice;
			int len=currency.length();
			if(len>1){
				symbol=currency.substring(len-1);
			}else{
				symbol=currency;
			}
			if(currency.equals("DKK")){
				mainprice = Float.parseFloat(price.split(currency)[1]);
			}else if(symbol.equals("D")||symbol.equals("K")||symbol.equals("N")){
				ApcoaListeners.logInfo("Expired Session Cost----->"+price);
				mainprice = Float.parseFloat(price.split(symbol)[1]);
			}else {
				mainprice = Float.parseFloat(price.split("\\"+symbol)[1]);
			}
			return mainprice;
		}catch(Exception e) {
			return -1;
		}
	}

	/**
	 * method to get the actual float price value from string
	 * @param price contains string price
	 * @param currency 
	 * 
	 * @return floating point price value
	 */
	public static float getprice(String price, String currency) {
		try {		
			GenericMethods.handlePrice(price);
			float mainprice;
			String symbol;
			int len=currency.length();
			if(len>1){
				symbol=currency.substring(len-1);
			}else{
				symbol=currency;
			}if(symbol.equals("D")||symbol.equals("N")){ 
				mainprice = Float.parseFloat(price.split(symbol)[1]); 
			}else{
				mainprice = Float.parseFloat(price.split("\\"+symbol)[1]);
			}
			return mainprice;
		}catch(Exception e) {
			return -1;
		}
	}

	
}