package com.qa.Tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Utility.UtilityMethods;
import com.qa.Pages.InventoryPage;
import com.qa.Pages.LoginPage;

public class InventoryPageTest extends BaseTest {
	@Test
	public void verifyItemAddedToCartTest() {
		login.doLogin();
		page.getInstance(InventoryPage.class).addItemsToCart();
		boolean actual = page.getInstance(InventoryPage.class).getBagPackName().isDisplayed();
		Assert.assertTrue(actual);
	}

	@Test
	public void verifyItemRemovesFromCart() {
		// verify removed Product from cart will get removed from cart
		// to login
		page.getInstance(LoginPage.class).doLogin();
		// add Products to cart
		page.getInstance(InventoryPage.class).addItemsToCart();
		// go to cart
		page.getInstance(InventoryPage.class).getCartLink().click();
		// remove Product from cart
		page.getInstance(InventoryPage.class).getBagPackRemoveBtn().click();
		try {
			// check product is removed
			page.getInstance(InventoryPage.class).getBagPackName().isDisplayed();
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void verifyCartCountTest() {
		// verify when Products added cart shows correct count
		// to login
		page.getInstance(LoginPage.class).doLogin();
		// add products to cart
		page.getInstance(InventoryPage.class).addItemsToCart();
		// check the count
		String actual = page.getInstance(InventoryPage.class).getCartLink().getText();
		Assert.assertEquals(actual, "2");
	}

	@Test
	public void verifyPriceLowToHighDropdownOptionTest() throws Exception {
		// verify low to high option sorts price in ascending order
		// to login
		page.getInstance(LoginPage.class).doLogin();
		// get all prices of page
		List<Double> expected = UtilityMethods
				.productPriceList(page.getInstance(InventoryPage.class).getProductPrices());
		// sort price in ascending order
		Collections.sort(expected);
		// select an low to high option from dropdown
		UtilityMethods.selectClass(page.getInstance(InventoryPage.class).getDropdown(), "lohi");
		// get the pricelist of all products
		List<Double> actual = UtilityMethods.productPriceList(page.getInstance(InventoryPage.class).getProductPrices());
		// compare expected pricelist with actual pricelist
		Assert.assertEquals(actual, expected);
	}

}
