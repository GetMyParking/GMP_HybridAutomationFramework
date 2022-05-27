package Pages.ConsumerWebApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;

public class PaymentPage{
	WebDriver driver;
	
	@FindBy(xpath="//p[text()='Payment Method']")
	WebElement payment;
	
	@FindBy(xpath="//p[text()='Add a payment method']")
	WebElement addPayment;
	
    @FindBy(id="email")
    WebElement email;
    
    @FindBy(id="fullName")
    WebElement name;
    
    @FindBy(id="address1")
    WebElement address;
    
    @FindBy(id="country")
    WebElement country;
    
    @FindBy(id="city")
    WebElement city;
    
    @FindBy(id="state")
    WebElement state;
    
    @FindBy(xpath="//button[@role='link']")
    WebElement button;
    
    @FindBy(id="ccnumber")
    WebElement ccNumber;
    
    @FindBy(id="credit-card-number")
    WebElement card_number;
    
    @FindBy(id="zip")
    WebElement zipcode;
	
    @FindBy(id="ccexp")
    WebElement ccExp;
    
	@FindBy(xpath="//span[text()='Cancel']")
	WebElement cancel;
	
	@FindBy(xpath="//*[local-name()='svg' and @height='30']")
	WebElement cross;
    
    @FindBy(css="input[placeholder='MM/YY']")
	WebElement expiry_date;
    
	@FindBy(css="input[placeholder='Enter your card number']")
	WebElement creditCard;
	
	@FindBy(className="card-expiration")
	WebElement expiration;
	
	@FindBy(id="cvv")
	WebElement cvv;
	
	@FindBy(name="displayName")
	WebElement nickName;
	
    @FindBy(xpath="//p[text()='Add Credit Card']")
    WebElement addcreditcard;
	
	public PaymentPage(WebDriver driver){
		this.driver = driver;
	}

	public void add_clicks(){
		payment.click();
		addPayment.click();
		addcreditcard.click();
	}
	public void addCard() throws InterruptedException {
		try {
			CreateSession.getAutomationConfiguration().Driver.switchTo().frame("braintree-hosted-field-number");
			card_number.sendKeys("4111111111111111");
			CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
			CreateSession.getAutomationConfiguration().Driver.switchTo().frame("braintree-hosted-field-expirationDate");
			expiry_date.sendKeys("12/27");
			CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
			cancel.click();
			cross.click();
			
		}
		
		catch(Exception e) {
			ApcoaListeners.logInfo("Clicking on Add vechile ");
			email.sendKeys("testing@yopmail.com");
			name.sendKeys("Test test");
			address.sendKeys("123Street");
			country.sendKeys("India");
			city.sendKeys("Bangalore");
			state.sendKeys("Karnataka");
			zipcode.sendKeys("263002");
			CreateSession.getAutomationConfiguration().Driver.switchTo().frame("CollectJSInlineccnumber");
			ccNumber.sendKeys("4111111111111111");
			CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
			CreateSession.getAutomationConfiguration().Driver.switchTo().frame("CollectJSInlineccexp");
			ccExp.sendKeys("12/27");
			CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
			CreateSession.getAutomationConfiguration().Driver.switchTo().frame("CollectJSInlinecvv");
			cvv.sendKeys("123");
			CreateSession.getAutomationConfiguration().Driver.switchTo().defaultContent();
			button.click();
	//		AutomationConfiguration.Driver.findElement(By.xpath("//button[text()='Add Card']")).click();

	}
	
	
	}
	
}


