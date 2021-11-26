package Pages.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import CommonUtility.AutomationConfiguration;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageAddVehicle {
	
	WebDriver driver;
	
	
	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[3]")
	WebElement btAllow;
	
	
	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.ImageView[1]")
	WebElement btnMenu;
	
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/tv_vehicle_nav")
	WebElement btnNavVehicle;
	
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/iv_add")
	WebElement btnAddVehicle;
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/et_vehicle_number")
	WebElement edtbxAddVehicle;
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/btn_save")
	WebElement btnSaveVehicle;
	
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/btn_save")
	WebElement btnbacktohome;
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/tv_search")
	WebElement btnsearchparking;
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/edt_Search")
	WebElement edtxsearchparking;
	
	//(//android.widget.TextView[@resource-id='com.apcoaflow.consumer.staging:id/tv_vehicle_number'])[1]
	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id='com.apcoaflow.consumer.staging:id/tv_vehicle_number'])[1]")
	WebElement txtfirstlpr;
	
	@AndroidFindBy(xpath="(//android.widget.ImageView[@resource-id='com.apcoaflow.consumer.staging:id/iv_delete_vehicle'])[1]")
	WebElement btnfirstlprdel;
	
	@AndroidFindBy(id="com.apcoaflow.consumer.staging:id/actv_positive_button")
	WebElement btnconfirmdel;
		
	public PageAddVehicle(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}
	
	public String getfirstvehiclelpr()
	{
		return txtfirstlpr.getText();
	}
	
	public void deletelpr() throws InterruptedException
	{
		ApcoaListeners.logInfo("Going to delete Vehicle");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,30);
		btnMenu.click();
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNavVehicle,30);
		btnNavVehicle.click();
		Thread.sleep(5000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnfirstlprdel,50);
		btnfirstlprdel.click();
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnconfirmdel,50);
		btnconfirmdel.click();
		ApcoaListeners.logInfo("Delete Vehicle End");
	}
	
	public void addVehicle(String vehiclenumber) throws InterruptedException
	{
	
		ApcoaListeners.logInfo("Add Vehicle Start");
		System.out.println("Going to add Vehicle");
		Thread.sleep(5000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,30);
		btnMenu.click();
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNavVehicle,30);
		btnNavVehicle.click();
		Thread.sleep(5000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnAddVehicle,30);
		btnAddVehicle.click();
		Thread.sleep(5000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,edtbxAddVehicle,30);
		edtbxAddVehicle.sendKeys(vehiclenumber);
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnSaveVehicle,30);
		btnSaveVehicle.click();
		Thread.sleep(8000);
		ApcoaListeners.logInfo("Add Vehicle End");

	}
	
	public static void goBack()
	{
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}
	

}
