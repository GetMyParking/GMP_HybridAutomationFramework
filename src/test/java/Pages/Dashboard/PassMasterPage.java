package Pages.Dashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PassMasterPage {
	WebDriver driver;
	
	@FindBy(xpath="//a[@href='/admin/pass-master']")
	WebElement clickpassmaster;
	
	public PassMasterPage(WebDriver driver){
		this.driver = driver;
	}
		
	public void gotoPassmaster() throws InterruptedException{	
		Thread.sleep(10000);
		clickpassmaster.click();				
	}

}