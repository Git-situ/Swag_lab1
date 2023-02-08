package com.qa.Tests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.Utility.ReadData;
import com.Utility.UtilityMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.BasePages.BasePage;
import com.qa.BasePages.Page;
import com.qa.Pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	SoftAssert softAssert;

	WebDriver driver;
	Page page;
	LoginPage login;
	String expected;

	
	@BeforeMethod
	public void setUpMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String url = ReadData.propertyFileData("url");
		driver.get(url);
		page = new BasePage(driver);

		login = new LoginPage(driver);
		softAssert = new SoftAssert();

	}


	@AfterMethod
	public void tearDownMethod(ITestResult a) throws Exception {
		
		UtilityMethods.screenshot(driver, a.getName());

		driver.close();
	}

}
