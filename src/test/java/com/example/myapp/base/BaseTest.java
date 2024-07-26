package com.example.myapp.base;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//https://www.softwaretestingmaterial.com/generate-extent-reports/

public class BaseTest {
	protected WebDriver driver;
	public static Properties prop= new Properties();
	public static FileReader fr;
	protected ExtentSparkReporter spark;
	protected ExtentReports extent;
	protected ExtentTest logger;
	
	protected WebDriver getDriver() {
		return driver;
	}
	
	@BeforeClass
	public void startReport() {
		DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		LocalDateTime now=LocalDateTime.now();
		String timestamp=now.format(formatter);
		String folderName="Report_"+timestamp;
		String folderDir= System.getProperty("user.dir") + "\\test-output\\ExtentReports";
		Path path=Paths.get(folderDir, folderName);
		try {
			Files.createDirectories(path);
			System.out.println("Folder created");
		}catch(IOException e) {
			System.err.println("Fail to create folder");
		}
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\"+ folderName + "\\ExtentReport.html");
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", "TestHest");
		extent.setSystemInfo("Environment", "TestEnvironment");
		extent.setSystemInfo("User Name", "Subrata");
		spark.config().setDocumentTitle("Title of the Report");
		spark.config().setReportName("Name of the Report");
		spark.config().setTheme(Theme.STANDARD);
	}
	@BeforeMethod
	public void setUp() throws IOException {
		if(driver==null)
		{
			FileReader fr= new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\config.properties");
			prop.load(fr);
		}
		if(prop.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			 //System.setProperty("webdriver.chrome.driver", "C:\\Users\\dell\\Desktop\\SeleniumAutomationFramework\\Automation-Selenium\\webdriver\\chromedriver.exe");
			 WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
	         driver.manage().window().maximize();
	         //driver.get(prop.getProperty("url"));
		}

	}
	@AfterMethod
	public void tearDown() {
		if(driver!=null){	
		driver.quit();
	}
	}
	@AfterClass
	public void endReport() {
	extent.flush();
	}

}
