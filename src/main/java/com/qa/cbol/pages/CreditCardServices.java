package com.qa.cbol.pages;

import java.io.IOException;

import com.qa.cbol.act.ElementKeywords;
import com.qa.cbol.base.TestBaseClass;
import com.qa.cbol.utils.ObjectRead;
import com.qa.cbol.utils.Reporters;
import com.qa.cbol.utils.Status;

public class CreditCardServices extends TestBaseClass{

	public void clickOnCardManagementLinks(String link) {
		
		System.out.println(link);
	try {
		ObjectRead.getObject("link", this.getClass().getSimpleName()).click();;
		Reporters.logMessage("Successfully Clicked on Link : "+link, Status.PASS);

	} catch (Exception e) {
		Reporters.logMessage("Unable to Click  on Link : "+link, Status.PASS);

	}
		
	}

}
