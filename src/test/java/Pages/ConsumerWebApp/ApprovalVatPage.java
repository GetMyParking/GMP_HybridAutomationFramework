package Pages.ConsumerWebApp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import CommonUtility.CreateSession;
import ConsumerWebApp.ObjectMapper.PermitMapper;
import TestNGListeners.ApcoaListeners;

public class ApprovalVatPage {
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='Choose Parking']")
	WebElement text;
	
	@FindBy(xpath="//p[text()='Continue']")
	WebElement continuele;
	
	@FindBy(xpath="//span[text()='Agree']")
	WebElement tap;
	
    @FindBy(name="rnr")
	WebElement agree;
    
    @FindBy(xpath="//span[text()='Payment Summary']//following::h5[@class='MuiTypography-root MuiTypography-h5']")
	WebElement total;
	
	@FindBy(xpath="//span[(text()='Send for approval')]")
	WebElement approval;
	
	@FindBy(xpath="//span[text()='Go to My Permits']")
	WebElement mypermits;
	
	@FindBy(xpath="//h5[@class='MuiTypography-root MuiTypography-h5']")
	WebElement total_2;
	
	@FindBy(xpath="(//p[text()='My Permits'])[1]")
	WebElement permit;
	
	@FindBy(xpath="((//*[local-name()='svg'][@height='16' ])[5])")
	WebElement dlt;
	
	@FindBy(xpath="//span[text()='Yes']")
	WebElement yes;
	
	public ApprovalVatPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean validateText() {
		ApcoaListeners.logInfo("Checking the text");
		highLightElement(text);
		return text.isDisplayed();
	}
	
	public void selectPermit(PermitMapper permitmapper) {

//		try {
			ApcoaListeners.logInfo("Selecting permit for approval");
			String permit_name = permitmapper.getPermitname	();
		    driver.findElement(By.xpath("//p[contains(text(),'"+permit_name+"')]")).click();
		    ApcoaListeners.logInfo("Permit for approval :"+permit_name);
//			
//		}
//		catch(Exception e) {
			
//		ApcoaListeners.logInfo("Selecting permit for approval");
//		driver.findElement(By.xpath("(//input[@aria-label='A'])[1]")).click();
//		Actions actions = new Actions(driver);
//		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
//		String permit_name = permitmapper.getPermitname	();
//	    driver.findElement(By.xpath("//p[contains(text(),'"+permit_name+"')]")).click();
//	    ApcoaListeners.logInfo("Permit for approval :"+permit_name);
//			
//		}
	}
	
	public void clicks() {
		ApcoaListeners.logInfo("Going to click Continue");
		highLightElement(continuele);
		continuele.click();
		ApcoaListeners.logInfo("Going to click Agree");
		highLightElement(tap);
		tap.click();
		
		
	}
	
	public String validateAmount() {
	ApcoaListeners.logInfo("Geting the total price of the permit");
	highLightElement(total);
	return total.getText();
}
	
	public void approve() {
		ApcoaListeners.logInfo("Going to Send for Approval");
		highLightElement(approval);
		approval.click();
		ApcoaListeners.logInfo("Going to Mypermit page");
		highLightElement(mypermits);
		mypermits.click();
		ApcoaListeners.logInfo("Checking permit details");	
		
	}
	
	public void check() {
		Actions action =new Actions(driver);
		WebElement ele =driver.findElement(By.cssSelector("div[id='scrollBox']>div:first-of-type"));
		highLightElement(ele);
		action.doubleClick(ele).perform();	
		ApcoaListeners.logInfo("Working");	
	}
	
	public String verifyAmount() {
		ApcoaListeners.logInfo("Geting the total price of the permit");
		highLightElement(total_2);
		return total_2.getText();	
	}
	
	public void mypermit() {
		highLightElement(permit);
		permit.click();
	}
	
	public void delete() {
		ApcoaListeners.logInfo("Clicking on my permits");
		highLightElement(dlt);
		dlt.click();
		highLightElement(yes);
		yes.click();	
	}
	 public static void highLightElement(WebElement element){
		   JavascriptExecutor js = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;   
		   js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);   
		 }
	
}


