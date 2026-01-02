package commonutilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	WebDriver driver;
	
	// ThreadLocal to handle WebDriver instances in a thread safe manner
	private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static void setDriver(WebDriver driver) {
		tlDriver.set(driver);
	}

	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	// Path to project directory
	private static final String project_directory_path = System.getProperty("user.dir");

	// ConfigReader to read the configuration properties
	private static final ConfigReader config = new ConfigReader();

	/*
	 * this method used to initialize the thread local driver on the basis of given
	 * browser
	 * 
	 * @param browser The Browser Type (e.g. "chrome","firefox")
	 * 
	 * @return The initialized WebDriver instance
	 * 
	 */
	public void init_driver(String browser) {

		// Initialized the WebDriver base on the provided browser type
		switch (browser.toLowerCase()) {

		case "chrome":

			// set the Chrome driver properties
			
			 WebDriverManager.chromedriver().setup();

	            //System.out.println("Launching Chrome Browser with WebDriverManager");
			/*System.setProperty(config.getProb("chrome_property"),
					project_directory_path + config.getProb("chrome_path"));

			System.out.println("Driver path: " + project_directory_path + config.getProb("chrome_path"));*/
			 
			 
			// set Chrome Options
			ChromeOptions opt = new ChromeOptions();
		
		
	    opt.addArguments(config.getProb("runHeadless").replace("--headless", "--headless=new")); // ensure new headless mode
			opt.addArguments("--" + config.getProb("headlessBrowserSize"));

			opt.addArguments("--no-sandbox");
			opt.addArguments("--remote-allow-origins=*");
			opt.addArguments("--incognito");
			opt.addArguments("--disable-popup-blocking");
			opt.addArguments("--disable-geolocation");
			opt.addArguments("--disable-notifications");
			opt.addArguments("--disable-infobars");
			opt.addArguments("--disable-dev-shm-usage");
		
			
			Map<String, Object> prefs = new HashMap<>();
		    prefs.put("profile.default_content_setting_values.geolocation", 2); // Block location
		    prefs.put("profile.default_content_setting_values.notifications", 2);
		    prefs.put("profile.default_content_setting_values.media_stream", 2);
		    opt.setExperimentalOption("prefs", prefs);


			// Initialized Chrome driver with options
			driver = new ChromeDriver(opt);
			setDriver(driver);
			getDriver().manage().deleteAllCookies();
		      
			break;

		default:

			// Throw exception for unsupported browsers
			throw new IllegalArgumentException("Unsupported browser " + browser);
		}

		// If driver initialized set it in the ThreadLocal and configure the browser
		// settings

		// return getDriver();

	}

	/**
	 * Retrieve the WebDriver instance from the ThreadLocal.
	 * 
	 * @return The WebDriver Instance
	 */

	public static synchronized void quitDriver() {

		WebDriver driver = getDriver();

		// check if WebDriver instance exists
		if (driver != null) {
			// quite the webdriver instance
			try {
				// driver.close();
				driver.quit();
				// cleanup the threadLocal variable to prevent the memory leaks
				tlDriver.remove();
			} catch (Exception e) {
				System.err.println("error closing webdriver :");
			}

		}
	}

}
