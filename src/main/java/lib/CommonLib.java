package lib;

import java.util.List;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import webelements.CommonWE;
import webelements.CommonWE.EXPECTED;
import webelements.FlipkartWE;

/**
 * @author Sanket Barde
 * @Date 11/07/2019
 *
 */
public class CommonLib {
	private WebDriver driver;
	public CommonWE commonWE;
	public FlipkartWE flipkartWE;
	private static final Logger LOGGER = Logger.getLogger(CommonLib.class);
	
	public CommonLib(WebDriver driver) {
		this.driver = driver;
		commonWE = PageFactory.initElements(driver, CommonWE.class);
		flipkartWE = PageFactory.initElements(driver, FlipkartWE.class); 
	}

	public static Function<WebDriver, Boolean> ListOfElemExist(final List<WebElement> elemList) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				try{
					return (elemList.size() > 0);
				}
				catch(Exception ex){return false;}
			}
		};
	}
	
	public static Function<WebDriver, Boolean> StalenessElem(final WebElement elem) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return !elem.isDisplayed();
				}
				catch(Exception ex) {
					return true;
				}
			}
		};
	}
	
	public static Function<WebDriver, Boolean> ClickableElem(final WebElement elem) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return elem.isEnabled();
			}
		};
	}
	
	public static Function<WebDriver, Boolean> VerifyElemText(final WebElement elem, final String expectedText) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (elem.getText()).trim().toLowerCase().contains(expectedText.trim().toLowerCase());
			}
		};
	}
	
	public static Function<WebDriver, Boolean> VisibleElem(final WebElement elem) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return elem.isDisplayed();
			}
		};
	}
	
	public boolean webElementSync(Object webElementObj, EXPECTED expected, String PropertyValue, long... waittime) {
	
		WebDriverWait wait = new WebDriverWait(driver, 0, 500);
		wait.ignoring(NoSuchElementException.class);

		boolean returnFlag= false;
		
		try {
			switch (expected) {
			case ListExist:
				wait.until(this.ListOfElemExist((List<WebElement>)webElementObj));
				break;
				
			case Visible:
				wait.until(this.VisibleElem((WebElement)webElementObj));
				break;

			case Clickable:
				wait.until(this.VisibleElem((WebElement)webElementObj));
				wait.until(this.ClickableElem((WebElement)webElementObj));
				break;

			case VerifyText:
				wait.until(this.VisibleElem((WebElement)webElementObj));
				wait.until(this.VerifyElemText((WebElement)webElementObj, PropertyValue));
				
				break;

			case Staleness:
				wait.until(this.StalenessElem((WebElement)webElementObj));
				break;

			default:
				break;
			}
			
			returnFlag = true;			
			
		} catch (Exception e) {
			//No Action to take			
		}
		
		wait = null;
		// Element is found. True is returned on success.
		return returnFlag;
	}
	
	public void login() {
		commonWE.getUsername().sendKeys(""); // Enter Username
		commonWE.getPassword().sendKeys(""); // Enter Password
		
		commonWE.getLogInBtn().click(); // Click on "LogIn" button
	}


}
