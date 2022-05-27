package Dashboard;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import CommonUtility.CreateSession;
import Dashboard.ObjectMapper.LoginMapper;
import Dashboard.ObjectMapper.UrlMapper;
import DataDriven.ExcelDriven;
import Pages.Dashboard.PageLogin;
import Pages.Dashboard.PermitcreatePage;

public class Permitcreate {
	
	WebDriver driver;
	
	@BeforeSuite 
	public void lauchweb() throws IOException{   
		System.err.close();
		System.setErr(System.out);
		CreateSession cs = new CreateSession();
		cs.readConfigFile("/src/test/java/resources/configDashboard.properties","");
		CreateSession.getAutomationConfiguration().Tenant = System.getProperty("Tenant");
		CreateSession.getAutomationConfiguration().Environment = System.getProperty("Environment");
		CreateSession.getAutomationConfiguration().Runner = System.getProperty("Runner");
	}
	
	@DataProvider
	public LoginMapper[] getLoginData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationRejectapprove.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"LOGIN");
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);
		return login;
	}
	
	@DataProvider
	public UrlMapper[] geturlData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationRejectapprove.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"url");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}
	
	
	
	
	
	@Test(priority=1, dataProvider="geturlData")
	public void launchUrl(UrlMapper URLMapper)
	{ 
		System.out.println(URLMapper.getUrl());
		CreateSession.getAutomationConfiguration().Driver.get(URLMapper.getUrl());
	}
	
	@Test(priority=2, dataProvider="getLoginData")
	public void login(LoginMapper loginMapper) throws InterruptedException{
		PageLogin dl = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, PageLogin.class);
		dl.enterCredentials(loginMapper.getUsername(), loginMapper.getPassword());
		dl.clickLoginBtn();
	}
	
	@Test(priority=3)
	public void clickpassmaster() throws InterruptedException{
		PermitcreatePage ap = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, PermitcreatePage.class);
		ap.gotoPassmaster();
		
	}
	
	@Test(priority=4)
	public void ScrollParking() throws InterruptedException{
		PermitcreatePage pp = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, PermitcreatePage.class);
		pp.ScrollParking();
		
	}
	
	
	
	
	
	
	
}	
