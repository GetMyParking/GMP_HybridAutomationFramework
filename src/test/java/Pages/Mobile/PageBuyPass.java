package Pages.Mobile;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PageBuyPass {

	WebDriver driver;
	SoftAssert SA=new SoftAssert();

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action')]")
	WebElement allowButton;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ub_page_button_cancel')]")
	WebElement cancelPopUpButton;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	WebElement searchBox;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_Search')]")
	WebElement searcheditBox;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/tv_address')])[1]")
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

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'Code')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_discount_heading')]")
	WebElement addPromoDiscount;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	WebElement addPromoTextbox;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'APPLY')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_apply_promo_code')]")
	WebElement applyButton;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'OKAY')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	WebElement okGotItButton;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/tv_pay')])[3]")
	WebElement proceedButton;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm_pay')]")
	WebElement confirmPayButton;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	WebElement paymentSuccessfulButton;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_okay_got_it')]")
	WebElement promoAppliedSuccssfullyOKButton;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_message')]")
	WebElement promoNotAppliedText;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='Yes, Proceed']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	WebElement confirmPromoCode;

	By puchasePassbtn= By.xpath("//*[contains(@resource-id,':id/tv_purchase_pass')]");

	By passList = By.xpath("//*[contains(@resource-id,':id/tv_pass_name')]");

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_discount_name')]")
	private WebElement isDiscountApplied;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_edit_discount')]")
	private WebElement btnEditPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/half_interstitial_button2')]")
	WebElement ConfirmPromoPayment;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	private WebElement btnclikAddPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_apply_promo_code')]")
	private WebElement btnApplyPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement btnConfirmPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_purchase_pass')]")
	private WebElement passbtn;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/button1')]")
	private WebElement btnok;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_add_promo_code')]")
	private WebElement addpromo;

	@AndroidFindBy(xpath="//android.widget.Toast")
	private WebElement toastcheck;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_buy_pass')]")
	private WebElement passBtn;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_passes')]")
	private WebElement passTab;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_back')]")
	private WebElement btnBack;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	private WebElement searchBar;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_address')]")
	private WebElement desiredLocation;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement ProceedBtn;
	
	@AndroidFindBy(xpath=".//android.widget.EditText[contains(@resource-id,':id/edt_Search')]")
	private WebElement searchParking;
	
	
	public PageBuyPass(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}



	@SuppressWarnings("rawtypes")
	public void returnPreviousPage() {
		((AndroidDriver) CreateSession.getAutomationConfiguration().AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}

	public void addAnotherPromoCode(String code) throws InterruptedException{

		try {
			if(isDiscountApplied.getText().equals(code))
			{   ApcoaListeners.logInfo("Same Promo already Applied");
			return;
			}
		}catch(Exception e) {}
		{
			Thread.sleep(3000);
			try {
				btnEditPromo.click();
			}catch(Exception e){
				addPromoDiscount.click();
			}
			Thread.sleep(2000);
			CommonUtility.GenericMethods.explicitWait(driver,btnclikAddPromo,200);
			btnclikAddPromo.sendKeys(code);
			Thread.sleep(2000);
			btnApplyPromo.click();

			CommonUtility.GenericMethods.explicitWait(driver,btnConfirmPromo,200);	
			btnConfirmPromo.click();
			try {
				btnConfirmPromo.click();
			}
			catch(Exception e) {}

		}
	}


	public void applyPromoCode(String PromoCode) throws InterruptedException {

		ApcoaListeners.logInfo("Going to apply promo code: start");
		CommonUtility.GenericMethods.waitForElement(driver,addPromoDiscount,10);
		addPromoDiscount.click();
		Thread.sleep(3000);
		CommonUtility.GenericMethods.waitForElement(driver,addPromoTextbox,10);
		addPromoTextbox.sendKeys(PromoCode);
		Thread.sleep(5000);
		CommonUtility.GenericMethods.waitForElement(driver,applyButton,10);
		applyButton.click();
		Thread.sleep(3000);
		try {
			CommonUtility.GenericMethods.waitForElement(driver,confirmPromoCode,10);
			confirmPromoCode.click();
		}catch(Exception e) {}
		try {
			CommonUtility.GenericMethods.waitForElement(driver,okGotItButton,15);
			okGotItButton.click();
			Thread.sleep(3000);
		}catch(Exception e) {}

		ApcoaListeners.logInfo("Promo code End");

	}

	public static void scrollPage() {
		Dimension dimension = CreateSession.getAutomationConfiguration().AppiumDriver.manage().window().getSize();
		int start_x=(int)(dimension.width*0.5);
		int start_y=(int)(dimension.height*0.8);

		int end_x=(int) (dimension.width*0.2);
		int end_y=(int) (dimension.height*0.2);

		@SuppressWarnings("rawtypes")
		TouchAction touch= new TouchAction(CreateSession.getAutomationConfiguration().AppiumDriver);
		touch.press(PointOption.point(start_x,start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(end_x,end_y)).release().perform();
	}

	public void clickOnPurchasePassButton() {
		List<WebElement> purchasePassButton = driver.findElements(puchasePassbtn);
		List<WebElement> listOfPass= driver.findElements(passList);
		int numberofpass=	listOfPass.size();
		for(int i=0;i<listOfPass.size();i++){
			String a=listOfPass.get(i).getText();
			System.out.println(a);
			if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Austria")){

				if(a.equalsIgnoreCase("Tagespauschale")){//Test_notification
					purchasePassButton.get(i).click();
					System.out.println("Clicked on purchase pass button");
					break;
				}
			}
			else if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Sweden"))
			{
				if(a.equalsIgnoreCase("En dagsperiodbiljett")){//Test_notification

					purchasePassButton.get(i).click();
					System.out.println("Clicked on purchase pass button");
					break;
				}
			}
			else if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Poland"))
			{
				if(a.equalsIgnoreCase("Abonament 12 godzinny")){//Test_notification

					purchasePassButton.get(i).click();
					System.out.println("Clicked on purchase pass button");
					break;
				}
			}
			else if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Germany"))
			{
				if(a.equalsIgnoreCase("proration allowed testing")){//Test_notification

					purchasePassButton.get(i).click();
					System.out.println("Clicked on purchase pass button");
					break;
				}
			}
			if(i==numberofpass-2)	
				scrollPage();
		}
	}

	public void searchAreaAndBuyPass(String areaCode,String promoName) throws InterruptedException {
		Thread.sleep(2500);
		searchBox.click();
		Thread.sleep(2500);
		searcheditBox.sendKeys(areaCode);
		Thread.sleep(6000);
		carparkName.click();
		Thread.sleep(6000);
		//returnPreviousPage(); //return to previous page
		Thread.sleep(6000);
		//scrollPage();
		//Thread.sleep(6000);
		buyPassButton.click();
		Thread.sleep(3000);
		try {
			CommonUtility.GenericMethods.explicitWait(driver,okGotItButton,5);
			okGotItButton.click();
		}
		catch(Exception e) {}

		//buyPassAgainButton.click();


		Thread.sleep(4000);
		clickOnPurchasePassButton();
		Thread.sleep(4000);
		selectDate.click();
		Thread.sleep(6000);
		okButton.click();
		Thread.sleep(6000);
		System.out.println(promoName);
		if(!promoName.equalsIgnoreCase("none")){
			addPromoLink.click();
			Thread.sleep(6000);
			addPromoTextbox.sendKeys(promoName);
			applyButton.click();
			Thread.sleep(6000);
			try {
				String text=(promoNotAppliedText.getText());
				if(text.equals("Promo code cannot be applied")){
					System.out.println("Promo not applied");
					okGotItButton.click();
					returnPreviousPage();
					Thread.sleep(4000);
				}
			}
			catch(Exception e) {
				System.out.println("Promo applied");
				promoAppliedSuccssfullyOKButton.click();
				Thread.sleep(4000);
			}
		}
		Thread.sleep(4000);
		proceedButton.click();
		Thread.sleep(4000);
		confirmPayButton.click();
		try {
			CommonUtility.GenericMethods.explicitWait(driver,ConfirmPromoPayment,5);
			ConfirmPromoPayment.click();
		} catch(Exception e) {}
		CommonUtility.GenericMethods.explicitWait(driver,promoNotAppliedText,5);
		SA.assertEquals(promoNotAppliedText.getText(),"Payment Successful");
		SA.assertAll();
		paymentSuccessfulButton.click();

		Thread.sleep(7000);
		((AndroidDriver<WebElement>) CreateSession.getAutomationConfiguration().AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));

	}


	public Boolean passPromoToastCheck() throws InterruptedException {
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Now going to click on pass button");
		passBtn.click();
		ApcoaListeners.logInfo("Now going to click on buy pass button");
		Thread.sleep(2000);
		passbtn.click();
		ApcoaListeners.logInfo("Now going to click on OK");
		Thread.sleep(2000);
		btnok.click();
		ApcoaListeners.logInfo("Now going to click on OK");
		Thread.sleep(2000);
		btnok.click();
		ApcoaListeners.logInfo("Now going to click on add promocode");
		Thread.sleep(2000);
		addpromo.click();
		Thread.sleep(2000);
		btnclikAddPromo.click();
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Going to Add Discount Coupon");
		btnclikAddPromo.sendKeys("TEST");
		Thread.sleep(2000);
		btnApplyPromo.click();
		int flag = 0;
		String text="";
		for(int i = 0;i<=30;i++) {
			try {
				text = toastcheck.getText();
				if(text.length()>0) {
					break;
				}
			}catch(Exception e) {}
		}

		if(text.length()>0) {
			return false;
		}
		return true;
	}

	public Boolean renewPass(String passName,String parkingLocation) throws InterruptedException {	
		ApcoaListeners.logInfo("Going to  Hamburger Menu");
		PageHomeApcoa pha = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
		pha.clickMenuBtn();
		Thread.sleep(4000);
		ApcoaListeners.logInfo("Going to PassTab");
		passTab.click();
		Thread.sleep(4000);
		boolean isPassPresent = false;
		int count = 0;
		List<WebElement> passNameList = CreateSession.getAutomationConfiguration().AppiumDriver.findElements(By.xpath("//*[contains(@resource-id,'id/tv_pass_name')]"));
		while(passNameList.size()>=1) {
			for(WebElement e:passNameList) {
				if(e.getText().equals(passName)) {
					int x = e.getRect().getX();
					int ystart = e.getRect().getY();
					TouchAction swipe = new TouchAction((PerformsTouchActions) driver)
							.press(PointOption.point(x,ystart)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
							.moveTo(PointOption.point(x,490))
							.release().perform();
					swipe.cancel();
					List<WebElement> passNamebtn = CreateSession.getAutomationConfiguration().AppiumDriver.findElements(By.xpath("//*[contains(@resource-id,':id/rl_item_pass')]/parent::android.widget.RelativeLayout//android.widget.LinearLayout[contains(@resource-id,':id/rl_auto_renew')]//android.widget.Switch[contains(@resource-id,':id/sw_auto_renew')]"));
					isPassPresent = true;
					// renewText.isDisplayed()
					if(passNamebtn.size()>0 ) {
						return false;

					}
					else {	
						ApcoaListeners.logInfo("TestCase is Passed SuccessFully");
						break;
					}
				}	
			}
			if(isPassPresent) {
				break;
			}
			TouchAction swipe = new TouchAction((PerformsTouchActions) driver)
					.press(PointOption.point(0,1343)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
					.moveTo(PointOption.point(0,590))
					.release().perform();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			swipe.cancel();	
			passNameList.clear();
			Thread.sleep(2000);
			passNameList = CreateSession.getAutomationConfiguration().AppiumDriver.findElements(By.xpath("//*[contains(@resource-id,'id/tv_tariff_type')]"));
		}
		if(isPassPresent==false) {
			ApcoaListeners.logInfo("this pass is not present");
		}
		Thread.sleep(3000);
		btnBack.click();
		Thread.sleep(3000);
		try{
			ApcoaListeners.logInfo("Now going to click on searchbar ");


			// get the parking
			searchBar.click();
			ApcoaListeners.logInfo("Now giving value ");
			searchParking.sendKeys(parkingLocation);
			Thread.sleep(4000);
			String topaddress= desiredLocation.getText();
			desiredLocation.click();

			////////////////////
			Thread.sleep(2000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		btnBack.click();
		Thread.sleep(3000);
		desiredLocation.click();
		Thread.sleep(3000);
		ApcoaListeners.logInfo(" click on pass button  ");
		Thread.sleep(3000);
		passBtn.click();
		ApcoaListeners.logInfo("Click on Proceed button ");
		Thread.sleep(3000);
		ProceedBtn.click();
		Thread.sleep(3000);
		List<WebElement> passNameLists = CreateSession.getAutomationConfiguration().AppiumDriver.findElements(By.xpath("//*[contains(@resource-id,':id/rclr_purchase_pass')]"));
		for(WebElement e:passNameLists) {
			if(e.getText()==passName) {
				return false;
			}
		}
		Thread.sleep(2000);
		SA.assertAll();
		return true;
	}


}