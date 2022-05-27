/*
-------------------------------------------------------------
Author Name: Karan Kumar Agarwal

Date:24-Sep-2021

Purpose /Description: Implementation of the TestNG ITestListener
	for implementing test pass and test failure events.
-------------------------------------------------------------

 */

package TestNGListeners;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import CommonUtility.CreateSession;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.TestResult;

/**
 * contains all the methods to ITestListener 

 */

public class ApcoaListeners implements ITestListener{


	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	public static void logInfo(String info){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  ").format(new Date()).toString() + info);
		saveTextLog(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  ").format(new Date()).toString() + info);
	}

	public void addScreenshotToReport(String msg) {
		WebDriver screenshotdriver ;
		try{
			if(CreateSession.getAutomationConfiguration().ScreenshotFor.toString().toUpperCase().contains ("WEB")){
				screenshotdriver = CreateSession.getAutomationConfiguration().Driver;
			}else{
				screenshotdriver= CreateSession.getAutomationConfiguration().AppiumDriver;
			}
			saveTextLog(msg);
			saveScreenshotPNG(screenshotdriver);
		}catch (Exception e){
			logInfo("Error in TestNG Listner(taking screenshot on failure): "+e.toString());
		} 	
	}
	/**
	 * method to Log the Test Finish in Reports
	 *
	 *@param context object of ITestContext
	 */
	public void onFinish(ITestContext context) {
		//		logInfo("Ends Succcessfully ");
		//		extent.flush();
		//		Path sourceDirectory = Paths.get(AutomationConfiguration.ExtentReportFilePath) ;//Paths.get("/Users/karankumaragarwal/eclipse-workspace/GMP_Automation_Framework/Output/Reports/ExtentReport2022_01_13___02_41_00.html");
		//        Path targetDirectory =  Paths.get(System.getProperty("user.dir").toString()+"/lastRunReport.html");
		//        try {
		//            Files.copy(sourceDirectory, targetDirectory,StandardCopyOption.REPLACE_EXISTING);
		//        }catch (IOException e) {
		//            System.out.println(e.toString());
		//        }
	}

	/**
	 * method to Log the Test Start in Reports
	 *
	 *@param result object of ITestResult
	 */

	public void changeTestName(final ITestResult result) {
		AllureLifecycle lifecycle = Allure.getLifecycle();
		lifecycle.updateTestCase(new Consumer<TestResult>() {
			@Override
			public void accept(TestResult testResult) {
				//System.out.println("Name changed : *************************** "+result.getMethod().getMethodName().toUpperCase());
				testResult.setName(result.getMethod().getMethodName().toUpperCase()+" ("+CreateSession.getAutomationConfiguration().Country+")");
			}
		});
	}

	public void onTestStart(ITestResult result) { 
		changeTestName(result);
		logInfo("New Test started: --> "+result.getMethod().getMethodName()+" ("+CreateSession.getAutomationConfiguration().Country+")");
	}

	/**
	 * method to Log the Test pass in Reports
	 *
	 *@param result object of ITestResult
	 */
	public void onTestSuccess(ITestResult result) { 
		logInfo("Test end:(Success) --> "+result.getMethod().getMethodName()+" ("+CreateSession.getAutomationConfiguration().Country+")");
	}

	/**
	 * method to Log the Test Failure with screenshot in Reports
	 *
	 *@param result object of ITestResult
	 */
	@Override
	public void onTestFailure(ITestResult result){ 
		logInfo("Test end:(Fail) --> "+result.getMethod().getMethodName()+" ("+CreateSession.getAutomationConfiguration().Country+"):"+result.getThrowable().toString());
		addScreenshotToReport(result.getMethod().getMethodName()+": "+result.getThrowable().toString());
	}


	public void onTestSkipped(ITestResult result){  
		logInfo("Test skipped: "+result.getMethod().getMethodName()+" ("+CreateSession.getAutomationConfiguration().Country+"):"+result.getThrowable().toString());
		addScreenshotToReport(result.getMethod().getMethodName()+": "+result.getThrowable().toString());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

	public void onStart(ITestContext context) {		}
}
