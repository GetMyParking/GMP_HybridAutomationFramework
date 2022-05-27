package Pages.PayG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import CommonUtility.CreateSession;

public class Login {
	@FindBy(xpath="//button[@aria-label='Select country']")
	WebElement dropdown;
	
	public void selectCountry (String countryName) throws InterruptedException {
		dropdown.click();
		long number = (long) Math.floor(Math.random() * 9_000_00L) + 1_000_00L;
		String Number = Long.toString(number);
		Thread.sleep(2000);
		CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//span[@class='country-name' and text()='"+countryName+"']")).click();
		CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//input[@value='+91']")).sendKeys(Number);
		CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
	}
}
