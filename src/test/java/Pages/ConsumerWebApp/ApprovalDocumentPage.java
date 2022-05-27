package Pages.ConsumerWebApp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ConsumerWebApp.ObjectMapper.PermitMapper;
import TestNGListeners.ApcoaListeners;

public class ApprovalDocumentPage {
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='Choose Parking']")
	WebElement text;
	
	@FindBy(xpath="//p[text()='Continue']")
	WebElement continuele;
	
	@FindBy(xpath="//span[text()='Agree']")
	WebElement tap;
	
    @FindBy(name="rnr")
	WebElement agree;
    
    @FindBy(xpath="//*[name()='svg'][@style='cursor: pointer;']")
	WebElement document;
    
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
	
	@FindBy(xpath="(//*[local-name()='svg' and  @width='30'])[2]")
	WebElement cross;
	
	@FindBy(xpath="((//*[local-name()='svg'][@height='16' ])[5])")
	WebElement dlt;
	
	@FindBy(xpath="//span[text()='Yes']")
	WebElement yes;
	
	@FindBy(xpath="//p[text()='View Documents']")
	WebElement documentview;
	
	@FindBy(xpath="(//div[starts-with(@class,'jss')])[26]")
	WebElement svg;
	
	public ApprovalDocumentPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean validateText() {
		ApcoaListeners.logInfo("Checking the text");
		return text.isDisplayed();
	}
	
	public void selectPermit(PermitMapper permitmapper) {
		
			ApcoaListeners.logInfo("Selecting permit for approval");
			String permit_name = permitmapper.getPermitname	();
		    driver.findElement(By.xpath("//p[contains(text(),'"+permit_name+"')]")).click();
		    ApcoaListeners.logInfo("Permit for approval :"+permit_name);
	}
	
	public void clicks() {
		ApcoaListeners.logInfo("Going to click Continue");
		continuele.click();
		ApcoaListeners.logInfo("Going to click Agree");
		tap.click();
	}
	
	public void uploadDocument() throws AWTException {

		    WebElement element = driver.findElement(By.xpath("//*[name()='svg'][@style='cursor: pointer;']"));
		    element.click();
		    
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    StringSelection str = new StringSelection("/home/gmp/Pictures/Screenshotgmp.png");
		    clipboard.setContents(str, null);

		    Robot robot = new Robot();
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
}
	public String validateAmount() {
	ApcoaListeners.logInfo("Geting the total price of the permit");
	return total.getText();
}
	
	public void approve() {
		ApcoaListeners.logInfo("Going to Send for Approval");
		approval.click();
		ApcoaListeners.logInfo("Going to Mypermit page");
		mypermits.click();
		ApcoaListeners.logInfo("Checking permit details");	
		
	}
	
	public void check() {
		Actions action =new Actions(driver);
		WebElement ele =driver.findElement(By.cssSelector("div[id='scrollBox']>div:first-of-type"));
		action.doubleClick(ele).perform();	
		ApcoaListeners.logInfo("Working");	
	}
	
	public String verifyAmount() {
		highLightElement(total_2);
		ApcoaListeners.logInfo("Geting the total price of the permit");
		return total_2.getText();	
	}
	
//	public void viewDocument() throws InterruptedException {
//		highLightElement(documentview);
//		documentview.click();
//		svg.click();
////		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
////		driver.switchTo().window(tabs2.get(1));
////		driver.close();
////		driver.switchTo().window(tabs2.get(0));
////		AutomationConfiguration.Driver.switchTo().defaultContent();	
//	}
	
	
	public void mypermit() {
//		highLightElement(cross);
//		cross.click();
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
	 public void highLightElement(WebElement element){
		   JavascriptExecutor js = (JavascriptExecutor)driver;   
		   js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);   
		 }
	
}


