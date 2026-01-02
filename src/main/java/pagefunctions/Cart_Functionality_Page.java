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

	    // Locate all product cards
	    List<WebElement> products = wait.until(
	        ExpectedConditions.presenceOfAllElementsLocatedBy(
	            By.xpath("//div[@id='products-container']//div[contains(@class,'product-info')]")
	        )
	    );

	    // Filter only visible products
	    List<WebElement> visibleProducts = products.stream()
	        .filter(WebElement::isDisplayed)
	        .collect(Collectors.toList());

	    if (visibleProducts.size() < 2) {
	        throw new RuntimeException("Less than 2 visible products available on the page");
	    }

	    WebElement secondProductCard = visibleProducts.get(1);

	    // Locate the actual clickable element inside the product (link or image)
	    WebElement clickableElement = secondProductCard.findElement(
	        By.xpath(".//a[contains(@class,'cmp-product__image-link-hover')] | .//a[contains(@class,'cmp-product__image-link')]")
	    );

	    // Scroll element into view
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].scrollIntoView({block:'center'});", clickableElement
	    );

	    // Wait
	    wait.until(ExpectedConditions.elementToBeClickable(clickableElement));

	    // Click 
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].click();", clickableElement
	    );
	}

}
