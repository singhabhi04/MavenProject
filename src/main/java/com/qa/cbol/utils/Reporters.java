package com.qa.cbol.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.qa.cbol.base.TestBaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporters extends TestBaseClass{

	public final static Logger logger = Logger.getLogger(Reporters.class);
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String folder;
	
	static
	{
	String log4jConfPath = "./config/log4j.properties";
	PropertyConfigurator.configure(log4jConfPath);
	Calendar calendar=Calendar.getInstance();
	SimpleDateFormat formatter=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

//	extent=new ExtentReports(System.getProperty("user.dir")+"//Report//"+formatter.format(calendar.getTime())+".html",false);
	}
	public void getResult(ITestResult result)//high level status
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
		test.log(LogStatus.PASS,  "<b>"+result.getName()+" : Test is Passed"+"</b>");
		logger.info(result.getName()+" : Test is Passed");
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
		test.log(LogStatus.SKIP, result.getName()+" test is SKIPPED and reason is "+result.getThrowable());	
		logger.info(result.getName()+" : Test is Skipped");
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL,  "<b>"+result.getName()+" : Test is Failed"+"</b>");
			logger.info(result.getName()+" : Test is Failed");
			
//		test.log(LogStatus.FAIL, result.getName()+" : TEST IS FAILED"+result.getThrowable());	
		}
		
	}
	@AfterMethod
	public void AfterMethod(ITestResult result)
	{
		getResult(result);
	}
	
	@BeforeMethod()
	public void beforeMethod(Method result)
	{
		SimpleDateFormat formatter=new SimpleDateFormat("ddMMyyyy_hhmmss");
		
		folder=result.getName()+"_"+formatter.format(new Date());
		File file=new File(System.getProperty("user.dir")+"//Report//"+folder);
		file.mkdir();
		extent=new ExtentReports(System.getProperty("user.dir")+"//Report//"+folder+"//HTMLReport.html",false);
		test=extent.startTest(result.getName());
		test.log(LogStatus.INFO, "<b>"+result.getName()+": Test Started"+"</b>");
		logger.info(result.getName()+" : Test Started");
		
	}
	@AfterClass(alwaysRun=true)
	public void endTest()
	{
		extent.endTest(test);
		extent.flush();
		driver.close();
	}
	public static void logMessage(String detailedMessage, Status logType) {
//		DateFormat dateFormat = new SimpleDateFormat("dd-MMM,yy HH:mm:ss");
//		Date date = new Date();

		String screenShotPath = "";

		if (logType.equals(Status.FAIL)) {
				screenShotPath = takeExecutionScreenshot(driver);

			
			
//			test.log(LogStatus.FAIL, detailedMessage+test.addScreenCapture(System.getProperty("user.dir")+"//"+screenShotPath));
			
			test.log(LogStatus.FAIL, "<b style=\"color:red;\">"+detailedMessage+"</b>"+test.addScreenCapture(System.getProperty("user.dir")+"//"+screenShotPath));
			logger.error("StepFailed: "+detailedMessage);

			 Assert.fail(detailedMessage);

		}

		else if (logType.equals(Status.PASS)) {
			
				screenShotPath = takeExecutionScreenshot(driver);

			
			String fullScreenShot=(System.getProperty("user.dir")+"//"+screenShotPath);
			test.log(LogStatus.PASS, "<font color=\"blue\">"+detailedMessage+"</font>"+test.addScreenCapture(System.getProperty("user.dir")+"//"+screenShotPath));
			logger.info("StepPassed: "+detailedMessage);
			//			test.log(LogStatus.PASS, detailedMessage+test.addScreenCapture(System.getProperty("user.dir")+"//"+screenShotPath));
//			test.log(LogStatus.PASS,detailedMessage+" : <a href="+fullScreenShot+">Click here for ScreenShot</a>");
			
		
		}

		
		

		else if (logType.equals(Status.SKIP)) {
			
			test.log(LogStatus.SKIP, detailedMessage);
			
		}

		else if ((logType.equals(Status.INFO))) {
			
			test.log(LogStatus.INFO, detailedMessage);
		}

	}
	public static String takeExecutionScreenshot(WebDriver driver) {


		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		Random rand = new Random();
		int random_No = rand.nextInt(99999) + 1;
		String fileName = (dateFormat.format(date)) + "" + random_No + ".png";
		File newScreenShotFolder=new File("./Report/"+folder+"/SS");
		newScreenShotFolder.mkdir();
		File newScreenShot=new File("./Report/"+folder+"/SS/"+fileName);
//		File newScreenShot = new File("./Screenshots/" + fileName);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, newScreenShot);
		} catch (IOException e) {

		}
		return "./Report/"+folder+"/SS/"+fileName;
//		return "Screenshots/" + fileName;
	}
	
}
