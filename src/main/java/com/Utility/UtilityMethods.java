package com.Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.Temperature;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class UtilityMethods {

	/**
	 * This method select a dropDown option of visible text, value or Index.You can
	 * provide any of them with WebElement
	 * 
	 * @author Situ
	 *
	 */
	public static void selectClass(WebElement dropdown, String visibleTextOrValue) {
		Select sl = new Select(dropdown);
		try {
			sl.selectByVisibleText(visibleTextOrValue);
		} catch (Exception e) {
			sl.selectByValue(visibleTextOrValue);
		}

	}

	/**
	 * This method select a dropDown option of visible text, value or Index.You can
	 * provide any of them with WebElement
	 * 
	 * @author Situ
	 *
	 */
	public static void selectClass(WebElement dropdown, int index) {
		Select sl = new Select(dropdown);
		sl.selectByIndex(index);
	}

	/**
	 * This method return a instance of Actions class you need to provide a driver.
	 * 
	 * @author Situ
	 *
	 */
	public static Actions actionsClass(WebDriver driver) {
		Actions ac = new Actions(driver);
		return ac;
	}

	/**
	 * This method return a instance of Alert interface you need to provide a
	 * driver.
	 * 
	 * @author Situ
	 *
	 */
	public static Alert alertClass(WebDriver driver) {
		Alert al = driver.switchTo().alert();
		return al;
	}

	/**
	 * This method return List of String of all products name with same locater
	 * You need to provide a list of WebElement
	 * 
	 * @author Situ
	 */
	public static List<String> productNameList(List<WebElement> ele) {
		List<String> str = new ArrayList<String>();
		for (WebElement v : ele)
			str.add(v.getText());
		return str;
	}
	/**
	 * This method return List of Double type value of all products price with same locater
	 * You need to provide a list of WebElement
	 * 
	 * @author Situ
	 */
	public static List<Double> productPriceList(List<WebElement> ele) {
		List<Double> dl = new ArrayList<Double>();
		for (WebElement v : ele)
			dl.add(Double.valueOf(v.getText().replace("$", "")));
		return dl;
	}

	/**
	 * This method takes screenshot
	 * 
	 * @author SITU
	 * @return 
	 */
	public  static String screenshot(WebDriver driver, String name) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File address = new File(".\\Screenshots/" + name + ".jpeg");
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(source, address);
		return ".\\Screenshots/" + name;

	}
}
