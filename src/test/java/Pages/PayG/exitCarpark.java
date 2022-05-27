package Pages.PayG;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;

public class exitCarpark {
	
	SoftAssert SA = new SoftAssert();
	

	public void exitPark(String ID,String ParkingName,String DiscountName) throws IOException, InterruptedException{
		Thread.sleep(5000);
//		AutomationConfiguration.Driver.get("https://stagingpermit-two.getmyparking.com/reverseqr/sessionprocessing/exit/PAYGOFFSTREET/PAYGEXIT");
//		AutomationConfiguration.Driver.get("https://permit.getmyparking.com/reverseQR/sessionProcessing/exit/PAYGOFF/PRODPAYGEXIT");
		Thread.sleep(20000);
		String SessionId=CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//p[@class='jss42']")).getText();
//		String Discount = AutomationConfiguration.Driver.findElement(By.xpath("//p[@class='jss56']")).getText();
		String parkingName=CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//p[@class='jss53']")).getText();
		ApcoaListeners.logInfo("Session Id while Exit - "+SessionId);
		ApcoaListeners.logInfo("Parking name while Exit - "+parkingName);
		String totalAmount=CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//p[@class='jss59']")).getText();
		String sessionStatus=CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//p[@class='jss41']")).getText();
		SA.assertEquals(SessionId, ID);
		SA.assertEquals(parkingName, ParkingName);
//		SA.assertEquals(Discount, DiscountName);
		SA.assertEquals(sessionStatus,"Your Session Summary");
		SA.assertAll();
		System.out.println("Total Amount = "+totalAmount);
		Thread.sleep(20000);
		CreateSession.getAutomationConfiguration().Driver.quit();
	}

}
