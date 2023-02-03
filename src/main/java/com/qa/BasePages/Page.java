package com.qa.BasePages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public Page(WebDriver driver) {
		this.driver = driver;
		// Explicit wait upto 15 sec
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		js = (JavascriptExecutor) driver;
	}
	
	

	/**
	 * This method returns URL of current page
	 * 
	 * @author Situ
	 *
	 */
	public abstract String getCurrentPageURL();

	/**
	 * This method returns Title of current page
	 * 
	 * @author Situ
	 *
	 */
	public abstract String getPageTitle();

	/**
	 * This method returns Header of current page
	 * 
	 * @author Situ
	 *
	 */
	public abstract String getPageHeader(WebElement element);

	/**
	 * This method provides max 15 sec of wait for the element to visible
	 * 
	 * @author Situ
	 *
	 */
	public abstract void waitForElementToPresent(WebElement element);

	/**
	 * To click on element using javascript executer
	 * 
	 * @author Situ
	 */
	public void doClick(WebElement element) {

	}

	/**
	 * This method provides max 15 sec of wait for the Title to get load
	 * 
	 * @author Situ
	 *
	 */
	public abstract void waitForTitleToLoad(String title);

	public <Tpage extends BasePage> Tpage getInstance(Class<Tpage> pageClass) {
		// To get instance(object) of any class
		try {
			// returns the instance of class
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		} catch (Exception e) {
			System.out.println("Got some Exception during ceate an instance " + e);
			return null; 
		}

	}
}
