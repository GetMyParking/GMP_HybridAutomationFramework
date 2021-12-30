package Pages.Dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PassMasterPage {
	WebDriver driver;
	By edtxtEmail = By.id("com.apcoaflow.consumer.staging:id/email");
	By edtxtPassword = By.id("com.apcoaflow.consumer.staging:id/edt_password");
	By btnLogin = By.id("com.apcoaflow.consumer.staging:id/btn_continue");
	By clickpassmaster = By.xpath("//a[@href='/admin/pass-master']");
	
	public PassMasterPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	
	public void gotoPassmaster() throws InterruptedException
	{
		
		Thread.sleep(10000);
		driver.findElement(clickpassmaster).click();
		
				
	}

	public void clickContinue()
	{
		driver.findElement(btnLogin).click();
	}
}