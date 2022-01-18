package Pages.ConsumerWebApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectParking{
	WebDriver driver;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div[3]/div/div[3]/div[2]/button[2]/span[1]")
	WebElement permit;
	
	public SelectParking(WebDriver driver){
		this.driver = driver;
	}
	
	public void choosePermit() {
		permit.click();     
	}
}
