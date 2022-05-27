package Pages.Mobile;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import CommonUtility.CreateSession;
import CommonUtility.GenericMethods;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PageSelectCountry {
	WebDriver driver;

	By selectCountryName = By.className("android.widget.TextView");

	@iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel")
	WebElement iOSSelectCountry;
	
	@iOSXCUITFindBy(xpath = "//*[@name='Done']")
	WebElement iOSDoneBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/text')]")
	WebElement selectCountryButton;
	
	@iOSXCUITFindBy(xpath = "//*[@name='Already registered? Log In']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_login')]")
	WebElement btnLog;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/footer_button_1')]")
	WebElement btnfooterok;

	public String CountrySelected = "";

	public PageSelectCountry(WebDriver appiumDriver){
		this.driver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator (appiumDriver), this);
	}	

	/**
	 * method to select a specific country
	 *
	 * @param countryName selects the specific country
	 */
	public void selectCountry(String countryName) throws InterruptedException{
		ApcoaListeners.logInfo("Going to Select Country: "+ countryName);

		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
			GenericMethods.explicitWait(driver, selectCountryName,20);	
			List<WebElement> selectCountry = this.driver.findElements(selectCountryName);
			for(WebElement singleCountry : selectCountry){
				ApcoaListeners.logInfo("Country Found: "+singleCountry.getText());
				if(singleCountry.getText().toUpperCase().equals(countryName.toUpperCase())){
					singleCountry.click();
					//	Thread.sleep(4000);//here
					ApcoaListeners.logInfo("Country found and clicked successfully");
					break;
				}
			}
		}else {
			CommonUtility.GenericMethods.waitForElement(driver,selectCountryButton,15);
			iOSSelectCountry.sendKeys(countryName);
			CommonUtility.GenericMethods.waitForElement(driver,iOSDoneBtn,5);
			iOSDoneBtn.click();
		}
		CommonUtility.GenericMethods.waitForElement(driver,selectCountryButton,15);
		CountrySelected = selectCountryButton.getText().toString();
		ApcoaListeners.logInfo("selectCountry ended: country selected: "+ selectCountryButton.getText());	
		try
		{btnfooterok.click();}
		catch(Exception e)
		{

		}
	}

	/**
	 * method click on select country button
	 *
	 */
	public void selectCountryClick() throws InterruptedException{
		Thread.sleep(5000);
		ApcoaListeners.logInfo("Going to click select_country button");
		CommonUtility.GenericMethods.waitForElement(driver,selectCountryButton,20);
		Thread.sleep(2000);
		selectCountryButton.click();
		ApcoaListeners.logInfo("Clicked select_country button successfully");		
	}

	/**
	 * method to click login button in select country page
	 *
	 * @return object that contains the user credentials
	 */
	public void btnLoginClick() throws InterruptedException{
		ApcoaListeners.logInfo("Going to click Login button");
		try {
			Thread.sleep(2000);
			btnfooterok.click();
		}catch(Exception e)
		{}
		CommonUtility.GenericMethods.waitForElement(driver,btnLog,150);
		btnLog.click();
		
	}
}
