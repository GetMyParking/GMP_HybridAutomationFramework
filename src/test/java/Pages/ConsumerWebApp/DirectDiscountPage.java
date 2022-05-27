package Pages.ConsumerWebApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ConsumerWebApp.ObjectMapper.PermitMapper;
import TestNGListeners.ApcoaListeners;


public class DirectDiscountPage {
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='Choose Parking']")
	WebElement text;
	
	@FindBy(xpath="//p[text()='Continue']")
	WebElement continuele;
	
	@FindBy(xpath="//span[text()='Agree']")
	WebElement tap;
	
    @FindBy(id="promoCode")
   	WebElement promo;
    
    @FindBy(xpath="//span[text()='Apply']")
	WebElement apply;
	
    @FindBy(name="rnr")
	WebElement agree;
    
    @FindBy(xpath="//span[text()='Payment Summary']//following::h5[@class='MuiTypography-root MuiTypography-h5']")
  	WebElement total;
	
	@FindBy(xpath="//span[contains(text(),'Buy Permit')]")
	WebElement buy;
	
	@FindBy(xpath="//span[text()='Go to My Permits']")
	WebElement mypermits;
	
	@FindBy(xpath="//span[text()='Active']")
	WebElement active;
	
	@FindBy(xpath="//h5[@class='MuiTypography-root MuiTypography-h5']")
	WebElement total_2;;
	
	
	public DirectDiscountPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean validateText() {
		ApcoaListeners.logInfo("Checking the text");
		return text.isDisplayed();
	}
	
	public void buyPermit(PermitMapper permitmapper) {
		ApcoaListeners.logInfo("Selecting permit for direct buy");
		String permit_name = permitmapper.getPermitname();
		driver.findElement(By.xpath("//p[contains(text(),'"+permit_name+"')]")).click();
		ApcoaListeners.logInfo("Permit for direct buy :"+permit_name);
	}

	public void clicks() {
		ApcoaListeners.logInfo("Going to click Continue");
		continuele.click();
		ApcoaListeners.logInfo("Going to click Agree");
			tap.click();
		
	}
	
	public void enterDiscount(String directcode) {
		ApcoaListeners.logInfo("Going to enter promocode");
		promo.sendKeys(directcode);
		ApcoaListeners.logInfo("Sucessfully Entered location: "+directcode);
		apply.click();
	}	
	
	public String validateAmount() {
	ApcoaListeners.logInfo("Geting the total price of the permit");
	return total.getText();
}
	
	public void directBuy() {
		ApcoaListeners.logInfo("Going to buy permit");
		buy.click();
		ApcoaListeners.logInfo("Going to Mypermit page");
		mypermits.click();
	}
	
	public void active() {
		ApcoaListeners.logInfo("Checking permit details");	
		active.click();
		
	}
	
	public void check() {
		Actions action =new Actions(driver);
		WebElement ele =driver.findElement(By.cssSelector("div[id='scrollBox']>div:first-of-type"));
		action.doubleClick(ele).perform();	
		ApcoaListeners.logInfo("Working");	
	}
	
	public String verifyAmount() {
		ApcoaListeners.logInfo("Geting the total price of the permit");
		return total_2.getText();
	}
}




