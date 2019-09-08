package com.qa.cbol.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.cbol.base.TestBaseClass;

public class ObjectRead extends TestBaseClass{
	
	

	public static WebElement getObject(String realName,String pageName) throws IOException
	{
		WebElement locator=null;
		String path=System.getProperty("user.dir")+"//ObjectRepository//"+pageName+".xlsx"	;
		FileInputStream fis=new FileInputStream(new File(path));
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sh=wb.getSheet(pageName);
		int rowCount=sh.getLastRowNum();
		for(int i=1;i<rowCount;i++)
		{
			if(sh.getRow(i).getCell(2).getStringCellValue().equals(realName))
			{
				String locatorType=sh.getRow(i).getCell(3).getStringCellValue();
			//	String locatorVal=sh.getRow(i).getCell(4).getStringCellValue();
				if(locatorType.equalsIgnoreCase("name"))
				{
				locator=driver.findElement(By.name(sh.getRow(i).getCell(4).getStringCellValue()));
				break;
				}
				else if(locatorType.equalsIgnoreCase("xpath"))
				{
					locator=driver.findElement(By.xpath(sh.getRow(i).getCell(4).getStringCellValue()));
							
					break;

				}
				else if(locatorType.equalsIgnoreCase("className"))
				{
					locator=driver.findElement(By.className(sh.getRow(i).getCell(4).getStringCellValue()));
					break;

				}
				else if(locatorType.equalsIgnoreCase("id"))
				{
					locator=driver.findElement(By.id(sh.getRow(i).getCell(4).getStringCellValue()));
					break;

				}
				else if(locatorType.equalsIgnoreCase("tagName"))
				{
					locator=driver.findElement(By.tagName(sh.getRow(i).getCell(4).getStringCellValue()));
					break;

				}
				else if(locatorType.equalsIgnoreCase("linkText"))
				{
					locator=driver.findElement(By.linkText(sh.getRow(i).getCell(4).getStringCellValue()));
					break;

				}
				else if(locatorType.equalsIgnoreCase("cssSelector"))
				{
					locator=driver.findElement(By.cssSelector(sh.getRow(i).getCell(4).getStringCellValue()));
					break;

				}
				else
				{
					System.out.println("Please enter a valid locator");
				}
			}
		}
		
		return locator;
		
	}
	
	public static Object[][] getTestDataArray(String testCaseName) throws Exception
	{
	File file=new File(System.getProperty("user.dir")+"//TestData//TestData.xlsx");
	FileInputStream fi=new FileInputStream(file);
	XSSFWorkbook wb=new XSSFWorkbook(fi);
	XSSFSheet sh=wb.getSheet("testData");
	int rowCount=sh.getLastRowNum();
	int cellNum=sh.getRow(0).getLastCellNum();
	Object[][] testDataArray=new Object[1][1];
//	testDataArray[0][0]="Execute";
//	testDataArray[0][1]="TestCaseName";
	Hashtable<String,String> hashtable;
//int c=0;
	for(int i=1;i<=rowCount	;i++)
	{
		if(sh.getRow(i).getCell(0).getStringCellValue().equals("End"))
		{
			break;
		}
		else if(sh.getRow(i).getCell(1).getStringCellValue().equals(testCaseName)&&sh.getRow(i).getCell(0).getStringCellValue().equals("Y"))
		{
			hashtable = new Hashtable<String, String>();
			for(int j=0;j<cellNum;j++)
			{
				
					hashtable.put(sh.getRow(0).getCell(j).getStringCellValue(), sh.getRow(i).getCell(j).getStringCellValue());
			
			}
			testDataArray[0][0] = hashtable;
			
		}
		
		

	}
	return testDataArray;
	

	

}
	public static WebElement getDynamicObject(String RealName,String replacable,String pageName) throws IOException
	{
		
		 String constructedXpath = null;
		String path=System.getProperty("user.dir")+"//ObjectRepository//"+pageName+".xlsx"	;
		FileInputStream fis=new FileInputStream(new File(path));
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sh=wb.getSheet(pageName);
		int rowCount=sh.getLastRowNum();
		for(int i=1;i<=rowCount;i++)
		{
			String name=sh.getRow(i).getCell(2).getStringCellValue();
			if(name.equals(RealName))
			{
				String locatorType=sh.getRow(i).getCell(3).getStringCellValue();
				String locatorVal=sh.getRow(i).getCell(4).getStringCellValue();

				String arr[]=locatorVal.split("#");
				
				constructedXpath=arr[0]+replacable+arr[2];
				break;
			}
			else
			{
				System.out.println("Checking next row");
			}
			

	}
		wb.close();
		WebElement element=driver.findElement(By.xpath(constructedXpath));
		return element;
		
	}
}
