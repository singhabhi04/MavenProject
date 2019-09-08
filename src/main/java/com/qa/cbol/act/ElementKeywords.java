package com.qa.cbol.act;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;

import com.qa.cbol.base.TestBaseClass;
import com.qa.cbol.utils.ObjectRead;
import com.qa.cbol.utils.Reporters;
import com.qa.cbol.utils.Status;

public class ElementKeywords extends TestBaseClass{
	
	
public static void setValueToXpathAndClick(String RealName,String replacable,String pageName)
{
	try {
		ObjectRead.getDynamicObject(RealName, replacable, pageName).click();
		Reporters.logMessage("Successfully performed click operation", Status.INFO);
	} catch (Exception e) {
		System.out.println();
		Reporters.logMessage("Unable to perform  click operation", Status.FAIL);
	}
}
public static void mouseHover(String realName,String pageName)  {
	Actions action = new Actions(driver);
	try {
		action.moveToElement(ObjectRead.getObject(realName, pageName)).build().perform();
		Reporters.logMessage("Successfully performed MouseHover operation", Status.INFO);

	} catch (Exception e) {
		Reporters.logMessage("Unable to perform  click operation", Status.FAIL);
	
	}
	
	
	
	
}


}
