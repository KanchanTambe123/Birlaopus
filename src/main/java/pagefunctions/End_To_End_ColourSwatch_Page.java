package pagefunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class End_To_End_ColourSwatch_Page {
	
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	
	
	
	
	public End_To_End_ColourSwatch_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	
	public void selectColourFromSwatch(String colourName) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    By loader = By.cssSelector("div.section-loader");
	    By changeBtn = By.id("open-select-colour");
	    By searchInput = By.id("search-colours");

	    // Wait 
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

	    // Click Change
	    WebElement change = wait.until(ExpectedConditions.presenceOfElementLocated(changeBtn));
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", change);
	    js.executeScript("arguments[0].click();", change);

	    // wait
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

	    // wait
	    WebElement search = wait.until(
	            ExpectedConditions.presenceOfElementLocated(searchInput));

	    // wait
	    wait.until(ExpectedConditions.and(
	            ExpectedConditions.visibilityOf(search),
	            ExpectedConditions.elementToBeClickable(search)
	    ));

	   //wait
	    search.clear();
	    search.sendKeys(colourName);

	    //wait 
	    By colourCard = By.xpath(
	            "//div[contains(@class,'colour-swatch-card') and @data-colorname='" + colourName + "']");

	    WebElement card = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(colourCard));

	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", card);
	    js.executeScript("arguments[0].click();", card);

	    // Final loader wait after selection
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
	}
	
	public void selectQuantity(int quantity) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    By loader = By.cssSelector("div.section-loader");

	   //wait
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

	    // wait quantity
	    WebElement qtyActionDiv = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.cssSelector(".cmp-product__actions--mini")));

	    // increment button & quantity span
	    WebElement incrementBtn = qtyActionDiv.findElement(
	            By.cssSelector(".cmp-product__quantity--increment"));

	    WebElement qtySpan = qtyActionDiv.findElement(
	            By.cssSelector(".cmp-product__quantity"));

	    
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", qtyActionDiv);

	 
	    int currentQty = Integer.parseInt(qtySpan.getText().trim());

	
	    for (int i = currentQty; i < quantity; i++) {

	        js.executeScript("arguments[0].click();", incrementBtn);

	        // Fire events so application JS detects change
	        js.executeScript(
	                "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));", qtySpan);
	        js.executeScript(
	                "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", qtySpan);

	        int expectedQty = i + 1;
	        wait.until(d ->
	                Integer.parseInt(qtySpan.getText().trim()) == expectedQty);
	    }

	    System.out.println("Quantity selected: " + quantity);
	}

	public void clickWishlistIcon() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    By loader = By.cssSelector("div.section-loader");
	    By wishlistIcon = By.cssSelector("a.details-favourite");

	    // 1️⃣ Wait for initial loader to disappear
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

	    // 2️⃣ Locate wishlist icon
	    WebElement wishlist = wait.until(
	            ExpectedConditions.presenceOfElementLocated(wishlistIcon));

	    // 3️⃣ Scroll into view
	    js.executeScript(
	            "arguments[0].scrollIntoView({block:'center'});", wishlist);

	    // 4️⃣ Wait until clickable
	    wait.until(ExpectedConditions.elementToBeClickable(wishlist));

	    // 5️⃣ Click wishlist icon
	    js.executeScript("arguments[0].click();", wishlist);

	    // 6️⃣ WAIT AFTER CLICK (VERY IMPORTANT)
	    // Wait for loader / wishlist state update
	    wait.until(ExpectedConditions.or(
	            ExpectedConditions.invisibilityOfElementLocated(loader),
	            ExpectedConditions.attributeContains(wishlist, "class", "active")
	    ));

	    System.out.println("Wishlist icon clicked and state updated successfully");
	}

	
	
}
