package Mobile;

import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import CommonUtility.CreateSession;
import DataDriven.ExcelDriven;
import Mobile.ObjectMapper.LoginMapper;
import Mobile.ObjectMapper.ParkingMapper;
import Pages.Mobile.PageMapScreen;
import Pages.Mobile.PagePaymentScreen;
import Pages.Mobile.PageSessionDetails;
import Pages.Mobile.QRScanPage;

public class CheckViewDetails {
	
	/**
	 * method to initialize the appium and launch application
	 *  
	 */
	@Parameters({ "Environment", "Country","Tenant","Platform" })
	@BeforeClass
	public void initializeDriver(String ennv, String country,String tenant, String platform) throws IOException{
		CreateSession cs = new CreateSession();
		cs.readConfigFile("/src/test/java/resources/Mobile/ConfigFiles/"+tenant+".properties",platform);
		CreateSession.getAutomationConfiguration().Country = country;
		CreateSession.getAutomationConfiguration().Tenant = tenant;
		CreateSession.getAutomationConfiguration().Environment = ennv;
		CreateSession.getAutomationConfiguration().Platform = platform;
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
	String ActiveSessionId="";
	String AppliedPromo = "";
	String AmountPaid = "";
	@Test(priority=4,dataProvider = "getParkingData")
		public void StartPostpaynew(ParkingMapper parkingMapper) throws InterruptedException, IOException 
		{
			String parkingName = parkingMapper.getParkingidentifier();
			PagePaymentScreen pps = new PagePaymentScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
			pps.changingDefaultProfile(CreateSession.getAutomationConfiguration().Profile);
			Thread.sleep(3000);
			PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
			pms.FullParkingName = "";
			pms.GettheParking(parkingName);

			QRScanPage SC = new QRScanPage(CreateSession.getAutomationConfiguration().AppiumDriver);

			ActiveSessionId= SC.StartPostpaySession();
			Thread.sleep(40000);

		}

		@Test(priority=5)
		public void GmpPostpayWithDiscounts() throws InterruptedException, IOException
		{
			QRScanPage SC = new QRScanPage(CreateSession.getAutomationConfiguration().AppiumDriver);
			AppliedPromo=SC.GmpPostpayWithDiscounts("FLAT10OFF");
		}

		@Test(priority=6,dataProvider = "getParkingData")
		public void StopPostpay(ParkingMapper parkingMapper) throws InterruptedException, IOException{

			QRScanPage SC = new QRScanPage(CreateSession.getAutomationConfiguration().AppiumDriver);
			AmountPaid=SC.StopPostpaySessionNew(ActiveSessionId,AppliedPromo);
		}


		@Test(priority=40)
		public void ViewDetails()throws InterruptedException{

			PageSessionDetails SC = new PageSessionDetails(CreateSession.getAutomationConfiguration().AppiumDriver);
			SC.ViewDetails(AmountPaid);
		}

}
