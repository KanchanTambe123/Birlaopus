package pagefunctions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class Wishlist_Functionality_Page {
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();
	
	
	
	@FindBy(xpath = "//img[@alt='Favourite']")
	public WebElement HeaderFavouriteIcon;
	
	@FindBy(xpath = "//a[@id='pip-favourite']")
	public WebElement WhishlistIcon;
	
	@FindBy(xpath = "//img[contains(@src,'favourite-filled')]")
	public WebElement filledHeartIcon;

	@FindBy(xpath = "(//img[contains(@src,'favourite-filled')])[1]")
	public WebElement emptyHeartIcon;

	
	public Wishlist_Functionality_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}


	public boolean isProductAddedToWishlist() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//img[contains(@src,'favourite-filled')]")
	        ));
	        return true;
	    } catch (TimeoutException e) {
	        return false;
	    }
	}

	public boolean isProductRemovedFromWishlist() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    return wait.until(driver ->
	        driver.findElements(
	            By.xpath("//div[contains(@class,'profileProductCard')]//div[contains(@class,'product-info')]")
	        ).isEmpty()
	    );
	}
	public void selectProductOptionIfVisible(String optionText) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Wait for full page load
	    wait.until(webDriver ->
	            ((JavascriptExecutor) webDriver)
	                    .executeScript("return document.readyState")
	                    .equals("complete")
	    );

	    // Locate option button
	    By optionButtonLocator = By.xpath("//button[contains(@class,'cmp-product__option-button')]");
	    List<WebElement> optionButtons = driver.findElements(optionButtonLocator);

	    // Button not present → continue journey
	    if (optionButtons.isEmpty()) {
	        System.out.println("Option button not present. Continuing journey...");
	        return;
	    }

	    WebElement optionButton = optionButtons.get(0);

	    //  Button present but not visible → continue journey
	    if (!optionButton.isDisplayed()) {
	        System.out.println("Option button not visible. Continuing journey...");
	        return;
	    }

	    // Scroll to button & wait until visible & clickable
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", optionButton);
	    wait.until(ExpectedConditions.visibilityOf(optionButton));
	    wait.until(ExpectedConditions.elementToBeClickable(optionButton));

	    // Click option button via JS
	    js.executeScript("arguments[0].click();", optionButton);
	 

	    // Wait for options list to open
	    By optionsListLocator = By.xpath("//ul[contains(@class,'cmp-product__options') and not(contains(@class,'d-none'))]");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(optionsListLocator));

	    //  Locate required option
	    By optionLocator = By.xpath("//ul[contains(@class,'cmp-product__options')]//li[.//span[normalize-space()='" + optionText + "']]");
	    List<WebElement> options = driver.findElements(optionLocator);

	    // Option not found → continue journey
	    if (options.isEmpty()) {
	        System.out.println("Option '" + optionText + "' not found. Continuing journey...");
	        return;
	    }

	    WebElement option = options.get(0);

	    // Scroll & wait before clicking the option
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
	    wait.until(ExpectedConditions.visibilityOf(option));
	    wait.until(ExpectedConditions.elementToBeClickable(option));

	    // Click the option via JS
	    js.executeScript("arguments[0].click();", option);

	    System.out.println("Clicked option: " + optionText);
	}



}
