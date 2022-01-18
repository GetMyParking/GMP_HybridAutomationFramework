package Pages.ConsumerWebApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ConsumerWebApp.ObjectMapper.PermitMapper;

public class Approval {
	WebDriver driver;
	
	
	@FindBy(xpath="//span[contains(text(),'Continue')]")
	WebElement continuele;
	
    @FindBy(xpath="//span[contains(text(),'Agree')]")
	WebElement agree;
	
	@FindBy(xpath="//span[contains(text(),'Send for approval')]")
	WebElement approval;
	
	@FindBy(css="button[aria-label='close']")
	WebElement mypermits;
	
	public Approval(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectPermit(PermitMapper permitmapper) {
		String permit_name = permitmapper.getCarparkname();
		driver.findElement(By.xpath("//li[contains(text(),'"+permit_name+"')]")).click();
	}
	
	public void clickContinue() {
		continuele.click();
	
	}
	
	public void clickAgree() {
		agree.click();	
	}

	public void sendForApproval() {
		  approval.click();
		
	}
    public void myPermits() {
		mypermits.click();
    }
}

