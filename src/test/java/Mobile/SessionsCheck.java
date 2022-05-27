package Mobile;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.databind.ObjectMapper;
import CommonUtility.CreateSession;
import DataDriven.ExcelDriven;
import Mobile.ObjectMapper.LoginMapper;
import Mobile.ObjectMapper.ParkingMapper;
import Pages.Mobile.PageHomeApcoa;
import Pages.Mobile.PageLogin;
import Pages.Mobile.PageMapScreen;
import Pages.Mobile.PageStartSession;

public class SessionsCheck {

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
	public void Teardown(){
		CreateSession.getAutomationConfiguration().AppiumDriver.quit();
	}

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
	 * method to Select country from the select country page
	 * 
	 */
	@Test(priority=1)
	public void selectCountry() throws Exception{
		SmokePnd spnd = new SmokePnd();
		spnd.selectCountry();
	}


	/**
	 * method to enter credentials and check whether user is able to login or not
	 * @param loginMapper contains information of user credentials and username
	 * 
	 */
	@Test(priority=2,dataProvider="getLoginData")
	public void loginAppcoa(LoginMapper loginMapper) throws InterruptedException{
		SmokePnd spnd = new SmokePnd();
		spnd.loginAppcoa(loginMapper);
	}

	@Test(priority=3)
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

	@Test(priority=23,dataProvider = "getParkingData")
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


}
