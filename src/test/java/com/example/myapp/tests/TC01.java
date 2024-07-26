package com.example.myapp.tests;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.example.myapp.pages.LogInPage;
import com.example.myapp.utils.ExcelUtils;
import com.example.myapp.base.BaseTest;


public class TC01 extends BaseTest{
	public static WebDriver driver;
	
	ExcelUtils excelutil=new ExcelUtils();
	
	@DataProvider(name="TestData")
	public String[][] getData(Method m) throws Exception{
		String elcelLocation= (System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\TestDataSheet.xlsx");
		String sheetName="Sheet1";
		String[][] testData = excelutil.GetDataFromExcel(elcelLocation, m.getName(), sheetName);
		return testData;
	}


	@Test(priority=1, dataProviderClass=TC01.class, dataProvider="TestData")
	public void testcase1(String tc, String UserId, String Password) throws Exception
	{
		ExtentTest report= extent.createTest("testcase1");
		WebDriver driver=getDriver();
		driver.get("https://www.geeksforgeeks.org/");
		Thread.sleep(5000);
		LogInPage Loginpage= new LogInPage(driver, report);
		Loginpage.SignIn(UserId, Password);
		
	}
}
	
