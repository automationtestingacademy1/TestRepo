package com.example.myapp.commonMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CommonMethods {

//	public CommonMethods (ExtentTest report) {
//		this.report=report;
//	}
	public static String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String destination = System.getProperty("user.dir") + "\\src\\test\\resources\\reportsScreenshots\\" + screenshotName + dateName + ".png";
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	public static void enterText(WebDriver driver, ExtentTest report, String locatorName, By locatorxpath, String text) throws Exception {
		try {
			String screenshotPath=captureScreenshot(driver, "image");
			WebElement ele= driver.findElement(locatorxpath);
			if(ele != null) {
				ele.clear();
				ele.sendKeys(text);
				
				//report.log(Status.PASS, "Enterd value in:"+locator.toString());
				report.pass("Enterd value in: "+locatorName, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			else {
				//report.log(Status.FAIL,"Not enterd value in:"+locator.toString());
				report.fail("Not enterd value in: "+locatorName, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
		}
		catch(Exception e){
			throw new Exception("Ëlement not gound using locator: "+locatorName);
			
		}
	}
	public static void clickElement(WebDriver driver, ExtentTest report, String locatorName, By locatorxpath) throws Exception {
		try {
			String screenshotPath=captureScreenshot(driver, "image");
			WebElement ele= driver.findElement(locatorxpath);
			if(ele != null) {
				ele.click();
				
				//report.log(Status.PASS, "Enterd value in:"+locator.toString());
				report.pass("Clicked on: "+locatorName, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			else {
				//report.log(Status.FAIL,"Not enterd value in:"+locator.toString());
				report.fail("Not clicked on: "+locatorName, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			
		}
		catch(Exception e){
			throw new Exception("Ëlement not gound using locator: "+locatorName);
		}
	}
}
