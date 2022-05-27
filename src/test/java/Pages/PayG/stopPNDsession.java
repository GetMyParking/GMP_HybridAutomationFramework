package Pages.PayG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import CommonUtility.CreateSession;

public class stopPNDsession {

	@FindBy(xpath="//p[contains(@class,'jss') and contains(text(),'ID:')]")
	WebElement SessionId;
	String Session_Id ="";
	SoftAssert SA = new SoftAssert();
	public void stopPND(String ID,String ParkingName,String DiscountName) throws InterruptedException {
	
		CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//span[text()='Stop Session']")).click();
	Thread.sleep(5000);
	CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//span[text()='Yes']")).click();
	String totalAmount=CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//p[@class='jss122']")).getText();
	SA.assertAll();
	System.out.println("Total Amount = "+totalAmount);
	Thread.sleep(20000);
	CreateSession.getAutomationConfiguration().Driver.quit();
	
}}
