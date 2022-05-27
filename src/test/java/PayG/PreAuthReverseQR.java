package PayG;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.databind.ObjectMapper;
import CommonUtility.CreateSession;
import DataDriven.ExcelDriven;
import Pages.PayG.AddPromo;
import Pages.PayG.Login;
import Pages.PayG.SavePayment;
import Pages.PayG.authorizeCard;
import Pages.PayG.dbVerification;
import Pages.PayG.exitCarpark;
import PayG.ObjectMapper.UrlMapper;
import TestNGListeners.ApcoaListeners;
import junit.framework.Assert;

public class PreAuthReverseQR {
	SoftAssert SA = new SoftAssert();
	String SessionId;
	String parkingName;
	Integer parsedSessionId;
	String Promoname;
	String AppliedCode;
	@Parameters({ "Environment", "Country","Tenant","Gateway"})
	@BeforeSuite 
	public void lauchweb(String ennv, String country,String tenant,String paymentGateway) throws IOException{
		System.out.println("Payment Gateway - "+paymentGateway);
		CreateSession cs = new CreateSession();
		cs.readConfigFile("/src/test/java/resources/PayG/ConfigFiles/configDashboard.properties","");
		CreateSession.getAutomationConfiguration().Tenant = tenant;
		CreateSession.getAutomationConfiguration().Environment = ennv;
		CreateSession.getAutomationConfiguration().Country = country;
		CreateSession.getAutomationConfiguration().Gateway=paymentGateway;
//		AutomationConfiguration.Driver.get("https://stagingpermit-two.getmyparking.com/reverseqr/entry/PAYGOFFSTREET/PAYGENTRY");
//		AutomationConfiguration.Driver.get("https://permit.getmyparking.com/reverseQR/entry/PAYGOFF/PRODPAYGENTRY");
		
		
	}
	@DataProvider
	public UrlMapper[] getEntryUrlData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/PayG/Dataset/PreAuthGMP.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Gateway,"Entry_URL");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}
	@DataProvider
	public UrlMapper[] getExitUrlData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/PayG/Dataset/PreAuthGMP.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,CreateSession.getAutomationConfiguration().Environment);
		String data = ExcelDriven.readDataRowandColumn(CreateSession.getAutomationConfiguration().Environment,CreateSession.getAutomationConfiguration().Gateway,"Exit_URL");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}
	@DataProvider
	public UrlMapper[] getViewUrlData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/PayG/Dataset/PreAuthGMP.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"PreAuthGMP");
		String data = ExcelDriven.readDataRowandColumn("PreAuthGMP",CreateSession.getAutomationConfiguration().Runner,"View_session");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}
	
	@Test(priority =0,dataProvider = "getEntryUrlData")
	public void launchUrl(UrlMapper EnUrl) {
		System.out.println(EnUrl.getUrl());
		CreateSession.getAutomationConfiguration().Driver.get(EnUrl.getUrl());
//		AutomationConfiguration.Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	
	@Test(priority = 1)
	public void login() throws InterruptedException {
		Login login = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, Login.class);
		Thread.sleep(5000);
		login.selectCountry("India");
	}
	@Test(priority = 3)
	public void authorizeButton() throws InterruptedException {
		authorizeCard button = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, authorizeCard.class);
		Thread.sleep(5000);
		Promoname=button.clickAutharizeButton();
	}
	@Test(priority = 4)
	public void savePayment() throws InterruptedException {
		SavePayment payment = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, SavePayment.class);
		Thread.sleep(5000);
		payment.savePayment();
	}
	@Test(priority = 2)
	public void enterPromo() throws InterruptedException {
		AddPromo promo = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, AddPromo.class);
		Thread.sleep(10000);
		promo.addDiscount();
	}
	@Test(priority =5)
	public void enterCCDetails() throws InterruptedException {
		Thread.sleep(5000);
		SavePayment payment = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, SavePayment.class);
		Thread.sleep(5000);
		if (CreateSession.getAutomationConfiguration().Gateway.equalsIgnoreCase("NMI")) {
			SessionId=payment.enterCreditCard();
		}
		else if (CreateSession.getAutomationConfiguration().Gateway.equalsIgnoreCase("Braintree")) {
			SessionId = payment.enterCreditCardBraintree();
			
		}
		else if (CreateSession.getAutomationConfiguration().Gateway.equalsIgnoreCase("Global")) {
			SessionId = payment.enterCreditCardGlobal();
		}
		else if (CreateSession.getAutomationConfiguration().Gateway.equalsIgnoreCase("Checkout")) {
			 		payment.enterCreditCardCheckout();
		}
		Assert.assertTrue(payment.checkbutton());
//		parkingName = payment.getTheParking();
		Assert.assertTrue(payment.failTransaciton());
		String[] entityId=SessionId.split("#");
		String Id = entityId[1];
		parsedSessionId = Integer.parseInt(Id);
		
	}
		
	@Test(priority=7,dataProvider = "getExitUrlData")
	public void exitCarPark(UrlMapper ExUrl) throws InterruptedException, IOException {
		Thread.sleep(5000);
		exitCarpark exit = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, exitCarpark.class);
		CreateSession.getAutomationConfiguration().Driver.get(ExUrl.getUrl());		
		exit.exitPark(SessionId,parkingName,Promoname);
		Thread.sleep(10000);
	}
	@Test(priority=8)
	public void driverQuit() throws InterruptedException, IOException {
		Thread.sleep(5000);
		((JavascriptExecutor) CreateSession.getAutomationConfiguration().Driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(CreateSession.getAutomationConfiguration().Driver.getWindowHandles());
		CreateSession.getAutomationConfiguration().Driver.switchTo().window(tabs.get(1));
		CreateSession.getAutomationConfiguration().Driver.get("https://stagingpermit-two.getmyparking.com/reverseqr/entry/PAYGOFFSTREET/PAYGENTRY");
//		AutomationConfiguration.Driver.get("https://stagingpermit-two.getmyparking.com/reverseQR/viewSession/qr/ACTIVE/PAYGOFFSTREET");
		Thread.sleep(10000);
		String SessionIds=CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//p[@class='jss24']")).getText();
		SA.assertEquals(SessionIds, SessionId);
		SA.assertAll();	
		Thread.sleep(2000);
		CreateSession.getAutomationConfiguration().Driver.quit();
	}
	@Test(priority=6)
	public void dbVerifications() throws InterruptedException, IOException {
		dbVerification mysqlDb = PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, dbVerification.class);
		String EntityId = Integer.toString(parsedSessionId);
		System.out.println(EntityId);
		String sqlQuery = "SELECT * FROM get_my_parking.transaction WHERE entity_id = "+ EntityId;
		@SuppressWarnings("static-access")
		HashMap<String, String> dbData = mysqlDb.sqlDbVerification(sqlQuery);
		String status = dbData.get("status");
		String amount = dbData.get("amount_in_cents");
		String session_id = dbData.get("entity_id");
		System.out.println("Amount - "+amount);
		System.out.println("Session id -"+session_id);
		System.out.println("Status = "+status);
		ApcoaListeners.logInfo("Transaction status in DB - "+status);
		SA.assertEquals(status, "PRE_AUTH_SUCCESSFUL");
		SA.assertAll();
		Thread.sleep(10000);

}}


