package framework;

import java.io.File;

/**
 * @author Sanket Barde
 * @Date 11/07/2019
 *
 */
public class Constant {

	//Time Constant used at anywhere in project
	public static final long NANO_WAIT_TIME = 2;
	public static final long MICRO_WAIT_TIME = 5;
	public static final long MIN_WAIT_TIME = 10;
	public static final long AVG_WAIT_TIME = 25;
	public static final long MAX_WAIT_TIME = 45;
	public static final long MEGA_WAIT_TIME = 60;
	public static final long EXTREME_WAIT_TIME = 75;
	
	public static final String SCREENSHOOT_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator;
	public static final String CHROME_DRIVER = System.getProperty("user.dir") + File.separator + "Driver" + File.separator + "chromedriver.exe";
}
