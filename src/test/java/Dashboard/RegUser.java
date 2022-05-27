/*
-------------------------------------------------------------
Author Name: Sudeshna Banerjee

Date:15-Feb-2022

Purpose /Description: Permit purchase of a Registered User through dashboard flow
	
-------------------------------------------------------------

*/

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
import Dashboard.ObjectMapper.UrlMapper;
import DataDriven.ExcelDriven;
import Pages.Dashboard.PageLogin;
import Pages.Dashboard.RegUserPage;
/*
 In RegUser Class -
 Launching of url in the chrome browser
 Login to the dashboard with the creds mentioned in the excel sheet
 click to permit management module and add new permit fill the parking name permit etc according to
 the details mentioned in the excel sheet
 */
public class RegUser {
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
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationRegUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"LOGIN");
		ObjectMapper mapper = new ObjectMapper(); 
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);
		return login;
	}
	
	/* method to read the excel sheet to login to the dashboard
	   In the login mapper we have mapped the login data that we have in the excel sheet 
	 */

	@DataProvider
	public UrlMapper[] geturlData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationRegUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"url");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}
	
	/* the method to read the excel sheet to enter url
	   In the url mapper we have mapped the url data that we have in the excel sheet 
	 */

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
	
	/* the method to read the excel sheet to read the data required for purchase of permit of registered user
	 In the DATA mapper we have mapped the  data that we have in the excel sheet to proceed after login to the url
	 */

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

	@Test(priority=3, dataProvider="getdataData")
	public void clickpermitpurchase(DATAMapper dataMapper) throws InterruptedException{
		RegUserPage bp = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, RegUserPage.class);
		bp.buypermit(dataMapper);
	}
}
