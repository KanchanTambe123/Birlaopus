package pagefunctions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class End_to_End_Wallpapers_Journey_Page {

	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	@FindBy(xpath = "//div[@class='cmp-wallpaper__other-combination-heading']/p")
	public WebElement availableShadesHeading;
	
	@FindBy(xpath = "//button[normalize-space()='Add to cart']")
	public WebElement AddToCartButton;
	
	@FindBy(xpath = "//button[@type='submit' and @class='cmp-wallpaper__submit']")
	public WebElement PincodeCheckButton;
	
	@FindBy(xpath = "//input[@placeholder='Enter pincode to view serviceability']")
	public WebElement PincodeInputFiled;
	

	

	
	@FindBy(xpath = "//span[@class='cmp-button__text' and text()='View Cart & Checkout']")
	public WebElement ViewCheckoutButton;
	
	

	public End_to_End_Wallpapers_Journey_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void selectShadeByProductCode(String productCode) {
		// Locate all shade elements under the container
		List<WebElement> shades = driver
				.findElements(By.xpath("//div[@class='cmp-wallpaper__other-combination-images']/div"));

		List<String> availableCodes = new ArrayList<>();
		boolean found = false;

		for (WebElement shade : shades) {
			String code = shade.getAttribute("data-product-code");
			availableCodes.add(code);

			if (code.equals(productCode)) {
				// Scroll into view
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shade);

				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
				// Wait until clickable and click
				wait.until(ExpectedConditions.elementToBeClickable(shade)).click();
				System.out.println("Selected shade with Product Code: " + productCode);
				found = true;
				break;
			}
		}

		if (!found) {
			System.err.println("Shade with Product Code " + productCode + " not found!");
			System.err.println("Available Product Codes: " + availableCodes);
			throw new RuntimeException("Shade with Product Code " + productCode + " not found!");
		}
	}

	public void hoverAndClickProduct(String navMenu, String navTab, String productName) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		// 1️.Hover over main navigation
		List<WebElement> mainNavItems = driver.findElements(By.xpath("//ul[@class='cmp-navigation__group']/li"));
		boolean mainNavFound = false;
		for (WebElement navItem : mainNavItems) {
			if (navItem.getText().trim().equalsIgnoreCase(navMenu)) {
				js.executeScript("arguments[0].scrollIntoView(true);", navItem);
				Actions actions = new Actions(driver);
				actions.moveToElement(navItem).perform();
				Thread.sleep(1000); // short wait for sub-menu
				mainNavFound = true;
				break;
			}
		}
		if (!mainNavFound) {
			throw new RuntimeException("Main navigation item not found: " + navMenu);
		}

		// 2️.Hover over tab/sub-navigation
		List<WebElement> tabNavItems = driver.findElements(By.xpath("//li[@class='navigation-tab-list__item']/a"));
		boolean tabFound = false;
		for (WebElement tab : tabNavItems) {
			if (tab.getText().trim().equalsIgnoreCase(navTab)) {
				js.executeScript("arguments[0].scrollIntoView(true);", tab);
				Actions actions = new Actions(driver);
				actions.moveToElement(tab).perform();
				Thread.sleep(1000); // products to load
				tabFound = true;
				break;
			}
		}
		if (!tabFound) {
			throw new RuntimeException("Tab navigation item not found: " + navTab);
		}

		// 3️⃣ Wallpaper-specific handling (NAME ONLY)

		List<WebElement> products = driver.findElements(By.cssSelector("div.cmp-wallpaper-card"));
		boolean productClicked = false;
		for (WebElement product : products) {
			WebElement title = product
					.findElement(By.cssSelector("p.cmp-wallpaperCard__other-combination-heading-text"));
			if (title.getText().trim().equalsIgnoreCase(productName)) {
				WebElement productLink = product.findElement(By.cssSelector("a.cmp-wallpaperCard__image-link"));
				wait.until(ExpectedConditions.elementToBeClickable(productLink));
				js.executeScript("arguments[0].scrollIntoView(true);", productLink);
				productLink.click();
				System.out.println("Clicked product: " + productName);
				productClicked = true;
				break;
			}
		}
		if (!productClicked) {
			throw new RuntimeException("Product not found: " + productName);
		}

	}
	
	
	


	public void enterPincodeAndCheck(String pincode) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    By pincodeInput = By.xpath("//input[@name='pinCode' and contains(@class,'cmp-wallpaper__input')]");
	    WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(pincodeInput));

	    field.clear();
	    field.sendKeys(pincode);

	    WebElement checkBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.cssSelector("button.cmp-wallpaper__submit")));
	    checkBtn.click();

	    By errorMsg = By.cssSelector("div.error-txt");
	    By successMsg = By.xpath("//*[contains(text(),'available') or contains(text(),'serviceable')]");

	    wait.until(driver -> {
	        if (!driver.findElements(errorMsg).isEmpty()) return true;
	        if (!driver.findElements(successMsg).isEmpty()) return true;
	        return false;
	    });

	    List<WebElement> errors = driver.findElements(errorMsg);
	    if (!errors.isEmpty() && errors.get(0).isDisplayed()) {
	        throw new AssertionError("Pincode validation failed: " + errors.get(0).getText());
	    }

	    System.out.println("Pincode " + pincode + " validated successfully");
	}
}
