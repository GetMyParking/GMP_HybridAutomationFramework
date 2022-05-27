package Dashboard;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import CommonUtility.CreateSession;
import Dashboard.ObjectMapper.LoginMapper;
import Dashboard.ObjectMapper.UrlMapper;
import DataDriven.ExcelDriven;
import Pages.Dashboard.ActiveandInactivePage;
import Pages.Dashboard.AddParkingPage;
import Pages.Dashboard.CombinedPage;
import Pages.Dashboard.PageLogin;
import Pages.Dashboard.PassMasterPage;


public class ActiveandInactive {
		@Parameters({"Runner"})
		@BeforeClass
		public void initializeDriver(String myrunner) throws IOException{
			System.err.close();
			System.setErr(System.out);
//			System.out.println(runner);
			System.out.println(System.getProperty("Runner"));
			CreateSession.getAutomationConfiguration().Runner = myrunner;
			CreateSession cs = new CreateSession();
			cs.readConfigFile("/src/test/java/resources/Dashboard/configfiles/configDashboard.properties","");
			
		}
		
		@DataProvider
		public LoginMapper[] getLoginData() throws Exception{
			String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Dashboard/Dataset/ActiveAndInactive.xlsx";
			ExcelDriven.readExcelFile(excelfilepath,"ActiveAndInactive");
			String data = ExcelDriven.readDataRowandColumn("ActiveAndInactive",CreateSession.getAutomationConfiguration().Runner,"Login");
			ObjectMapper mapper = new ObjectMapper();
			LoginMapper []login = new LoginMapper[1];
			login[0] = mapper.readValue(data, LoginMapper.class);
			return login;
		}
		
		@DataProvider
		public UrlMapper[] getUrlData() throws Exception{
			String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Dashboard/Dataset/ActiveAndInactive.xlsx";
			ExcelDriven.readExcelFile(excelfilepath,"ActiveAndInactive");
			String data = ExcelDriven.readDataRowandColumn("ActiveAndInactive",CreateSession.getAutomationConfiguration().Runner,"URL");
			ObjectMapper mapper = new ObjectMapper();
			UrlMapper []url = new UrlMapper[1];
			url[0] = mapper.readValue(data, UrlMapper.class);
			return url;
		}
		
		/***
		 * Test for URL 
		 */
		@Test(priority=0,dataProvider="getUrlData")
		public void gotoUrl(UrlMapper urlMapper) {
			CreateSession.getAutomationConfiguration().Driver.get(urlMapper.getUrl());
			CreateSession.getAutomationConfiguration().Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		}
		
		/***
		 * Test for login credentials 
		 */
		@Test(priority=2,dataProvider="getLoginData")
		public void login(LoginMapper loginMapper) throws InterruptedException{
			Thread.sleep(2000);
			PageLogin login = new PageLogin(CreateSession.getAutomationConfiguration().Driver);
			PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,login);
			login.validateText();
			login.enterCredentials(loginMapper.getUsername(), loginMapper.getPassword());
			login.clickLoginBtn();

		}
		
		@Test(priority=3)
		public void passMaster() throws InterruptedException{
			Thread.sleep(2000);
			PassMasterPage pass = new PassMasterPage(CreateSession.getAutomationConfiguration().Driver);
			PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,pass);
			pass.gotoPassmaster();
			Thread.sleep(5000);
			pass.clickicon();
		}
		
		@Test(priority=4)
		public void Active() throws InterruptedException{
			Thread.sleep(2000);
			ActiveandInactivePage active = new ActiveandInactivePage(CreateSession.getAutomationConfiguration().Driver);
			PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,active);
			active.selectactive();
			Thread.sleep(5000);
			String active_id = active.validateId();
			active.selectinactive();
			Thread.sleep(5000);
			String inactive_id = active.verifyId();
			Assert.assertNotSame(active_id,inactive_id);	
		}

		@Test(priority=5)
		public void Combined() throws InterruptedException{
			Thread.sleep(2000);
			CombinedPage combined = new CombinedPage(CreateSession.getAutomationConfiguration().Driver);
			PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,combined);
			combined.selectype();
			Thread.sleep(3000);
			combined.selecpermit();
			Thread.sleep(3000);


		}
		
		@Test(priority=6)
		public void overView() throws InterruptedException{
			Thread.sleep(2000);
			AddParkingPage pass = new AddParkingPage(CreateSession.getAutomationConfiguration().Driver);
			PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,pass);
			pass.gotoOverview();
			pass.createParking();
			pass.open_timezone();
			Thread.sleep(3000);
			pass.close_timezone();
			Thread.sleep(5000);
			pass.add();
			Thread.sleep(3000);
			pass.searchparking();

			
		}
		
}