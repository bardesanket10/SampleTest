package scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import framework.Constant;

/**
 * @author Sanket Barde
 * @Date 11/07/2019
 *
 */
public class BaseTest {
	
	public static WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		
		System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER);
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constant.MIN_WAIT_TIME, TimeUnit.SECONDS);
		
		driver.get("https://www.flipkart.com/");
		
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
