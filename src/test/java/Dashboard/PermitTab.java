package Dashboard;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import CommonUtility.CreateSession;
import Pages.Dashboard.HomePage;
import Pages.Dashboard.PageLogin;
import Pages.Dashboard.PermitTabPage;

public class PermitTab {
	
	WebDriver driver;
	
	@BeforeSuite 
	public void lauchweb() throws IOException{
		CreateSession cs = new CreateSession();
		cs.readConfigFile("/src/test/java/resources/configDashboard.properties","");
	}
	
	@Test(priority=1)
	public void login() throws InterruptedException{
		PageLogin dl = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, PageLogin.class);
		dl.enterCredentials("dashboard_user", "dashboard_user");
		dl.clickLoginBtn();
	}
	
	@Test(priority=2)
	public void clickPermit() throws InterruptedException{
		HomePage ob = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, HomePage.class);
		ob.gotoPermit();
	}
	
	@Test(priority=3)
	public void clicktab_Permit() throws InterruptedException{
		PermitTabPage tb = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, PermitTabPage.class);
		tb.gotoPermittab();
	}	
}	
		