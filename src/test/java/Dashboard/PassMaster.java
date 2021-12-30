package Dashboard;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import CommonUtility.AutomationConfiguration;
import CommonUtility.CreateSession;
import Pages.Dashboard.PageLogin;

public class PassMaster {
	
	WebDriver driver;
	
	@Test(priority=1)
	public void login() throws InterruptedException
	{
		PageLogin dl = PageFactory.initElements(AutomationConfiguration.Driver, PageLogin.class);
		dl.enterCredentials("dashboard_user", "dashboard_user");
		dl.clickLoginBtn();
	}

	@Test(priority=2)
	public void clickpassmaster() throws InterruptedException
	{
		Pages.Dashboard.PassMasterPage pm = PageFactory.initElements(AutomationConfiguration.Driver, Pages.Dashboard.PassMasterPage.class);
		pm.gotoPassmaster();
	}
		
	@BeforeSuite 
	public void lauchweb() throws IOException
	{
		CreateSession.readConfigFile("/src/test/java/resources/configDashboard.properties");
	}
	
}	
