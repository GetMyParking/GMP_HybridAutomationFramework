package Pages.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PermitTabPage {
	WebDriver driver;
	By clickpermit = By.xpath("//a[@href=('/admin/permit')]");
	By clicktab_permit = By.xpath("//div[contains(text(),'Permits')]");
	
	public PermitTabPage(WebDriver driver){
		this.driver = driver;
	}
	

	public void gotoPermit() throws InterruptedException{
		Thread.sleep(15000);
		driver.findElement(clickpermit).click();				
	}
	
	public void gotoPermittab() throws InterruptedException{
		Thread.sleep(10000);
		driver.findElement(clicktab_permit).click();			
	}
}
