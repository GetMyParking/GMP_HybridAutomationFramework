package Pages.PayG;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;

public class GetTheParking {
	SoftAssert SA=new SoftAssert();
	String parking_Name = "";
	
	@FindBy(xpath = "//p[contains(@class,'jss') and contains(text(),'PAYG')]")
	WebElement parkingName;
	
	@FindBy(xpath = "//p[contains(@class,'jss') and contains(text(),'PAYG')]")
	WebElement parkingNameStaging;

	public String getTheParking() {
		if(CreateSession.getAutomationConfiguration().Environment.equalsIgnoreCase("QA"));{
			System.out.println("QA");
			ApcoaListeners.logInfo("Parking name while session is ongoing - "+parkingName.getText());
			parking_Name  = parkingName.getText();
		}
		if(CreateSession.getAutomationConfiguration().Environment.equalsIgnoreCase("Staging"));
		{
			ApcoaListeners.logInfo("Parking name while session is ongoing - "+parkingNameStaging.getText());
			parking_Name = parkingNameStaging.getText();
		}
		return parking_Name;
}}
