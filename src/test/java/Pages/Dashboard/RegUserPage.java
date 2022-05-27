package Pages.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import CommonUtility.CreateSession;
import Dashboard.ObjectMapper.DATAMapper;
import TestNGListeners.ApcoaListeners;

public class RegUserPage {

	WebDriver driver;

	@FindBy(xpath="//a[@href=('/admin/permit')]")
	WebElement clickpermit;

	@FindBy(xpath="//a[@href='/admin/permit/create']")
	WebElement clickAddpermit;

	@FindBy(xpath="//div[@id='parking']//div[@class='ant-select-selection__rendered']")
	WebElement carparking;

	@FindBy(xpath="//input[@id='parking']")
	WebElement parkingname;

	@FindBy(xpath="//div[@id='permit']")
	WebElement carpermit;

	@FindBy(xpath="//div[@title='First Name']")
	WebElement clickfirstname;

	@FindBy(xpath="//li[contains(text(),'Email')]")
	WebElement EmailID;

	@FindBy(xpath="//input[@id='userData']")
	WebElement searchuser;

	@FindBy(xpath="//input[@id='chequeNo']")
	WebElement chequeno;

	@FindBy(xpath="//i[@class='anticon anticon-search ant-input-search-icon']")
	WebElement search;

	@FindBy(xpath="//span[contains(text(), 'Select')]//parent::button")
	WebElement selectuser;

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



	public RegUserPage(WebDriver driver){ 
		this.driver = driver;
	}

	public void buypermit(DATAMapper dataMapper) throws InterruptedException{
		String carpark_name = dataMapper.getCarparkname();
		String carpermit_name = dataMapper.getCarpermit();
		String user_name = dataMapper.getUsername();

		Thread.sleep(15000);
		clickpermit.click();
		//click on permit management module

		ApcoaListeners.logInfo("click on permit management module");

		Thread.sleep(3000);
		clickAddpermit.click(); //click on add new permit
		ApcoaListeners.logInfo("click on add new permit");

		Thread.sleep(3000);
		carparking.click();  //click on parking name to search the parking in the dropdown list
		Thread.sleep(6000);



		JavascriptExecutor executor = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;

		parkingname.sendKeys(carpark_name);

		while(true) {
			try {
				WebElement ele = driver.findElement(By.xpath("//li[contains(text(),'"+carpark_name+"')]"));
				executor = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
				executor.executeScript("arguments[0].click();", ele);
				break;
			}
			catch(Exception e) {
				try {
					JavascriptExecutor executor1 = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
					executor1.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=800");
					Thread.sleep(2000);
					executor1.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=800");
					Thread.sleep(2000);
					executor1.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=800");
				}catch(Exception m) {
				}
				continue;
			}
		}



		Thread.sleep(3000);
		carpermit.click(); //click on permit to search the desired permit from the dropdown list
		Thread.sleep(6000);

		while(true) {
			try {
				WebElement ele = driver.findElement(By.xpath("//li[contains(text(),'"+carpermit_name+"')]"));
				executor = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
				executor.executeScript("arguments[0].click();", ele);
				break;
			}
			catch(Exception e) {
				try {
					JavascriptExecutor executor1 = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
					executor1.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=800");
					Thread.sleep(2000);
					executor1.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=800");
					Thread.sleep(2000);
					executor1.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=800");
				}

				catch(Exception c) {

				}
				continue;
			}
		}



		ApcoaListeners.logInfo("select parking and permit");

		Thread.sleep(2000);
		clickfirstname.click();
		Thread.sleep(3000);
		EmailID.click();
		Thread.sleep(3000);
		searchuser.click();   //click on search user
		Thread.sleep(2000);
		searchuser.sendKeys(user_name);  //type the firstname of the user
		Thread.sleep(2000);
		search.click();  //click on search button to search user
		Thread.sleep(3000);
		selectuser.click();  //click on select for the desired user
		Thread.sleep(4000);
		checkbox.click();   // click on the checkbox for vehicle to select the vehicle
		Thread.sleep(2000);
		Confirmproceed.click();  //click on confirm and proceed

		ApcoaListeners.logInfo("search the user, select vehicle and confirm proceed");

		Thread.sleep(4000);
		Proceedpayment.click();  //click on porceed to payment
		Thread.sleep(3000);
		Paymenttype.click();  //click on payment type to search desired payment method from dropdown
		Thread.sleep(2000);
		Cash.click(); //click on cash from the dropdown list
		Thread.sleep(1000);
		Transaction.click(); //click on Transaction to select current date & time for payment from dropdown
		Thread.sleep(2000);
		now.click(); //click on now in the dropdown 

		ApcoaListeners.logInfo("select payment method as cash , select transaction date-time");

		Thread.sleep(2000);
		Collectpayment.click(); //click on collect payment 

		ApcoaListeners.logInfo("collect payment to purchase the pemrit");

		Thread.sleep(3000);
		clickcross.click();

		ApcoaListeners.logInfo("cancel the pop up of permit purchased appeared to view the permit in permits tab");

		// as soon as user clicks on collect payment, a pop up should occur that permit is created
	}
}
