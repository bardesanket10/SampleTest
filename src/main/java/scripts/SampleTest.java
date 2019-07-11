package scripts;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import lib.FlipkartLib;

/**
 * @author Sanket Barde
 * @Date 11/07/2019
 */
public class SampleTest extends BaseTest {

	private static final Logger LOGGER = Logger.getLogger(SampleTest.class);

	private String getMethodName(){
		StackTraceElement[] stackElemArray = Thread.currentThread().getStackTrace();
		return stackElemArray[2].getMethodName(); //2-> Caller method name
	}

	@Test
	public void filpcartTest() {
		String methodName = this.getMethodName();
		LOGGER.info("\n" + methodName + " Script is started\n");
		FlipkartLib flipcart = new FlipkartLib(driver);

		if (!flipcart.orderProduct("Apple iPhone X Space gray color 256 GB", 2, null)) {
			LOGGER.error("Order the product action perform unsuccessfully ");
			Assert.fail("Order the product action perform unsuccessfully ");
		} else {
			LOGGER.info("Order the product action perform successfully ");
		}

	}

}
