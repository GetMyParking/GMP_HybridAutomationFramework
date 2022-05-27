package Pages.PayG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;

@Parameters({ "Environment", "Country","Tenant","Gateway"})


public class SavePayment {
	SoftAssert SA=new SoftAssert();
	String parking_Name = "";
	
	
	
	@FindBy(xpath="//input[@id='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='fullName']")
	WebElement fullName;
	
	@FindBy(xpath="//input[@id='address1']")
	WebElement streetAddress;
	
	@FindBy(xpath="//input[@id='city']")
	WebElement city;
	
	@FindBy(xpath="//input[@id='state']")
	WebElement state;
	
	@FindBy(xpath="//select[@id='country']")
	WebElement country;
	
	@FindBy(xpath="//input[@id='zip']")
	WebElement zip;
	
	@FindBy(xpath="//input[@id='ccnumber']")
	WebElement ccNumber;
	
	@FindBy(xpath="//input[@id='ccexp']")
	WebElement ccExp;
	
	
	@FindBy(xpath="//input[@id='credit-card-number']")
	WebElement BTccNumber;
	
	@FindBy(xpath="//input[@id='checkout-frames-card-number']")
	WebElement COccNumber;
	
	@FindBy(xpath="//input[@id='checkout-frames-expiry-date']")
	WebElement COExp;
	
	@FindBy(xpath="//input[@id='checkout-frames-cvv']")
	WebElement COCvv;
	
	@FindBy(xpath="//input[@class='card-number']")
	WebElement globalccard;
	
	@FindBy(xpath="//input[@class='card-expiration']")
	WebElement globalccExp;
	
	@FindBy(xpath="//input[@type='tel']")
	WebElement globalcvv;
	
	@FindBy(xpath="//input[@id='expiration']")
	WebElement BTccExp;
	
	@FindBy(xpath="//input[@id='cvv']")
	WebElement cvv;
	
	@FindBy(xpath="//div[@id='collect-checkout-cart']")
	WebElement click;
	
	@FindBy(xpath="//p[contains(@class,'jss') and contains(text(),'ID:')]")
	WebElement SessionId;
	
	@FindBy(xpath ="//p[contains(@class,'jss') and contains(text(),'Your')]")
	WebElement SessionStatus;
	
	@FindBy(xpath="//input[@id='addressOne']")
	WebElement addressOne;
	
	
	@FindBy(xpath="//input[@id='addressTwo']")
	WebElement addressTwo;
	
	@FindBy(xpath="//input[@id='postalCode']")
	WebElement postalCode;
	
	

	
	
	
	
	public void savePayment() throws InterruptedException {
		Thread.sleep(15000);
		email.sendKeys("abcd@yopmail.com");
		fullName.sendKeys("Automation Testing");
		streetAddress.sendKeys("HSR Layout");
		city.sendKeys("Bangalore");
		Select select = new Select(country);
		select.selectByVisibleText("India");
		state.sendKeys("Bangalore");
		zip.sendKeys("123456");
		
	}
	public String enterCreditCard() throws InterruptedException {
		Thread.sleep(5000);
		CreateSession.getAutomationConfiguration().Driver.switchTo().frame("CollectJSInlineccnumber");
		ccNumber.sendKeys("4111111111111111");
		CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
		CreateSession.getAutomationConfiguration().Driver.switchTo().frame("CollectJSInlineccexp");
		ccExp.sendKeys("12/27");
		CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
		CreateSession.getAutomationConfiguration().Driver.switchTo().frame("CollectJSInlinecvv");
		cvv.sendKeys("123");
		CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
		CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//button[text()='Add Card']")).click();
		Thread.sleep(20000);
		ApcoaListeners.logInfo("Session Id while session is ongoing - "+SessionId.getText());
		String status = SessionStatus.getText();
		SA.assertEquals(status, "Your Ongoing Session");
		SA.assertAll();
		return SessionId.getText(); 
		
	}
	public String enterCreditCardBraintree() throws InterruptedException {
		Thread.sleep(5000);
		CreateSession.getAutomationConfiguration().Driver.switchTo().frame("braintree-hosted-field-number");
		BTccNumber.sendKeys("4111111111111111");
		CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
		CreateSession.getAutomationConfiguration().Driver.switchTo().frame("braintree-hosted-field-expirationDate");
		BTccExp.sendKeys("12/27");
		CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
		CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//span[text()='Authorize Card']")).click();
		Thread.sleep(20000);
		ApcoaListeners.logInfo("Session Id while session is ongoing - "+SessionId.getText());
		String status = SessionStatus.getText();
		SA.assertEquals(status, "Your Ongoing Session");
		SA.assertAll();
		return SessionId.getText(); 
		
	}
	public String enterCreditCardGlobal() throws InterruptedException {
		Thread.sleep(10000);
		CreateSession.getAutomationConfiguration().Driver.switchTo().frame("card-number");
		globalccard.sendKeys("4111111111111111");
		CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
		CreateSession.getAutomationConfiguration().Driver.switchTo().frame("card-expiration");
		globalccExp.sendKeys("12/27");
		CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
		CreateSession.getAutomationConfiguration().Driver.switchTo().frame("card-cvv");
		globalcvv.sendKeys("123");
		CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
		addressOne.sendKeys("Address Line 1");
		addressTwo.sendKeys("Address Line 2");
		postalCode.sendKeys("124533");
		CreateSession.getAutomationConfiguration().Driver.switchTo().frame("submit");
		CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//button[text()='Submit']")).click();
		Thread.sleep(20000);
		ApcoaListeners.logInfo("Session Id while session is ongoing - "+SessionId.getText());
		String status = SessionStatus.getText();
		SA.assertEquals(status, "Your Ongoing Session");
		SA.assertAll();
		return SessionId.getText();
	}
	public void enterCreditCardCheckout() throws InterruptedException {
		try {
			Thread.sleep(10000);
			System.out.println("Inside enterCreditCardCheckout");
			CreateSession.getAutomationConfiguration().Driver.switchTo().frame(0);
			COccNumber.sendKeys("4242 4242 4242 4242");
			COExp.sendKeys("12/23");
			COCvv.sendKeys("100");
			CreateSession.getAutomationConfiguration().Driver.switchTo().parentFrame();
			System.out.println("Outside Iframe");
			CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//button[text()='Add Card']")).click();
			Thread.sleep(20000);
			System.out.println("After 20 secs");
//			AutomationConfiguration.Driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
	public boolean checkbutton() {
		try{CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//button[text()='Save Payment Info']"));
		return false;
	}catch (Exception e) {
		return true;
	}}
	public boolean failTransaciton() {
		try{CreateSession.getAutomationConfiguration().Driver.findElement(By.className("sc-kEqXSa bAVzgZ"));
		System.out.println("Transaction Failed");
		return false;
	}catch (Exception e) {
		return true;
	}}
}