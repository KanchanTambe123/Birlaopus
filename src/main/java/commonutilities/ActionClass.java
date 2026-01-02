package commonutilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionClass {

	public WebDriver driver;
	Actions action;

	public ActionClass() {
		driver = DriverManager.getDriver();
		action = new Actions(driver);
	}

	
	public void sendEnterKey() {
		action.sendKeys(Keys.ENTER).build().perform();
	}
	public void hoverOverElement(WebElement element) {
		action.moveToElement(element).perform();
	}

	public void handleSlider(WebElement slider, WebElement target) {
		action.dragAndDrop(slider, target);
	}
	
	
	

}
