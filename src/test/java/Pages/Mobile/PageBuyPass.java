package Pages.Mobile;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import CommonUtility.AutomationConfiguration;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PageBuyPass {

	WebDriver driver;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action')]")
	WebElement allowButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ub_page_button_cancel')]")
	WebElement cancelPopUpButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	WebElement searchBox;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_Search')]")
	WebElement searcheditBox;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_address')]")
	WebElement carparkName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_buy_pass')]")
	WebElement buyPassButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_positive_action')]")
	WebElement buyPassAgainButton;
	
	@AndroidFindBy(id="android:id/button1")
	WebElement selectDate;
	
	@AndroidFindBy(id="android:id/button1")
	WebElement okButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_add_promo_code')]")
	WebElement addPromoLink;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_add_discount')]")
	WebElement addPromoDiscount;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	WebElement addPromoTextbox;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_apply_promo_code')]")
	WebElement applyButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	WebElement okGotItButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_pay')]")
	WebElement proceedButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm_pay')]")
	WebElement confirmPayButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	WebElement paymentSuccessfulButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_okay_got_it')]")
	WebElement promoAppliedSuccssfullyOKButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_message')]")
	WebElement promoNotAppliedText;
	
	By puchasePassbtn= By.xpath("//*[contains(@resource-id,':id/tv_purchase_pass')]");
	
	By passList = By.xpath("//*[contains(@resource-id,':id/tv_pass_name')]");
	
	public PageBuyPass(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}



	@SuppressWarnings("rawtypes")
	public void returnPreviousPage() {
	((AndroidDriver) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}

	public void applyPromoCode(String PromoCode) throws InterruptedException {
		ApcoaListeners.logInfo("Going to apply promo code");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,addPromoDiscount,30);
		addPromoDiscount.click();
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,addPromoTextbox,30);
		addPromoTextbox.sendKeys(PromoCode);
		Thread.sleep(5000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,applyButton,50);
		applyButton.click();
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,okGotItButton,50);
		okGotItButton.click();
		Thread.sleep(3000);
		
		ApcoaListeners.logInfo("Promo code End");
	}
	
	public static void scrollPage() {
		Dimension dimension = AutomationConfiguration.AppiumDriver.manage().window().getSize();
		int start_x=(int)(dimension.width*0.5);
		int start_y=(int)(dimension.height*0.8);

		int end_x=(int) (dimension.width*0.2);
		int end_y=(int) (dimension.height*0.2);

		@SuppressWarnings("rawtypes")
		TouchAction touch= new TouchAction(AutomationConfiguration.AppiumDriver);
		touch.press(PointOption.point(start_x,start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(end_x,end_y)).release().perform();
	}

	public void clickOnPurchasePassButton() {
		List<WebElement> purchasePassButton = driver.findElements(puchasePassbtn);
		List<WebElement> listOfPass= driver.findElements(passList);
		for(int i=0;i<listOfPass.size();i++){
			String a=listOfPass.get(i).getText();
			System.out.println(a);
			if(a.equalsIgnoreCase("Test_notification")){
				purchasePassButton.get(i).click();
				System.out.println("Clicked on purchase pass button");
				break;
			}
		}
	}

	public void searchAreaAndBuyPass(String areaCode,String promoName) throws InterruptedException {
		Thread.sleep(2500);
		allowButton.click();
		Thread.sleep(2500);
		cancelPopUpButton.click();
		Thread.sleep(2500);
		searchBox.click();
		Thread.sleep(2500);
		searcheditBox.sendKeys(areaCode);
		Thread.sleep(2500);
		carparkName.click();
		Thread.sleep(2500);
		returnPreviousPage();
		Thread.sleep(2500);
		scrollPage();
		Thread.sleep(2500);
		buyPassButton.click();
		Thread.sleep(2500);
		buyPassAgainButton.click();
		Thread.sleep(2500);
		scrollPage();
		Thread.sleep(2500);
		clickOnPurchasePassButton();
		selectDate.click();
		Thread.sleep(2500);
		okButton.click();
		Thread.sleep(2500);
		addPromoLink.click();
		Thread.sleep(2500);
		addPromoTextbox.sendKeys(promoName);
		applyButton.click();
		Thread.sleep(2500);
		try {
			String text=(promoNotAppliedText.getText());
			if(text.equals("Promo code cannot be applied")){
				System.out.println("Promo not applied");
				okGotItButton.click();
				returnPreviousPage();
				Thread.sleep(2500);
			}
		}catch(Exception e) {
			System.out.println("Promo applied");
			promoAppliedSuccssfullyOKButton.click();
			Thread.sleep(2500);
		}
		Thread.sleep(2500);
		proceedButton.click();
		Thread.sleep(2500);
		confirmPayButton.click();
		Thread.sleep(2500);
		paymentSuccessfulButton.click();
	}
}