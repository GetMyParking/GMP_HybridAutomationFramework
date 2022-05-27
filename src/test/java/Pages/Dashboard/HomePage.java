package Pages.Dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
	WebDriver driver;
	
	By clickpermit = By.xpath("//a[@href=('/admin/permit')]");
	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	public void gotoPermit() throws InterruptedException{
		Thread.sleep(15000);
		driver.findElement(clickpermit).click();				
	}
}
	
