package webelements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Sanket Barde
 * @Date 11/07/2019
 *
 */
public class FlipkartWE {

	@FindBy(xpath = "//input[@name = 'q']")
	private WebElement searchBox;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//div[contains(@data-id, 'MOBE')]")
	private List<WebElement> searchResult;
	
	@FindBy(xpath = "//*[@id='container']/div/div[3]/div[2]/div[1]/div[1]/div[2]/div/ul/li[1]/button")
	private WebElement addToCartBtn;
	
	@FindBy(xpath = "//*[@id='container']/div/div[2]/div[2]/div/div[1]/div/div[3]/div/form/button")
	private WebElement placeOrderBtn;
	
	@FindBy(xpath = "//*[@id='container']/div/div[2]/div[2]/div/div[1]/div/div[2]/div/div[2]/div[1]/div/button[2]")
	private WebElement addProductQuantity;

	public WebElement getAddProductQuantity() {
		return addProductQuantity;
	}

	public WebElement getPlaceOrderBtn() {
		return placeOrderBtn;
	}

	public WebElement getAddToCartBtn() {
		return addToCartBtn;
	}

	public List<WebElement> getSearchResult() {
		return searchResult;
	}

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
}
