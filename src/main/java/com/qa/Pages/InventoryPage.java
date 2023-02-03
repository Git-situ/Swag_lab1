package com.qa.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Utility.UtilityMethods;
import com.qa.BasePages.BasePage;

public class InventoryPage extends BasePage {

	@FindBy(id = "item_4_title_link")
	private WebElement bagPackName;
	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement bagPackAddCartBtn;
	@FindBy(id = "item_5_title_link")
	private WebElement jacketName;
	@FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
	private WebElement jacketAddCartBtn;
	@FindBy(className = "product_sort_container")
	private WebElement dropdown;
	@FindBy(className = "shopping_cart_link")
	private WebElement cartLink;
	@FindBy(className = "inventory_item_name")
	private List<WebElement> productNames;
	@FindBy(className = "inventory_item_price")
	private List<WebElement> productPrices;
	@FindBy(id = "remove-sauce-labs-backpack")
	private WebElement bagPackRemoveBtn;

	public InventoryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public WebElement getBagPackName() {
		return bagPackName;
	}

	public WebElement getBagPackAddCartBtn() {
		return bagPackAddCartBtn;
	}

	public WebElement getJacketName() {
		return jacketName;
	}

	public WebElement getJacketAddCartBtn() {
		return jacketAddCartBtn;
	}

	public WebElement getDropdown() {
		return dropdown;
	}

	public WebElement getCartLink() {
		return cartLink;
	}

	public List<WebElement> getProductNames() {
		return productNames;
	}

	public List<WebElement> getProductPrices() {
		return productPrices;
	}

	public WebElement getBagPackRemoveBtn() {
		return bagPackRemoveBtn;
	}

	/**
	 * This method added the two items to the cart This method added Backpack and
	 * Jacket
	 * 
	 * @author SITU
	 *
	 */
	public void addItemsToCart() {
		doClick(bagPackAddCartBtn);
		doClick(jacketAddCartBtn);
		
	}

	/**
	 * This method returns List of Double type values of products prices
	 * 
	 * @author SITU
	 *
	 */
	public List<Double> priceList() {
		return UtilityMethods.productPriceList(productPrices);
	}

	/**
	 * This method returns List of String type values of products Names
	 * 
	 * @author SITU
	 *
	 */
	public List<String> nameList() {
		return UtilityMethods.productNameList(productNames);
	}
	

}
