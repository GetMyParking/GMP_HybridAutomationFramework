/*
 1. create session without permit
 2. create session with permit
 3. create session with half permit applied
 */
package Pages.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import CommonUtility.CreateSession;
import java.time.format.DateTimeFormatter; 
import java.time.LocalDateTime;   
import Dashboard.ObjectMapper.SessionMapper;
import TestNGListeners.ApcoaListeners;




public class SessionPage {

	WebDriver driver;

	//session creation without permit

	@FindBy(xpath="//a[@href=('/admin/permit')]")
	WebElement clickpermit;

	@FindBy(xpath="//span[text()='Consumer Support']")
	WebElement consumer;

	@FindBy(xpath="//a[@href='/admin/consumers']")
	WebElement users;

	@FindBy(xpath="//input[@id='email']")
	WebElement Email;

	@FindBy(xpath="//button[@type='submit']")
	WebElement Apply;

	@FindBy(xpath="//td[@class='ant-table-row-cell-break-word']//a")   
	WebElement id;

	@FindBy(xpath="//span[text()='Create Session']//parent::button")
	WebElement createsession;

	@FindBy(xpath="//div[@id='parkingId']//parent::span[@class='ant-form-item-children']")
	WebElement selectparking;

	@FindBy(xpath="//li[contains(text(),'GMP [enable working]')]")
	WebElement clickparking;

	@FindBy(xpath="//div[@title='CONSUMERAPP']")
	WebElement accessmethod;

	@FindBy(xpath="//div[@class='ant-select-dropdown ant-select-dropdown--single ant-select-dropdown-placement-bottomLeft  ant-select-dropdown-hidden']")
	WebElement selectaccess;

	@FindBy(xpath="//input[@id='comments']") 
	WebElement comments;

	@FindBy(xpath="//input[@class='ant-calendar-picker-input ant-input']")
	WebElement calendar;

	@FindBy(xpath="//input[contains(@class,'ant-calendar-input')]")
	WebElement inputtime;

	@FindBy(xpath="//a[@class='ant-calendar-ok-btn']")
	WebElement clickok; 

	@FindBy(xpath="//button[@type='submit']//parent::button")
	WebElement startsession; 

	@FindBy(xpath="//i[@class='anticon anticon-sync']//parent::button")
	WebElement refresh;

	@FindBy(xpath="//td[@class='ant-table-fixed-columns-in-body']//a")
	WebElement sessionid;

	@FindBy(xpath="//span[text()='End Session']//parent::button")
	WebElement endsession;

	@FindBy(xpath="//input[@id='parkingId']")
	WebElement parkingname;


	@FindBy(xpath="//a[@href='/admin/consumer-session']")
	WebElement Sessions;

	@FindBy(xpath="//input[@type='number']")
	WebElement Sessionid;

	@FindBy(xpath="//button[@type='submit']")
	WebElement Search;



	public SessionPage(WebDriver driver){
		this.driver = driver;
	}


	public String localtime(int min) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime now = LocalDateTime.now(); 

		now = now.minusMinutes(min);

		System.out.println(dtf.format(now));
		return dtf.format(now);
	}
	public void gotoconsumer(SessionMapper sessionMapper, int time) throws InterruptedException {


		String carpark = sessionMapper.getCarpark();
		String carpermit = sessionMapper.getCarpermit();
		String email = sessionMapper.getEmail();

		Thread.sleep(10000);
		clickpermit.click();
		ApcoaListeners.logInfo("select permit management module");    //select permit management module




		Thread.sleep(5000);
		consumer.click();
		ApcoaListeners.logInfo("click on consumer support");   //click on consumer support


		Thread.sleep(3000);
		users.click();
		ApcoaListeners.logInfo("click on users");     //click on users

		Thread.sleep(2000);
		Email.click();                   //click on email



		Thread.sleep(2000);
		Email.sendKeys(email);            //enter email

		ApcoaListeners.logInfo("enter the email id of user to start session");

		Thread.sleep(2000);               //click on apply
		Apply.click();

		Thread.sleep(5000);
		id.click();                      //click on id to create session


		Thread.sleep(5000);
		createsession.click();             //click on create session


		Thread.sleep(3000);


		WebElement el = driver.findElement(By.xpath("//div[text()='Please Select Parking']"));
		el.click();
		Thread.sleep(5000);



		selectparking.click();               //click on parking to select parking




		Thread.sleep(4000);

		JavascriptExecutor executor = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;

		parkingname.sendKeys(carpark);          //enter parking name
		Thread.sleep(4000);


		//		
		//		

		System.out.println("CHECKING SESSION FLOW");             // scroll down after entering the parking name, and select the desired parking
		for(int i=0;i<=10;i++) {
			try {
				WebElement ele = driver.findElement(By.xpath("//li[contains(text(),'"+carpark+"')]"));
				executor = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
				executor.executeScript("arguments[0].click();", ele);
				break;
			}
			catch(Exception e) {
				try {
					JavascriptExecutor executor1 = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
					executor.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=200");
					Thread.sleep(2000);
					executor.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=200");
					Thread.sleep(2000);
					executor.executeScript("document.querySelector(\"ul.ant-select-dropdown-menu\").scrollTop=200");
				}
				catch(Exception m) {

				}
				continue;
			}
		}








		WebElement ele = driver.findElement(By.xpath("//li[contains(text(),'"+carpark+"')]"));
		executor = (JavascriptExecutor)CreateSession.getAutomationConfiguration().Driver;
		executor.executeScript("arguments[0].click();", ele);

		//driver.findElement(By.xpath("//li[contains(text(),'"+carpark+"')]")).click();





		Thread.sleep(4000);      
		comments.click();                                //click on comments


		Thread.sleep(4000);
		comments.sendKeys("testingsessionstart");           //enter any comment
		Thread.sleep(3000);
		calendar.click();                               //click on calendar to select the desired time-date
		Thread.sleep(2000);




		System.out.println(inputtime.getAttribute("value"));
		while(inputtime.getAttribute("value").length()!=0) {
			inputtime.sendKeys(Keys.BACK_SPACE);                

		}
		inputtime.sendKeys(localtime(time));                   

		System.out.println(inputtime.getAttribute("value"));     

		Thread.sleep(3000);
		clickok.click();                             //click on ok button

		Thread.sleep(5000);
		startsession.click();                        //click on start session to start the session


		ApcoaListeners.logInfo("Started the session");

		Thread.sleep(7000);
		refresh.click();                        //click on refresh to view the session 


		Thread.sleep(18000);

		((JavascriptExecutor)driver).executeScript("arguments[0].click()", sessionid);   //click on sessionid
		Thread.sleep(5000);
		comments.click();                                   //click on comments
		Thread.sleep(2000);
		comments.sendKeys("Testingsessionend");          // enter the comment
		Thread.sleep(9000);
		endsession.click();                           //click on end session to end the session


		ApcoaListeners.logInfo("Session Ended");

		Thread.sleep(5000);
		refresh.click();                         //click on refresh to view the recently ended session


		Thread.sleep(4000);
		WebElement objLinks = driver.findElement(By.xpath("//table[@class='ant-table-fixed']//td[3]/a"));   //click on the duration to view the status of the session

		objLinks.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='ant-modal-close-x']//parent::button")).click();


		ApcoaListeners.logInfo("SUDESHNA :view the session details");     //view the session 
		Thread.sleep(5000);

		WebElement myOrdertext = driver.findElement(By.xpath("//td[@class='ant-table-fixed-columns-in-body']//a"));
		String Sessionidtext=myOrdertext.getAttribute("href");

		String[] ar = Sessionidtext.split("/");
		Sessionidtext = ar[ar.length-2];
		System.out.println(Sessionidtext);
		Thread.sleep(3000);
		Sessions.click();
		Thread.sleep(3000);
		Sessionid.click();
		Thread.sleep(3000);
		Sessionid.sendKeys(Sessionidtext);
		Thread.sleep(2000);
		Search.click();
	}

}
