package Pages.Mobile;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PageAddVehicle {

	WebDriver driver;

	@iOSXCUITFindBy(xpath = "//*[@name='Vehicles']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_vehicle_nav')]")
	WebElement btnNavVehicle;

	@iOSXCUITFindBy(xpath = "//*[@name='add icon']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_add')]")
	WebElement btnAddVehicle;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField)[2]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_vehicle_number')]")
	WebElement edtbxAddVehicle;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'SAVE') or contains(@name,'CONTINUE')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_save')]")
	WebElement btnSaveVehicle;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/tv_vehicle_number')])[1]")
	WebElement txtfirstlpr;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_delete_vehicle')])[1]")
	WebElement btnfirstlprdel;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Delete Vehicle?']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/actv_positive_button')]")
	WebElement btnconfirmdel;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='icn back']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/actv_positive_button')]")
	WebElement btnback;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='YES']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/actv_positive_button')]")
	WebElement btnconfirmadd;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='NEXT']")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_vehicle_nav')]")
	WebElement btnNxtNewUI;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_vehicle_number')]")
	WebElement edtbxAddVehicleNewUI;

	public PageAddVehicle(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}

	/**
	 * method to check specific LP present or not
	 *
	 * @return vehicleNumber to search specific LP
	 */
	public boolean chechVehiclePresent(String vehiclenumber) {
		String lptxt = "//*[contains(@resource-id,':id/tv_vehicle_number')]";
		String lpdltbtn = "//*[contains(@resource-id,':id/iv_delete_vehicle')]";

		int flag = 0;
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
			lptxt = "//XCUIElementTypeStaticText[@name='"+vehiclenumber+"']";
		}
		List<WebElement> lprs = CreateSession.getAutomationConfiguration().AppiumDriver.findElements(By.xpath(lptxt));
		for(int i=0;i<lprs.size();i++) {
			if(lprs.get(i).getText().equalsIgnoreCase(vehiclenumber)) {
				flag = 1;
				break;
			}
		}
		if(flag == 1) {
			return true;
		}
		return false;

	}

	/**
	 * method to delete specific LP
	 *
	 * @return vehicleNumber to delete specific LP
	 */
	public void deletelpr(String vehiclenumber) throws InterruptedException{
		ApcoaListeners.logInfo("Going to delete Vehicle");
		PageHomeApcoa pha = new PageHomeApcoa(driver);
		pha.clickMenuBtn();
		CommonUtility.GenericMethods.waitForElement(driver,btnNavVehicle,15);
		btnNavVehicle.click();
		if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("IOS")) {
			Thread.sleep(3000);
			WebElement delbtn = CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+vehiclenumber+"']//parent:: XCUIElementTypeCell/XCUIElementTypeImage[1]"));
			CommonUtility.GenericMethods.waitForElement(driver,delbtn,15);
			delbtn.click();
		}else {
			CommonUtility.GenericMethods.waitForElement(driver,btnfirstlprdel,15);
			String lptxt = "//*[contains(@resource-id,':id/tv_vehicle_number')]";
			String lpdltbtn = "//*[contains(@resource-id,':id/iv_delete_vehicle')]";
			List<WebElement> lprs = CreateSession.getAutomationConfiguration().AppiumDriver.findElements(By.xpath(lptxt));
			List<WebElement> lprsbtn = CreateSession.getAutomationConfiguration().AppiumDriver.findElements(By.xpath(lpdltbtn));
			for(int i=0;i<lprs.size();i++) {
				if(lprs.get(i).getText().equalsIgnoreCase(vehiclenumber)) {
					lprsbtn.get(i).click();
					break;
				}
			}
		//	CommonUtility.GenericMethods.waitForElement(driver,btnfirstlprdel,15);
		//	btnfirstlprdel.click();
		}	
		CommonUtility.GenericMethods.waitForElement(driver,btnconfirmdel,15);
		btnconfirmdel.click();
		ApcoaListeners.logInfo("Delete Vehicle End");
	}


	/**
	 * method to add specific lpr for a account
	 *
	 * @param vehiclenumber used to add specific LP
	 */
	public void addVehicle(String vehiclenumber) throws InterruptedException{
		ApcoaListeners.logInfo("Add Vehicle Start");
		PageHomeApcoa pha = new PageHomeApcoa(driver);
		pha.clickMenuBtn();
		CommonUtility.GenericMethods.waitForElement(driver,btnNavVehicle,15);
		btnNavVehicle.click();
		CommonUtility.GenericMethods.waitForElement(driver,btnAddVehicle,15);
		btnAddVehicle.click();
		Thread.sleep(4000);
		if(btnNxtNewUI.getText().equalsIgnoreCase("NEXT")) {
			btnNxtNewUI.click();
			CommonUtility.GenericMethods.waitForElement(driver,edtbxAddVehicleNewUI,15);
			edtbxAddVehicleNewUI.sendKeys(vehiclenumber);
		}else {
			CommonUtility.GenericMethods.waitForElement(driver,edtbxAddVehicle,15);
			edtbxAddVehicle.sendKeys(vehiclenumber);
		}
		CommonUtility.GenericMethods.waitForElement(driver,btnSaveVehicle,15);
		btnSaveVehicle.click();
		try {
			CommonUtility.GenericMethods.waitForElement(driver,btnconfirmadd,5);
			btnconfirmadd.click();		
		}catch(Exception e){}
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Add Vehicle End");
	}

	/**
	 * method to go back
	 *
	 */
	public void goback() {
		try {
			CommonUtility.GenericMethods.waitForElement(CreateSession.getAutomationConfiguration().AppiumDriver,btnback,25);
			btnback.click();	
		}catch(Exception e) {}
	}

	/**
	 * method to go back in android
	 *
	 */
	public static void goBack(){
		try {
			if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
				((AndroidDriver<WebElement>) CreateSession.getAutomationConfiguration().AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
			}
		}catch(Exception e) {}
	}
}



