package Pages.Dashboard;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Dashboard.Utils.GenerateCsv;
import TestNGListeners.ApcoaListeners;

public class PartneruserPage {
WebDriver driver;
	
	@FindBy(xpath="//a[@href='/admin/partner-users']")
	WebElement clickpartneruser;
	
	@FindBy(xpath="//div[@class='sc-jAaTju ioUKg ant-select ant-select-enabled']")
	WebElement clickcorporate;
	
	@FindBy(xpath="//button[@class='ant-btn ant-btn-primary'][3]//parent::button")
	WebElement clickAdduser;
	
	@FindBy(xpath="//input[@id='firstName']")
	WebElement firstname;
	
	@FindBy(xpath="//input[@id='lastName']")
	WebElement lastname;
	
	@FindBy(xpath="//input[@id='phoneNumber']")
	WebElement phoneno;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='externalCorporateUserId']")
	WebElement externalcorpId;
	
	@FindBy(xpath="//input[@id='accessMethods[0].identifier']")
	WebElement ID;
	
	@FindBy(xpath="//span[text()='Add Row']//parent::button")
	WebElement Addrow;
	
	@FindBy(xpath="//span[text()='Save']//parent::button") 
	WebElement Save;
	
	@FindBy(xpath="//span[text()='Add Bulk Users']//parent::button")
	WebElement Addbulkusers;
	
	@FindBy(xpath="//input[@type='file']")
	WebElement upload;
	
	@FindBy(xpath="//span[text()='Create Users and Proceed']//parent::button")
	WebElement createproceed;
	
	@FindBy(xpath="//span[text()='Save and Continue']//parent::button")
	WebElement savecontinue;
	
	@FindBy(xpath="//span[text()='Skip and Save']//parent::button")
	WebElement skipsave;
	
	@FindBy(xpath="//span[@class='ant-modal-close-x']")
	WebElement clickcross;
	
	public PartneruserPage(WebDriver driver){
		this.driver = driver;
	}
	
	

	public void gotoPartneruser() throws InterruptedException{
		Thread.sleep(10000);
		clickpartneruser.click();	
		ApcoaListeners.logInfo("Click on Partner  user");
//		Thread.sleep(3000);
//		clickcorporate.click();
//		Thread.sleep(2000);
//		WebElement element = driver.findElement(By.xpath("//li[text()='Stanford University (stest)']"));
//		((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);
        Thread.sleep(4000);
		clickAdduser.click();
		ApcoaListeners.logInfo("Click on add user");
		Thread.sleep(3000);
		firstname.click();
		Thread.sleep(2000);
		firstname.sendKeys("Testing");
		ApcoaListeners.logInfo("Add firstname of user");
		Thread.sleep(2000);
		lastname.click();
		Thread.sleep(2000);
		lastname.sendKeys("permit");
		ApcoaListeners.logInfo("Add lastname of user");
		Thread.sleep(2000);
		phoneno.sendKeys(String.valueOf("1234567890"));
		
		ApcoaListeners.logInfo("Add mobile no of user");
		
		String emailtemp = "User@yopmail.com";
	    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());         
	    String actualemail = emailtemp.split("@")[0] + timeStamp +"@"+ emailtemp.split("@")[1];
	    Thread.sleep(3000);
	    email.click();
	    Thread.sleep(2000);
	    email.sendKeys(actualemail);
	    ApcoaListeners.logInfo("Add email address");
	    
	    String extID = "EMPID";
	    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String actualID = extID.split("I")[0] + timestamp + "I"+ extID.split("I")[1];
	    Thread.sleep(3000);
	    externalcorpId.click();
	    Thread.sleep(2000);
	    externalcorpId.sendKeys(actualID);
	    ApcoaListeners.logInfo("Add corporate id");
	    
	    String id = "LPRID";
	    String time_stamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String actualid = id.split("I")[0] + time_stamp + "I"+ id.split("I")[1];
	    Thread.sleep(3000);
	    ID.click();
	    Thread.sleep(2000);
	    ID.sendKeys(actualid);
	    ApcoaListeners.logInfo("Add identifier");
	    
	    Thread.sleep(2000);
	    Addrow.click();
	    ApcoaListeners.logInfo("Add permit to the user");
	    Thread.sleep(2000);
	    Save.click();
	    ApcoaListeners.logInfo("Click on save");
	    
	    Thread.sleep(8000);
	    Addbulkusers.click();
	    ApcoaListeners.logInfo("Click on add bulk user");
	    Thread.sleep(3000);
	    String filepath = GenerateCsv.generateCsv();
	    System.out.println(filepath);
	    upload.sendKeys(filepath);
	    ApcoaListeners.logInfo("Click on upload");
	    Thread.sleep(3000);
	    createproceed.click();
	    ApcoaListeners.logInfo("Click on create and proceed");
	    Thread.sleep(3000);
	    savecontinue.click();
	    ApcoaListeners.logInfo("Click on save and continue");
	    Thread.sleep(3000);
	    skipsave.click();
	    ApcoaListeners.logInfo("Click on save");
	    Thread.sleep(3000);
	    clickcross.click();
	    ApcoaListeners.logInfo("Cut the pop up of users uploaded successfully");
	    Thread.sleep(5000);
	    List<WebElement> li = driver.findElements(By.xpath("//li[contains(@class,'ant-pagination-item')]"));
	    li.get(li.size()-1).click();
	    ApcoaListeners.logInfo("Clicked on the last page to view the uploaded users");
	    
	    
	    
	    
	    
		
		
		
	}

}
