package com.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Utility.ReadData;
import com.qa.BasePages.BasePage;

public class LoginPage extends BasePage {
	@FindBy(id = "user-name")
	private WebElement usernameTextbox;
	@FindBy(id = "password")
	private WebElement passwordTextbox;
	@FindBy(id = "login-button")
	private WebElement loginButton;
	@FindBy(id = "logout_sidebar_link")
	private WebElement logoutButton;
	@FindBy(className = "bot_column")
	private WebElement boatlogo;
	@FindBy(className = "login_logo")
	private WebElement loginLogo;
	@FindBy(css = ".error-message-container.error")
	private WebElement errorContainer; // Error message text

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method enters the userName and password in the userName and password
	 * textBox and click on login button.You can provide userName or Password but
	 * this is optional. If you have not provided any parameter it takes userName
	 * and password from property file.
	 * 
	 * @author SITU
	 *
	 */
	public void doLogin(String username, String password) {
		doSendKeys(usernameTextbox, username);
		doSendKeys(passwordTextbox, password);
		doClick(loginButton);
	}

	/**
	 * This method enters the userName and password in the userName and password
	 * textBox and click on login button.You can provide userName or Password but
	 * this is optional. If you have not provided any parameter it takes userName
	 * and password from property file.
	 * 
	 * @author SITU
	 * @since 1 JAN 2023
	 */
	public void doLogin() {
		String username = ReadData.propertyFileData("username");
		String password = ReadData.propertyFileData("password");
		doSendKeys(usernameTextbox, username);
		doSendKeys(passwordTextbox, password);
		doClick(loginButton);
	}

	/**
	 * This methods returns the error message that get when click on login button
	 * without entering UserName and only entering password
	 * 
	 * @author SITU
	 */
	public String errorMessageForUsername() {
		String password = ReadData.propertyFileData("password");
		doLogin("", password);
		return errorContainer.getText();
	}

	/**
	 * @return Error message that gets when click on login button without entering
	 *         password and only entering UserName
	 * 
	 * @author SITU
	 */
	public String errorMessageForPassword() {
		// get username from property file
		String username = ReadData.propertyFileData("username");
		// call doLogin method
		doLogin(username, "");
		// get actual error message
		return errorContainer.getText();

	}

	/**
	 * @return Text that entered in userName textBox
	 * 
	 * @author SITU
	 */
	public String getUsernameFromTextbox() {
		return usernameTextbox.getAttribute("value");
	}

	/**
	 * This method will return password that enters in the Password Textbox
	 * 
	 * @author SITU
	 */
	public String getPasswordFromTextbox() {
		return passwordTextbox.getAttribute("value");
	}

	/**
	 * @author SITU
	 * @return Username TextBox WebElement
	 */
	public WebElement getUsernameTextbox() {
		return usernameTextbox;
	}

	/**
	 * @author SITU
	 * @return Password TextBox WebElement
	 */
	public WebElement getPasswordTextbox() {
		return passwordTextbox;
	}

	/**
	 * @author SITU
	 * @return Login button WebElement
	 */
	public WebElement getLoginButton() {
		return loginButton;
	}

	/**
	 * @author SITU
	 * @return Logout button WebElement
	 */
	public WebElement getLogoutButton() {
		return logoutButton;
	}

	/**
	 * @author SITU
	 * @return Boat Logo WebElement
	 */
	public WebElement getBoatlogo() {
		return boatlogo;
	}

	/**
	 * Enter invalid username and invalid password in respective textboxes
	 * 
	 * @return Error message
	 * @author SITU
	 */
	public String errorMessageForWrongCredentials() {
		// enter wrong username and password
		doLogin("user", "pass");
		return getErrorContainer();
	}

	/**
	 * @return Error Message
	 * 
	 * @author SITU
	 */
	public String getErrorContainer() {
		return errorContainer.getText();
	}

	/**
	 * 
	 * @return Login logo webelement
	 * @author SITU
	 */
	public WebElement getLoginLogo() {
		return loginLogo;
	}

}
