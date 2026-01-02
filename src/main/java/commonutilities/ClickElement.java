package commonutilities;

import java.time.Duration;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ConfigReader;

public class ClickElement {
	// ConfigReader to read the configuration properties
		private static final ConfigReader config = new ConfigReader();
		WebDriverWaitHelper waitHelper;
		WebDriver driver;
		JavascriptExecutor jse;
		Actions action;

		public ClickElement() {
			driver = DriverManager.getDriver();
			waitHelper = new WebDriverWaitHelper();
			jse = ((JavascriptExecutor) driver);
			action = new Actions(driver);
		}

	//normal click method

		public void clickWebElement(WebElement ele) {
			waitHelper.waitForElementToBeClickable(ele, Integer.parseInt(config.getProb("wait")));
			ele.click();
		}

	//method to double click

		public void dobleClick(WebElement element) {
			waitHelper.waitForElementToBeClickable(element, Integer.parseInt(config.getProb("wait")));
			action.moveToElement(element).doubleClick().perform();
		}

	//method to uncheck the checkbox if already checked

		public void uncheckCheckBox(WebElement ele) {
			// check for visibility of ele
			waitHelper.waitForElementToBeVisible(ele, Integer.parseInt(config.getProb("wait")));
			// check if check box is already selected
			if (ele.getAttribute("class").contains("active")) {
				// if check box is selected, click it to uncheck
				ele.click();
			}
		}

		public void scrollPage(String x, String y, WebElement ele) {
			jse.executeScript("window.scrollBy(" + x + "," + y + ")");
			waitHelper.waitForElementToBeVisible(ele, Integer.parseInt(config.getProb("wait")));
		}

		public void radioBtnClick(WebElement ele) {
			waitHelper.waitForElementToBeClickable(ele, Integer.parseInt(config.getProb("wait")));
			jse.executeScript("arguments[0].checked=true;", ele);
		}

	//method to click by mouse hovering on the element	
		
		public void clickUsingMouseHover(WebElement ele) {
			waitHelper.waitForElementToBeClickable(ele, Integer.parseInt(config.getProb("wait")));
			action.moveToElement(ele).click().perform();
		}

		public void clearInput(WebElement ele) {
			waitHelper.waitForElementToBeClickable(ele, Integer.parseInt(config.getProb("wait")));
			ele.clear();
		}
}
