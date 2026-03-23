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
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Wait for overlays to disappear
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".page-loader, .loading, .shimmer")));

    // Locate input field dynamically
    By pincodeInput = By.xpath("//input[@name='pinCode' and contains(@class,'cmp-wallpaper__input')]");
    WebElement PincodeField = wait.until(ExpectedConditions.presenceOfElementLocated(pincodeInput));

    // Scroll into view and focus
    js.executeScript("arguments[0].scrollIntoView({block:'center'}); arguments[0].focus();", PincodeField);

    // Clear and set value via JS
    js.executeScript(
            "arguments[0].value='';" +
            "arguments[0].value=arguments[1];" +
            "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
            "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
            PincodeField, pincode
    );

    // Wait for value to be set
    wait.until(d -> pincode.equals(PincodeField.getAttribute("value")));

    // Click check button
    WebElement checkButtonPincode = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.cmp-wallpaper__submit") // Adjust if your button class is different
    ));
    js.executeScript("arguments[0].click();", checkButtonPincode);

    // Wait for validation
    By errorMsg = By.cssSelector("div.error-txt");
    wait.until(d -> {
        boolean hasError = !d.findElements(errorMsg).isEmpty() && d.findElement(errorMsg).isDisplayed();
        String classes = PincodeField.getAttribute("class");
        boolean isValid = classes.contains("isValid");
        return hasError || isValid;
    });

    // Assertion
    List<WebElement> errors = driver.findElements(errorMsg);
    if (!errors.isEmpty() && errors.get(0).isDisplayed()) {
        throw new AssertionError("Pincode validation failed: " + errors.get(0).getText());
    }

    System.out.println("Pincode " + pincode + " validated successfully");
}
}
