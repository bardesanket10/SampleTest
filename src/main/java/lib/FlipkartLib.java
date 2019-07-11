package lib;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.Constant;
import webelements.CommonWE.EXPECTED;

/**
 * @author Sanket Barde
 * @Date 11/07/2019
 *
 */
public class FlipkartLib extends CommonLib {

	private WebDriver driver;
	private static final Logger LOGGER = Logger.getLogger(FlipkartLib.class);

	public FlipkartLib(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public boolean searchProduct(String searchItem) {

		if (! webElementSync(flipkartWE.getSearchBox(), EXPECTED.Clickable, null, Constant.MIN_WAIT_TIME)) {
			LOGGER.info("Search box has not available..... ");

			commonWE.getFlipkartImg().click();
			LOGGER.info("Click on the 'Flipkart' img....");
		}

		flipkartWE.getSearchBox().sendKeys(searchItem);
		LOGGER.info("Search item : " + searchItem);

		flipkartWE.getSearchBtn().click();
		LOGGER.info("click on Search button...");

		return webElementSync(flipkartWE.getSearchResult(), EXPECTED.ListExist , null, Constant.MIN_WAIT_TIME);
	}

	public boolean searchProductAndAddIntoCart(String searchItem) {
		if (this.searchProduct(searchItem)) {
			LOGGER.info("'" + searchItem + "' has successfully searched.....");

			// current window
			String parentWindow = driver.getWindowHandle();

			flipkartWE.getSearchResult().get(0).click();
			LOGGER.info("Click on the first item of the list");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// switch to new tab
			Set<String> wins = driver.getWindowHandles();

			wins.remove(parentWindow); // remove parent window

			for (String win : wins) {
				driver.switchTo().window(win); // switch to new tab
			}

			new WebDriverWait(driver,Constant.MIN_WAIT_TIME).until(ExpectedConditions.elementToBeClickable(flipkartWE.getAddToCartBtn())).click();;
			LOGGER.info("click on the 'ADD TO PRODUCT' button");

			return webElementSync(flipkartWE.getPlaceOrderBtn(), EXPECTED.Clickable, null, Constant.MIN_WAIT_TIME);

		} else {
			LOGGER.error("'" + searchItem + "' product not found....");
			return false;
		}
	}

	public void updateProductQuantity(int productQuantity) {
		for (int i=0 ; i < productQuantity; i++) {
			new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(flipkartWE.getAddProductQuantity())).click();
		}
	}

	public boolean orderProduct(String productName, int productQuantity, String deliveryAddress) {
		// handle 'signin' popup
		if (webElementSync(commonWE.getCloseBtnOfLoginPopup(), EXPECTED.Visible, null, Constant.MIN_WAIT_TIME)) {
			commonWE.getCloseBtnOfLoginPopup().click();
		}
		
		if (this.searchProductAndAddIntoCart(productName)) {
			LOGGER.info("'" + productName + "' product has searched and added into the cart");
			
			this.updateProductQuantity(2);
			
			// click on the 'Place Order'
			flipkartWE.getPlaceOrderBtn().click();
			
			// TODO - login with credentials and add delivery address
		
			return true;
		} else {
			LOGGER.error("'" + productName + "' product has searched and added into the cart");
			return false;
		}
		
	}


}
