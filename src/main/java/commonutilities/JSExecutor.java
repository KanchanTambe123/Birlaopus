package commonutilities;

import javax.lang.model.element.Element;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.ConfigReader;

public class JSExecutor {

	WebDriverWaitHelper waitHelper;
	private static final ConfigReader config = new ConfigReader();
	ConfigReader configReader = new ConfigReader();

	WebDriver driver;
	JavascriptExecutor jse;

	public JSExecutor() {

		driver = DriverManager.getDriver();
		waitHelper = new WebDriverWaitHelper();
		jse = (JavascriptExecutor) driver;
	}

	// Click WebElement Using JS Click
	public void jsClickWithWait(WebElement ele) {
		waitHelper.waitForElementToBeClickable(ele, Integer.parseInt(config.getProb("wait")));
		jse.executeScript("arguments[0].click();", ele);
	}
		public void jsClickWithoutWait(WebElement ele) {
		jse.executeScript("arguments[0].click();", ele);
	}

//Scrolling option Utility 
//Note :- Enter negative y value to scroll up and positive y value to scroll down
//Enter negative x value to scroll left and positive x value to scroll right

	public void scroll(int x, int y) {
		jse.executeScript("window.scrollBy(" + Integer.toString(x) + "," + Integer.toString(y) + ")");
	}

//Scroll until visibilty of element

	public void scrollUntilElementVisible(WebElement element) {
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	}

//SendKeys Method Using JS

	public void sendKeysUsingJS(WebElement element, String inputText) {
		jse.executeScript("arguments[0].value = arguments[1]", element, inputText);
	}

//scroll to the top of the page

	public void topOfPage() {
		jse.executeScript("window.scrollTo(0,0);");
	}

	public void scrollUsingAction(WebElement ele) {
		int getposition = ele.getLocation().getY();
		jse.executeScript("window.scrollTo(0, arguments[0] - 100);", getposition);
	}

	public void getScrollPixelAndScroll(WebElement ele) {
		int getElementPosition = ele.getLocation().getY();
		jse.executeScript("window.scrollTo(0,arguments[0]);", getElementPosition);
	}
	public void scrollToBottomOfPage() { 
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	  

	
	}
	public void mousehover(WebElement ele) {
		 
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
		action.pause(20);
	}
	   public void executeScript(String script, WebElement element) {
	        ((JavascriptExecutor) driver).executeScript(script, element);
	    }

}
