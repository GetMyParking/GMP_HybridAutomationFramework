package Pages.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddPermitPage {
	WebDriver driver;
	
	By clickpermit = By.xpath("//a[@href=('/admin/permit')]");
	By clickAddpermit = By.xpath("//a[@href=\"/admin/permit/create\"]");
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
	
	public AddPermitPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void gotoPermit() throws InterruptedException{
		Thread.sleep(15000);
		driver.findElement(clickpermit).click();
	}
	
	public void gotoAddPermit() throws InterruptedException{
		String carpark_name = "Get My Parking Office (old)";
		String firstname_name = "testing";
		String lastname_name = "permit";
		String email_name = "automation14@yopmail.com";
		String permitname = "saransh";
		String countryname = "INDIA";
		int contact_no = 111111;
		String address_name = "testingpermitaddress";
		String city_name = "testingpermitcity";
		String state_name = "testingpermitstate";
		int zipcode_no = 1234;
		String vehicle_no = "VEHICLE7";
		String car_make = "make7";
		String model_name = "model7";
		String color_name = "color7";
		String display_name = "displayvehicle7"; 
		
		Thread.sleep(10000);
		driver.findElement(clickAddpermit).click();
		Thread.sleep(10000);
		driver.findElement(carparking).click();
		Thread.sleep(2000);
		driver.findElement(carpark).sendKeys(carpark_name);
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[contains(text(),'"+carpark_name+"')]")).click();
		Thread.sleep(5000);
		driver.findElement(carpermit).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//li[contains(text(),'"+permitname+"')]")).click();
		
		Thread.sleep(1000);
		driver.findElement(firstname).sendKeys(firstname_name);
		Thread.sleep(2000);
		driver.findElement(lastname).sendKeys(lastname_name);
		Thread.sleep(2000);
		driver.findElement(email).sendKeys(email_name);
		Thread.sleep(2000);
	    driver.findElement(contact).sendKeys(String.valueOf(contact_no));
		Thread.sleep(2000);
		driver.findElement(address).sendKeys(address_name);
		Thread.sleep(2000);
		driver.findElement(city).sendKeys(city_name);
		Thread.sleep(2000);
		driver.findElement(state).sendKeys(state_name);
		Thread.sleep(2000);
		driver.findElement(zipcode).sendKeys(String.valueOf(zipcode_no));
		Thread.sleep(2000);
		driver.findElement(country).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[contains(text(),'"+countryname+"')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[contains(text(), 'Create User')]//parent::button")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[contains(text(), 'Add Vehicle')]//parent::button")).click();
		Thread.sleep(1000);
		driver.findElement(vehicleno).sendKeys(vehicle_no);
		Thread.sleep(1000);
		driver.findElement(carmake).sendKeys(car_make);
		Thread.sleep(1000);
		driver.findElement(model).sendKeys(model_name);
		Thread.sleep(1000);
		driver.findElement(color).sendKeys(color_name);
		Thread.sleep(1000);
		driver.findElement(displayname).sendKeys(display_name);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'Save')]//parent::button")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'Confirm & Proceed')]//parent::button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(), 'Proceed to Payment')]//parent::button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='paymentType']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[contains(text(),'Cheque')]")).click();
		Thread.sleep(1000);
		driver.findElement(chequeno).sendKeys(String.valueOf(123));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='ant-calendar-picker-input ant-input']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='ant-calendar-today-btn ']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'Collect Payment')]//parent::button")).click();
	}
	
}


