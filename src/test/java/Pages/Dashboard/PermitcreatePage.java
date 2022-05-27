package Pages.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;

import java.util.List;

public class PermitcreatePage {
	WebDriver driver;
	SoftAssert SA = new SoftAssert();
	
	@FindBy(xpath="//a[@href='/admin/pass-master']")
	WebElement clickpassmaster;
	
	@FindBy(xpath="//span[contains(text(), 'Add Permit Master')]//parent::button")
	WebElement permitmaster;
	
	@FindBy(xpath="//div[@id='companyId']")
	WebElement company;
	
	@FindBy(xpath="//li[contains(text(),'Gmp Test (for Permit)')]")
	WebElement companyname;
	
	@FindBy(xpath="//input[@id='name']")
	WebElement permitname;
	
	@FindBy(xpath="//input[@id='number']")
	WebElement permitduration;
	
	@FindBy(xpath="//input[@id='description']")
	WebElement description;
	
	@FindBy(xpath="//div[@id='isVisible']//input[@value='1']")
	WebElement visible;
	
	@FindBy(xpath="//div[@id='isApprovalRequired']//input[@value='1']")
	WebElement approve;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submit;
	
	@FindBy(xpath="//div[@class='ant-select-selection__placeholder']")
	WebElement Parking;
	
	@FindBy(xpath="//li[@class='ant-select-search ant-select-search--inline']")
	WebElement parkinglot;
	
	@FindBy(xpath="//span[@class='ant-switch-inner']//parent::button")
	WebElement access;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement addpermit;
	
	@FindBy(xpath="//span[@class='ant-modal-close-x']//parent::button")
	WebElement popup;
	
	public PermitcreatePage(WebDriver driver){
		this.driver = driver;
		
	
	}
	
	public void ScrollParking() {
		JavascriptExecutor executor = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
		executor.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=400");
	}
	
	public void selectparking(String Brand) {
		System.out.print("select parking function");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> brand_with_class = driver.findElements(By.xpath("//li[@class='ant-select-dropdown-menu-item']"));
		List<WebElement> brand_with_role  = driver.findElements(By.xpath("//li[@role='option']"));
		System.out.println(brand_with_class.size());
		System.out.println("print brand_with_class");
		for(WebElement i:brand_with_class) {
			System.out.println(i.getText()+" ");
		}
		System.out.println(brand_with_role.size());
		System.out.println("print brand_with_role");
		for(WebElement i:brand_with_role) {
			System.out.println(i.getText()+" ");
		}
		int loop=10;
		int is_found=0;
		while(loop>0 && is_found==0) {
			List<WebElement> brand_with_role_here  = driver.findElements(By.xpath("//li[@role='option']"));
			for(WebElement i:brand_with_role_here) {
				System.out.println(i.getText()+" ");
				if(i.getText().equalsIgnoreCase(Brand)) {
					i.click();
					is_found=1;
					break;
				}
			}
			if(is_found==0)
				ScrollParking();
		}
		if(is_found==0) {
			ApcoaListeners.logInfo(Brand+"parking not found");
		}
		SA.assertNotEquals(is_found, 0);
	
}
	
	
	public void selectparkinglot(String Brand) {
		
		
		
		System.out.print("select parking function");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> brand_with_class = driver.findElements(By.xpath("//li[@class='ant-select-dropdown-menu-item']"));
		List<WebElement> brand_with_role  = driver.findElements(By.xpath("//li[@role='option']"));
		System.out.println(brand_with_class.size());
		System.out.println("print brand_with_class");
		for(WebElement i:brand_with_class) {
			System.out.println(i.getText()+" ");
		}
		System.out.println(brand_with_role.size());
		System.out.println("print brand_with_role");
		for(WebElement i:brand_with_role) {
			System.out.println(i.getText()+" ");
		}
		int loop=10;
		int is_found=0;
		while(loop>0 && is_found==0) {
			List<WebElement> brand_with_role_here  = driver.findElements(By.xpath("//li[@role='option']"));
			for(WebElement i:brand_with_role_here) {
				System.out.println(i.getText()+" ");
				if(i.getText().equalsIgnoreCase(Brand)) {
					i.click();
					is_found=1;
					break;
				}
			}
			if(is_found==0)
				ScrollParking();
		}
		if(is_found==0) {
			ApcoaListeners.logInfo(Brand+"parking not found");
		}
		SA.assertNotEquals(is_found, 0);
	
}
		
	public void gotoPassmaster() throws InterruptedException{	
		
		
		
		Thread.sleep(10000);
		clickpassmaster.click();
		
		Thread.sleep(2000);
		permitmaster.click();
		
		Thread.sleep(2000);
		company.click();
		
		Thread.sleep(5000);
		companyname.click();
		
		
		Thread.sleep(2000);
		permitname.click();
		
		Thread.sleep(1000);
		permitname.sendKeys("Perm_1");
		
		Thread.sleep(2000);
		permitduration.click();
		
		Thread.sleep(1000);
		permitduration.sendKeys(String.valueOf(1));
		
		Thread.sleep(2000);
		description.click();
		
		Thread.sleep(1000);
		description.sendKeys("Testing for permit");
		
		Thread.sleep(2000);
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", approve);
		Thread.sleep(3000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", visible);
		
		Thread.sleep(2000);
		submit.click();
		
		Thread.sleep(3000);
		Parking.click();
		
		Thread.sleep(4000);
		selectparking("GMP [enable working]");
		
		
		
		
		
		
		
		Thread.sleep(4000);
		parkinglot.click();
		
		Thread.sleep(4000);
		selectparkinglot("CAR");
		
		
		Thread.sleep(2000);
		access.click();
		
		Thread.sleep(2000);
		addpermit.click();
		
		Thread.sleep(2000);
		popup.click();
		
		Thread.sleep(3000);
		clickpassmaster.click();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
	

	
	}

}