package pagefunctions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class End_to_End_Flow_Page {
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	public End_to_End_Flow_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	

	// Page Elements 	//Scenarios 1
	@FindBy(xpath = "//img[@alt='Paint Product']")
	public WebElement ProductImages;

	@FindBy(xpath = "(//img[@alt='login-close'])[2]")
	public WebElement closeiconLoginPopup;

	@FindBy(xpath = "//a[contains(text(),'Products')]")
	public WebElement ProductTitle;

	@FindBy(css = "div.shop-now-button a.cmp-button")
	public WebElement shopNowButton;

	@FindBy(xpath = "//div[@class='popular-colours']")
	public WebElement ProductColourSection;

	@FindBy(xpath = "//div[contains(@class,'cmp-product__pincode')]//input[@name='pinCode']")
	public WebElement PincodeField;

	@FindBy(xpath = "//button[normalize-space()='Check']")
	public WebElement checkButtonPincode;

	@FindBy(xpath = "//button[contains(@class,'cmp-product__cart')]")
	public WebElement AddToCartButton;

	@FindBy(xpath = "//a[contains(@class,'cmp-cart-product__action-link') and contains(text(),'View cart')]")
	public WebElement ViewcartCheckoutButton;

	@FindBy(xpath = "//div[@class='cmp-product__selection-step' and .//span[@class='step-title' and normalize-space()='Select quantity']]")
	public WebElement SelectQuantityTitle;

    @FindBy(css = "div.cart__card-delete")
	public WebElement deleteIcon;

	@FindBy(id = "cart-icon")
	public WebElement cartIcon;
	
	@FindBy(xpath = "//button[@id=\"cart-submit\"]")
	public WebElement ProceedenteraddressButton;

	@FindBy(xpath = "//button[@id='address-submit']")
	public WebElement ProceedtoshipmentaddressButton;

	@FindBy(css = "div.cart__card-add-minus")
	private List<WebElement> cartItemList;
	
	// shipping details//guest user//new address
	
    @FindBy(xpath = "//button[normalize-space()='Edit']")
	public WebElement ShippingEditButton;

	@FindBy(xpath = "(//input[@id='shipping-first-name'])[1]")
	public WebElement ShippingFirstNameInputFiled;

	@FindBy(xpath = "(//input[@id='shipping-last-name'])[1]")
	public WebElement ShippingLastNameInputFiled;

	@FindBy(xpath = "//input[@id='shipping-email']")
	public WebElement ShippingEmailInputFiled;

	@FindBy(xpath = "(//input[@id='shipping-contact'])[1]")
	public WebElement ShippingMobileNumberInputFiled;

	@FindBy(xpath = "(//input[@id='address-first'])[1]")
	public WebElement ShippingAddressInputFiled;

	@FindBy(xpath = "(//input[@id='shipping-pincode'])[1]")
	public WebElement ShippingPincodeInputFiled;
	
	//exsiting user//save address
	@FindBy(xpath = "//input[@id='firstName']")
	public WebElement FirstNameInputFiled;

	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement LastNameInputFiled;

    @FindBy(xpath = "//input[@id='contactNumberInput']")
	public WebElement MobileNumberInputFiled;

	@FindBy(xpath = "//input[@id='addressOneInput']")
	public WebElement AddressInputFiled;
	
	@FindBy(xpath = "//button[@class='black-submit-form-button']")
	public WebElement SubmitButtonForSaveAdress;

    @FindBy(xpath = "//button[@id='address-submit']")
	public WebElement ProceedShipmentbutton;
	
	@FindBy(id = "shipping-submit")
	public WebElement ProceedPaymentbutton;
	
	@FindBy(xpath = "//div[contains(@class,'cmp-teaser__description')]//p[normalize-space()='To proceed to checkout, we request a minimum cart value of INR 999.']")
	public WebElement minimumCartValueMessage;
	
	
	@FindBy(xpath = "(//div[contains(@class,'cmp-teaser__action-container')]//a[normalize-space()='Close'])[1]")
	public WebElement PopupClose;
	


	
	//Scenarios 2
	
		@FindBy(xpath = "//div[@class='cmp-text-button']//span[text()='Continue Shopping']")
		public WebElement ContinueShoppingButton;
		

		@FindBy(xpath = "//span[contains(@class,'text-title') and text()='Our Product Range']")
		public WebElement OurProductRangeTitle;

		//span[contains(@class,'text-title') and text()='Our Product Range']
	
	

	// Click first product
	public WebElement firstProduct() {
		List<WebElement> products = driver
				.findElements(By.cssSelector("div.products-info-cards-wrap div.product-info"));
		return products.get(0);
	}

	// main navigation
	public void hoverAndClickProduct(String mainNavName, String tabName, String productName)
			throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

		// 1️.Hover over main navigation
		List<WebElement> mainNavItems = driver.findElements(By.xpath("//ul[@class='cmp-navigation__group']/li"));
		boolean mainNavFound = false;
		for (WebElement navItem : mainNavItems) {
			if (navItem.getText().trim().equalsIgnoreCase(mainNavName)) {
				js.executeScript("arguments[0].scrollIntoView(true);", navItem);
				Actions actions = new Actions(driver);
				actions.moveToElement(navItem).perform();
				Thread.sleep(2000); // short wait for sub-menu
				mainNavFound = true;
				break;
			}
		}
		if (!mainNavFound) {
			throw new RuntimeException("Main navigation item not found: " + mainNavName);
		}

		// 2️.Hover over tab/sub-navigation
		List<WebElement> tabNavItems = driver.findElements(By.xpath("//li[@class='navigation-tab-list__item']/a"));
		boolean tabFound = false;
		for (WebElement tab : tabNavItems) {
			if (tab.getText().trim().equalsIgnoreCase(tabName)) {
				js.executeScript("arguments[0].scrollIntoView(true);", tab);
				Actions actions = new Actions(driver);
				actions.moveToElement(tab).perform();
				Thread.sleep(2000); // products to load
				tabFound = true;
				break;
			}
		}
		if (!tabFound) {
			throw new RuntimeException("Tab navigation item not found: " + tabName);
		}

		// 3️.Click the product by name
		List<WebElement> products = driver
				.findElements(By.cssSelector("div.products-info-cards-wrap div.product-info"));
		boolean productClicked = false;
		for (WebElement product : products) {
			WebElement title = product.findElement(By.cssSelector("h4.cmp-product__title-text"));
			if (title.getText().trim().equalsIgnoreCase(productName)) {
				WebElement productLink = product.findElement(By.cssSelector("a.cmp-product__image-link"));
				wait.until(ExpectedConditions.elementToBeClickable(productLink));
				js.executeScript("arguments[0].scrollIntoView(true);", productLink);
				System.out.println("Product Name : " + title.getText());
				System.out.println("Product Href : " + productLink.getAttribute("href"));
				productLink.click();
				System.out.println("Current URL : " + driver.getCurrentUrl());

				System.out.println("Clicked product: " + productName);
				productClicked = true;
				break;
				
			}
		}
		if (!productClicked) {
			throw new RuntimeException("Product not found: " + productName);
		}

          Thread.sleep(3000);
	
		
		
	}
	
	
	
	
	
	
	

	public void selectColorByName(String colorName) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	    By colorLocator = By.xpath("//span[@data-colorname='" + colorName + "']");

	    WebElement color = wait.until(
	            ExpectedConditions.elementToBeClickable(colorLocator));

	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({block:'center'});", color);

	    // Real mouse click
	    Actions actions = new Actions(driver);
	    actions.moveToElement(color)
	           .pause(Duration.ofMillis(500))
	           .click()
	           .perform();

	    // Wait until selected colour changes
	    wait.until(ExpectedConditions.textToBePresentInElementLocated(
	            By.cssSelector(".selected-colour__title"), colorName));

	    // Wait until quantity section has at least one pack
	    wait.until(driver ->
	            driver.findElements(By.cssSelector(".cmp-product--mini")).size() > 0);

	    System.out.println("Pack Count : "
	            + driver.findElements(By.cssSelector(".cmp-product--mini")).size());

	    driver.findElements(By.cssSelector(".cmp-product--mini"))
	            .forEach(e -> System.out.println(
	                    "Pack : " + e.getAttribute("data-litre")));
	}

	// Select quantity
	public void selectQuantity(String litrePack, int quantity) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	    By packLocator = By.xpath("//div[@data-litre='" + litrePack + "']");

	    WebElement packDiv = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(packLocator));

	    WebElement incrementBtn = packDiv.findElement(
	            By.cssSelector(".cmp-product__quantity--increment"));

	    for (int i = 0; i < quantity; i++) {
	        wait.until(ExpectedConditions.elementToBeClickable(incrementBtn));
	        incrementBtn.click();
	    }
	}

	public void enterPincodeAndCheck(String pincode) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // 1️. Wait for any overlay / container to settle
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(
	            By.cssSelector(".page-loader, .loading, .shimmer")
	    ));

	    // 2️.Scroll input into view
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", PincodeField);

	    // 3.click
	    js.executeScript("arguments[0].focus();", PincodeField);

	    // 4️.Clear & enter pincode via JS 
	    js.executeScript(
	        "arguments[0].value='';" +
	        "arguments[0].value=arguments[1];" +
	        "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
	        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
	        PincodeField, pincode
	    );

	    // 5️.Validate value is set
	    wait.until(d -> pincode.equals(PincodeField.getAttribute("value")));

	    // 6️.Click Check button via JS
	    wait.until(ExpectedConditions.visibilityOf(checkButtonPincode));
	    js.executeScript("arguments[0].click();", checkButtonPincode);

	    // 7️.Wait for validation result
	    By errorMsg = By.cssSelector("div.error-txt");

	    wait.until(d -> {
	        String classes = PincodeField.getAttribute("class");
	        boolean isValid = classes.contains("isValid");

	        boolean hasError = !d.findElements(errorMsg).isEmpty()
	                && d.findElement(errorMsg).isDisplayed();

	        return isValid || hasError;
	    });

	    // 8.Assertion
	    List<WebElement> errors = driver.findElements(errorMsg);
	    if (!errors.isEmpty() && errors.get(0).isDisplayed()) {
	        throw new AssertionError("Pincode validation failed: " + errors.get(0).getText());
	    }

	    System.out.println(" Pincode " + pincode + " validated successfully");
	}


	// add to cart
	public void clickAddToCart() {

		By addToCartBtn = By.xpath("//button[contains(@class,'cmp-product__cart')]");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

		WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartBtn));

		// Wait until button becomes enabled
		wait.until(driver -> addToCart.isEnabled());

		js.scrollUntilElementVisible(addToCart);
		js.jsClickWithWait(addToCart);

		System.out.println("Add to Cart button clicked");
	}

	// update qty
	public int updateCartQuantity(int desiredQty) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

		while (true) {

			WebElement cartItem = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cart__card-add-minus")));

			WebElement qtyText = cartItem.findElement(By.cssSelector("span.cart__card-qty"));
			WebElement addBtn = cartItem.findElement(By.cssSelector("span.cart__card-add"));
			WebElement minusBtn = cartItem.findElement(By.cssSelector("span.cart__card-minus"));

			int currentQty = Integer.parseInt(qtyText.getText().trim());

			if (currentQty == desiredQty) {
				return currentQty; // final cart quantity
			}

			WebElement btnToClick = currentQty < desiredQty ? addBtn : minusBtn;
			safeClick(btnToClick);

			int previousQty = currentQty;

			// Wait until quantity text updates
			wait.until(driver -> Integer.parseInt(
					driver.findElement(By.cssSelector("span.cart__card-qty")).getText().trim()) != previousQty);

			// (increment/decrement)
			try {
				Thread.sleep(2500); // 1-sec each click
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void safeClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();",
					element);
		}
	}

	// remove the cart product
	public void removeProductFromCartIfAvailable() {

		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

		By deleteIcon = By.cssSelector("div.cart__card-delete");

		try {
			// Wait until delete icon is
			WebElement deleteBtn = wait.until(ExpectedConditions.presenceOfElementLocated(deleteIcon));

			wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));

			try {
				deleteBtn.click();
			} catch (ElementClickInterceptedException e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
			}
			 wait.until(ExpectedConditions.invisibilityOf(deleteBtn));

		        System.out.println("Product removed from cart successfully");
			

		} catch (TimeoutException e) {
			// Delete icon
			System.out.println("Cart is empty, continuing flow");
		}
	}

	// apply coupoun code
	public void applyCouponIfAvailable() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    try {

	        // Check if Apply Coupon button is available
	        List<WebElement> couponButtons = driver.findElements(
	                By.cssSelector("button.apply__coupons-btn"));

	        if (couponButtons.isEmpty()) {
	            ExtentCucumberAdapter.addTestStepLog("Coupon section is not available. Continuing the flow.");
	            return;
	        }

	        WebElement couponBtn = couponButtons.get(0);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", couponBtn);

	        // Get available coupons
	        List<WebElement> coupons = driver.findElements(
	                By.cssSelector("input.apply__coupons-card-checkbox"));

	        if (!coupons.isEmpty()) {

	            // Apply first coupon
	            WebElement coupon = coupons.get(0);
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", coupon);

	            WebElement applyBtn = wait.until(
	                    ExpectedConditions.elementToBeClickable(By.id("applyCouponBtn")));
	            applyBtn.click();

	            WebElement appliedCoupon = wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(
	                            By.cssSelector("span.applied__coupon-percent-name")));

	            String couponName = appliedCoupon.getText().trim();

	            ExtentCucumberAdapter.addTestStepLog("Applied Coupon : " + couponName);
	            System.out.println("Applied Coupon : " + couponName);

	        } else {

	            // No coupon available - Close popup
	            closeCouponPopup();

	            ExtentCucumberAdapter.addTestStepLog("No coupon available. Closed popup and continuing flow.");
	            System.out.println("No coupon available. Closed popup.");

	        }

	    } catch (Exception e) {

	        // In case popup is open but something failed
	        closeCouponPopup();

	        ExtentCucumberAdapter.addTestStepLog("Unable to apply coupon. Closed popup and continuing flow.");
	        System.out.println("Coupon not applied. Continuing flow.");
	    }
	}

	private void closeCouponPopup() {

	    try {
	        WebElement closeBtn = driver.findElement(
	                By.cssSelector("div.apply__coupons-close"));

	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);

	    } catch (Exception ignored) {
	        // Popup already closed
	    }
	}
	// order summary
	public int getFinalOrderSummaryQuantity() {

	    WebElement summaryQtyElement = new WebDriverWait(driver, Duration.ofSeconds(80))
	            .until(ExpectedConditions.visibilityOfElementLocated(
	                    By.cssSelector("span.summary__card-qty-desp")));

	    int finalQty = Integer.parseInt(summaryQtyElement.getAttribute("data-qty"));

	    // Extent Report Log
	    ExtentCucumberAdapter.getCurrentStep().log(
	            Status.INFO,
	            "Final Order Summary Quantity: " + finalQty
	    );

	    System.out.println("Final Order Summary Quantity: " + finalQty);

	    return finalQty;
	}

	// total
	public void verifyTotalPayableAmount() {

	    WebDriver driver = DriverManager.getDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

	    // Subtotal (Excl. Tax)
	    double subtotal = extractAmount(wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.cssSelector(".cart__subtotal-value")))
	            .getText());

	    // Taxes
	    double tax = extractAmount(wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.cssSelector(".cart__taxes-value")))
	            .getText());

	    // Coupon Discount (extractAmount returns negative value, e.g. -1017)
	    double discount = 0.0;

	    List<WebElement> discountEls = driver.findElements(
	            By.cssSelector(".cart__coupon-discount-value"));

	    if (!discountEls.isEmpty() && discountEls.get(0).isDisplayed()) {
	        String discountText = discountEls.get(0).getText().trim();

	        if (!discountText.equals("-") && !discountText.isEmpty()) {
	            discount = extractAmount(discountText);
	        }
	    }

	    // Total
	    By totalLocator = By.cssSelector(".cart__total");

	    wait.until(ExpectedConditions.visibilityOfElementLocated(totalLocator));

	    double displayedTotal = extractAmount(
	            driver.findElement(totalLocator).getText());

	    // Correct Calculation
	    double expectedTotal = subtotal+tax+discount;//If discount = -1017 -use:

	    long expected = Math.round(expectedTotal);
	    long actual = Math.round(displayedTotal);

	    // Console Logs
	    System.out.println("Subtotal: " + subtotal);
	    System.out.println("Discount: " + discount);
	    System.out.println("Tax: " + tax);
	    System.out.println("Expected: " + expectedTotal);
	    System.out.println("Displayed: " + displayedTotal);

	    // Extent Logs
	    ExtentCucumberAdapter.getCurrentStep().log(
	            Status.INFO,
	            "Subtotal (Excl. Tax): ₹" + subtotal);

	    ExtentCucumberAdapter.getCurrentStep().log(
	            Status.INFO,
	            "Coupon Discount: ₹" + discount);

	    ExtentCucumberAdapter.getCurrentStep().log(
	            Status.INFO,
	            "Taxes: ₹" + tax);

	    ExtentCucumberAdapter.getCurrentStep().log(
	            Status.INFO,
	            "Expected Total: ₹" + expected);

	    ExtentCucumberAdapter.getCurrentStep().log(
	            Status.INFO,
	            "Displayed Total: ₹" + actual);

	    Assert.assertEquals(actual, expected,
	            "Total payable amount calculation mismatch.");

	    ExtentCucumberAdapter.getCurrentStep().log(
	            Status.PASS,
	            "Total payable amount verified successfully.");

	    System.out.println("Total payable amount verified successfully.");
	}
	

	public double extractAmount(String text) {

		if (text == null || text.trim().isEmpty() || text.trim().equals("-")) {
		    return 0.0;
		}

		try {
		    text = text.trim();

		    // Preserve negative sign if present
		    boolean isNegative = text.contains("-");

		    // Remove everything except digits and decimal
		    text = text.replaceAll("[^0-9.]", "");

		    if (text.isEmpty()) {
		        return 0.0;
		    }

		    double value = Double.parseDouble(text);

		    return isNegative ? -value : value;

		} catch (Exception e) {
		    System.out.println("Error parsing amount: " + text);
		    return 0.0;
		}
	

		}


	public void selectCategory(String categoryName) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

	    // Locate the category link by span text
	    WebElement categoryLink = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//span[@class='cmp-text-btn-text' and text()='" + categoryName + "']/parent::a")
	        )
	    );

	    // Scroll element into view
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", categoryLink);

	    // Optional: wait for any overlay/popups to disappear
	    try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

	    // Click using JavaScript to avoid interception
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryLink);
	}

	
	//pending
	public void scrollAndClickValidShopNow() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    List<WebElement> shopNowButtons = wait.until(
	            ExpectedConditions.presenceOfAllElementsLocatedBy(
	                    By.xpath("//a[contains(@class,'cmp-shop-now-cta')]")
	            )
	    );

	    for (int i = 0; i < shopNowButtons.size(); i++) {

	        try {
	            // Re-fetch elements to avoid StaleElementReferenceException
	            shopNowButtons = driver.findElements(
	                    By.xpath("//a[contains(@class,'cmp-shop-now-cta')]")
	            );

	            WebElement shopNow = shopNowButtons.get(i);

	            js.executeScript("arguments[0].scrollIntoView({block:'center'});", shopNow);
	            wait.until(ExpectedConditions.elementToBeClickable(shopNow));
	            js.executeScript("arguments[0].click();", shopNow);

	            // wait for page load
	            Thread.sleep(3000);

	            // Check for 404 image
	            boolean is404 = driver.findElements(
	                    By.xpath("//img[contains(@alt,'404')]")
	            ).size() > 0;

	            if (is404) {
	                System.out.println("404 page found for Shop Now index: " + i);
	                driver.navigate().back();
	                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                        By.xpath("//a[contains(@class,'cmp-shop-now-cta')]")
	                ));
	                continue;
	            }

	            // Valid product page found
	            System.out.println("Valid product page opened for Shop Now index: " + i);
	            break;

	        } catch (Exception e) {
	            System.out.println("Error while clicking Shop Now index: " + i);
	            e.printStackTrace();
	        }
	    }
	}

	
}
