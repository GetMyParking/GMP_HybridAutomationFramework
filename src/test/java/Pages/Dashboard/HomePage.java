package Pages.Dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
	WebDriver driver;
	By edtxtEmail = By.id("com.apcoaflow.consumer.staging:id/email");
	By edtxtPassword = By.id("com.apcoaflow.consumer.staging:id/edt_password");
	By btnLogin = By.id("com.apcoaflow.consumer.staging:id/btn_continue");
	By clickpermit = By.xpath("//a[@href=('/admin/permit')]");
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	
	public void gotoPermit() throws InterruptedException
	{
		
		Thread.sleep(15000);
		driver.findElement(clickpermit).click();
		
				
	}

	public void clickContinue()
	{
		driver.findElement(btnLogin).click();
	}
}
