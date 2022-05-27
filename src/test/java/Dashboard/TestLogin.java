package Dashboard;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import CommonUtility.CreateSession;
import Pages.Dashboard.PageLogin;

public class TestLogin {
	
	WebDriver driver;
	
	@Test
	public void login() throws InterruptedException{
		PageLogin dl = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, PageLogin.class);
		dl.enterCredentials("dashboard_user", "dashboard_user");
		dl.clickLoginBtn();
	}
	
	
	@BeforeMethod
	public void lauchweb() throws IOException{
		CreateSession cs = new CreateSession();
		cs.readConfigFile("/src/test/java/resources/configDashboard.properties","");
	}

}
