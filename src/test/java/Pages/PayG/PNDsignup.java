package Pages.PayG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import CommonUtility.CreateSession;

public class PNDsignup {
	@FindBy(xpath="//button[@aria-label='increase' and @type = 'button']")
	WebElement addTime;
	
	public void signupPND () throws InterruptedException {
		Thread.sleep(5000);
		for(int i =0;i<10;i++)
		addTime.click();
		Thread.sleep(2000);
		String licensePlate = vehicleNumber();
		CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//input[@placeholder='Enter license plate details' and @type = 'text']")).sendKeys(licensePlate);
//		AutomationConfiguration.Driver.findElement(By.xpath("//span[text()='Proceed to Payment']")).click();		
}
	public String vehicleNumber() {
		
		  int alpha1 = 'A' + (int)(Math.random() * ('Z' - 'A'));
		  int alpha2 = 'A' + (int)(Math.random() * ('Z' - 'A'));
		  int alpha3 = 'A' + (int)(Math.random() * ('Z' - 'A')); 

		  int digit1 = (int)(Math.random() * 10);
		  int digit2 = (int)(Math.random() * 10);
		  int digit3 = (int)(Math.random() * 10);
		  int digit4 = (int)(Math.random() * 10);

		  String LPR = ("" + (char)(alpha1) + ((char)(alpha2)) + 
		   ((char)(alpha3)) + digit1 + digit2 + digit3 + digit4);
		return LPR;
		 }
		
	}
