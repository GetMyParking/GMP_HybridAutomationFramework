package Mobile;

import java.io.File;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.databind.ObjectMapper;
import CommonUtility.CreateSession;
import DataDriven.ExcelDriven;
import Mobile.ObjectMapper.LoginMapper;
import Pages.Mobile.PageBuyPass;
import Pages.Mobile.PageMapScreen;
import Pages.Mobile.PageStartSession;
import TestNGListeners.ApcoaListeners;
import io.qameta.allure.Description;

public class CheckButtons {


	@BeforeSuite
	public static void  setupAutomation() {
		try {
			String directory = System.getProperty("user.dir").toString() + "/allure-results";
			File file = new File(directory);      
			String[] myFiles;    
			if (file.isDirectory()) {
				myFiles = file.list();
				System.out.println(myFiles.length);
				for (int i = 0; i < myFiles.length; i++) {

					File myFile = new File(file, myFiles[i]); 
					myFile.delete();
				}
			}
		}catch(Exception e) {}

	}

	public static void main(String []agrs) {
		setupAutomation();
	}

	/**
	 * method to initialize the appium and launch application
	 *  
	 */
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
	@Description("first test in allure")
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


	/**
	 * method to check EV charging Buttons
	 * 
	 * 
	 */
	@Test(priority=3)
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

	/**
	 * method to check EMPM parking -> Start Parking Button
	 * 
	 * 
	 */
	@Test(priority = 4)
	public void EpmpStartButtonCheck() throws InterruptedException {
		Thread.sleep(2000);
		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		SoftAssert softAssert = pms.EpmpCheck();
		softAssert.assertAll();

	}

	@Test(priority=5)
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

	@Test(priority=6)
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


}
