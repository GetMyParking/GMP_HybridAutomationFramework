package Pages.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Dashboard.ObjectMapper.DATAMapper;

public class RegUserPage {
	
	WebDriver driver;
	By clickpermit = By.xpath("//a[@href=('/admin/permit')]");
	By clickAddpermit = By.xpath("//a[@href=\"/admin/permit/create\"]");
	By carparking = By.xpath("//div[@class='ant-select-selection__placeholder']");
	By carpark = By.xpath("//input[@id='parking']");
	By carpermit = By.xpath("//div[@id='permit']");
	By searchuser = By.xpath("//input[@id='userData']");
	By chequeno = By.xpath("//input[@id='chequeNo']");
	
	public RegUserPage(WebDriver driver){ 
		this.driver = driver;
	}
		
	public void buypermit(DATAMapper dataMapper) throws InterruptedException{
		String carpark_name = dataMapper.getCarparkname();
		String carpermit_name = dataMapper.getCarpermit();
		String user_name = dataMapper.getUsername();
		
		Thread.sleep(15000);
		driver.findElement(clickpermit).click();
		//click on permit management module
		Thread.sleep(3000);
		driver.findElement(clickAddpermit).click(); //click on add new permit
		Thread.sleep(3000);
		driver.findElement(carparking).click();  //click on parking name to search the parking in the dropdown list
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[contains(text(),'"+carpark_name+"')]")).click();  //search the carparking from the dropdown list
		Thread.sleep(3000);
		driver.findElement(carpermit).click(); //click on permit to search the desired permit from the dropdown list
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[contains(text(),'"+carpermit_name+"')]")).click(); //search for permit
		Thread.sleep(2000);
		driver.findElement(searchuser).click();   //click on search user
		Thread.sleep(2000);
		driver.findElement(searchuser).sendKeys(user_name);  //type the firstname of the user
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='anticon anticon-search ant-input-search-icon']")).click();  //click on search button to search user
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(), 'Select')]//parent::button")).click();  //click on select for the desired user
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();   // click on the checkbox for vehicle to select the vehicle
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'Confirm & Proceed')]//parent::button")).click();  //click on confirm and proceed
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[contains(text(), 'Proceed to Payment')]//parent::button")).click();  //click on porceed to payment
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='paymentType']")).click();  //click on payment type to search desired payment method from dropdown
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[contains(text(),'Cash')]")).click(); //click on cash from the dropdown list
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='ant-calendar-picker-input ant-input']")).click(); //click on Transaction to select current date & time for payment from dropdown
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='ant-calendar-today-btn ']")).click(); //click on now in the dropdown 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'Collect Payment')]//parent::button")).click(); //click on collect payment		
      // as soon as user clicks on collect payment, a pop up should occur that permit is created
	}
}
