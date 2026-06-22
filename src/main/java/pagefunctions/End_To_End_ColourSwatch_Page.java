package pagefunctions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;  
import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class End_To_End_ColourSwatch_Page {
	
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	//Scenarios 6
	@FindBy(xpath = "//div[contains(@class,'error-txt') and contains(text(),'valid pincode')]")
	public WebElement ErrMessageInvalidPincode;
	
	@FindBy(xpath = "//div[contains(@class,'cmp-product__pincode')]//input[@name='pinCode']")
	public WebElement PincodeField;
	
	@FindBy(xpath = "//form[contains(@class,'cmp-product__delivery-check')]//button[@type='submit']")
	public WebElement PincodeCheckButton;
	

	
	//Scenarios 7
	@FindBy(xpath = "(//input[@name='pinCode']/following-sibling::div[contains(@class,'error-txt')])[4]")
	public WebElement ErrMessageEmptyPincode;
	//Scenarios 8
	@FindBy(xpath =  "//button[contains(@class,'cmp-product__cart-button') and contains(@class,'disabled')]")
	public WebElement AddTocartButtonDisable;

	@FindBy(xpath = "//button[contains(@class,'cmp-product__option-button')]")
	public WebElement productOptionButton;
	
	
	public End_To_End_ColourSwatch_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	
	public void selectColourFromSwatch(String colourName) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    By loader = By.cssSelector("div.section-loader");
	    By changeBtn = By.id("open-select-colour");
	    By searchInput = By.id("search-colours");

	    // Wait loader disappear
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

	    //  FIXED CLICK (Change button)
	    WebElement change = wait.until(ExpectedConditions.elementToBeClickable(changeBtn));

	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", change);

	    try {
	        change.click();   // normal click
	    } catch (Exception e) {
	        js.executeScript("arguments[0].click();", change); // fallback
	    }

	    // Wait loader again
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
	    

	    // Search box wait 
	    WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchInput));

	    search.clear();
	    search.sendKeys(colourName);

	    //  Colour card
	    By colourCard = By.xpath(
	        "//div[contains(@class,'colour-swatch-card') and @data-colorname='" + colourName + "']");

	    WebElement card = wait.until(ExpectedConditions.elementToBeClickable(colourCard));

	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", card);

	    try {
	        card.click();
	        Thread.sleep(2000);
	    } catch (Exception e) {
	        js.executeScript("arguments[0].click();", card);
	    }

	    // Final loader wait
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
	}
	
	public void selectQuantity(int quantity) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
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

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
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

	    // 6️ WAIT AFTER CLICK 
	    // Wait for loader / wishlist state update
	    wait.until(ExpectedConditions.or(
	            ExpectedConditions.invisibilityOfElementLocated(loader),
	            ExpectedConditions.attributeContains(wishlist, "class", "active")
	    ));

	    System.out.println("Wishlist icon clicked and state updated successfully");
	}

	public void enterInvalidPincodeAndCheck(String pincode) throws InterruptedException {
		PincodeField.clear();
		PincodeField.sendKeys(pincode);

	    // Trigger validation
		PincodeField.sendKeys(Keys.TAB);
		
		Thread.sleep(2000);
		 PincodeCheckButton.click();
		

	    
	}
	
	
	
	public void clickTabFromList(String tabName) {

	    By tabsLocator = By.xpath("//ol[@role='tablist']//li[@role='tab']");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

	    List<WebElement> tabs = driver.findElements(
	    	    By.xpath("//ol[@role='tablist']//li[@role='tab']")
	    	);

	    boolean isFound = false;

	    for (int i = 0; i < tabs.size(); i++) {

	        // Re-fetch elements to avoid stale element issue
	        List<WebElement> updatedTabs = driver.findElements(tabsLocator);

	        WebElement tab = updatedTabs.get(i);
	        String name = tab.getText().trim();

	        if (name.equalsIgnoreCase(tabName)) {

	            wait.until(ExpectedConditions.elementToBeClickable(tab));

	            // Normal click
	            try {
	                tab.click();
	            } catch (Exception e) {
	                // Fallback JS click
	                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
	            }

	            System.out.println("Clicked Tab: " + name);
	            isFound = true;
	            break;
	        }
	    }

	    if (!isFound) {
	        throw new RuntimeException("Tab not found: " + tabName);
	    }
	}
	public void selectOptionFromWishlist(String optionText) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

	    // Step 1: Get option button
	    WebElement optionBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
	        By.xpath("(//button[contains(@class,'cmp-product__option-button')])[1]")
	    ));

	    // Scroll to button
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", optionBtn);

	    // Step 2: FORCE CLICK (important)
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionBtn);

	    // Step 3: Wait for dropdown using size > 0 (more reliable than visibility)
	    wait.until(driver -> driver.findElements(
	        By.xpath("//ul[contains(@class,'cmp-product__options')]")
	    ).size() > 0);

	    // Step 4: Now click option directly (no need to wait for full visibility)
	    WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(
	        By.xpath("//span[normalize-space()='" + optionText + "']")
	    ));

	    // Scroll to option
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);

	    // Step 5: Click option
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
	}
	
	
	public boolean isProductRemovedFromWishlist() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

	    return wait.until(driver ->
	        driver.findElements(
	            By.xpath("//div[contains(@class,'profile-swatch__info-swatch')]")
	        ).isEmpty()
	    );
	}
}
