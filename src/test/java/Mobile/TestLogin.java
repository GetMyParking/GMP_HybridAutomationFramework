package Mobile;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import CommonUtility.AutomationConfiguration;
import CommonUtility.CreateSession;
import DataDriven.ExcelDriven;
import Mobile.Utils.SessionUtils;
import MobileObjectMapper.LoginMapper;
import MobileObjectMapper.ParkingMapper;
import MobileObjectMapper.VehicleMapper;
import Pages.Mobile.PageAddVehicle;
import Pages.Mobile.PageHomeApcoa;
import Pages.Mobile.PageLogin;
import Pages.Mobile.PageSelectCountry;
import Pages.Mobile.SessionCreationPage;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestLogin {

	@Parameters({ "Environment", "Country","Tenant","Platform" })
	@BeforeSuite
	public void initializeDriver(String ennv, String country,String tenant, String platform) throws IOException{
		String msg = System.getProperty("xmlSuiteFileName");
		System.out.println("inside java code: "+msg);
		System.out.println("inside java code: "+System.getProperty("myown"));
		System.err.close();
		System.setErr(System.out);
		AutomationConfiguration.Tenant = tenant;
		AutomationConfiguration.Environment = ennv;
		AutomationConfiguration.Country = country;
		AutomationConfiguration.Platform = platform;
		CreateSession.readConfigFile("/src/test/java/resources/"+platform+tenant+".properties");
	}

	@DataProvider
	public LoginMapper[] getLoginData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"Login");	
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);
		
		return login;	
	}

	@DataProvider
	public VehicleMapper[] getVehicleData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"Add Vehicle");
		ObjectMapper mapper = new ObjectMapper();
		VehicleMapper []vehicle = new VehicleMapper[1];
		vehicle[0] = mapper.readValue(data, VehicleMapper.class);
		
		return vehicle;	
	}

	@DataProvider
	public ParkingMapper[] getParkingData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"Session");	
		System.out.println("Data get from excel: "+ data);
		ObjectMapper mapper = new ObjectMapper();
		ParkingMapper []parking = new ParkingMapper[1];
		parking[0] = mapper.readValue(data, ParkingMapper.class);

		return parking;	
	}


	@BeforeMethod
	public void initializeAssertions(){
		AutomationConfiguration.SoftAsserts = new SoftAssert();
	}

	@AfterMethod
	public void AssertAll(){
		AutomationConfiguration.SoftAsserts.assertAll();;
	}

	//@Test(priority=1)
	public void selectCountry() throws Exception{
		Thread.sleep(2000);
		PageSelectCountry selectcountry = new PageSelectCountry(AutomationConfiguration.AppiumDriver);
		SoftAssert softAssert = new SoftAssert();
		if(!AutomationConfiguration.Tenant.equalsIgnoreCase("Elite")) {
			selectcountry.selectCountryClick();
			Thread.sleep(2000);
			selectcountry.selectCountry(AutomationConfiguration.Country);
			softAssert.assertEquals(AutomationConfiguration.Country.toUpperCase(), selectcountry.CountrySelected.toUpperCase(),"Country not selected" );		
		}
		selectcountry.btnLoginClick();
		softAssert.assertAll();
	}

	//@Test(priority=2,dataProvider="getLoginData")
	public void loginAppcoa(LoginMapper loginMapper) throws InterruptedException{
		Thread.sleep(2000);
		PageLogin login = new PageLogin(AutomationConfiguration.AppiumDriver);
		login.enterCredentials(loginMapper.getEmail(), loginMapper.getPassword());
		login.clickContinue();

		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.acceptPushNotification();
		home.checkUserName();
		home.cancelActivatePopUp();
		home.cancelQuestionPopUp();	

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(loginMapper.getUsername().toUpperCase(),home.Username.toUpperCase() );
		softAssert.assertAll();	
	}

	//@Test(priority=3,dataProvider="getVehicleData")
	public void addVehicle(VehicleMapper vehicleMapper) throws InterruptedException{
		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.dismissFoodAlert();
		String vehicleno = vehicleMapper.getLpr();
		SoftAssert softAssert = new SoftAssert();
		PageAddVehicle addvehicle = new PageAddVehicle(AutomationConfiguration.AppiumDriver);
		addvehicle.addVehicle(vehicleno);
		Thread.sleep(2000);
		String vno = addvehicle.getfirstvehiclelpr();
		softAssert.assertEquals(vehicleno, vno,"Vehicle LPR not added");
		PageAddVehicle.goBack();
		softAssert.assertAll();

	}

	//@Test(priority=5,dataProvider="getVehicleData")
	public void deleteVehicle(VehicleMapper vehicleMapper) throws InterruptedException{
		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.dismissFoodAlert();
		Thread.sleep(2000);
		String vehicleno = vehicleMapper.getLpr();
		SoftAssert softAssert = new SoftAssert();
		PageAddVehicle delvehicle = new PageAddVehicle(AutomationConfiguration.AppiumDriver);
		delvehicle.deletelpr();
		Thread.sleep(2000);
		String vno = delvehicle.getfirstvehiclelpr();
		softAssert.assertNotEquals(vehicleno, vno,"Vehicle LPR not deleted");
		PageAddVehicle.goBack();
		softAssert.assertAll();
	}
	
	//@Test(priority = 4, dataProvider = "getParkingData")
	public void startExtendStopSession(ParkingMapper parkingMapper) throws InterruptedException {
		Thread.sleep(2000);
		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.dismissFoodAlert();
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		String country = AutomationConfiguration.Country;
		SoftAssert softAssert = new SoftAssert();

		SessionUtils sessionUtils = new SessionUtils(SC, country, softAssert);
		sessionUtils.startSession(parkingMapper);

		float finalPrice = sessionUtils.extendSession(parkingMapper);
		sessionUtils.stopSession(finalPrice,parkingMapper);

		softAssert.assertAll();
		PageAddVehicle.goBack();
	}
}