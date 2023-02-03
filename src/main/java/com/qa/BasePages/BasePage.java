package com.qa.BasePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageTitle() {
		// returns the current page Title
		return driver.getTitle(); 
	}

	@Override
	public String getPageHeader(WebElement ele) {
		// get the header text
		return ele.getText();
	}

	@Override
	public void waitForElementToPresent(WebElement ele) {
		// provides dynamic wait upto 15 sec(defined in page class) to element to load
		// properly
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			System.out.println("Exception occured during waiting for element to present :" + ele.toString());
		}

	}

	@Override
	public void waitForTitleToLoad(String title) {
		// provides dynamic wait upto 15 sec(defined in page class) to Title to load
		// properly
		try {
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			System.out.println("Exception occured during waiting for Title :" + title);
		}

	}

	public void doSendKeys(WebElement ele, String str) {
		// method to send text to element
		ele.sendKeys(str);
	}

	@Override
	public void doClick(WebElement element) {
		js.executeScript("arguments[0].click();", element);

	}

	@Override
	public String getCurrentPageURL() {
		// method to get current page URL
		return driver.getCurrentUrl();
	}

}
