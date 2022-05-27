/*
-------------------------------------------------------------
Author Name: Hemant Rautela

Date:13-Jan-2022

Purpose /Description: Implementation of Data Providers and Test cases for the  
                     automation of Consumer Web Portals.
	
-------------------------------------------------------------

 */

package ConsumerApp;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import CommonUtility.CreateSession;
import ConsumerWebApp.ObjectMapper.LocationMapper;
import ConsumerWebApp.ObjectMapper.LoginMapper;
import ConsumerWebApp.ObjectMapper.PermitMapper;
import ConsumerWebApp.ObjectMapper.ProfileMapper;
import ConsumerWebApp.ObjectMapper.UrlMapper;
import DataDriven.ExcelDriven;
import Pages.ConsumerWebApp.ApprovalConveniencePage;
import Pages.ConsumerWebApp.ApprovalDiscountPage;
import Pages.ConsumerWebApp.ApprovalDocumentPage;
import Pages.ConsumerWebApp.ApprovalNormalPage;
import Pages.ConsumerWebApp.ApprovalVatPage;
import Pages.ConsumerWebApp.ChooseLocationPage;
import Pages.ConsumerWebApp.DirectAutoPage;
import Pages.ConsumerWebApp.DirectConveniencePage;
import Pages.ConsumerWebApp.DirectDiscountPage;
import Pages.ConsumerWebApp.DirectNormalPage;
import Pages.ConsumerWebApp.DirectVatPage;
import Pages.ConsumerWebApp.GoToPage;
import Pages.ConsumerWebApp.HomePage;
import Pages.ConsumerWebApp.LoginPage;
import Pages.ConsumerWebApp.PaymentPage;
import Pages.ConsumerWebApp.ProfilePage;
import Pages.ConsumerWebApp.SelectParkingPage;
import Pages.ConsumerWebApp.VechilePage;
import junit.framework.Assert;


public class ConsumerTest {
	
//	@Parameters({"RunnerPermit"})
	@BeforeClass
	public void initializeDriver() throws IOException{
		System.err.close();
		System.setErr(System.out);
//		System.out.println(runner);
		System.out.println(System.getProperty("RunnerPermit"));
		CreateSession.getAutomationConfiguration().Runner = System.getProperty("RunnerPermit");
		CreateSession cs = new CreateSession();
		cs.readConfigFile("/src/test/java/resources/Permit/ConfigFiles/configConsumerApp.properties","");
		
	}
	
	@AfterClass
	public void teardown() {
		CreateSession.getAutomationConfiguration().Driver.quit();
	}
	/***
	 * Provides the data for login (email & password)
	 */

	@DataProvider
	public LoginMapper[] getLoginData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"Login");
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);
		return login;
	}
	/***
	 * Provides the URL 
	 */

	@DataProvider
	public UrlMapper[] getUrlData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"URL");
		ObjectMapper mapper = new ObjectMapper();
		UrlMapper []url = new UrlMapper[1];
		url[0] = mapper.readValue(data, UrlMapper.class);
		return url;
	}
	/***
	 * Provides the Location data 
	 */

	@DataProvider
	public LocationMapper[] getLocationData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"Location");
		ObjectMapper mapper = new ObjectMapper();
		LocationMapper []LocationData = new LocationMapper[1];
		LocationData[0] = mapper.readValue(data, LocationMapper.class);
		return LocationData;
	}
	
	/***
	 * Provides the My_Profile details
	 */

	@DataProvider
	public ProfileMapper[] getProfileData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"Myprofile");
		ObjectMapper mapper = new ObjectMapper();
		ProfileMapper []ProfileData = new ProfileMapper[1];
		ProfileData[0] = mapper.readValue(data, ProfileMapper.class);
		return ProfileData;
	}
	/***
	 * Provides the permit to send for approval 
	 */

	@DataProvider
	public PermitMapper[] getNormalApprovalData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"ApprovalNormal");
		ObjectMapper mapper = new ObjectMapper();
		PermitMapper []ApprovalData = new PermitMapper[1];
		ApprovalData[0] = mapper.readValue(data, PermitMapper.class);
		return ApprovalData;
	}
	
	@DataProvider
	public PermitMapper[] getDocumentApprovalData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"ApprovalDocument");
		ObjectMapper mapper = new ObjectMapper();
		PermitMapper []ApprovalData = new PermitMapper[1];
		ApprovalData[0] = mapper.readValue(data, PermitMapper.class);
		return ApprovalData;
	}

	/***
	 * Provides the permit to send for approval 
	 */

	@DataProvider
	public PermitMapper[] getVatApprovalData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"ApprovalVat");
		ObjectMapper mapper = new ObjectMapper();
		PermitMapper []ApprovalData = new PermitMapper[1];
		ApprovalData[0] = mapper.readValue(data, PermitMapper.class);
		return ApprovalData;
	}
	/***
	 * Provides the permit to send for approval 
	 */

	@DataProvider
	public PermitMapper[] getConvenienceApprovalData() throws Exception{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"ApprovalConvenience");
		ObjectMapper mapper = new ObjectMapper();
		PermitMapper []ApprovalData = new PermitMapper[1];
		ApprovalData[0] = mapper.readValue(data, PermitMapper.class);
		return ApprovalData;
	}
	
	/***
	 * Provides the permit for direct buy
	 */

	@DataProvider
	public PermitMapper[] getNormalDirectData() throws Exception	{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"DirectNormal");
		ObjectMapper mapper = new ObjectMapper();
		PermitMapper []DirectData = new PermitMapper[1];
		DirectData[0] = mapper.readValue(data, PermitMapper.class);
		return DirectData;
	}
	
	/***
	 * Provides the permit for direct buy
	 */

	@DataProvider
	public PermitMapper[] getVatDirectData() throws Exception	{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"DirectVat");
		ObjectMapper mapper = new ObjectMapper();
		PermitMapper []DirectData = new PermitMapper[1];
		DirectData[0] = mapper.readValue(data, PermitMapper.class);
		return DirectData;
	}
	
	/***
	 * Provides the permit for direct buy
	 */

	@DataProvider
	public PermitMapper[] getConvenienceDirectData() throws Exception	{
		String excelfilepath =System.getProperty("user.dir") + "/src/test/java/resources/Permit/Dataset/ConsumerApp.xlsx";
		ExcelDriven.readExcelFile(excelfilepath,"ConsumerApp");
		String data = ExcelDriven.readDataRowandColumn("ConsumerApp",CreateSession.getAutomationConfiguration().Runner,"DirectConvenience");
		ObjectMapper mapper = new ObjectMapper();
		PermitMapper []DirectData = new PermitMapper[1];
		DirectData[0] = mapper.readValue(data, PermitMapper.class);
		return DirectData;
	}
	
	/***
	 * Test for URL 
	 */
	@Test(priority=0,dataProvider="getUrlData")
	public void gotoUrl(UrlMapper urlMapper) {
		CreateSession.getAutomationConfiguration().Driver.get(urlMapper.getUrl());
		CreateSession.getAutomationConfiguration().Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}
	
	/***
	 * Test for home page
	 */
	@Test(priority=1)
	public void home() throws InterruptedException{
		Thread.sleep(2000);
		HomePage login = new HomePage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,login);
		login.validateText();
		login.clickLogin();
	}
	
	/***
	 * Test for login credentials 
	 */
	@Test(priority=2,dataProvider="getLoginData")
	public void login(LoginMapper loginMapper) throws InterruptedException{
		Thread.sleep(2000);
		LoginPage login = new LoginPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,login);
		login.validateText();
		login.enterCredentials(loginMapper.getUseremail(), loginMapper.getPassword());
		login.clickLoginBtn();

	}
	@Test(priority=3)
	public void goTo() throws InterruptedException{
		GoToPage goto_ = new GoToPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,goto_);
		goto_.name();
		Thread.sleep(2000);
		goto_.profile();

	}

	@Test(priority=4)
	public void Profile() throws InterruptedException{
		ProfilePage details = new ProfilePage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,details);
		details.contactNumber();
		details.city();
		details.country();
	}
	
	@Test(priority=5)
	public void addVechile() throws InterruptedException{
		VechilePage car = new VechilePage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,car);
		car.addVechile();
	}
	
	@Test(priority=6)
	public void addCard() throws InterruptedException{
		Thread.sleep(2000);
		PaymentPage payment = new PaymentPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,payment);
		payment.add_clicks();
		Thread.sleep(5000);
		payment.addCard();
	}
	
	@Test(priority=7,dataProvider="getLocationData")
	public void documentLocation(LocationMapper locationMapper) throws InterruptedException{
		ChooseLocationPage choose = new ChooseLocationPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,choose);
		choose.enterLocation(locationMapper.getLocation());
	}
	
	/***
	 * Test for  selecting approval permit 
	 * @throws AWTException
	 * 
	 */
	@Test(priority=8,dataProvider="getDocumentApprovalData")
	public void documentApproval(PermitMapper permitMapper) throws Exception{
		
		SelectParkingPage parking = new SelectParkingPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, parking);
		parking.choosePermit(permitMapper);

		ApprovalDocumentPage send = new ApprovalDocumentPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,send);
		boolean flag =send.validateText();
		Assert.assertTrue(flag);
		send.selectPermit(permitMapper);
		send.clicks();
		Thread.sleep(2000);
		send.uploadDocument();
		String cost = send.validateAmount();
		Thread.sleep(2000);
		send.approve();
		Thread.sleep(3000);
		send.check();
		Thread.sleep(2000);
		String price = send.verifyAmount();
		Assert.assertEquals(cost,price);
//		Thread.sleep(2000);
//		send.viewDocument();
//		Thread.sleep(3000);
		send.mypermit();
		Thread.sleep(2000);
		send.delete();
		Thread.sleep(2000);

	}
	
//	@Test(priority=9)
//	public void switchBack() throws InterruptedException{
//		ApprovalDocumentPage cross = new ApprovalDocumentPage(AutomationConfiguration.Driver);
//		PageFactory.initElements(AutomationConfiguration.Driver,cross);
//		cross.mypermit();
//		Thread.sleep(2000);
//		cross.delete();
//		Thread.sleep(2000);
//	}
	

	/***
	 * Test for Location 
	 */
	@Test(priority=9,dataProvider="getLocationData")
	public void normalLocation(LocationMapper locationMapper) throws InterruptedException{
		ChooseLocationPage choose = new ChooseLocationPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,choose);
		choose.enterLocation(locationMapper.getLocation());
		
	}
	
	/***
	 * Test for  selecting approval permit  
	 */
	@Test(priority=10,dataProvider="getNormalApprovalData")
	public void normalApproval(PermitMapper permitMapper) throws Throwable{
		
		SelectParkingPage parking = new SelectParkingPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, parking);
		parking.choosePermit(permitMapper);

		ApprovalNormalPage send = new ApprovalNormalPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,send);
		boolean flag =send.validateText();
		Assert.assertTrue(flag);
		send.selectPermit(permitMapper);
		send.clicks();
		Thread.sleep(2000);
		String cost = send.validateAmount();
		Thread.sleep(3000);
		send.approve();
		Thread.sleep(3000);
		send.check();
		Thread.sleep(2000);
		String price = send.verifyAmount();
		Assert.assertEquals(cost,price);
		send.mypermit();
		Thread.sleep(2000);
		send.delete();
		Thread.sleep(2000);
		
	}
	
	/***
	 * Test for Location 
	 */
	@Test(priority=11,dataProvider="getLocationData")
	public void vatApprovalLocation(LocationMapper locationMapper) throws InterruptedException{
		ChooseLocationPage choose = new ChooseLocationPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,choose);
		choose.enterLocation(locationMapper.getLocation());
	}
	/***
	 * Test for  selecting approval permit 
	 */
	@Test(priority=12,dataProvider="getVatApprovalData")
	public void vatApproval(PermitMapper permitMapper) throws InterruptedException, AWTException{
		
		SelectParkingPage parking = new SelectParkingPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, parking);
		parking.choosePermit(permitMapper);

		ApprovalVatPage send = new ApprovalVatPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,send);
		boolean flag =send.validateText();
		Assert.assertTrue(flag);
		send.selectPermit(permitMapper);
		send.clicks();
		Thread.sleep(2000);
		String cost = send.validateAmount();
		Thread.sleep(3000);
		send.approve();
		Thread.sleep(3000);
		send.check();
		Thread.sleep(2000);
		String price = send.verifyAmount();
		Assert.assertEquals(cost,price);
		send.mypermit();
		Thread.sleep(2000);
		send.delete();
		Thread.sleep(2000);
	}
	
	/***
	 * Test for Location 
	 */
	@Test(priority=13,dataProvider="getLocationData")
	public void ConveniencelLocation(LocationMapper locationMapper) throws InterruptedException{
		ChooseLocationPage choose = new ChooseLocationPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,choose);
		choose.enterLocation(locationMapper.getLocation());
	}
	/***
	 * Test for  selecting approval permit 
	 */
	@Test(priority=14,dataProvider="getConvenienceApprovalData")
	public void convenienceApproval(PermitMapper permitMapper) throws InterruptedException{
		
		SelectParkingPage parking = new SelectParkingPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, parking);
		parking.choosePermit(permitMapper);

		ApprovalConveniencePage send = new ApprovalConveniencePage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,send);
		boolean flag =send.validateText();
		Assert.assertTrue(flag);
		send.selectPermit(permitMapper);
		send.clicks();
		Thread.sleep(2000);
		String cost = send.validateAmount();
		Thread.sleep(3000);
		send.approve();
		Thread.sleep(3000);
		send.check();
		Thread.sleep(2000);
		String price = send.verifyAmount();
		Assert.assertEquals(cost,price);
		send.mypermit();
		Thread.sleep(2000);
		send.delete();
		Thread.sleep(2000);
	}
	
	/***
	 * Test for Location 
	 */
//	@Test(priority=12,dataProvider="getLocationData")
//	public void discountApprovalLocation(LocationMapper locationMapper) throws InterruptedException{
//		ChooseLocationPage choose = new ChooseLocationPage(AutomationConfiguration.Driver);
//		PageFactory.initElements(AutomationConfiguration.Driver,choose);
//		choose.enterLocation(locationMapper.getLocation());
//	}
//	/***
//	 * Test for  selecting approval permit 
//	 */
//	@Test(priority=13,dataProvider="getNormalApprovalData")
//	public void discountApproval(PermitMapper permitMapper) throws InterruptedException{
//		
//		SelectParkingPage parking = new SelectParkingPage(AutomationConfiguration.Driver);
//		PageFactory.initElements(AutomationConfiguration.Driver, parking);
//		parking.choosePermit(permitMapper);
//
//		ApprovalDiscountPage send = new ApprovalDiscountPage(AutomationConfiguration.Driver);
//		PageFactory.initElements(AutomationConfiguration.Driver,send);
//		boolean flag =send.validateText();
//		Assert.assertTrue(flag);
//		send.selectPermit(permitMapper);
//		send.clicks();
//		send.enterDiscount(permitMapper.getApprovalcode());
//		String cost = send.validateAmount();
//		send.approve();
//		Thread.sleep(3000);
//	    send.check();
//	    Thread.sleep(2000);
//		String price = send.verifyAmount();
//		Assert.assertEquals(cost,price);
//		
//	}

	/***
	 * Test for selecting location for direct buy permit 
	 */
	@Test(priority=15,dataProvider="getLocationData")
	public void normaLocation(LocationMapper locationMapper) throws InterruptedException{
		ChooseLocationPage clicks = new ChooseLocationPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,clicks);
		clicks.enterLocation(locationMapper.getLocation());
	}
	/***
	 * Test for selecting direct buy permit 
	 */
	@Test(priority=16,dataProvider="getNormalDirectData")
	public void normalPermit(PermitMapper permitMapper) throws InterruptedException{
		
		SelectParkingPage parking = new SelectParkingPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, parking);
		parking.choosePermit(permitMapper);
		
		DirectNormalPage buy = new DirectNormalPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,buy);
		boolean flag =buy.validateText();
		Assert.assertTrue(flag);
		buy.buyPermit(permitMapper);
		buy.clicks();
		Thread.sleep(2000);
		String cost = buy.validateAmount();
		Thread.sleep(3000);
		buy.directBuy();
		buy.active();
		Thread.sleep(3000);
		buy.check();
		Thread.sleep(2000);
		String price = buy.verifyAmount();
		Assert.assertEquals(cost,price);
	}
	
	/***
	 * Test for selecting location for direct buy permit 
	 */
	@Test(priority=17,dataProvider="getLocationData")
	public void vatLocation(LocationMapper locationMapper) throws InterruptedException{
		ChooseLocationPage clicks = new ChooseLocationPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,clicks);
		clicks.enterLocation(locationMapper.getLocation());
	}
	/***
	 * Test for selecting direct buy permit 
	 */
	@Test(priority=18,dataProvider="getVatDirectData")
	public void vatPermit(PermitMapper permitMapper) throws InterruptedException{
		
		SelectParkingPage parking = new SelectParkingPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, parking);
		parking.choosePermit(permitMapper);
		
		DirectVatPage buy = new DirectVatPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,buy);
		boolean flag =buy.validateText();
		Assert.assertTrue(flag);
		buy.buyPermit(permitMapper);
		buy.clicks();
		Thread.sleep(2000);
		String cost = buy.validateAmount();
		Thread.sleep(3000);
		buy.directBuy();
		buy.active();
		Thread.sleep(3000);
		buy.check();
		Thread.sleep(2000);
		String price = buy.verifyAmount();
		Assert.assertEquals(cost,price);

	}
	
	/***
	 * Test for selecting location for direct buy permit 
	 */
	@Test(priority=19,dataProvider="getLocationData")
	public void convenienceLocation(LocationMapper locationMapper) throws InterruptedException{
		ChooseLocationPage clicks = new ChooseLocationPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,clicks);
		clicks.enterLocation(locationMapper.getLocation());
	}
	/***
	 * Test for selecting direct buy permit 
	 */
	@Test(priority=20,dataProvider="getConvenienceDirectData")
	public void conveniencePermit(PermitMapper permitMapper) throws InterruptedException{
		
		SelectParkingPage parking = new SelectParkingPage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver, parking);
		parking.choosePermit(permitMapper);
		
		DirectConveniencePage buy = new DirectConveniencePage(CreateSession.getAutomationConfiguration().Driver);
		PageFactory.initElements(CreateSession.getAutomationConfiguration().Driver,buy);
		boolean flag =buy.validateText();
		Assert.assertTrue(flag);
		buy.buyPermit(permitMapper);
		buy.clicks();
		Thread.sleep(3000);
		String cost = buy.validateAmount();
		Thread.sleep(3000);
		buy.directBuy();
		buy.active();
		Thread.sleep(3000);
		buy.check();
		Thread.sleep(3000);
		String price = buy.verifyAmount();
		Assert.assertEquals(cost,price);
	}
	
//	@Test(priority=10,dataProvider="getLocationData")
//	public void autoLocation(LocationMapper locationMapper) throws InterruptedException{
//		ChooseLocationPage clicks = new ChooseLocationPage(AutomationConfiguration.Driver);
//		PageFactory.initElements(AutomationConfiguration.Driver,clicks);
//		clicks.enterLocation(locationMapper.getLocation());
//	}
//	/***
//	 * Test for selecting direct buy permit 
//	 */
//	@Test(priority=11,dataProvider="getNormalDirectData")
//	public void autoPermit(PermitMapper permitMapper) throws InterruptedException{
//		
//		SelectParkingPage parking = new SelectParkingPage(AutomationConfiguration.Driver);
//		PageFactory.initElements(AutomationConfiguration.Driver, parking);
//		parking.choosePermit(permitMapper);
//		
//		DirectAutoPage buy = new DirectAutoPage(AutomationConfiguration.Driver);
//		PageFactory.initElements(AutomationConfiguration.Driver,buy);
//		boolean flag =buy.validateText();
//		Assert.assertTrue(flag);
//		buy.buyPermit(permitMapper);
//		buy.clicks();
//		Thread.sleep(2000);
//		String cost = buy.validateAmount();
//		Thread.sleep(3000);
//		buy.directBuy();
//		buy.active();
//		Thread.sleep(3000);
//		String permit=buy.check();
//		Thread.sleep(3600000);
//		String permitID = buy.verifyID();
//		Assert.assertEquals(permit,permitID);
//	}
	
//	/***
//	 * Test for Location 
//	 */
//	@Test(priority=22,dataProvider="getLocationData")
//	public void discountDirectLocation(LocationMapper locationMapper) throws InterruptedException{
//		ChooseLocationPage choose = new ChooseLocationPage(AutomationConfiguration.Driver);
//		PageFactory.initElements(AutomationConfiguration.Driver,choose);
//		choose.enterLocation(locationMapper.getLocation());
//	}
//	/***
//	 * Test for  selecting approval permit 
//	 */
//	@Test(priority=23,dataProvider="getNormalDirectData")
//	public void directDiscount(PermitMapper permitMapper) throws InterruptedException{
//		
//		SelectParkingPage parking = new SelectParkingPage(AutomationConfiguration.Driver);
//		PageFactory.initElements(AutomationConfiguration.Driver, parking);
//		parking.choosePermit(permitMapper);
//		
//		DirectDiscountPage buy = new DirectDiscountPage(AutomationConfiguration.Driver);
//		PageFactory.initElements(AutomationConfiguration.Driver,buy);
//		boolean flag =buy.validateText();
//		Assert.assertTrue(flag);
//		buy.buyPermit(permitMapper);
//		buy.clicks();
//		buy.enterDiscount(permitMapper.getDirectcode());
//		String cost = buy.validateAmount();
//		buy.directBuy();
//		buy.active();
//		Thread.sleep(3000);
//		buy.check();
//		Thread.sleep(2000);
//		String price = buy.verifyAmount();
//		Assert.assertEquals(cost,price);
//	}
	
}