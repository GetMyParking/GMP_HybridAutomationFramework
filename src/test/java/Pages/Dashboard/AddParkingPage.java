package Pages.Dashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddParkingPage {
	WebDriver driver;
	
	@FindBy(xpath="//a[@href='/admin/list']")
	WebElement overviewbtn;
	
	@FindBy(xpath="//a[text()='Apcoa Netherlands']")
	WebElement parking;
	
	@FindBy(xpath="//button[@class='ant-btn ant-btn-primary']")
	WebElement addparkingbtn;
	
	@FindBy(id="name")
	WebElement parkingname;
	
	@FindBy(id="displayName")
	WebElement displayparking;
	
	@FindBy(xpath="//div[@id='isParkingEnabled']//child::span[text()='Yes']")
	WebElement enableparking;
	
	@FindBy(xpath="//div[@id='isOnstreet']//child::span[text()='No']")
	WebElement onstreet;
	
	@FindBy(xpath="//div[@id='sendAlert']//child::span[text()='No']")
	WebElement sendalert;
	
	@FindBy(xpath="//div[@id='ownership']//child::span[text()='Private']")
	WebElement ownership;
	
	@FindBy(xpath="//div[@id='isEnableActive']//child::span[text()='No']")
	WebElement enableactive;
	
	@FindBy(xpath="(//span[@style=\"user-select: none;\"])[6]")
	WebElement svg;
	
	@FindBy(xpath="//li[text()='EUR']")
	WebElement currencycode;
	
	@FindBy(xpath="//div[text()='Select Time zone']")
	WebElement timezone;
	
	@FindBy(xpath="//div[text()='Select access methods types']")
	WebElement accessmethods;
	
	@FindBy(xpath="//li[text()='CONSUMERAPP']")
	WebElement consumerapp;
	
	@FindBy(xpath="//li[text()='ETC/GMT+12']")
	WebElement europe;
	
	@FindBy(xpath="//input[@placeholder='eg: 1/2 Downstreet']")
	WebElement adress;
	
	
	@FindBy(xpath="//input[@class='ant-time-picker-panel-input']")
	WebElement time;
	
	@FindBy(xpath="//input[@class='ant-time-picker-panel-input']")
	WebElement time_2;
	
	@FindBy(xpath="(//li[text()='15'])[1]")
	WebElement zero;
	
	@FindBy(id="operationalHours.closeTime")
	WebElement closetime;
	
	@FindBy(id="operationalHours.openTime")
	WebElement opentime;
	
	@FindBy(css="input[placeholder='Search by Parking']")
	WebElement searchparking;

	
//	@FindBy(xpath="//button[@class='ant-btn sc-hEsumM fcEQWv ant-btn-default']")
//	WebElement cancel;
	
	@FindBy(xpath="//button[@class='ant-btn sc-cmTdod drDJGm ant-btn-primary']")
	WebElement addparking;
	
	@FindBy(xpath="//span[@class='ant-modal-close-x']")
	WebElement cancel;
	
	@FindBy(xpath="//span[@class='ant-input-suffix']")
	WebElement search;
	
	
//	@FindBy(xpath="//button[@class='ant-btn sc-cmTdod drDJGm ant-btn-primary']")
//	WebElement addparking;
	
	public AddParkingPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void gotoOverview() throws InterruptedException{
		overviewbtn.click();
		parking.click();
		addparkingbtn.click();
		
}
	public void createParking() throws InterruptedException{
		parkingname.sendKeys("Test_Parking");
		displayparking.sendKeys("Test_Parking");
		enableparking.click();
		onstreet.click();
		sendalert.click();
		ownership.click();
		enableactive.click();
		svg.click();
		currencycode.click();
		timezone.click();
		europe.click();
		accessmethods.click();
		consumerapp.click();
		adress.sendKeys("123Downstreet");

}
	
	public void open_timezone() {
		opentime.click();
		time.sendKeys("00:00:59");
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("document.getElementByclassName('ant-time-picker-panel-input').setAttribute('value', '00:00:59')");
	}
	
	public void close_timezone() {
		closetime.click();
//		time_2.sendKeys("23:59:59");
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("document.getElementByclassName('ant-time-picker-panel-input').setAttribute('value', '23:59:59')");
		
	}
	
	public void add() {
		time_2.sendKeys("23:59:59");
		addparking.click();
			
	}
	
	public void searchparking() {
		cancel.click();	
		searchparking.sendKeys("Test_Parking");
		
		
	}
	
}
