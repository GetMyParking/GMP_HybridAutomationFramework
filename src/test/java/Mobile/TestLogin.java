package Mobile;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonUtility.AutomationConfiguration;
import CommonUtility.CreateSession;
import DataDriven.ExcelDriven;
import MobileObjectMapper.LoginMapper;
import MobileObjectMapper.ParkingMapper;
import MobileObjectMapper.VehicleMapper;
import Pages.Mobile.PageAddVehicle;
import Pages.Mobile.PageHomeApcoa;
import Pages.Mobile.PageLogin;
import Pages.Mobile.PageSelectCountry;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestLogin {
	//old
	// /Users/karankumaragarwal/Downloads/staging_1.0.60_100_apcoaflow_2021112507.apk
	
	// new
	// /Users/karankumaragarwal/Downloads/staging_1.0.60_100_apcoaflow_2021120212.apk
	
	public String ExpectedExtendedParkingPrice="0.00";
	public String ExpectedSessionEndSuccessmsg="Your session has been ended";
	public String ExpectedSessionEndMsg="You will not get any refund on the amount you already paid for the session";
	public String ExpectedPaymentSuccess="Payment Successful";
	public String ExpectedInitialPrice="0.00";
	public String ExpectedParkingHour="00";
	public String ExpectedParkingMin="21";
	
	
	
	WebDriver driver;
	
	@Parameters({ "Environment", "Country" })
	@BeforeSuite
	public void initializeDriver(String ennv, String country) throws IOException
	{
		AutomationConfiguration.Environment = ennv;
		AutomationConfiguration.Country = country;
		CreateSession.readConfigFile("/src/test/java/resources/configAppcoaStaging.properties");
	}
	
	@DataProvider
	public LoginMapper[] getLoginData() throws Exception
	{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AppcoaDataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"Login");
		
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);
	    System.out.println(data);
	
	    return login;
		
	}
	
	@DataProvider
	public VehicleMapper[] getVehicleData() throws Exception
	{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AppcoaDataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"Add Vehicle");
		
		ObjectMapper mapper = new ObjectMapper();
		VehicleMapper []login = new VehicleMapper[1];
		login[0] = mapper.readValue(data, VehicleMapper.class);
	    System.out.println(login[0].getlpr());
	
	    return login;
		
	}
	
	
	@DataProvider
	public ParkingMapper[] getParkingData() throws Exception
	//public void getParkingData() throws Exception
	{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AppcoaDataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"Session");
		
		ObjectMapper mapper = new ObjectMapper();
		ParkingMapper []login = new ParkingMapper[1];
		login[0] = mapper.readValue(data, ParkingMapper.class);
	    System.out.println(login[0].getparkingidentifier());
	    System.out.println(login[0].getparkingname());
	
	    return login;
		
	}
	
	
	@BeforeMethod
	public void initializeAssertions()
	{
		AutomationConfiguration.SoftAsserts = new SoftAssert();
	}
	
	@AfterMethod
	public void AssertAll()
	{
		AutomationConfiguration.SoftAsserts.assertAll();;
	}
	

	@Test(priority=1)
	public void selectCountry() throws Exception
	{
		//getVehicleData();
		//getParkingData();
		Thread.sleep(15000);
		PageSelectCountry selectcountry = new PageSelectCountry(AutomationConfiguration.AppiumDriver);
		selectcountry.selectCountryClick();
		Thread.sleep(2000);
		selectcountry.selectCountry(AutomationConfiguration.Country);
		Thread.sleep(5000);
		selectcountry.btnLoginClick();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(AutomationConfiguration.Country.toUpperCase(), selectcountry.CountrySelected.toUpperCase(),"Country not selected" );
		softAssert.assertAll();
	}
	
	@Test(priority=2,dataProvider="getLoginData")
	public void loginAppcoa(LoginMapper loginMapper) throws InterruptedException
	{
		System.out.println(loginMapper.getEmail()+" "+loginMapper.getPassword());
		Thread.sleep(10000);
		
		PageLogin login = new PageLogin(AutomationConfiguration.AppiumDriver);
		login.enterCredentials(loginMapper.getEmail(), loginMapper.getPassword());
		login.clickContinue();
		
		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.acceptPushNotification();
		
		home.checkUserName();
		
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(loginMapper.getUserName().toUpperCase(),home.Username.toUpperCase() );
		softAssert.assertAll();
	
	}
	
	//@Test(priority=3)
	public void testexpired()
	{
		Pages.Mobile.SessionCreationPage SC=new Pages.Mobile.SessionCreationPage(AutomationConfiguration.AppiumDriver);
		SC.GotoMyExpiredSessions();
	}
	
	
	@Test(priority=3,dataProvider="getVehicleData")
	public void addVehicle(VehicleMapper vehicleMapper) throws InterruptedException
	{
		//System.out.println("inside the addvehicle: "+vehicleMapper.getlpr());
		String vehicleno = vehicleMapper.getlpr();
		SoftAssert softAssert = new SoftAssert();
		PageAddVehicle addvehicle = new PageAddVehicle(AutomationConfiguration.AppiumDriver);
		addvehicle.addVehicle(vehicleno);
		Thread.sleep(4000);
		System.out.println("first lpr: "+ addvehicle.getfirstvehiclelpr());
		String vno = addvehicle.getfirstvehiclelpr();
		softAssert.assertEquals(vehicleno, vno,"Vehicle LPR not added");
		PageAddVehicle.goBack();
		softAssert.assertAll();
		
	}

	//testaustriastaging@yopmail.com
	//testing

	
	@Test(priority=5,dataProvider="getVehicleData")
		public void deleteVehicle(VehicleMapper vehicleMapper) throws InterruptedException
		{
			//System.out.println("inside the deletevehicle: "+vehicleMapper.getlpr());
			Thread.sleep(8000);
			String vehicleno = vehicleMapper.getlpr();
			SoftAssert softAssert = new SoftAssert();
			PageAddVehicle delvehicle = new PageAddVehicle(AutomationConfiguration.AppiumDriver);
			delvehicle.deletelpr();
			Thread.sleep(5000);
			System.out.println("first lpr: "+delvehicle.getfirstvehiclelpr());
			String vno = delvehicle.getfirstvehiclelpr();
			softAssert.assertNotEquals(vehicleno, vno,"Vehicle LPR not deleted");
			PageAddVehicle.goBack();
			softAssert.assertAll();
			Thread.sleep(5000);
			
		}

	
	@Test(priority = 4,dataProvider="getParkingData")
	public void startExtendStopSession(ParkingMapper parkingMapper) throws InterruptedException 
	{
		Thread.sleep(8000);
		Pages.Mobile.SessionCreationPage SC=new Pages.Mobile.SessionCreationPage(AutomationConfiguration.AppiumDriver);
		
		String  Parkingname=parkingMapper.getparkingidentifier(),
				FullParkingName = parkingMapper.getparkingname(),
				country=AutomationConfiguration.Country;
				
		SoftAssert softAssert = new SoftAssert();
		
		
		SC.GettheParking(Parkingname);
		softAssert.assertEquals(FullParkingName, SC.ActualParkingName,"Parking Not Found");
		Thread.sleep(8000);
		SC.StartsessionforParkingwithPass();
		Thread.sleep(8000);
		SC.dialerMovement(country);
				
		
		float initialparkingprice;// initialparkinghours, initialparkingminutes;
		float extendedparkingprice;
		float finalprice;
		initialparkingprice = Float.parseFloat(SC.ActualInitialParkingPrice);
		//initialparkinghours = Float.parseFloat(SC.ActualParkingHour);
		//initialparkingminutes = Float.parseFloat(SC.ActualParkingMin);
		
		System.out.println("Main ActualInitialParkingPrice:"+SC.ActualInitialParkingPrice);//Main ActualInitialParkingPrice:1.14
		System.out.println("Main ActualParkingHour:"+SC.ActualParkingHour);//Main ActualParkingHour:00
		System.out.println("Main ActualParkingMin:"+SC.ActualParkingMin);//Main ActualParkingMin:12
		
		Thread.sleep(8000);
		
		//softAssert.assertEquals(initialparkingprice, 1.14,"Initial parking price fail.");
		//softAssert.assertEquals(initialparkinghours, 0,"Initial parking hours fail.");
		//softAssert.assertEquals(initialparkingminutes, 12,"Initial parking min fail.");
		
		SC.PaymentConfirmation();
		System.out.println("Main ActualPaymentSuccess:"+SC.ActualPaymentSuccess);//Main ActualPaymentSuccess:Payment Successful
		softAssert.assertEquals(SC.ActualPaymentSuccess, "Payment Successful", "Payment Successful message fail");
		
		Thread.sleep(8000);
		
		SC.ExtendSession(country);
		extendedparkingprice = Float.parseFloat(SC.ActualExtendedParkingPrice);
		System.out.println("Main ActualExtendedParkingPrice:"+SC.ActualExtendedParkingPrice);//Main ActualExtendedParkingPrice:0.00
		
		Thread.sleep(8000);
		
		SC.ExtendPaymentConfirmation();
		
		Thread.sleep(8000);
		
		finalprice = extendedparkingprice + initialparkingprice;
		SC.GotoMyActiveSessions();
		
		float activesessionid=Float.parseFloat(SC.ActiveSessionID.split("#")[1]); 
		float activesessionprice=Float.parseFloat(SC.ActiveSessionCost.split("€")[1]);
		
		softAssert.assertEquals(activesessionprice, finalprice,"Final price in active session fail");
		
		System.out.println("Main ActiveSessionID:"+SC.ActiveSessionID);//Main ActiveSessionID:ID: #105899
		System.out.println("Main ActiveSessionCost:"+SC.ActiveSessionCost);//Main ActiveSessionCost:Total : €1.14
		
		Thread.sleep(3000);
		
		SC.StopSession();
		
		Thread.sleep(3000);
		SC.GotoMyExpiredSessions();
		
		float expiredsessionid=Float.parseFloat(SC.ExpiredSessionID.split("#")[1]);  
		float expiredsessionprice=Float.parseFloat(SC.ExpiredSessionCost.split("€")[1]);
		
		softAssert.assertEquals(expiredsessionprice, finalprice,"Final price in expired session fail");
		
		softAssert.assertEquals(expiredsessionid, activesessionid,"Session ID is not matching in expired sessions.");
		
		System.out.println("Main ExpiredSessionID:"+SC.ExpiredSessionID);//Main ExpiredSessionID:ID: #105899
		System.out.println("Main ExpiredSessionCost:"+SC.ExpiredSessionCost);//Main ExpiredSessionCost:Total : €1.14
		
		Thread.sleep(8000);
		
		
		softAssert.assertAll();
		PageAddVehicle.goBack();
	} 

	
}





