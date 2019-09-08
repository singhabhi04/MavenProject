package com.qa.cbol.tests;


import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.cbol.base.TestBaseClass;
import com.qa.cbol.pages.LoginPage;
import com.qa.cbol.utils.*;;

public class Test001 extends Reporters{


@DataProvider(name="testData")	
public Object[][] getTestData() throws Exception{
	
	return(ObjectRead.getTestDataArray(this.getClass().getSimpleName()));
}


@Test(dataProvider="testData")
public void TestCase001(Hashtable<String,String> testData) throws IOException, InterruptedException{
TestBaseClass.initDriver();
LoginPage login=new LoginPage();
login.loginIntoApplication(testData);
	
}

}
