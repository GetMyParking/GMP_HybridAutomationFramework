package Pages.Mobile;

import java.io.File;
import java.math.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import CommonUtility.CreateSession;
import CommonUtility.GenericMethods;
import TestNGListeners.ApcoaListeners;
import Utility.ImageComparison;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PageSessionDetails {
	WebDriver driver;
	
	public String ActiveSessionID = "";
	public String ActiveSessionCost = "";
	public String ExpiredSessionCost = "";
	public String ExpiredSessionID = "";

	@iOSXCUITFindBy(xpath="//*[@name='My Sessions']")	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_sessions')]")
	private WebElement MenuMySessions;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'ID: #')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_session_id')]")
	private WebElement MySessionsSessionID;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'Total :')]")
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_cost')]")
	private WebElement MySessionsTotalCost;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='Past']")
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc='Expired' or @content-desc='Past']/android.widget.TextView")
	private WebElement GotoExpiredSessions;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/layout_item_pass')]//android.view.ViewGroup)[2]")
	private WebElement promotext;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_passes')]")
	private WebElement passTab;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_view_details')]")
	private WebElement ViewDetails;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_total')]")
	private WebElement tvTotal;

	public PageSessionDetails(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}


	/**
	 * method to goto activesession page and collect information
	 *
	 * @param parking type is the type of parking
	 */
	public void GotoMyActiveSessions(String parkingtype) {
		try{
			PageHomeApcoa pha = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
			pha.clickMenuBtn();
			Thread.sleep(2000);
			CommonUtility.GenericMethods.waitForElement(driver,MenuMySessions,3);
			MenuMySessions.click();
			Thread.sleep(2000);
			CommonUtility.GenericMethods.waitForElement(driver,MySessionsSessionID,25);
			ActiveSessionID = MySessionsSessionID.getText();
			ApcoaListeners.logInfo("Active Session ID: "+ ActiveSessionID);
			
			if(!parkingtype.equalsIgnoreCase("postpay")) {
				TouchAction action=new TouchAction((PerformsTouchActions) driver);
				action.press(PointOption.point(505, 1870)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(528, 784)).release().perform();
				CommonUtility.GenericMethods.waitForElement(driver,MySessionsTotalCost,10);
				ActiveSessionCost = MySessionsTotalCost.getText().replaceAll(",", "").replaceAll(" ","");
				ActiveSessionCost = GenericMethods.handlePrice(ActiveSessionCost);
				ApcoaListeners.logInfo("Active Session cost : "+ ActiveSessionCost);
			}else{
				Thread.sleep(3000);
			}
			ApcoaListeners.logInfo("PaymentConfirmation end.");
		}catch(Exception e){
			ApcoaListeners.logInfo("PaymentConfirmation end error: "+e.toString());

		}
	}
	
	
	/**
	 * method to goto expired Session page and collect information
	 *
	 */
	public void GotoMyExpiredSessions() {
		try{
			Thread.sleep(2000);
			PageHomeApcoa pha = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
			pha.clickMenuBtn();

			Thread.sleep(2000);
			CommonUtility.GenericMethods.waitForElement(driver,MenuMySessions,3);
			MenuMySessions.click();
			Thread.sleep(3000);//8000
			
			CommonUtility.GenericMethods.waitForElement(driver,GotoExpiredSessions,30);
			GotoExpiredSessions.click();
			Thread.sleep(3000);
						
			try {
				if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
					CommonUtility.GenericMethods.waitForElement(driver,MySessionsSessionID,100);
					ExpiredSessionID = MySessionsSessionID.getText();
				}else {	
					for(int i=1;i<2;i++) {
						try {
							WebElement id = CreateSession.getAutomationConfiguration().AppiumDriver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'ID:')]"));
							ExpiredSessionID = id.getText();
							System.out.println("Found session id.");
							break;
						}catch(Exception e) {
							System.out.println("Fail to find the Session ID. Try no.:" +i);
						}
					}		
				}
			}catch(Exception e) {}
			
			ApcoaListeners.logInfo("Expired Session ID: "+ ExpiredSessionID);

			Thread.sleep(3000);
			ExpiredSessionCost = MySessionsTotalCost.getText().replaceAll(",", "").replaceAll(" ","");
			ExpiredSessionCost = GenericMethods.handlePrice(ExpiredSessionCost);
			ApcoaListeners.logInfo("Expired Session Cost: "+ ExpiredSessionCost);
		}catch(Exception e){
			ApcoaListeners.logInfo("Expired Session Page end error: "+e.toString());
		}
	}

	public Boolean elementAlignment() throws InterruptedException, IOException {
		Thread.sleep(2000);
		PageHomeApcoa pha = new PageHomeApcoa(CreateSession.getAutomationConfiguration().AppiumDriver);
		pha.clickMenuBtn();

		Thread.sleep(2000);
		CommonUtility.GenericMethods.waitForElement(driver,passTab,3);
		passTab.click();
		Thread.sleep(3000);//8000
		
		CommonUtility.GenericMethods.waitForElement(driver,GotoExpiredSessions,30);
		GotoExpiredSessions.click();
		Thread.sleep(3000);
		//Thread.sleep(4000);
		
		File scr = ((TakesScreenshot)promotext).getScreenshotAs(OutputType.FILE);		
		String filename = System.getProperty("user.dir").toString()+"/Output/Screenshot/logo"+ new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss'.jpg'").format(new Date()).toString();
		File dest = new File( filename);
		FileUtils.copyFile(scr, dest);
		String img1 = "/Users/karankumaragarwal/Downloads/logo2022_04_07_02_12_49.jpg";
		System.out.println(filename);
		ImageComparison IC=new ImageComparison();
	    Double diff=	IC.comapareImages(img1, filename, true);
		if(diff>=0.0&&diff<=0.5){
		  ApcoaListeners.logInfo("Images Are Same");
		  return true;
		}else{
			ApcoaListeners.logInfo("Images Are Different");
			return false;
		}
		
		
	}
	
	
	
	public void ViewDetails(String TotalCostPaid) throws InterruptedException {
		SoftAssert SA = new SoftAssert();
		ApcoaListeners.logInfo("Going to  Click on View Details");
		ViewDetails.click();
		Thread.sleep(4000);
		List<WebElement> names = driver.findElements(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_transaction_detail_title')]"));
		List<WebElement> prices = driver.findElements(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_transaction_detail_price_units')]"));
		float total=0;
		float roundedTotal=0;
		for(int i=0;i<names.size();i++) {
			System.out.println(names.get(i).getText());
			System.out.println(prices.get(i).getText());
			String sprice = prices.get(i).getText();
			float price=0;
			String [] SplitPrice =sprice.split("₹");
			if(SplitPrice[0].equals("-")) {
				price = -1 * Float.parseFloat(SplitPrice[1].trim());
			}else {
				price = Float.parseFloat(SplitPrice[1].trim());
			}
			if(names.get(i).getText().contains("Parking price")) {
				System.out.println("Actual Parking Price- "+ tvTotal.getText());
				SA.assertEquals(tvTotal.getText(),prices.get(i).getText() );
			}
			
			total = total + price;
			roundedTotal=BigDecimal.valueOf(total).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue(); 
			
			
		}
		String[] TotalAmounts=TotalCostPaid.split("₹");
		String Total = TotalAmounts[1];
		float Amount = Float.parseFloat(Total);
		System.out.printf("Total Amount = "+ roundedTotal );
		System.out.printf("Paid Amount = "+ Amount);
		SA.assertEquals(Amount,roundedTotal);
		SA.assertAll();
		 
	}
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session')]")
	private WebElement gmpstartsession;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[4]")
	private WebElement gmpAustriatarrifSelection;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ivTariffInfo')]")
	private WebElement tariffInfo;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Convenience Fee')]")
	private WebElement TextConvenience;
	
	public void ConvenienceFee() throws InterruptedException
	{	SoftAssert SA = new SoftAssert();
		ApcoaListeners.logInfo("going to click Start Session button");
		CommonUtility.GenericMethods.explicitWait(driver,gmpstartsession,15);
		gmpstartsession.click();
		Thread.sleep(5000);
		try {
		CommonUtility.GenericMethods.explicitWait(driver,gmpAustriatarrifSelection,10);
		gmpstartsession.click();}
		catch(Exception e) {}
		Thread.sleep(4000);
		PageStartSession pss = new PageStartSession(driver);
		pss.findDialerAndRotate(75);
		//dailerRotationControl(75);
//		action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(602, 1444)).release().perform();
		Thread.sleep(4000);
		tariffInfo.click();
		ApcoaListeners.logInfo("Checking Tarrif Breakdown");
		Thread.sleep(5000);
		SA.assertEquals(TextConvenience.getText(),"Convenience Fee");
		List<WebElement> names = driver.findElements(By.xpath("//android.widget.TextView[contains(@resource-id,'txtTariffName')]"));
		List<WebElement> prices = driver.findElements(By.xpath("//android.widget.TextView[contains(@resource-id,'txtTariffCost')]"));
		float price = 0;
		
		for(int i=0;i<names.size();i++) {
			System.out.println(names.get(i).getText());
			System.out.println(prices.get(i).getText());
			String sprice = prices.get(i).getText();
			String Country = CreateSession.getAutomationConfiguration().Country ;
			String Tenant =  CreateSession.getAutomationConfiguration().Tenant;
			String [] SplitPrice;
			if (names.get(i).getText().equals("Convenience Fee")&& Country.equalsIgnoreCase("Sweden"))
			{   
//				price=price - 1;
				SplitPrice= sprice.split("SEK");
				ApcoaListeners.logInfo("Convenience Fee present------->" +prices.get(i).getText());
				System.out.println(SplitPrice[1]+"Float Value");
				if(SplitPrice[0].equals("-")) {
					price = -1 * Float.parseFloat(SplitPrice[1].trim());
				}else {
					System.out.println("Convenience Fee is not there in the Invoice");
					price = Float.parseFloat(SplitPrice[1].trim());
				}
				}
			 
			if (names.get(i).getText().equals("Convenience Fee")&& Country.equalsIgnoreCase("Denmark"))
			{   
//				price=price - 1;
				SplitPrice= sprice.split("DKK");
				System.out.println(SplitPrice[1]+"Float Value");
				ApcoaListeners.logInfo("Convenience Fee present------->" +prices.get(i).getText());
				;
				if(SplitPrice[0].equals("-")) {
					price = -1 * Float.parseFloat(SplitPrice[1].trim());
				}else {
					System.out.println("Convenience Fee is not there in the Invoice");
					price = Float.parseFloat(SplitPrice[1].trim());
				}
				}
			if (names.get(i).getText().equals("Convenience Fee")&& Country.equalsIgnoreCase("Germany")||names.get(i).getText().equals("Convenience Fee")&& Country.equalsIgnoreCase("Netherlands")||names.get(i).getText().equals("Convenience Fee")&& Country.equalsIgnoreCase("Belgium")||names.get(i).getText().equals("Convenience Fee")&& Country.equalsIgnoreCase("Austria"))
			{   
//				price=price - 1;
				SplitPrice= sprice.split("EUR");
				System.out.println(SplitPrice[1]+"Float Value");
				ApcoaListeners.logInfo("Convenience Fee present------->" +prices.get(i).getText());
				;
				if(SplitPrice[0].equals("-")) {
					price = -1 * Float.parseFloat(SplitPrice[1].trim());
				}else {
					System.out.println("Convenience Fee is not there in the Invoice");
					price = Float.parseFloat(SplitPrice[1].trim());
				}
				}
			 
			if (names.get(i).getText().equals("Convenience Fee")&& Country.equalsIgnoreCase("Poland"))
			{   
//				price=price - 1;
				SplitPrice= sprice.split("PLN");
				System.out.println(SplitPrice[1]+"Float Value");
				ApcoaListeners.logInfo("Convenience Fee present------->" +prices.get(i).getText());
				;
				if(SplitPrice[0].equals("-")) {
					price = -1 * Float.parseFloat(SplitPrice[1].trim());
				}else {
					System.out.println("Convenience Fee is not there in the Invoice");
					price = Float.parseFloat(SplitPrice[1].trim());
				}
				}
			 
			if (names.get(i).getText().equals("Convenience Fee")&& Country.equalsIgnoreCase("Switzerland"))
			{   
//				price=price - 1;
				SplitPrice= sprice.split("CHF");
				System.out.println(SplitPrice[1]+"Float Value");
				ApcoaListeners.logInfo("Convenience Fee present------->" +prices.get(i).getText());
				;
				if(SplitPrice[0].equals("-")) {
					price = -1 * Float.parseFloat(SplitPrice[1].trim());
				}else {
					System.out.println("Convenience Fee is not there in the Invoice");
					price = Float.parseFloat(SplitPrice[1].trim());
				}
				}
			 
			if (names.get(i).getText().equals("Convenience Fee")&& Tenant.equalsIgnoreCase("GMP"))
			{   
//				price=price - 1;
				SplitPrice= sprice.split("₹");
				System.out.println(SplitPrice[1]+"Float Value");
				ApcoaListeners.logInfo("Convenience Fee present------->" +prices.get(i).getText());
				;
				if(SplitPrice[0].equals("-")) {
					price = -1 * Float.parseFloat(SplitPrice[1].trim());
				}else {
					System.out.println("Convenience Fee is not there in the Invoice");
					price = Float.parseFloat(SplitPrice[1].trim());
				}
				}
			
		}
		assert(price!=0.0);
		SA.assertAll();

	}
	
	
	


}
