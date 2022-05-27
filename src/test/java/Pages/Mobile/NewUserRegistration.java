package Pages.Mobile;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NewUserRegistration {

	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_get_started')]")
	private WebElement btnletGetStart;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_next')]")
	private WebElement btnNext;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btnNext')]")
	private WebElement btnNext2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action')]")
	private WebElement btnAllow;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_sign_up_with_email')]")
	private WebElement btnSignUp;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/etEmail')]")
	private WebElement btnEnterEmail;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btnNext')]")
	private WebElement btnNextafterEmail;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_password')]")
	private WebElement btnEnterPassword;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cb_terms_conditions')]")
	private WebElement btnCond1;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cb_privacy_policy')]")
	private WebElement btnCond2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_vehicle_number')]")
	private WebElement btnEditVehcileNumber;

	@AndroidFindBy(xpath="//*[contains(@resource-id,'cardNumber')]")
	private WebElement btnAddCardNumber;

	@AndroidFindBy(xpath="//*[contains(@resource-id,'cardholderName')]")
	private WebElement btnAddCardName;     //cardholderName

	@AndroidFindBy(xpath="//*[contains(@resource-id,'expiryDate')]")
	private WebElement btnAddExpiryDate;  //expiryDate

	@AndroidFindBy(xpath="//*[contains(@resource-id,'cvv')]")
	private WebElement btnAddCVV;  //cvv

	@AndroidFindBy(xpath="//android.widget.Button[contains(@resource-id,'primaryButton')]")
	private WebElement btnAddCard;//primaryButton

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_title')]")
	private WebElement btnPaymentTitle;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/txtPreferredPass')]")
	private WebElement emailconfirm;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_country_name')]")
	private WebElement passwordconfirm;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	private WebElement btnmenu;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_customer_id')]")
	private WebElement custId;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close')]")
	private WebElement btnskip;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_title')]")
	private WebElement addvehcnfrm;

	@AndroidFindBy(xpath="(//android.widget.Button)[1]")
	private WebElement btnVisa;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NewUserRegistration(AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
	}

	public void FillDetails() throws InterruptedException{
		ApcoaListeners.logInfo("Email Registration Started");
		System.out.println(CreateSession.getAutomationConfiguration().Country);
		CommonUtility.GenericMethods.waitForElement(driver,btnletGetStart,15);
		btnletGetStart.click();
		CommonUtility.GenericMethods.waitForElement(driver,btnNext,20);
		btnNext.click();
		CommonUtility.GenericMethods.waitForElement(driver,btnAllow,20);
		btnAllow.click();
		CommonUtility.GenericMethods.waitForElement(driver,btnSignUp,20);
		btnSignUp.click();
		String emailid=EmailGenerator();
		ApcoaListeners.logInfo("Email id to be enter-->"+emailid);
		CommonUtility.GenericMethods.waitForElement(driver,btnEnterEmail,20);
		btnEnterEmail.sendKeys(emailid);

		Thread.sleep(4000);

		CommonUtility.GenericMethods.waitForElement(driver,btnNextafterEmail,20);
		btnNextafterEmail.click();
		Thread.sleep(6000);
		SA.assertEquals(emailconfirm.getText(),"Your preferred password");
		ApcoaListeners.logInfo("Email entered Successful");
		ApcoaListeners.logInfo("Going to enter Password");
		ApcoaListeners.logInfo("Password to be enter-->"+"testing");
		CommonUtility.GenericMethods.waitForElement(driver,btnEnterPassword,20);
		btnEnterPassword.sendKeys("testing");
		CommonUtility.GenericMethods.waitForElement(driver,btnCond1,20);
		btnCond1.click();
		CommonUtility.GenericMethods.waitForElement(driver,btnCond2,20);
		btnCond2.click();
		Thread.sleep(4000);
		//CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNext,200);
		btnNext2.click();

		Thread.sleep(8000);
		if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Italy")){
			btnNext2.click();
			Thread.sleep(4000);
		}

		SA.assertEquals(passwordconfirm.getText(),CreateSession.getAutomationConfiguration().Country);
		ApcoaListeners.logInfo("Email Registered Successfully");
		SA.assertAll();
	}

	public void AddVehicle() throws InterruptedException{
		ApcoaListeners.logInfo("AddVehicle Started:");
		Thread.sleep(4000);
		CommonUtility.GenericMethods.waitForElement(driver,btnNext2,20);
		btnNext2.click();
		String vnum=VehicleNumberGenerator();
		ApcoaListeners.logInfo("Going to add vehicle-->"+vnum);
		CommonUtility.GenericMethods.waitForElement(driver,btnEditVehcileNumber,20);
		btnEditVehcileNumber.sendKeys(vnum);
		Thread.sleep(4000);
		CommonUtility.GenericMethods.waitForElement(driver,btnNext2,20);
		if(!(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Sweden")|| CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Denmark"))){
			if(CreateSession.getAutomationConfiguration().Environment.equalsIgnoreCase("Staging")){ 
				btnNext2.click();
				//------------------------------------------	
				Thread.sleep(10000);
				SA.assertEquals(addvehcnfrm.getText(),"Setup secure credit card info");
				ApcoaListeners.logInfo("Vehicle Added successfully");
				System.out.println(addvehcnfrm.getText());
				SA.assertAll();
				Thread.sleep(3000);
			}
		}else {
			btnskip.click();
			Thread.sleep(6000);
			btnmenu.click();//Setup secure credit card info
			Thread.sleep(5000);
			SA.assertTrue(!(custId.getText().equals(null)));
		}
	}

	
	public void AddCard() throws InterruptedException{  
		ApcoaListeners.logInfo("Going to Payment_Card");
		if(!(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Sweden")||CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Denmark")||CreateSession.getAutomationConfiguration().Environment.equalsIgnoreCase("Production"))){
			Thread.sleep(4000);
			btnVisa.click();
			Thread.sleep(3000);
			ApcoaListeners.logInfo("Payment_Card---->"+"4525425464350336");
			CommonUtility.GenericMethods.waitForElement(driver,btnAddCardNumber,20);
			btnAddCardNumber.sendKeys("4525425464350336");
			Thread.sleep(3000);
			ApcoaListeners.logInfo("Username---->"+"Automation Test");
			CommonUtility.GenericMethods.waitForElement(driver,btnAddCardName,20);
			btnAddCardName.sendKeys("Automation Test");
			Thread.sleep(3000);
			ApcoaListeners.logInfo("ExpiryDate---->"+"11/27");
			CommonUtility.GenericMethods.waitForElement(driver,btnAddExpiryDate,20);
			btnAddExpiryDate.sendKeys("1127");
			Thread.sleep(3000);
			ApcoaListeners.logInfo("CVV---->"+"123");
			CommonUtility.GenericMethods.waitForElement(driver,btnAddCVV,20);
			btnAddCVV.sendKeys("123");
			Thread.sleep(3000);
			CommonUtility.GenericMethods.waitForElement(driver,btnAddCard,20);
			btnAddCard.click();
			Thread.sleep(20000);
			//	SA.assertNotEquals(btnPaymentTitle.getText(),"Payment failed");
			btnmenu.click();
			Thread.sleep(5000);
			System.out.println(custId.getText());
			SA.assertTrue(!(custId.getText().equals(null)));
			SA.assertAll();
		}
	}

	public String EmailGenerator(){
		String email = "";
		Random rd = new Random();
		String s=String.valueOf(rd.nextInt(100000));   
		email="Automation_NewUser_"+s+"@yopmail.com";
		return email;
	}

	public String VehicleNumberGenerator(){
		String vehnum = "";
		Random rd= new Random();
		String s=String.valueOf(rd.nextInt(100000));
		if(CreateSession.getAutomationConfiguration().Country.equalsIgnoreCase("Austria")){
			vehnum="W A "+s;
		}else{
			vehnum="W A"+s;
		}
		return vehnum;
	}


}
