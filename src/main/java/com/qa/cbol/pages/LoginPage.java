package com.qa.cbol.pages;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.cbol.base.TestBaseClass;
import com.qa.cbol.utils.ObjectRead;
import com.qa.cbol.utils.Reporters;
import com.qa.cbol.utils.Status;

public class LoginPage extends TestBaseClass{
	


	public void loginIntoApplication(Hashtable<String, String> testData) throws InterruptedException, IOException {
		
			
		Thread.sleep(2000);
		try
		{
		ObjectRead.getObject("Username",this.getClass().getSimpleName()).click();
		System.out.println("Successfully clicked");
		Thread.sleep(2000);
		Actions action=new Actions(driver);
		action.moveToElement(ObjectRead.getObject("Username",this.getClass().getSimpleName())).sendKeys(globalParamMap.get("Username")).build().perform();
		Reporters.logMessage("Successfully entered he user name", Status.PASS);
//			ObjectRead.getObject("Username",this.getClass().getSimpleName()).sendKeys(globalParamMap.get("Username"));
				
			Thread.sleep(2000);
			ObjectRead.getObject("Password",this.getClass().getSimpleName()).click();
			
			Thread.sleep(2000);
			
			ObjectRead.getObject("Password",this.getClass().getSimpleName()).sendKeys(globalParamMap.get("Password"));
			Reporters.logMessage("Successfully entered the password", Status.PASS);
			
			
			Thread.sleep(2000);
			ObjectRead.getObject("SignOn" ,this.getClass().getSimpleName()).click();
			Reporters.logMessage("Successfully Logged in", Status.PASS);


		}
		catch(Exception e)
		{
			
		}
			
		
		
	}

}

