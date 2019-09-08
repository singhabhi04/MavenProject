package com.qa.cbol.pages;

import com.qa.cbol.act.ElementKeywords;
import com.qa.cbol.base.TestBaseClass;
import com.qa.cbol.utils.ObjectRead;
import com.qa.cbol.utils.Reporters;
import com.qa.cbol.utils.Status;

public class HomePage extends TestBaseClass{
	
	public void selectMenu(String mainMenu,String submenu) throws Exception
	{
		Thread.sleep(2000);
		try
		{
			ElementKeywords.setValueToXpathAndClick("MainMenu", mainMenu, this.getClass().getSimpleName());
			Reporters.logMessage("Successfully performed click operation on "+mainMenu, Status.INFO);
			
		}
		catch(Exception e)
		{
			Reporters.logMessage("Unable to perform  click operation on "+mainMenu, Status.FAIL);

		}
		Thread.sleep(2000);
		try
		{
			ElementKeywords.setValueToXpathAndClick("SubMenu", submenu, this.getClass().getSimpleName());
			Reporters.logMessage("Successfully performed click operation on "+submenu, Status.INFO);

		}
		catch(Exception e)
		{
			Reporters.logMessage("Unable to perform  click operation on "+submenu, Status.FAIL);
	
		}
		
		
	}

}
