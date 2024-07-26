package com.example.myapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.example.myapp.commonMethods.CommonMethods;

public class LogInPage extends CommonMethods{
	private WebDriver driver;
	private ExtentTest report;
	private By SignIn_Button = By.xpath("//button[text()='Sign In']");
	private By User_Input = By.xpath("//*[@placeholder='Username or email']");
	private By Pass_Input = By.xpath("//*[@placeholder='Password']");
	private By SignIn_Button2 = By.xpath("//button[@title='Sign In']");
		
	public LogInPage(WebDriver driver, ExtentTest report) {
		this.driver=driver;
		this.report=report;
	}
	
	public void SignIn(String UserId, String Password) throws Exception
	{
		Thread.sleep(2000);
		clickElement(driver, report, "SignIn Button", SignIn_Button);
		enterText(driver, report, "UserName", User_Input, UserId);
		enterText(driver, report, "Password", Pass_Input, Password);
		clickElement(driver, report, "SignIn Button", SignIn_Button2);
	}
	
	
	
		
	
}
