package ConsumerApp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import CommonUtility.AutomationConfiguration;
import CommonUtility.CreateSession;
import ConsumerWebApp.ObjectMapper.LocationMapper;
import ConsumerWebApp.ObjectMapper.LoginMapper;
import ConsumerWebApp.ObjectMapper.PermitMapper;
import ConsumerWebApp.ObjectMapper.UrlMapper;
import DataDriven.ExcelDriven;
import Pages.ConsumerWebApp.Approval;
import Pages.ConsumerWebApp.ChooseLocation;
import Pages.ConsumerWebApp.City;
import Pages.ConsumerWebApp.DirectBuy;
import Pages.ConsumerWebApp.LoginPage;
import Pages.ConsumerWebApp.SelectParking;


public class ConsumerTest {
	
	@Parameters({"Runner"})
	@BeforeSuite
	public void initializeDriver(String runner) throws IOException{
		System.err.close();
		System.setErr(System.out);
		System.out.println(runner);
		AutomationConfiguration.Runner = runner;
		CreateSession.readConfigFile("/src/test/java/resources/configConsumerApp.properties");
		
	}

	@DataProvider
	public LoginMapper[] getLoginData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",AutomationConfiguration.Runner,"Login");
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);
		return login;
	}

	@DataProvider
	public UrlMapper[] getUrlData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",AutomationConfiguration.Runner,"URL");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}

	@DataProvider
	public LocationMapper[] getLocationData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",AutomationConfiguration.Runner,"Location");
		ObjectMapper mapper = new ObjectMapper();
		LocationMapper []LocationData = new LocationMapper[1];
		LocationData[0] = mapper.readValue(data, LocationMapper.class);
		return LocationData;
	}

	@DataProvider
	public PermitMapper[] getApprovalData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",AutomationConfiguration.Runner,"Approval");
		ObjectMapper mapper = new ObjectMapper();
		PermitMapper []ApprovalData = new PermitMapper[1];
		ApprovalData[0] = mapper.readValue(data, PermitMapper.class);
		return ApprovalData;
	}

	@DataProvider
	public PermitMapper[] getDirectData() throws Exception	{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",AutomationConfiguration.Runner,"Direct Buy");
		ObjectMapper mapper = new ObjectMapper();
		PermitMapper []DirectData = new PermitMapper[1];
		DirectData[0] = mapper.readValue(data, PermitMapper.class);
		return DirectData;
	}

	@Test(priority=0,dataProvider="getUrlData")
	public void gotoUrl(UrlMapper urlMapper) {
		AutomationConfiguration.Driver.get(urlMapper.getUrl());
		AutomationConfiguration.Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test(priority=1,dataProvider="getLoginData" )
	public void login(LoginMapper loginMapper) throws InterruptedException{
		Thread.sleep(2000);
		LoginPage login = new LoginPage(AutomationConfiguration.Driver);
		PageFactory.initElements(AutomationConfiguration.Driver,login);
		login.enterCredentials(loginMapper.getUseremail(), loginMapper.getPassword());
		login.clickLoginBtn();

	}
	@Test(priority=2,dataProvider="getLocationData")
	public void location(LocationMapper locationMapper) throws InterruptedException{
		ChooseLocation choose = new ChooseLocation(AutomationConfiguration.Driver);
		PageFactory.initElements(AutomationConfiguration.Driver,choose);
		choose.enterLocation(locationMapper.getLocation());
	}


	@Test(priority=3)
	public void clickPermit() throws InterruptedException{
		SelectParking parking = new SelectParking(AutomationConfiguration.Driver);
		PageFactory.initElements(AutomationConfiguration.Driver, parking);
		parking.choosePermit();
	}

	@Test(priority=4,dataProvider="getLocationData")
	public void clicks(LocationMapper locationMapper) throws InterruptedException{
		City clicks = new City(AutomationConfiguration.Driver);
		PageFactory.initElements(AutomationConfiguration.Driver,clicks);
		clicks.choosepermit();
		clicks.entercity(locationMapper.getLocation());
	}

	@Test(priority=5,dataProvider="getApprovalData")
	public void approval(PermitMapper permitMapper) throws InterruptedException{
		Approval send = new Approval(AutomationConfiguration.Driver);
		PageFactory.initElements(AutomationConfiguration.Driver,send);
		send.selectPermit(permitMapper);
		send.clickContinue();
		send.clickAgree();
	}

	@Test(priority=6,dataProvider="getDirectData" )
	public void buy(PermitMapper permitMapper) throws InterruptedException{
		DirectBuy buy = new DirectBuy(AutomationConfiguration.Driver);
		PageFactory.initElements(AutomationConfiguration.Driver,buy);
		buy.buypermit(permitMapper);
		buy.clicks();
	}
}	
