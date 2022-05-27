package Pages.PayG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPromo {
	@FindBy(className="jss77")
	WebElement promoButton;
	
	@FindBy(xpath="//input[@id='email-address']")
	WebElement enterPromo;
	
	@FindBy(className="MuiButton-label")
	WebElement Add_promo_button;
	
	public void addDiscount() throws InterruptedException {
		promoButton.click();
		Thread.sleep(5000);
		enterPromo.sendKeys("20OFFPAYG");
		Add_promo_button.click();
		
	}

}
