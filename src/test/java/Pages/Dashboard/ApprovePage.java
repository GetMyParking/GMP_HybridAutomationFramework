package Pages.Dashboard;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import TestNGListeners.ApcoaListeners;



public class ApprovePage {

	
	WebDriver driver;

   
	
    @FindBy(xpath="//a[@href=('/admin/permit')]")
    WebElement clickpermit;
    
    @FindBy(xpath="//i[@class='anticon anticon-check']//parent::button")
    WebElement clickApprove;
    
    @FindBy(xpath="//button[@class='ant-btn ant-btn-primary ant-btn-sm']")
    WebElement clickOK;
    
    @FindBy(xpath="//div[contains(text(),'Permits')]")
    WebElement clickPermits;
    
  
    
    public ApprovePage(WebDriver driver){
		this.driver = driver;    // open the browser enter url
	}
    
    
    public void gotoApprove() throws InterruptedException{
		Thread.sleep(5000);
		System.out.println("**********");
		clickpermit.click();
		Thread.sleep(10000);
		
//		 ((JavascriptExecutor)AutomationConfiguration.Driver).executeScript("arguments[0].style.border='3px solid red'", approvalpermit);
//			Thread.sleep(5000); 
//			
//				((JavascriptExecutor)AutomationConfiguration.Driver).executeScript("arguments[0].style.border='3px solid white'", approvalpermit);
//				 ApcoaListeners.logInfo("Going to Approve a permit");	
//		       Thread.sleep(5000);
			JavascriptExecutor as = (JavascriptExecutor)driver;	     //approve the first permit in apporvals tab
				as.executeScript("arguments[0].click();", (clickApprove));
				Thread.sleep(4000);
				JavascriptExecutor ms = (JavascriptExecutor)driver;	      //click on ok to approve the permit
			ms.executeScript("arguments[0].click();", (clickOK));
			
			 ApcoaListeners.logInfo("Approved a permit successfully");
			 
			 
			 
				Thread.sleep(3000);
		clickPermits.click();
    }
}
