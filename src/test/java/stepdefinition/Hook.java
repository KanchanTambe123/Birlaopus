package stepdefinition;

import java.util.HashMap;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import commonutilities.DriverManager;
import commonutilities.NetworkUtils;
import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook {

	private static WebDriver driver;
	ConfigReader configReader = new ConfigReader();
	DriverManager driverManager = new DriverManager();

	@Before
	public void browser() {

		driverManager.init_driver(configReader.getProb("browser"));
	
		   DriverManager.resetApiData();

	}

	@AfterStep
	public void postEachStep(Scenario scenario) {

		if (scenario.isFailed()) {
			TakesScreenshot takescreenshot = (TakesScreenshot) DriverManager.getDriver();
			byte[] screenshot = takescreenshot.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", screenshot.toString());
		}
	}


	@After

	public void tearDown() {

		DriverManager.quitDriver();

	}

}