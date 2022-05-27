package Pages.Dashboard;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;


public class RejectapprovePage {

	WebDriver driver;

	@FindBy(xpath="//div[@class='sc-gPEVay sc-gipzik hkBqCB']")
	WebElement text1;

	@FindBy(xpath="//a[@href=('/admin/permit')]")
	WebElement clickpermit;

	@FindBy(xpath="//input[@id='searchedText']")
	WebElement searchname;

	@FindBy(xpath="//i[@class='anticon anticon-search ant-input-search-icon']")
	WebElement clicksearch;

	@FindBy(xpath="//button[@style='color: rgb(245, 34, 45);']")
	WebElement clickReject;


	@FindBy(xpath="//*[@id=\"root\"]/div/section/section/div/div[2]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div/div/div[3]/div/div/table/tbody/tr[1]/td/div")
	WebElement text2;

	@FindBy(xpath="//*[@id=\"root\"]/div/section/section/div/div[2]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div/div/div[3]/div/div/table/tbody/tr[1]/td")
	WebElement rejectedpermit;

	@FindBy(xpath="//button[@style='color: rgb(82, 196, 26);']")
	WebElement clickApprove;

	@FindBy(xpath="//button[@class='ant-btn ant-btn-primary ant-btn-sm']")
	WebElement clickOK;

	@FindBy(xpath="//button[@class='ant-btn ant-btn-circle ant-btn-icon-only']")    
	WebElement clickfilter;

	@FindBy(xpath="//div[@id='status']")
	WebElement selectfilter;

	@FindBy(xpath="//li[contains(text(),'Rejected')]")
	WebElement selectRejected;

	@FindBy(xpath="(//span[text()='Apply'])[2]//parent::button")
	WebElement clickApply;

	@FindBy(xpath="//button[@class='ant-btn ant-btn-primary ant-btn-circle ant-btn-icon-only']")
	WebElement clickFilter;

	@FindBy(xpath="//*[local-name()='svg' and @data-icon='cloud-download']")
	WebElement clickdownload;

	@FindBy(xpath="//div[contains(text(),'Permits')]")
	WebElement clickPermit;

	@FindBy(xpath="//button[@class='ant-btn ant-btn-circle ant-btn-icon-only']")
	WebElement clickPermitfilter;

	@FindBy(xpath="//li[contains(text(),'Expired')]")
	WebElement selectExpired;


	@FindBy(xpath="//*[@id=\"root\"]/div/section/section/div/div[2]/div/div[2]/div[3]/div[2]/div[3]/div/div/div/div/div[1]/div/table/tbody/tr[1]/td[4]")
	WebElement text3;

	@FindBy(xpath="//*[@id=\"root\"]/div/section/section/div/div[2]/div/div[2]/div[3]/div[2]/div[3]/div/div/div/div/div[1]/div/table/tbody/tr[1]/td[4]")
	WebElement expiredpermit;

	@FindBy(xpath="//li[contains(text(),'Suspended')]")
	WebElement selectSuspended;

	@FindBy(xpath="//*[@id=\"root\"]/div/section/section/div/div[2]/div/div[2]/div[3]/div[2]/div[3]/div/div/div/div/div[1]/div/table/tbody/tr[1]/td[4]")
	WebElement text4;

	@FindBy(xpath="//*[@id=\"root\"]/div/section/section/div/div[2]/div/div[2]/div[3]/div[2]/div[3]/div/div/div/div/div[1]/div/table/tbody/tr[1]/td[4]")
	WebElement suspendedpermit;

	@FindBy(xpath="//li[contains(text(),'Disabled')]")
	WebElement selectDisabled;

	@FindBy(xpath="//*[@id=\"root\"]/div/section/section/div/div[2]/div/div[2]/div[3]/div[2]/div[3]/div/div/div/div/div[1]/div/table/tbody/tr[1]/td[4]")
	WebElement text5;

	@FindBy(xpath="//*[@id=\"root\"]/div/section/section/div/div[2]/div/div[2]/div[3]/div[2]/div[3]/div/div/div/div/div[1]/div/table/tbody/tr[1]/td[4]")
	WebElement disabledpermit;


	@FindBy(xpath="//div[contains(text(),'Approvals')]")
	WebElement clickApprovals;

	@FindBy(xpath="//*[@id=\"root\"]/div/section/section/div/div[2]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td/a")
	WebElement approvalpermit;

	@FindBy(xpath="//div[contains(text(),'Permits')]")
	WebElement clickPermits;

	@FindBy(xpath="//*[@id=\"root\"]/div/section/section/div/div[2]/div/div[2]/div[3]/div[2]/div[3]/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td/a")
	WebElement approvedpermit;

	@FindBy(xpath="//div[@id='searchType']")
	WebElement searchtype;

	@FindBy(xpath="//li[contains(text(),'Email')]")
	WebElement searchemail;





	public RejectapprovePage(WebDriver driver){
		this.driver = driver;    // open the browser enter url
	}

	public void gotoPermit() throws InterruptedException{
		Thread.sleep(10000);


		ApcoaListeners.logInfo("Going to check the logo");

		WebElement screenshotdriver = CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//div[@class='ant-layout-sider-children']//div[@class='sc-csuQGl bWRrat']//div"));
		try{

			File scr = ((TakesScreenshot)screenshotdriver).getScreenshotAs(OutputType.FILE);		
			//String filename = System.getProperty("user.dir").toString()+"/Output/Screenshot/"+ new SimpleDateFormat("Logo_yyyy_MM_dd_hh_mm_ss'.jpg'").format(new Date()).toString();

			String filename = System.getProperty("user.dir").toString()+"/Output/Screenshot/Logo"+ new SimpleDateFormat("_yyyy_MM_dd_hh_mm_ss'.jpg'").format(new Date()).toString();
			System.out.println(filename);
			File dest = new File( filename); 
			FileUtils.copyFile(scr, dest);
			//AutomationConfiguration.extentTest.get().addScreenCaptureFromPath(dest.getAbsolutePath(), msg);
		}catch (Exception e){
			System.out.println(e.toString());
			//logInfo("Error in TestNG Listner(taking screenshot on failure): "+e.toString());
		} 	
		ApcoaListeners.logInfo("logo is correct");



		ApcoaListeners.logInfo("Going to click on permit management module");
		clickpermit.click();     // click on permit management module
		ApcoaListeners.logInfo("Clicked on permit management module");   

	}


	public void gotoReject() throws InterruptedException{


		Thread.sleep(10000);
		ApcoaListeners.logInfo("Going to reject a permit");   
		JavascriptExecutor js = (JavascriptExecutor)driver;	
		js.executeScript("arguments[0].click();", (clickReject));    // click on reject for the 1st permit in approval tab
		ApcoaListeners.logInfo("Rejected a permit successfully");

		Thread.sleep(8000);
		ApcoaListeners.logInfo("Going to download the data from approvals tab");
		clickdownload.click();      // download permits for approval in the approval tab
		ApcoaListeners.logInfo("downloaded the csv successfully");
		Thread.sleep(10000);

		String filename=getLatestFilefromDir("/Users/sudeshna/Downloads");
		System.out.println(filename);



		Thread.sleep(5000);
		clickfilter.click();        // click on filter
		Thread.sleep(3000);       
		selectfilter.click();       //click on the box to select filter

		ApcoaListeners.logInfo("Going to select Rejected filter");
		Thread.sleep(3000);
		selectRejected.click();      // select rejected
		ApcoaListeners.logInfo("Selected Rejected filter successfully");

		Thread.sleep(2000);
		clickApply.click();       // click on apply
		Thread.sleep(2000);
		clickFilter.click();     //click on filter again 
		Thread.sleep(8000);

		System.out.println(rejectedpermit.getText());

		ApcoaListeners.logInfo("Going to check the status of Rejected");
		text2.getText();
		if(text2.getText().equals("Rejected")) {
			System.out.println("Text is correct"); 
			ApcoaListeners.logInfo("Text is correct");
		}

		else {
			System.out.println("Text is incorrect");
			ApcoaListeners.logInfo("Text is incorrect");
		}


		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid red'", rejectedpermit);
		Thread.sleep(5000);

		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid white'", rejectedpermit);



		Thread.sleep(7000);
		clickPermit.click();      //click on permits tab
		ApcoaListeners.logInfo("click on permits tab");
		Thread.sleep(4000);
		ApcoaListeners.logInfo("Going to download the datain permits tab");
		clickdownload.click();    //click on download to download active permits in permit tab

		ApcoaListeners.logInfo("downloaded the data from permits tab");

		Thread.sleep(5000);
		String filename2=getLatestFilefromDir("/Users/sudeshna/Downloads");
		System.out.println(filename2);

		Thread.sleep(4000);
		clickPermitfilter.click();   // click on filter
		Thread.sleep(2000);
		selectfilter.click();       //click on the box to select the type of filter
		ApcoaListeners.logInfo("Going to select Expired filter");
		Thread.sleep(3000);   
		selectExpired.click();      // click on expired

		ApcoaListeners.logInfo("selected expired filter successfully");


		Thread.sleep(5000);
		clickApply.click(); 

		Thread.sleep(2000);
		clickFilter.click();      //click on filter again to view the permits having expired status

		ApcoaListeners.logInfo("Going to check the text of Expired status");


		text3.getText();
		if(text3.getText().equals("Expired")) {   
			System.out.println("Text is correct");
			ApcoaListeners.logInfo("Text is correct");
		}

		else {
			System.out.println("Text is incorrect");
			ApcoaListeners.logInfo("Text is incorrect");
		}



		WebElement screenshotdriver = CreateSession.getAutomationConfiguration().Driver.findElement(By.xpath("//div[@class='ant-layout-sider-children']//div[@class='sc-csuQGl bWRrat']//div"));
		try{

			File scr = ((TakesScreenshot)screenshotdriver).getScreenshotAs(OutputType.FILE);		
			String filename1 = System.getProperty("user.dir").toString()+"/Output/Screenshot/"+ new SimpleDateFormat("Logo_yyyy_MM_dd_hh_mm_ss'.jpg'").format(new Date()).toString();
			File dest = new File( filename1); 
			FileUtils.copyFile(scr, dest);
			//AutomationConfiguration.extentTest.get().addScreenCaptureFromPath(dest.getAbsolutePath(), msg);
		}catch (Exception e){
			//logInfo("Error in TestNG Listner(taking screenshot on failure): "+e.toString());
		} 

		Thread.sleep(6000);
		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid red'", expiredpermit);
		Thread.sleep(5000);


		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid white'", expiredpermit);
		Thread.sleep(5000);
		clickPermitfilter.click();   //click on filter
		Thread.sleep(2000);
		selectfilter.click();      //select type of filter
		Thread.sleep(2000);
		selectSuspended.click();     //select suspended
		ApcoaListeners.logInfo("Going to select Suspended filter");



		Thread.sleep(2000);
		clickApply.click();      //click on apply

		ApcoaListeners.logInfo("Suspended filter selected successfully");
		Thread.sleep(2000);
		clickFilter.click();         //click on filter

		ApcoaListeners.logInfo("Going to check the text of Suspended status");



		text4.getText();
		if(text4.getText().equals("Suspended")) {
			System.out.println("Text is correct");
			ApcoaListeners.logInfo("Text is correct");
		}

		else {
			System.out.println("Text is incorrect");
			ApcoaListeners.logInfo("Text is incorrect");
		}

		System.out.println(filename);
		Thread.sleep(6000);



		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid red'", suspendedpermit);
		Thread.sleep(5000);


		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid white'", suspendedpermit);

		Thread.sleep(5000);
		clickPermitfilter.click();    //click on filter
		Thread.sleep(2000);
		selectfilter.click();        //select type of filter
		Thread.sleep(2000);
		selectDisabled.click();     //select disabled
		ApcoaListeners.logInfo("Going to select disabled filter");


		Thread.sleep(2000);
		clickApply.click();       //click on apply
		Thread.sleep(2000);
		clickFilter.click();        //click on filter
		ApcoaListeners.logInfo("Disabled filter selected successfully");

		ApcoaListeners.logInfo("Going to check the text of Disabled status");


		text5.getText();
		if(text5.getText().equals("Expired")) {
			System.out.println("Text is correct");
			ApcoaListeners.logInfo("Text is correct");
		}

		else {
			System.out.println("Text is incorrect");
			ApcoaListeners.logInfo("Text is incorrect");
		}


		Thread.sleep(6000);

		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid red'", disabledpermit);
		Thread.sleep(5000);

		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid white'", disabledpermit);

		Thread.sleep(5000);
		clickApprovals.click();      //go to approvals tab
		ApcoaListeners.logInfo("clicked on approvals tab");


		Thread.sleep(6000);

		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid red'", approvalpermit);
		Thread.sleep(5000); 

		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid white'", approvalpermit);
		ApcoaListeners.logInfo("Going to Approve a permit");	
		Thread.sleep(5000);
		JavascriptExecutor as = (JavascriptExecutor)driver;	     //approve the first permit in apporvals tab
		as.executeScript("arguments[0].click();", (clickApprove));
		Thread.sleep(4000);
		JavascriptExecutor ms = (JavascriptExecutor)driver;	      //click on ok to approve the permit
		ms.executeScript("arguments[0].click();", (clickOK));

		ApcoaListeners.logInfo("Approved a permit successfully");



		Thread.sleep(3000);
		clickPermits.click();

		ApcoaListeners.logInfo("clicked on permits tab to check the approved permit");

		Thread.sleep(6000);
		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid red'", approvedpermit);
		Thread.sleep(5000);
		((JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver).executeScript("arguments[0].style.border='3px solid white'", approvedpermit);


		Thread.sleep(4000);
		searchname.click();
		Thread.sleep(2000);
		searchname.sendKeys("S");
		Thread.sleep(2000);
		clicksearch.click();
		ApcoaListeners.logInfo("search a name in the search box of permits tab to check the search functionality");


		Thread.sleep(6000);
		clickApprovals.click();
		Thread.sleep(4000);
		searchname.click();
		Thread.sleep(2000);
		searchname.sendKeys("S");   
		Thread.sleep(2000);
		clicksearch.click();
		Thread.sleep(4000);

		clickPermit.click();
		Thread.sleep(3000);
		searchtype.click();
		Thread.sleep(4000);
		searchemail.click();
		Thread.sleep(2000);
		searchname.click();
		Thread.sleep(2000);
		searchname.sendKeys("sudeshna14@yopmail.com");
		Thread.sleep(2000);
		clicksearch.click();
		Thread.sleep(3000);

		clickApprovals.click(); 
		Thread.sleep(3000);
		searchtype.click();
		Thread.sleep(4000);
		searchemail.click();
		Thread.sleep(2000);
		searchname.click();
		Thread.sleep(2000);
		searchname.sendKeys("sudeshna14@yopmail.com");
		Thread.sleep(2000);
		clicksearch.click();
		Thread.sleep(3000);









		ApcoaListeners.logInfo("search a name in the search box of approvals tab to check the search functionality");

	}

	private String getLatestFilefromDir(String dirPath){
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile.getName(); 

	}



}


































