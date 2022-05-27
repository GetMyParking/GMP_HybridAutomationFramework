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
import Pages.Mobile.PageSessionDetails;

public class CheckConvenienceFees {
	
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

	
	@Test(priority=4,dataProvider = "getParkingData")
	public void CheckConvenienceFee(ParkingMapper parkingMapper) throws InterruptedException
	{
		PageSessionDetails SC = new PageSessionDetails(CreateSession.getAutomationConfiguration().AppiumDriver);
		String parkingName = parkingMapper.getParkingidentifier();
		PageMapScreen pms = new PageMapScreen(CreateSession.getAutomationConfiguration().AppiumDriver);
		pms.FullParkingName = "";
		pms.GettheParking(parkingName);
		SC.ConvenienceFee();
	}

}
