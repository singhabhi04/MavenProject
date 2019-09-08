package com.qa.cbol.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBaseClass {
	public static Hashtable<String, String> globalParamMap = new Hashtable<String, String>();
	public static WebDriver driver;
	


	public static void readGlobalParameters() throws IOException {
		String path = System.getProperty("user.dir") + "//config//GlobalParameter.xlsx";
		FileInputStream fis = new FileInputStream(new File(path));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("globalparameters");
		int totalRows = sh.getLastRowNum();
		
		for (int i = 0; i < totalRows; i++) {
			if(!sh.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase("end"))
			{
			globalParamMap.put(sh.getRow(i).getCell(0).getStringCellValue(),
					sh.getRow(i).getCell(1).getStringCellValue());
			}
			else
			{
				break;
			}
		}
	}

	public static void initDriver() throws IOException{
		readGlobalParameters();	
	if(globalParamMap.get("Browser").equals("IE"))
	{
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//BrowserExeFiles//"+globalParamMap.get("drivername"));
		driver=new InternetExplorerDriver();
	}
	else if(globalParamMap.get("Browser").equals("Chrome")){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//BrowserExeFiles//"+globalParamMap.get("drivername"));
		driver=new ChromeDriver();
		
	}
	else if(globalParamMap.get("Browser").equals("Firefox"))
	{
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//BrowserExeFiles//"+globalParamMap.get("drivername"));
		driver=new FirefoxDriver();
	}
	driver.manage().window().maximize();
	driver.get(globalParamMap.get("URL"));
	
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

}
