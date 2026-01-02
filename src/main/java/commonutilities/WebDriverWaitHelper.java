package commonutilities;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaitHelper {

	WebDriver driver;

	public WebDriverWaitHelper() {
		driver = DriverManager.getDriver();
	}

	/**
	 * Method : WebDriver Dynamic Wait to handle the visibility of a WebElement
	 * 
	 * @param time       : int : Time to wait
	 * @param WebElement : Webelement : locator
	 * @return WebElement : return webelement after it become visible
	 * 
	 */



	    public WebDriverWaitHelper(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Wait for WebElement to be visible
	    public void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.visibilityOf(element));
	    }

	    // Wait for element located by By to be visible
	    public void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    // Wait for element to be clickable
	    public void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	    }

	    // Wait for element located by By to be clickable
	    public void waitForElementToBeClickable(By locator, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.elementToBeClickable(locator));
	    }

	    // Wait for all elements in list to be visible
	    public void waitForAllElementsVisible(List<WebElement> elements, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        for (WebElement element : elements) {
	            wait.until(ExpectedConditions.visibilityOf(element));
	        }
	    }
	    
	    public void waitForElementVisible(WebElement ele) {
			
			WebDriverWait webDriverWait=new WebDriverWait(driver,Duration.ofSeconds(20));
			webDriverWait.until(ExpectedConditions.visibilityOf(ele));
		}
	    
	    public <V> V until(Function<? super WebDriver, V> condition, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        return wait.until(condition);
	    }
}
