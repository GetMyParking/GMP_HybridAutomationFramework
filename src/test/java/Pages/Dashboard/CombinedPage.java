package Pages.Dashboard;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

	public class CombinedPage {
		WebDriver driver;
		
		@FindBy(xpath="(//span[@class='ant-select-arrow'])[2]")
		WebElement dropdown;
		
		@FindBy(xpath="//button[@class='ant-btn sc-dnqmqq iPGAyJ ant-btn-primary']")
		WebElement apply;
		
		@FindBy(xpath="//a[@href='/admin/list']")
		WebElement overviewbtn;
		
		public CombinedPage(WebDriver driver){
			this.driver = driver;
		}
		
		public void selectype(){	
			dropdown.click();

		}
		public void selecpermit(){	

			Actions action =new Actions(driver);
			action.sendKeys(Keys.UP).build().perform();
			action.sendKeys(Keys.UP).build().perform();
			action.sendKeys(Keys.RETURN).build().perform();
			apply.click();
			
		}
		
		public void gotoOverview() throws InterruptedException{
			overviewbtn.click();

	}
}

