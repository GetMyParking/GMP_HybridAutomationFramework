package Mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.databind.ObjectMapper;
import CommonUtility.CreateSession;
import DataDriven.ExcelDriven;
import Mobile.ObjectMapper.CorporateProfileMapper;
import Mobile.ObjectMapper.LoginMapper;
import Pages.Mobile.PageMapScreen;
import Pages.Mobile.PagePaymentScreen;
import Pages.Mobile.PageStartSession;
import TestNGListeners.ApcoaListeners;

public class Profiles {


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
	public void ChangingDefaultProfile() throws InterruptedException{
		//changingDefaultProfile("Corporate");
		PagePaymentScreen pps = new PagePaymentScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pps.changingDefaultProfile("Personal Profile");//Personal Profile Business
	}

	@Test(priority=4)
	public void BuisnessProfileCreation() throws InterruptedException{
		PagePaymentScreen pps = new PagePaymentScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pps.DeleteBuisnessProfile();
		pps.AddBuisnessprofile();
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


	@Test(priority=5,dataProvider = "getCorporateProfileMapper")
	public void CorporateProfileCreation(CorporateProfileMapper mapper) throws InterruptedException{
		SoftAssert SA=new SoftAssert();
		PagePaymentScreen SC = new PagePaymentScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		boolean Temp1=SC.DeleteCorporateProfile(mapper.getcorporate()+" Profile");//Region Värmland Profile
		SA.assertEquals(Temp1, true);
		boolean Temp2=SC.AddCorporateprofile(mapper.getcorporate(),mapper.getcorporateid(),mapper.getemail());
		SA.assertEquals(Temp2,true);
		SA.assertAll();

	}

	@Test(priority=6)
	public void ProfileAutoSwitch() throws InterruptedException
	{
		/* require to add the tariff name
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

		for (Map<String, String> map : myMap){
			ApcoaListeners.logInfo("Searching the parking"+map);
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




}
