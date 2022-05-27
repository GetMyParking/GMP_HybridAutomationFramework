package Pages.ConsumerWebApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ConsumerWebApp.ObjectMapper.PermitMapper;
import TestNGListeners.ApcoaListeners;

public class DiscountParkingPage{
	WebDriver driver;
	
	public DiscountParkingPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void choosePermit(PermitMapper permitMapper) {
		ApcoaListeners.logInfo("Selecting parking");
		String parking_name = permitMapper.getCarparkname();
		driver.findElement(By.xpath("//h6[text()='"+parking_name+"']//following::span[text()='Choose Permit'][1]")).click();
		ApcoaListeners.logInfo(parking_name+" selected sucessfully");
	}
}

