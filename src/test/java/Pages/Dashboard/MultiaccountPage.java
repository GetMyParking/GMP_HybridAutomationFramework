package Pages.Dashboard;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import CommonUtility.CreateSession;
import Dashboard.ObjectMapper.multiaccountMapper;
import TestNGListeners.ApcoaListeners;

public class MultiaccountPage {
	
	WebDriver driver;
	SoftAssert SA = new SoftAssert();
	
	@FindBy(xpath="//a[@href=('/admin/permit')]")
	WebElement clickpermit;
	
	@FindBy(xpath="//a[@href='/admin/permit/create']")
	WebElement clickAddpermit;
	
	@FindBy(xpath="//div[@id='parking']//div[@class='ant-select-selection__rendered']")
	WebElement carparking;
	
	@FindBy(xpath="//div[@id='permit']")
	WebElement carpermit;
	
	@FindBy(xpath="//div[@title='First Name']")
	WebElement clickfirstname;
	
	@FindBy(xpath="//li[contains(text(),'Email')]")
	WebElement EmailID;
	
	@FindBy(xpath="//input[@id='userData']")
	WebElement searchuser;
	
	@FindBy(xpath="//span[contains(text(), 'Select')]//parent::button")
	WebElement selectuser;
	
	@FindBy(xpath="//i[@class='anticon anticon-search ant-input-search-icon']")
	WebElement search;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement checkbox;
	
	@FindBy(xpath="//span[contains(text(), 'Confirm & Proceed')]//parent::button")
	WebElement Confirmproceed;
	
	@FindBy(xpath="//span[contains(text(), 'Proceed to Payment')]//parent::button")
	WebElement Proceedpayment;
	
	@FindBy(xpath="//div[@id='paymentType']")
	WebElement Paymenttype;
	
	@FindBy(xpath="//li[contains(text(),'Cash')]")
	WebElement Cash;
	
	@FindBy(xpath="//input[@class='ant-calendar-picker-input ant-input']")
	WebElement Transaction;
	
	@FindBy(xpath="//a[@class='ant-calendar-today-btn ']")
	WebElement now;
	
	@FindBy(xpath="//span[contains(text(), 'Collect Payment')]//parent::button")
	WebElement Collectpayment;
	
	@FindBy(xpath="(//span[@class='ant-modal-close-x']/i)[2]")
	WebElement clickcross;
	
	@FindBy(xpath="//div[contains(text(),'Permits')]")
	    WebElement clickPermit;
	 
	@FindBy(xpath="//a[@href='/admin/pass-master']")
		WebElement clickpassmaster; 
	
	@FindBy(xpath="//input[@id='searchedText']")
	WebElement typepermit;
	
	@FindBy(xpath="//span[@class='ant-input-suffix']")
	WebElement searchpermit;
	
	
	@FindBy(xpath="//i[@aria-label='icon: edit']")
	WebElement edit;
	
	
	@FindBy(xpath="//div[@id='permitAllocationType']")
	WebElement allocationtype;
	
	@FindBy(xpath="//li[contains(text(),'Account')]")
	WebElement Account;
	
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement save;
	
	@FindBy(xpath="//li[contains(text(),'Vehicle')]")
	WebElement vehicle;
	
	public MultiaccountPage(WebDriver driver){
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
	
	public void selectpermit(String Brand) {
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
	
	public void Multiacc(multiaccountMapper MultiaccountMapper) throws InterruptedException{
		
		
		String carpark_name = MultiaccountMapper.getCarparkname();
		String carpermit_name = MultiaccountMapper.getCarpermit();
		String user_name = MultiaccountMapper.getUsername();
		
		
		
		Thread.sleep(10000);
		clickpermit.click();		
		Thread.sleep(3000);
		clickAddpermit.click();
		
		ApcoaListeners.logInfo("Going to click on Add new permit");
		
		Thread.sleep(3000);
		carparking.click();
		Thread.sleep(3000);
		selectparking(carpark_name);
		Thread.sleep(3000);
		carpermit.click();
		Thread.sleep(3000);
		selectpermit(carpermit_name);
		
		
		ApcoaListeners.logInfo("Selected parking name and permit name successfully");
		
		Thread.sleep(3000);
		clickfirstname.click();
		Thread.sleep(3000);
		EmailID.click();
		Thread.sleep(3000);
		searchuser.click();  
		Thread.sleep(2000);
		searchuser.sendKeys(user_name);
		Thread.sleep(2000);
		search.click();  
		Thread.sleep(3000);
		selectuser.click(); 
		Thread.sleep(5000);
		
		ApcoaListeners.logInfo("Selected the desired registered user successfully");
		
		
		//checkbox.click();
		
		System.out.println(checkbox.isEnabled());
		if (checkbox.isEnabled()) {
		    checkbox.click();
		    System.out.println("Checkbox for vehicle selection is clickable");
		    
		}
		else {
			System.out.println("Checkbox for vehicle selection is not clickable");
		}
		Thread.sleep(3000);
        Confirmproceed.click();  
		
		ApcoaListeners.logInfo("search the user, select vehicle and confirm proceed");
		
		Thread.sleep(4000);
		Proceedpayment.click();  
		Thread.sleep(3000);
		Paymenttype.click();  
		Thread.sleep(2000);
		Cash.click(); 
		Thread.sleep(1000);
		Transaction.click(); 
		Thread.sleep(2000);
		now.click(); 
		
		ApcoaListeners.logInfo("select payment method as cash , select transaction date-time");
		
		Thread.sleep(2000);
		Collectpayment.click();
		
		ApcoaListeners.logInfo("collect payment to purchase the pemrit");
		
		Thread.sleep(3000);
		clickcross.click();
		
		ApcoaListeners.logInfo("cancel the pop up of permit purchased appeared to view the permit in permits tab");
		
		Thread.sleep(2000);
		clickPermit.click();
		Thread.sleep(3000);
//	
		
		
		clickpassmaster.click();
		Thread.sleep(4000);
		typepermit.click();
		Thread.sleep(2000);
		typepermit.sendKeys(carpermit_name);
		
		ApcoaListeners.logInfo("Going to enter the permit name to edit the permit");
		
		
		Thread.sleep(2000);
		searchpermit.click();
		Thread.sleep(2000);
		edit.click();
		Thread.sleep(2000);
		allocationtype.click();
		Thread.sleep(2000);
		Account.click();
		Thread.sleep(3000);
		save.click();
		
		ApcoaListeners.logInfo("Created the permit allocation type as Account successfully");
		
		
		
		
		Thread.sleep(4000);
		clickpermit.click();		
		Thread.sleep(3000);
		clickAddpermit.click();
		Thread.sleep(3000);
		carparking.click();
		Thread.sleep(3000);
		selectparking(carpark_name);
		Thread.sleep(3000);
		carpermit.click();
		Thread.sleep(3000);
		selectpermit(carpermit_name);
		
		ApcoaListeners.logInfo("Entered the parking name and permit name successfully");
		
		Thread.sleep(3000);
		clickfirstname.click();
		Thread.sleep(3000);
		EmailID.click();
		Thread.sleep(3000);
		searchuser.click();  
		Thread.sleep(2000);
		searchuser.sendKeys(user_name);
		Thread.sleep(2000);
		search.click();  
		Thread.sleep(3000);
		selectuser.click(); 
		
		ApcoaListeners.logInfo("Selected the desired user successfully");
		
		
		Thread.sleep(3000);
		
		System.out.println(checkbox.isEnabled());
		if (checkbox.isEnabled()) {
		    checkbox.click();
		    System.out.println("Checkbox for vehicle selection is clickable");
		    
		}
		else {
			System.out.println("Checkbox for vehicle selection is not clickable");
		}
		//checkbox.click();
		Thread.sleep(3000);
        Confirmproceed.click();  
		
		ApcoaListeners.logInfo("Able to successfully proceed to purchase the permit");
		
		Thread.sleep(4000);
		Proceedpayment.click();  
		Thread.sleep(3000);
		Paymenttype.click();  
		Thread.sleep(2000);
		Cash.click(); 
		Thread.sleep(1000);
		Transaction.click(); 
		Thread.sleep(2000);
		now.click(); 
		
		ApcoaListeners.logInfo("select payment method as cash , select transaction date-time");
		
		Thread.sleep(2000);
		Collectpayment.click();
		
		ApcoaListeners.logInfo("collect payment to purchase the pemrit");
		
		Thread.sleep(3000);
		clickcross.click();
		
		ApcoaListeners.logInfo("cancel the pop up of permit purchased appeared to view the permit in permits tab");
		Thread.sleep(2000);
		clickPermit.click();
		ApcoaListeners.logInfo("Click on permits tab the vehicle allocation for the permit purchased should now be Account type");
		
		Thread.sleep(2000);
		clickpassmaster.click();
		Thread.sleep(4000);
		typepermit.click();
		Thread.sleep(2000);
		typepermit.sendKeys(carpermit_name);
		Thread.sleep(2000);
		searchpermit.click();
		Thread.sleep(2000);
		edit.click();
		Thread.sleep(2000);
		allocationtype.click();
		Thread.sleep(2000);
		vehicle.click();
		Thread.sleep(3000);
		ApcoaListeners.logInfo("Going to change the back the allocation type from Account to vehicle");
		save.click();
		ApcoaListeners.logInfo("Allocation type changed to vehicle successfully");
		Thread.sleep(4000);
		clickpermit.click();
		Thread.sleep(2000);
		clickPermit.click();
		ApcoaListeners.logInfo("Click on permits tab the vehicle allocation for the permit purchased should now be multivehicle type");
		
		
		
		
		
		
	}
	
	

}
