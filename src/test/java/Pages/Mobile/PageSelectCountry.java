package Pages.Mobile;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import CommonUtility.AutomationConfiguration;
import CommonUtility.GenericMethods;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageSelectCountry {
	
	WebDriver driver;
	
	By selectCountryName = By.className("android.widget.TextView");
	
	//	
	//com.apcoaflow.consumer.staging:id/tv_next
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/text")
	WebElement selectCountryButton;
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/tv_login")
	WebElement btnLog;
	
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/tv_next")
	WebElement nextbtn;
	
	
	
	public String CountrySelected = "";
	
	public PageSelectCountry(WebDriver appiumDriver)
	{
		this.driver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator (appiumDriver), this);
	
	}
	
	
	public void selectCountry(String countryName) throws InterruptedException
	{
		ApcoaListeners.logInfo("Going to Select Country: "+ countryName);
		GenericMethods.explicitWait(driver, selectCountryName,75);
		
		
		List<WebElement> selectCountry = this.driver.findElements(selectCountryName);
		
	
		for(WebElement singleCountry : selectCountry)
		{
			ApcoaListeners.logInfo("Country Found: "+singleCountry.getText());
			if(singleCountry.getText().toUpperCase().equals(countryName.toUpperCase()))
			{
				singleCountry.click();
				ApcoaListeners.logInfo("Country found and clicked successfully");
				break;
			}
		}
		//Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,selectCountryButton,15);
		CountrySelected = selectCountryButton.getText().toString();
		ApcoaListeners.logInfo("selectCountry ended: country selected: "+ selectCountryButton.getText());
		
	}
	
	
	public void selectCountryClick() throws InterruptedException
	{
		ApcoaListeners.logInfo("Going to click select_country button");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,selectCountryButton,150);
		Thread.sleep(15000);
		selectCountryButton.click();
		ApcoaListeners.logInfo("Clicked select_country button successfully");
		
	}
	
	public void btnLoginClick()
	{
		ApcoaListeners.logInfo("Going to click Login button");
		btnLog.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,nextbtn,80);
		nextbtn.click();
		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.acceptPushNotification();
		ApcoaListeners.logInfo("Clicked Login button successfully");
	}
	

}
