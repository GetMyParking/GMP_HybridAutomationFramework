/*
-------------------------------------------------------------
Author Name: Karan Kumar Agarwal

Date:24-Sep-2021

Purpose /Description: Implementation of the TestNG ITestListener
	for implementing test pass and test failure events.
-------------------------------------------------------------

 */

package TestNGListeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import CommonUtility.AutomationConfiguration;
import CommonUtility.CreateSession;
import CommonUtility.ExtentReporterNG;

/**
 * contains all the methods to ITestListener 

 */



public class ApcoaListeners extends CreateSession implements ITestListener{


	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();


	public static void logInfo(String info)
	{
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  ").format(new Date()).toString() + info);
		AutomationConfiguration.Log.info(info);
		extentTest.get().log(Status.INFO, info); 
	}
	
	
	/**
	 * method to Log the Test Finish in Reports
	 *
	 *@param context object of ITestContext
	 */
	public void onFinish(ITestContext context) {
		logInfo("Ends Succcessfully ");
		extent.flush();

	}


	/**
	 * method to Log the Test Start in Reports
	 *
	 *@param result object of ITestResult
	 */

	public void onTestStart(ITestResult result) { 
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		logInfo("New Test started: --> "+result.getMethod().getMethodName());

	}

	/**
	 * method to Log the Test pass in Reports
	 *
	 *@param result object of ITestResult
	 */
	public void onTestSuccess(ITestResult result) { 
		logInfo("Test Passed: "+result.getMethod().getMethodName());
		extentTest.get().log(Status.PASS, "Test Passed");
		logInfo("Test end:(Success) --> "+result.getMethod().getMethodName());
	}

	/**
	 * method to Log the Test Failure with screenshot in Reports
	 *
	 *@param result object of ITestResult
	 */
	@Override
	public void onTestFailure(ITestResult result) 
	{ 
		logInfo("Test end:(Fail) --> "+result.getMethod().getMethodName());
		//logInfo("Test Failed: Reason for Failure: "+result.getThrowable().toString());
		extentTest.get().log(Status.FAIL, " Reason for failure: "+result.getThrowable().toString());
		
		WebDriver screenshotdriver ;
		try 
		{
			if(AutomationConfiguration.ScreenshotFor.toString().toUpperCase().contains ("WEB"))
			{
				screenshotdriver = AutomationConfiguration.Driver;

			}
			else
			{
				screenshotdriver= AutomationConfiguration.AppiumDriver;
			}
			File scr = ((TakesScreenshot)screenshotdriver).getScreenshotAs(OutputType.FILE);		
			String filename = System.getProperty("user.dir").toString()+"/Output/Screenshot/"+ new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss'.jpg'").format(new Date()).toString();
			File dest = new File( filename); //Directory where Screenshot get saved.

			FileUtils.copyFile(scr, dest);
			
			extentTest.get().addScreenCaptureFromPath(dest.getAbsolutePath(), result.getMethod().getMethodName()+": "+result.getThrowable().toString());
		} 
		catch (Exception e)
		{
			logInfo("Error in TestNG Listner(taking screenshot on failure): "+e.toString());
		} 
		
	}


	public void onTestSkipped(ITestResult result) 
	{  
		logInfo("Test skipped: "+result.getMethod().getMethodName());
		extentTest.get().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

	public void onStart(ITestContext context) {
		System.out.println("*************** im started");
		
	}


}
