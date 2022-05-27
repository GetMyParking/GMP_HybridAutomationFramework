package Dashboard;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import CommonUtility.CreateSession;
import Pages.Dashboard.PageLogin;
import Pages.Dashboard.PassMasterPage;

public class PassMaster {
	
	WebDriver driver;
	
	@BeforeSuite 
	public void lauchweb() throws IOException{
		CreateSession cs = new CreateSession();
		cs.readConfigFile("/src/test/java/resources/Dashboard/configfiles/configDashboard.properties","");
	}
	
	@Test(priority=1)
	public void login() throws InterruptedException{
		PageLogin dl = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, PageLogin.class);
		dl.enterCredentials("dashboard_user", "dashboard_user");
		dl.clickLoginBtn();
	}

	@Test(priority=2)
	public void clickpassmaster() throws InterruptedException{
		PassMasterPage pm = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, PassMasterPage.class);
		pm.gotoPassmaster();
	}
	
	
}	
