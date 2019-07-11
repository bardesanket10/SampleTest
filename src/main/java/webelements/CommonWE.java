package webelements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Sanket Barde
 * @Date 11/07/2019
 *
 */
public class CommonWE {
	
	/** Enum used in WebElementSync function in CommonLib library */
	public enum EXPECTED { Visible, Clickable, VerifyText, Staleness, VerifyPageTitle, None, ListExist };

	@FindBy(xpath = "/html/body/div[2]/div/div/button")
	private WebElement closeBtnOfLoginPopup;

	@FindBy(xpath = "//span[contains(text(),'Enter Email/Mobile number')]")
	private WebElement username;

	@FindBy(xpath = "//input[@type = 'password']")
	private WebElement password;
	
	@FindBy(xpath = "//button[@type = 'submit']/span")
	private WebElement logInBtn;
	
	@FindBy(xpath = "//img[@title = 'Flipkart']")
	private WebElement flipkartImg;

	public WebElement getFlipkartImg() {
		return flipkartImg;
	}

	public WebElement getCloseBtnOfLoginPopup() {
		return closeBtnOfLoginPopup;
	}

	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLogInBtn() {
		return logInBtn;
	}
	
	
}
