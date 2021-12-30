package Pages.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PermitTabPage {
	WebDriver driver;
	By edtxtEmail = By.id("com.apcoaflow.consumer.staging:id/email");
	By edtxtPassword = By.id("com.apcoaflow.consumer.staging:id/edt_password");
	By btnLogin = By.id("com.apcoaflow.consumer.staging:id/btn_continue");
	By clickpermit = By.xpath("//a[@href=('/admin/permit')]");
	By clicktab_permit = By.xpath("//div[contains(text(),'Permits')]");
	
	public PermitTabPage(WebDriver driver)
	{
		this.driver = driver;
	}
	

	public void gotoPermit() throws InterruptedException
	{
		
		Thread.sleep(15000);
		driver.findElement(clickpermit).click();
		
				
	}
	
	public void gotoPermittab() throws InterruptedException
	{
		
		Thread.sleep(10000);
		driver.findElement(clicktab_permit).click();
		
				
	}
	
	

	public void clickContinue()
	{
		driver.findElement(btnLogin).click();
	}
}
