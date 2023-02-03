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

	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;

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

	@BeforeClass
	public void startReport() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String reportName = dateFormat.format(new Date()) + ".html";

		htmlReporter = new ExtentSparkReporter(".//Extent Reports/" + "Sauce Lab Demo_" + reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);

		// add environment details
		reports.setSystemInfo("Machine", "HP");
		reports.setSystemInfo("Author", "SITU");
		reports.setSystemInfo("Environment", "SIT");

		// configuration to change look and feel
		htmlReporter.config().setDocumentTitle("Sauce Lab Demo");
		htmlReporter.config().setReportName("Sauce Labs Report");
		htmlReporter.config().setTimeStampFormat("yyyy-MM-dd_HH-mm-ss");

	}

	@AfterMethod
	public void tearDownMethod(ITestResult a) throws Exception {
		
		UtilityMethods.screenshot(driver, a.getName());

		if (ITestResult.FAILURE == a.getStatus()) {

			test.log(Status.FAIL, MarkupHelper.createLabel(a.getName() + " Failed", ExtentColor.RED));
			test.fail(a.getThrowable());
		} else if (ITestResult.SUCCESS == a.getStatus()) {
			test.log(Status.PASS, MarkupHelper.createLabel(a.getName() + " Passed", ExtentColor.GREEN));
			test.pass(a.getThrowable());
		} else if (ITestResult.SKIP == a.getStatus())
			test.log(Status.SKIP, MarkupHelper.createLabel(a.getName() + " Skipped", ExtentColor.PURPLE));

		reports.flush();
		driver.close();
	}

}
