/*
-------------------------------------------------------------
Author Name: Sudeshna Banerjee

Date:10-Feb-2022

Purpose /Description: Permit purchase of a New User through dashboard flow
	
-------------------------------------------------------------

*/

package Dashboard;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import CommonUtility.CreateSession;
import Dashboard.ObjectMapper.LoginMapper;
import Dashboard.ObjectMapper.PermitDataMapper;
import Dashboard.ObjectMapper.UrlMapper;
import DataDriven.ExcelDriven;
import Pages.Dashboard.NewUserPage;
import Pages.Dashboard.PageLogin;
/*
 In New User class - 
 Launch of dashboard url in chrome browser
 Login to the dashboard with the creds mentioned in the excel sheet
 click to permit management module and add new permit fill the parking name permit etc according to
 the details mentioned in the excel sheet
 
 
 
 */
public class NewUser {
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
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationNewUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"Login");
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);
		return login;
	}
	
	/* the method to read the excel sheet to login the dashboard url
	   In the login mapper we have mapped the login data that we have in the excel sheet 
	 */

	@DataProvider
	public UrlMapper[] getUrlData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationNewUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"URL");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}
	
	/* the method to read the excel sheet to enter url 
	   In the url mapper we have mapped the url data that we have in the excel sheet 
	 */

	@DataProvider
	public PermitDataMapper[] getPermitData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AutomationNewUser.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Runner,"PermitData");
		ObjectMapper mapper = new ObjectMapper();
		PermitDataMapper []PermitData = new PermitDataMapper[1];
		PermitData[0] = mapper.readValue(data, PermitDataMapper.class);
		return PermitData;
	}
	
	/* the method to read the excel sheet to read the permit data required to fill the details of new user permit purchase
	    In the PermitData mapper we have mapped the permit data that we have in the excel sheet to proceed after login to the url
	 
	 */


	@Test(priority=1,dataProvider="getUrlData")
	public void launchUrl(UrlMapper urlMapper){ 
		System.out.println(urlMapper.getUrl());
		CreateSession.getAutomationConfiguration().Driver.get(urlMapper.getUrl());
	}
	
	@Test(priority=2,dataProvider="getLoginData")
	public void login(LoginMapper loginMapper) throws InterruptedException{
		PageLogin dl = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, PageLogin.class);
		dl.enterCredentials(loginMapper.getUsername(), loginMapper.getPassword());
		dl.clickLoginBtn();
	}

	@Test(priority=3, dataProvider="getPermitData")
	public void clickAddpermit(PermitDataMapper permitdataMapper) throws InterruptedException
	{
		NewUserPage ap = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, NewUserPage.class);
		ap.gotoPermit();
		ap.gotoAddPermit(permitdataMapper);
	}
}	


