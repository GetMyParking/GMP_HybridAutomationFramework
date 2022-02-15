package Pages.Dashboard;
//Permit purchase of a new user through Dashboard

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Date;
import java.text.SimpleDateFormat;
import CommonUtility.AutomationConfiguration;
import Dashboard.ObjectMapper.PermitDataMapper;



public class NewUserPage {
	WebDriver driver;
	
	By clickpermit = By.xpath("//a[@href=('/admin/permit')]");
	By clickAddpermit = By.xpath("//a[@href='/admin/permit/create']");
	By carparking = By.xpath("//div[@class='ant-select-selection__placeholder']");
	By carpark = By.xpath("//input[@id='parking']");
	By carpermit = By.xpath("//div[@id='permit']");
	By firstname = By.xpath("//input[@id='firstName']");
	By lastname = By.xpath("//input[@id='lastName']");
	By email = By.xpath("//input[@id='email']");
	By contact = By.xpath("//input[@id='phoneNumber']");
	By address = By.xpath("//input[@id='address1']");
	By city = By.xpath("//input[@id='city']");
	By state = By.xpath("//input[@id='state']");
	By zipcode = By.xpath("//input[@id='zipCode']");
	By country = By.xpath("//div[@id='country']");
	By vehicleno = By.xpath("//input[@id='vehicleNumber']");
	By carmake = By.xpath("//input[@id='carMake']");
	By model = By.xpath("//input[@id='model']");
	By color = By.xpath("//input[@id='color']");
	By displayname = By.xpath("//input[@id='displayName']");
	By chequeno = By.xpath("//input[@id='chequeNo']");
	
	public NewUserPage(WebDriver driver){
		this.driver = driver;
	}
	
	
	public void gotoPermit() throws InterruptedException{
		Thread.sleep(15000);
		driver.findElement(clickpermit).click();    //click on permit management module
	}
	
	public void gotoAddPermit(PermitDataMapper permitdataMapper) throws InterruptedException{   
		String emailtemp = permitdataMapper.getEmail();
	    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());         // adding timestanp for email to have unique email for every second
	    String actualemail = emailtemp.split("@")[0] + timeStamp +"@"+ emailtemp.split("@")[1];
		String carpark_name = permitdataMapper.getCarparkname();
		String carpermit_name = permitdataMapper.getCarpermit();
		String country_name = AutomationConfiguration.Country ;
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
		driver.findElement(clickAddpermit).click();      //click on add new permit
		Thread.sleep(10000);
		driver.findElement(carparking).click();          //click on carparking to search the parking name
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[contains(text(),'"+carpark_name+"')]")).click();    // search the desired parking name in the dropdown
		Thread.sleep(5000);
		driver.findElement(carpermit).click();     //click on permit to search the desired permit for the selected parking
		Thread.sleep(4000);
		driver.findElement(By.xpath("//li[contains(text(),'"+carpermit_name+"')]")).click();    //select the permit name in the dropdown
		Thread.sleep(1000);
		driver.findElement(firstname).sendKeys(firstname_name);   // enter  firstname of the new user
		Thread.sleep(2000);
		driver.findElement(lastname).sendKeys(lastname_name);    // enter  lastname of user
		Thread.sleep(2000);
		driver.findElement(email).sendKeys(email_name);        // enter the valid email id
		Thread.sleep(2000);
	    driver.findElement(contact).sendKeys(String.valueOf(contact_no));  //enter the contact no 
		Thread.sleep(2000);
		driver.findElement(address).sendKeys(address_name);     // enter the address
		Thread.sleep(2000);
		driver.findElement(city).sendKeys(city_name);         // enter the city name of the user
		Thread.sleep(2000);
		driver.findElement(state).sendKeys(state_name);        // enter the state
		Thread.sleep(2000);
		driver.findElement(zipcode).sendKeys(String.valueOf(zipcode_no));    // enter zipcode
		Thread.sleep(2000);
		driver.findElement(country).click();         // click on country to select country for the new user
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[contains(text(),'"+country_name+"')]")).click();   // search the desired country from the dropdown
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[contains(text(), 'Create User')]//parent::button")).click();  // click on create user, user should get registered 
		Thread.sleep(7000);
		driver.findElement(By.xpath("//span[contains(text(), 'Add Vehicle')]//parent::button")).click();  // click on add vehicle
		Thread.sleep(1000);
		driver.findElement(vehicleno).sendKeys(vehicle_no);    // enter VRN
		Thread.sleep(1000);
		driver.findElement(carmake).sendKeys(car_make);      // enter MAKE
		Thread.sleep(1000);
		driver.findElement(model).sendKeys(model_name);     // enter MODEL
		Thread.sleep(1000);
		driver.findElement(color).sendKeys(color_name);      // enter COLOR
		Thread.sleep(1000);
		driver.findElement(displayname).sendKeys(display_name);    // enter DISPLAY
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'Save')]//parent::button")).click();    // click on save
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();      // click on the checkbox of vehicle to select the vehicle which we have entered
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'Confirm & Proceed')]//parent::button")).click();  // click on confirm and proceed
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(), 'Proceed to Payment')]//parent::button")).click();  // click on proceed to payment
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='paymentType']")).click();       // click on payment box to get the dropdown to select payment type
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[contains(text(),'Cheque')]")).click();  // click on cheque from the dropdown
		Thread.sleep(1000);
		driver.findElement(chequeno).sendKeys(String.valueOf(123));       // enter the cheque no 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='ant-calendar-picker-input ant-input']")).click();    //click on the calender to select the date-time of payment
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='ant-calendar-today-btn ']")).click();     //select now in the calendar
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'Collect Payment')]//parent::button")).click();  // click on collect payment
	}
}




