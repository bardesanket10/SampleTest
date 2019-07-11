package framework;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.google.common.io.Files;

import scripts.BaseTest;

/**
 * @author Sanket Barde
 * @Date 11/07/2019
 *
 */
public class ScreenshotUtils extends TestListenerAdapter {

	private static final Logger LOGGER = Logger.getLogger(ScreenshotUtils.class);
	private WebDriver driver;

	public final void onTestFailure(ITestResult result) {
		LOGGER.error("***** Error " + result.getName() + " test has failed *****");
		String methodName = result.getName().toString().trim();
		takeScreenShot(methodName);
	}

	public final void takeScreenShot(String methodName) {
		// get the driver
		driver = BaseTest.driver;
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

		// Take screenshot
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			Files.copy(scrFile, new File(Constant.SCREENSHOOT_FOLDER_PATH + methodName + " - " + dateFormat.format(new Date()) + ".png"));
			LOGGER.info("***Placed screen shot in " + Constant.SCREENSHOOT_FOLDER_PATH + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
