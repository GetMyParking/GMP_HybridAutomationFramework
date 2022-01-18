package Pages.ConsumerWebApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ConsumerWebApp.ObjectMapper.PermitMapper;

public class DirectBuy {
	WebDriver driver;
	
	@FindBy(xpath="//span[contains(text(),'Continue')]")
	WebElement continuele;
	
	@FindBy(xpath="//span[contains(text(),'Agree')]")
	WebElement agree;
	
	@FindBy(xpath="//span[contains(text(),'Buy Permit')]")
	WebElement permitel;
	
	@FindBy(css="button[aria-label='close']")
	WebElement mypermit;
	
	@FindBy(xpath="//span[contains(text(),'Active')]")
	WebElement active;
	
	
	public DirectBuy(WebDriver driver) {
		this.driver=driver;
	}
	
	public void buypermit(PermitMapper permitmapper) {
		String permit_name = permitmapper.getCarparkname();
		driver.findElement(By.xpath("//h6[contains(text(),'"+permit_name+"')]")).click();
	}

	public void clicks() {
		continuele.click();
		agree.click();
		permitel.click();
 		mypermit.click();
		active.click();
	}
}




