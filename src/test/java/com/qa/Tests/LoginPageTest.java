package com.qa.Tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.Utility.ReadData;
import com.qa.Pages.LoginPage;

public class LoginPageTest extends BaseTest {

	@Test
	public void verifyLogin() {

		// to verify login
		// get username from property file
		String username = ReadData.propertyFileData("username");
		// get password from property file
		String password = ReadData.propertyFileData("password");
		// call doLogin method
//		page.getInstance(LoginPage.class).doLogin(username, password);
		login.doLogin(username, password);
		// check logout button is enabled
		boolean actual;
		try {
			actual = login.getLogoutButton().isEnabled();
		} catch (Exception e) {
			actual = false;
		}
//		Assert.assertTrue(actual);
		softAssert.assertTrue(actual);
		softAssert.assertAll();

	}

	@Test(dependsOnMethods = "verifyLogin")
	public void verifyLoginLogoTest() {
		// verify logo is displayed
		boolean actual = page.getInstance(LoginPage.class).getLoginLogo().isDisplayed();
		Assert.assertTrue(actual);

	}

	@Test
	public void verifyBoatLogoTest() {
		// verify logo is displayed

//		boolean actual = page.getInstance(LoginPage.class).getBoatlogo().isDisplayed();
		boolean actual = login.getBoatlogo().isDisplayed();
		Assert.assertTrue(actual);
	}

	@Test
	public void verifyErrorMessageForUsername() {

		// verify error message when enter only password and click on login button
		String actual = page.getInstance(LoginPage.class).errorMessageForUsername();

		// get expected error message from excel file
		String expected = ReadData.excelFileData(0, 1);

		Assert.assertEquals(actual, expected);
		Reporter.log("Error message : " + actual);
	}

	@Test
	public void verifyErrorMessageForPassword() {
		// verify error message when click on login button without entering password
		String actual = page.getInstance(LoginPage.class).errorMessageForPassword();
		// get actual error message from excel file
		String expected = ReadData.excelFileData(1, 1);
		Assert.assertEquals(actual, expected);
		Reporter.log("Error message : " + actual);
	}

	@Test
	public void verifyErrorMessageForWrongCredentials() {
		// verify error message when entered wrong credentials
		String actual = page.getInstance(LoginPage.class).errorMessageForWrongCredentials();
		// get expected error message from excel file
		String expected = ReadData.excelFileData(2, 1);
		Assert.assertEquals(actual, expected);
		Reporter.log("Error message : " + actual);
	}

	@Test
	public void verifyUsername() {
		// verify entered username enters in username textbox
		// to get username from property file
		String username = ReadData.propertyFileData("username");
		// to enter username in username textbox
		page.getInstance(LoginPage.class).getUsernameTextbox().sendKeys(username);
		// get username from username textbox
		String actual = page.getInstance(LoginPage.class).getUsernameFromTextbox();
		// compare
		Assert.assertEquals(actual, username);
	}

	@Test
	public void verifyPassword() {
		// verify entered password enters in password textbox
		// to get password from property file
		String password = ReadData.propertyFileData("password");
		// enter password to password textbox
		page.getInstance(LoginPage.class).getPasswordTextbox().sendKeys(password);
		// to get password from password textbox
		String actual = page.getInstance(LoginPage.class).getPasswordTextbox().getAttribute("value");
		// compare
		Assert.assertEquals(actual, password);
	}

	@Test
	public void verifyLoginPageTitle() throws Exception {
		// verify login page title
		// get actual title
		String actual = /* page.getInstance(LoginPage.class).title(); */ page.getPageTitle();
		// get expected Login page title from excel
		String expected = ReadData.excelFileData(3, 1);
		// compare
		Assert.assertEquals(actual, expected);
		Reporter.log("Login page Title : " + actual);
	}

	@Test
	public void verifyLoginPageURL() {
		// verify url of login page
		// get actual URL
		String actual = page.getCurrentPageURL();
		// get expected URL from excel
		String expected = ReadData.excelFileData(4, 1);
		Assert.assertEquals(actual, expected);
		Reporter.log("Login page URL : " + actual);
	}
}