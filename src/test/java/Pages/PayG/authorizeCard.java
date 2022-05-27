package Pages.PayG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import CommonUtility.CreateSession;

public class authorizeCard {
	@FindBy(className="jss116")
	WebElement promoName;
	String Discount_name = "";
	public String clickAutharizeButton() throws InterruptedException {
		Thread.sleep(10000);
//		String Discount_name = promoName.getText();
//		System.out.println("Discount Name is - "+Discount_name);
		
		CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//span[text()='Authorize Card']")).click();
		return Discount_name;	
	}

}
