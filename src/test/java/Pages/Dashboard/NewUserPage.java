package Pages.Dashboard;
//Permit purchase of a new user through Dashboard

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import CommonUtility.CreateSession;
import java.util.Date;
import java.text.SimpleDateFormat;
import Dashboard.ObjectMapper.PermitDataMapper;
import TestNGListeners.ApcoaListeners;



public class NewUserPage {
	WebDriver driver;
	
	@FindBy(xpath="//a[@href=('/admin/permit')]")
	WebElement clickpermit;
	
	@FindBy(xpath="//a[@href='/admin/permit/create']")
	WebElement clickAddpermit;
	
	@FindBy(xpath="//div[@class='ant-select-selection__placeholder']")
	WebElement carparking;
	
	
	
	@FindBy(xpath="//input[@id='parking']")
	WebElement carpark;
	
	@FindBy(xpath="//ul[contains(@class,'ant-select-dropdown-menu')]")
	WebElement dropdown;
	
	@FindBy(xpath="//div[@id='permit']")
	WebElement carpermit;
	
	@FindBy(xpath="//input[@id='firstName']")
	WebElement firstname;
	
	@FindBy(xpath="//input[@id='lastName']")
	WebElement lastname;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='phoneNumber']")
	WebElement contact;
	
	@FindBy(xpath="//input[@id='address1']")
	WebElement address;
	
	@FindBy(xpath="//input[@id='city']")
	WebElement city;
	
	@FindBy(xpath="//input[@id='state']")
	WebElement state;
	
	@FindBy(xpath="//input[@id='zipCode']")
	WebElement zipcode;
	
	@FindBy(xpath="//div[@id='country']")
	WebElement country;
	
	@FindBy(xpath="//input[@id='vehicleNumber']")
	WebElement vehicleno;
	
	@FindBy(xpath="//input[@id='carMake']")
	WebElement carmake;
	
	@FindBy(xpath="//input[@id='model']")
	WebElement model;
	
	@FindBy(xpath="//input[@id='color']")
	WebElement color;
	
	@FindBy(xpath="//input[@id='displayName']")
	WebElement displayname;
	
	@FindBy(xpath="//input[@id='chequeNo']")
	WebElement chequeno;
	
	@FindBy(xpath="//span[contains(text(), 'Create User')]//parent::button")
	WebElement CreateUser;
	
	@FindBy(xpath="//span[contains(text(), 'Add Vehicle')]//parent::button")
	WebElement AddVehicle;
	
	@FindBy(xpath="//span[contains(text(), 'Save')]//parent::button")
	WebElement Save;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement checkbox;
	
	@FindBy(xpath="//span[contains(text(), 'Confirm & Proceed')]//parent::button")
	WebElement Confirmproceed;
	
	@FindBy(xpath="//span[contains(text(), 'Proceed to Payment')]//parent::button")
	WebElement Proceedpayment;
	
	@FindBy(xpath="//div[@id='paymentType']")
	WebElement Paymenttype;
	
	@FindBy(xpath="//li[contains(text(),'Cheque')]")
	WebElement Cheque;
	
	@FindBy(xpath="//input[@class='ant-calendar-picker-input ant-input']")
	WebElement datetime;
	
	@FindBy(xpath="//a[@class='ant-calendar-today-btn ']")
	WebElement today;
	
	@FindBy(xpath="//span[contains(text(), 'Collect Payment')]//parent::button")
	WebElement Collectpayment;
	
	//By clickpermit = By.xpath("//a[@href=('/admin/permit')]");
	//By clickAddpermit = By.xpath("//a[@href='/admin/permit/create']");
	//By carparking = By.xpath("//div[@class='ant-select-selection__placeholder']");
	//By carpark = By.xpath("//input[@id='parking']");
	//By carpermit = By.xpath("//div[@id='permit']");
	//By firstname = By.xpath("//input[@id='firstName']");
	//By lastname = By.xpath("//input[@id='lastName']");
	//By email = By.xpath("//input[@id='email']");
	//By contact = By.xpath("//input[@id='phoneNumber']");
	//By address = By.xpath("//input[@id='address1']");
	//By city = By.xpath("//input[@id='city']");
	//By state = By.xpath("//input[@id='state']");
	//By zipcode = By.xpath("//input[@id='zipCode']");
	//By country = By.xpath("//div[@id='country']");
	//By vehicleno = By.xpath("//input[@id='vehicleNumber']");
	//By carmake = By.xpath("//input[@id='carMake']");
	//By model = By.xpath("//input[@id='model']");
	//By color = By.xpath("//input[@id='color']");
	//By displayname = By.xpath("//input[@id='displayName']");
	//By chequeno = By.xpath("//input[@id='chequeNo']");
	
	public NewUserPage(WebDriver driver){
		this.driver = driver;
	}
	
	
	public void gotoPermit() throws InterruptedException{
		Thread.sleep(15000);
		clickpermit.click();    //click on permit management module
		
		ApcoaListeners.logInfo("click on permit management module");
		
	}
	
	public void gotoAddPermit(PermitDataMapper permitdataMapper) throws InterruptedException{   
		String emailtemp = permitdataMapper.getEmail();
	    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());         // adding timestamp for email to have unique email for every second
	    String actualemail = emailtemp.split("@")[0] + timeStamp +"@"+ emailtemp.split("@")[1];
	    String carpark_name = permitdataMapper.getCarparkname();
		String carpermit_name = permitdataMapper.getCarpermit();
		//String country_name = AutomationConfiguration.Country ;
		String firstname_name = permitdataMapper.getFirstname();
		String lastname_name = permitdataMapper.getLastname();
		
		String email_name = actualemail;
		String contact_no = permitdataMapper.getContactno();
		String address_name = permitdataMapper.getAddress();
		String city_name = permitdataMapper.getCity();
		String state_name = permitdataMapper.getState();
		String zipcode_no = permitdataMapper.getZipcode();
		String vehicle_no = permitdataMapper.getVehicleno();
		String car_make = permitdataMapper.getCarmake();
		String model_name = permitdataMapper.getModelname();
		String color_name = permitdataMapper.getColorname();
		String display_name = permitdataMapper.getDisplayname(); 
		
		Thread.sleep(10000);
		clickAddpermit.click();      //click on add new permit
		
		
		
		ApcoaListeners.logInfo("click on add new permit");
		 
		Thread.sleep(10000);
		carparking.click();          //click on carparking to search the parking name
		 
		Thread.sleep(5000);
		
		JavascriptExecutor executor = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
		executor.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=200");
		Thread.sleep(2000);
		executor.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=200");
		Thread.sleep(2000);
		executor.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=200");
		

		
		WebElement ele = driver.findElement(By.xpath("//li[contains(text(),'"+carpark_name+"')]"));
		 executor = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
		executor.executeScript("arguments[0].click();", ele);                 // search the desired parking name in the dropdown










		
		
		
		Thread.sleep(5000);
		
		
		
		carpermit.click();     //click on permit to search the desired permit for the selected parking
		
		ApcoaListeners.logInfo("select permit and parking ");
		
		Thread.sleep(4000);
		
		JavascriptExecutor executor1 = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
		executor1.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=200");
		Thread.sleep(2000);
		executor1.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=200");
		Thread.sleep(2000);
		executor1.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=200");
		
		WebElement ele1 = driver.findElement(By.xpath("//li[contains(text(),'"+carpermit_name+"')]"));
		executor1 = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
		executor1.executeScript("arguments[0].click();", ele1);
		
		
		//driver.findElement(By.xpath("//li[contains(text(),'"+carpermit_name+"')]")).click();    //select the permit name in the dropdown
		Thread.sleep(1000);
		
		firstname.sendKeys(firstname_name);   // enter  firstname of the new user
		Thread.sleep(2000);
		
		lastname.sendKeys(lastname_name);    // enter  lastname of user
		Thread.sleep(2000);
		
		email.sendKeys(email_name);        // enter the valid email id
		Thread.sleep(2000);
		
	    contact.sendKeys(String.valueOf(contact_no));  //enter the contact no 
		Thread.sleep(2000);
		
		address.sendKeys(address_name);     // enter the address
		Thread.sleep(2000);
		
		city.sendKeys(city_name);         // enter the city name of the user
		Thread.sleep(2000);
		
		state.sendKeys(state_name);        // enter the state
		Thread.sleep(2000);
		
		zipcode.sendKeys(String.valueOf(zipcode_no));    // enter zipcode
		Thread.sleep(2000);
		
///		country.click();         // click on country to select country for the new user
///		Thread.sleep(3000);
///		driver.findElement(By.xpath("//li[contains(text(),'"+country_name+"')]")).click();   // search the desired country from the dropdown
////		Thread.sleep(4000);
		CreateUser.click();  // click on create user, user should get registered 
		Thread.sleep(9000);
		
		AddVehicle.click();  // click on add vehicle
		Thread.sleep(1000);
		
		
		
	   vehicleno.sendKeys(vehicle_no);
	   Thread.sleep(1000);                  //enter VRN
		
		carmake.sendKeys(car_make);      // enter MAKE
		Thread.sleep(1000);
		
		model.sendKeys(model_name);     // enter MODEL
		Thread.sleep(1000);
		
		color.sendKeys(color_name);      // enter COLOR
		Thread.sleep(1000);
		
		displayname.sendKeys(display_name);    // enter DISPLAY
		Thread.sleep(2000);
		
		Save.click();    // click on save
		
		ApcoaListeners.logInfo("Fill the required details of the new user");
		
		Thread.sleep(4000);
		checkbox.click();      // click on the checkbox of vehicle to select the vehicle which we have entered
		
		ApcoaListeners.logInfo("select the vehicle");
		
		Thread.sleep(2000);
		
		Confirmproceed.click();  // click on confirm and proceed
		Thread.sleep(1000);
		
		Proceedpayment.click();  // click on proceed to payment
		
		ApcoaListeners.logInfo("select proceed to payment");
		
		Thread.sleep(2000);
		
		Paymenttype.click();       // click on payment box to get the dropdown to select payment type
		Thread.sleep(1000);
		
		Cheque.click();  // click on cheque from the dropdown
		Thread.sleep(1000);
		
		chequeno.sendKeys(String.valueOf(123));       // enter the cheque no 
		Thread.sleep(1000);
		
		datetime.click();    //click on the calender to select the date-time of payment
		Thread.sleep(2000);
		
		today.click();     //select now in the calendar
		
		ApcoaListeners.logInfo("select payement method as cheque and fill the cheque no and transaction date time");
		
		Thread.sleep(2000);
		
		Collectpayment.click();  // click on collect payment
		
		ApcoaListeners.logInfo("click on collect payment to purchase the permit");
	}

}




