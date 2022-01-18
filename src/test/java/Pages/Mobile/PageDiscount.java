package Pages.Mobile;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class PageDiscount {

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	WebElement btnAddDiscount;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	WebElement edtPromoCode;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	WebElement btnApplyPromoCode;
	
	
	
}
