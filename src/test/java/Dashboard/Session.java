package Dashboard;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import CommonUtility.CreateSession;
import Dashboard.ObjectMapper.DATAMapper;
import Dashboard.ObjectMapper.LoginMapper;
import Dashboard.ObjectMapper.SessionMapper;
import Dashboard.ObjectMapper.UrlMapper;
import DataDriven.ExcelDriven;
import Pages.Dashboard.PageLogin;
import Pages.Dashboard.RegUserPage;
import Pages.Dashboard.SessionPage;

public class Session {
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
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/Session.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"LOGIN");
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);
		return login;
	}

	@DataProvider
	public UrlMapper[] geturlData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/Session.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"url");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}

	@DataProvider
	public SessionMapper[] getSessionData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/Session.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"SessionData");
		ObjectMapper mapper = new ObjectMapper();
		SessionMapper [] sessiondata = new SessionMapper[1];
		sessiondata[0] = mapper.readValue(data, SessionMapper.class);
		return sessiondata;
	}

	@DataProvider
	public DATAMapper[] getdataData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationRegUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"DATA");
		ObjectMapper mapper = new ObjectMapper();
		DATAMapper []permitdata = new DATAMapper[1];
		permitdata[0] = mapper.readValue(data, DATAMapper.class);
		return permitdata;
	}


	@Test(priority=1,dataProvider="geturlData")
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


	@Test(priority=3, dataProvider="getSessionData")
	public void clickpermitpurchase(SessionMapper sessionMapper) throws InterruptedException{
		SessionPage sp = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, SessionPage.class);
		sp.gotoconsumer(sessionMapper, 20);
	}


	@Test(priority=4, dataProvider="getdataData")
	public void clickpermitpurchaseagain(DATAMapper dataMapper) throws InterruptedException{
		RegUserPage bp = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, RegUserPage.class);
		bp.buypermit(dataMapper); 
	}



	@Test(priority=5, dataProvider="getSessionData")
	public void clickpermitpurchaseagain(SessionMapper sessionMapper) throws InterruptedException{
		SessionPage mp = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, SessionPage.class);
		mp.gotoconsumer(sessionMapper, 0); 
	}






	@Test(priority=6, dataProvider="getSessionData")
	public void clickpermitpurchasenew(SessionMapper sessionMapper) throws InterruptedException{
		SessionPage mp = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, SessionPage.class);
		mp.gotoconsumer(sessionMapper, 20); 
	}







	//	@Test(priority=1)
	//	public void login() throws InterruptedException{
	//		PageLogin dl = PageFactory.initElements(AutomationConfiguration.Driver, PageLogin.class);
	//		dl.enterCredentials("dashboard_user", "dashboard_user");
	//		dl.clickLoginBtn();
	//	}

	//	@Test(priority=2)
	//	public void clickconsumersupport() throws InterruptedException{
	//		SessionPage sc = PageFactory.initElements(AutomationConfiguration.Driver, SessionPage.class);
	//		sc.gotoconsumer();
	//	}


}
