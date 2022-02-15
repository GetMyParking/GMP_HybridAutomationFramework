package Dashboard;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import CommonUtility.AutomationConfiguration;
import CommonUtility.CreateSession;
import Dashboard.ObjectMapper.LoginMapper;
import Dashboard.ObjectMapper.PermitDataMapper;
import Dashboard.ObjectMapper.UrlMapper;
import DataDriven.ExcelDriven;
import Pages.Dashboard.NewUserPage;
import Pages.Dashboard.PageLogin;

public class NewUser {
	WebDriver driver;
	
	@BeforeSuite 
	public void lauchweb() throws IOException
	{
		System.err.close();
		System.setErr(System.out);
		AutomationConfiguration.Tenant = System.getProperty("Tenant");
		AutomationConfiguration.Environment = System.getProperty("Environment");
		AutomationConfiguration.Country = System.getProperty("Country");
		AutomationConfiguration.Runner = System.getProperty("Runner");
		CreateSession.readConfigFile("/src/test/java/resources/configDashboard.properties");
	}
	@DataProvider
	public LoginMapper[] getLoginData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationNewUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Runner,"Login");
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);
		return login;
	}

	@DataProvider
	public UrlMapper[] getUrlData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationNewUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Runner,"URL");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}

	@DataProvider
	public PermitDataMapper[] getPermitData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationNewUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Runner,"PermitData");
		ObjectMapper mapper = new ObjectMapper();
		PermitDataMapper []PermitData = new PermitDataMapper[1];
		PermitData[0] = mapper.readValue(data, PermitDataMapper.class);
		return PermitData;
	}


	@Test(priority=1,dataProvider="getUrlData")
	public void launchUrl(UrlMapper urlMapper){ 
		System.out.println(urlMapper.getUrl());
		AutomationConfiguration.Driver.get(urlMapper.getUrl());
	}
	
	@Test(priority=2,dataProvider="getLoginData")
	public void login(LoginMapper loginMapper) throws InterruptedException{
		PageLogin dl = PageFactory.initElements(AutomationConfiguration.Driver, PageLogin.class);
		dl.enterCredentials(loginMapper.getUsername(), loginMapper.getPassword());
		dl.clickLoginBtn();
	}

	@Test(priority=3, dataProvider="getPermitData")
	public void clickAddpermit(PermitDataMapper permitdataMapper) throws InterruptedException
	{
		NewUserPage ap = PageFactory.initElements(AutomationConfiguration.Driver, NewUserPage.class);
		ap.gotoPermit();
		ap.gotoAddPermit(permitdataMapper);
	}
}	


