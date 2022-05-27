package Pages.PayG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;

public class vaidations {
	
	@FindBy(xpath ="//p[contains(@class,'jss') and contains(text(),'Your')]")
	WebElement SessionStatus;
	
	
	SoftAssert SA = new SoftAssert();
	String Session_Id = "";
	public String fetchSessionId(){
	try {
		ApcoaListeners.logInfo("Session Id while session is ongoing - "+CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//p[contains(@class,'jss') and contains(text(),'ID:')]")).getText());
		Session_Id = CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//p[contains(@class,'jss') and contains(text(),'ID:')]")).getText();
		String status = SessionStatus.getText();
		SA.assertEquals(status, "Your Session Summary");
		SA.assertAll();
		return Session_Id;
	
	} catch (Exception e) {
		e.printStackTrace();
		return "";
				
	}
}}
