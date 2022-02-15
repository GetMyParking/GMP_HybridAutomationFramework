package Dashboard;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import CommonUtility.AutomationConfiguration;
import CommonUtility.CreateSession;
import Dashboard.ObjectMapper.DATAMapper;
import Dashboard.ObjectMapper.LoginMapper;
import Dashboard.ObjectMapper.UrlMapper;
import DataDriven.ExcelDriven;
import Pages.Dashboard.PageLogin;
import Pages.Dashboard.RegUserPage;

public class RegUser {
	WebDriver driver;

	@BeforeSuite 
	public void lauchweb() throws IOException{   
		System.err.close();
		System.setErr(System.out);
		AutomationConfiguration.Tenant = System.getProperty("Tenant");
		AutomationConfiguration.Environment = System.getProperty("Environment");
		AutomationConfiguration.Runner = System.getProperty("Runner");
		CreateSession.readConfigFile("/src/test/java/resources/configDashboard.properties");
	}

	@DataProvider
	public LoginMapper[] getLoginData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationRegUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Runner,"LOGIN");
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);
		return login;
	}

	@DataProvider
	public UrlMapper[] geturlData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationRegUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Runner,"url");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}

	@DataProvider
	public DATAMapper[] getdataData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationRegUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Runner,"DATA");
		ObjectMapper mapper = new ObjectMapper();
		DATAMapper []permitdata = new DATAMapper[1];
		permitdata[0] = mapper.readValue(data, DATAMapper.class);
		return permitdata;
	}

	@Test(priority=1,dataProvider="geturlData")
	public void launchUrl(UrlMapper URLMapper)
	{ 
		System.out.println(URLMapper.getUrl());
		AutomationConfiguration.Driver.get(URLMapper.getUrl());
	}

	@Test(priority=2, dataProvider="getLoginData")
	public void login(LoginMapper loginMapper) throws InterruptedException{
		PageLogin dl = PageFactory.initElements(AutomationConfiguration.Driver, PageLogin.class);
		dl.enterCredentials(loginMapper.getUsername(), loginMapper.getPassword());
		dl.clickLoginBtn();
	}

	@Test(priority=3, dataProvider="getdataData")
	public void clickpermitpurchase(DATAMapper dataMapper) throws InterruptedException{
		RegUserPage bp = PageFactory.initElements(AutomationConfiguration.Driver, RegUserPage.class);
		bp.buypermit(dataMapper);
	}
}
