package pagefunctions;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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

public class Cart_Functionality_Page {
	
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();
	
	// Page Elements
	
	// ------------------- Scenario 1 ------------------------
	
	@FindBy(id = "cart-icon")
	private WebElement cartIcon;
	
	
	
	public  Cart_Functionality_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	public int getCartItemCount() {
	    try {
	        String countValue = cartIcon.getAttribute("data-count");
	        return Integer.parseInt(countValue);
	    } catch (NumberFormatException | NullPointerException e) {
	        return 0; // cart empty
	    }
	}

	public void waitForCartCountToIncrease(int previousCount) {
	    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));
	    wait.until(driver -> getCartItemCount() > previousCount);
	}

	

	public void selectSecondVisibleProduct() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Locator for visible product cards
	    By visibleProductCards = By.xpath(
	            "//div[@id='products-container']//div[contains(@class,'product-info') and not(contains(@style,'display: none'))]"
	    );

	    // 1️.Wait until at least 2 visible products are present
	    wait.until(d -> d.findElements(visibleProductCards).size() >= 2);

	    // 2️.Re-fetch elements from fresh DOM
	    List<WebElement> products = driver.findElements(visibleProductCards);

	    // 3️.Select second product (index 1)
	    WebElement secondProductCard = products.get(1);

	    // 4️.Find clickable element inside product card
	    By clickableProduct = By.xpath(
	            ".//a[contains(@class,'cmp-product__image-link-hover') or contains(@class,'cmp-product__image-link')]"
	    );

	    WebElement productLink = secondProductCard.findElement(clickableProduct);

	    // scroll
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", productLink);
	    wait.until(ExpectedConditions.elementToBeClickable(productLink));
	    js.executeScript("arguments[0].click();", productLink);

	    System.out.println("Second visible product clicked successfully");
	}

}
