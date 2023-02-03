package com.qa.Tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.Utility.ReadData;
import com.aventstack.extentreports.Status;
import com.qa.Pages.LoginPage;

public class LoginPageTest extends BaseTest {

	@Test
	public void verifyLogin() {
		test = reports.createTest("verify Login");

		// to verify login
		// get username from property file
//		String username = ReadData.propertyFileData("username");
		String username = "username";
		// get password from property file
		String password = ReadData.propertyFileData("password");
		// call doLogin method
//		page.getInstance(LoginPage.class).doLogin(username, password);
		login.doLogin(username, password);
		// check logout button is enabled
		boolean actual;
		try {
			actual = page.getInstance(LoginPage.class).getLogoutButton().isEnabled();
		} catch (Exception e) {
			actual = false;
		}
		test.log(Status.INFO, "Login verify Sccessfully : " + actual);
//		Assert.assertTrue(actual);
		softAssert.assertTrue(actual);
		softAssert.assertAll();

	}

	@Test(dependsOnMethods = "verifyLogin")
	public void verifyLoginLogoTest() {
		test = reports.createTest("verify login logo test");
		// verify logo is displayed
		boolean actual = page.getInstance(LoginPage.class).getLoginLogo().isDisplayed();
//		Assert.assertTrue(actual);
		test.log(Status.INFO, "Login Logo is Present : " + actual);
		
	}

	@Test
	public void verifyBoatLogoTest() {
		test = reports.createTest("verify boat logo test");
		// verify logo is displayed

//		boolean actual = page.getInstance(LoginPage.class).getBoatlogo().isDisplayed();
		boolean actual = login.getBoatlogo().isDisplayed();
		Assert.assertTrue(actual);
		test.log(Status.INFO, "Boat Logo is Present : " + actual);
	}

	@Test
	public void verifyErrorMessageForUsername() {
		test = reports.createTest("verify error message for username");

		// verify error message when enter only password and click on login button
		String actual = page.getInstance(LoginPage.class).errorMessageForUsername();

		// get expected error message from excel file
		String expected = ReadData.excelFileData(1, 1);
		
		test.log(Status.INFO, "Expected Error Message : " + expected);
		test.log(Status.INFO, "Actual Error Message : " + actual);
		
		Assert.assertEquals(actual, expected);
		Reporter.log("Error message : " + actual);

		test.log(Status.INFO, "Expected Error Message : " + expected);
		test.log(Status.INFO, "Actual Error Message : " + actual);
	}

	@Test
	public void verifyErrorMessageForPassword() {
		test = reports.createTest("verify error message for password");
		// verify error message when click on login button without entering password
		String actual = page.getInstance(LoginPage.class).errorMessageForPassword();
		// get actual error message from excel file
		String expected = ReadData.excelFileData(1, 1);
		Assert.assertEquals(actual, expected);
		Reporter.log("Error message : " + actual);
		test.log(Status.INFO, "Expected Error Message : " + expected);
		test.log(Status.INFO, "Actual Error Message : " + actual);
	}

	@Test
	public void verifyErrorMessageForWrongCredentials() {
		test = reports.createTest("verify error message for wrong credentials");
		// verify error message when entered wrong credentials
		String actual = page.getInstance(LoginPage.class).errorMessageForWrongCredentials();
		// get expected error message from excel file
		String expected = ReadData.excelFileData(2, 1);
		Assert.assertEquals(actual, expected);
		Reporter.log("Error message : " + actual);
//		test.log(Status.INFO, MarkupHelper.createLabel("Expected Result : " + expected, ExtentColor.LIME));
//		test.log(Status.INFO, MarkupHelper.createLabel("Actual Result : " + actual, ExtentColor.CYAN));
		test.log(Status.INFO, "Expected Error Message : " + expected);
		test.log(Status.INFO, "Actual Error Message : " + actual);
	}

	@Test
	public void verifyUsername() {
		test = reports.createTest("verify Username");
		// verify entered username enters in username textbox
		// to get username from property file
		String username = ReadData.propertyFileData("username");
		// to enter username in username textbox
		page.getInstance(LoginPage.class).getUsernameTextbox().sendKeys(username);
		// get username from username textbox
		String actual = page.getInstance(LoginPage.class).getUsernameFromTextbox();
		// compare
		Assert.assertEquals(actual, username);
		test.log(Status.INFO, "Expected username : " + username);
		test.log(Status.INFO, "Actual username : " + actual);
	}

	@Test
	public void verifyPassword() {
		test = reports.createTest("verify Password");
		// verify entered password enters in password textbox
		// to get password from property file
		String password = ReadData.propertyFileData("password");
		// enter password to password textbox
		page.getInstance(LoginPage.class).getPasswordTextbox().sendKeys(password);
		// to get password from password textbox
		String actual = page.getInstance(LoginPage.class).getPasswordTextbox().getAttribute("value");
		// compare
		Assert.assertEquals(actual, password);
		test.log(Status.INFO, "Expected Password : " + password);
		test.log(Status.INFO, "Actual Password : " + actual);
	}

	@Test
	public void verifyLoginPageTitle() throws Exception {
		test = reports.createTest("verify Login page Title");
		// verify login page title
		// get actual title
		String actual = /* page.getInstance(LoginPage.class).title(); */ page.getPageTitle();
		// get expected Login page title from excel
		String expected = ReadData.excelFileData(3, 1);
		// compare
		Assert.assertEquals(actual, expected);
		Reporter.log("Login page Title : " + actual);
		test.log(Status.INFO, "Expected Title : " + expected);
		test.log(Status.INFO, "Actual Title : " + actual);
	}

	@Test
	public void verifyLoginPageURL() {
		test = reports.createTest("verify Login page URL");
		// verify url of login page
		// get actual URL
		String actual = page.getCurrentPageURL();
		// get expected URL from excel
		String expected = ReadData.excelFileData(4, 1);
		Assert.assertEquals(actual, expected);
		Reporter.log("Login page URL : " + actual);
		test.log(Status.INFO, "Expected URL : " + expected);
		test.log(Status.INFO, "Actual URL : " + actual);
	}
}
