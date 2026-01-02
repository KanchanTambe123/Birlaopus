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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		// 1️.Hover over main navigation
		List<WebElement> mainNavItems = driver.findElements(By.xpath("//ul[@class='cmp-navigation__group']/li"));
		boolean mainNavFound = false;
		for (WebElement navItem : mainNavItems) {
			if (navItem.getText().trim().equalsIgnoreCase(mainNavName)) {
				js.executeScript("arguments[0].scrollIntoView(true);", navItem);
				Actions actions = new Actions(driver);
				actions.moveToElement(navItem).perform();
				Thread.sleep(1000); // short wait for sub-menu
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
				Thread.sleep(1000); // products to load
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

	// Select color by name
	public void selectColorByName(String colorName) {
		// Locate the color element
		By colorLocator = By.xpath("//div[contains(@class,'popular-colours')]//span[@data-colorname='" + colorName
				+ "' and contains(@class,'colour-circle')]");

		List<WebElement> colors = driver.findElements(colorLocator);

		if (colors.isEmpty()) {
			throw new AssertionError("Color not found: " + colorName);
		}

		WebElement colorCircle = colors.get(0);

		// Scroll
		js.scrollUntilElementVisible(colorCircle);
		wait.waitForElementToBeVisible(colorCircle, 5);

		// Click
		// js.jsClickWithWait(colorCircle);

		js.jsClickWithWait(colorCircle);
		js.executeScript("arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", colorCircle);

		System.out.println("Selected color: " + colorName);
	}

	// Select quantity

	public void selectQuantity(String litrePack, int quantity) {
		By packLocator = By.xpath("//div[contains(@class,'cmp-product--mini') and @data-litre='" + litrePack + "']");
		WebElement packDiv = driver.findElements(packLocator).stream().findFirst()
				.orElseThrow(() -> new AssertionError("Pack not found: " + litrePack + " Ltr"));

		// Scroll into view
		js.scrollUntilElementVisible(packDiv);
		wait.waitForElementToBeVisible(packDiv, 5);

		WebElement incrementBtn = packDiv.findElement(By.cssSelector(".cmp-product__quantity--increment"));
		WebElement qtySpan = packDiv.findElement(By.cssSelector(".cmp-product__quantity"));

		JavascriptExecutor jsExec = (JavascriptExecutor) driver;

		for (int i = 0; i < quantity; i++) {
			// Click increment button
			js.jsClickWithWait(incrementBtn);

			// Trigger change/input events so page JS detects quantity change
			jsExec.executeScript("arguments[0].dispatchEvent(new Event('input', {bubbles:true}));", qtySpan);
			jsExec.executeScript("arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", qtySpan);

			// Wait until quantity updates visually
			int expectedQty = i + 1;
			WebDriverWait waitQty = new WebDriverWait(driver, Duration.ofSeconds(10));
			waitQty.until(d -> Integer.parseInt(qtySpan.getText()) == expectedQty);
		}

		System.out.println("Selected " + quantity + " unit(s) of " + litrePack + " Ltr pack");
	}

	public void enterPincodeAndCheck(String pincode) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    JavascriptExecutor jsExec = (JavascriptExecutor) driver;

	    // Scroll to Pincode field
	    jsExec.executeScript("arguments[0].scrollIntoView(true);", PincodeField);

	    // Wait and click
	    wait.until(ExpectedConditions.elementToBeClickable(PincodeField)).click();

	    // Clear existing value
	    jsExec.executeScript("arguments[0].value='';", PincodeField);

	    // Enter pincode via JS and trigger input event
	    jsExec.executeScript(
	        "arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
	        PincodeField, pincode
	    );

	    // Move focus out to trigger any change event
	    PincodeField.sendKeys(Keys.TAB);

	    // Wait until value is set correctly
	    wait.until(driver -> pincode.equals(PincodeField.getAttribute("value")));

	    // Click the Check button via JS
	    wait.until(ExpectedConditions.elementToBeClickable(checkButtonPincode));
	    jsExec.executeScript("arguments[0].click();", checkButtonPincode);

	    // Locator for error message
	    By errorMsgLocator = By.cssSelector("div.error-txt");

	    // Wait for either validation success or error message
	    wait.until(driver -> {
	        String classes = PincodeField.getAttribute("class");
	        boolean isValid = classes.contains("isValid");

	        List<WebElement> errorElems = driver.findElements(errorMsgLocator);
	        boolean hasError = !errorElems.isEmpty() && errorElems.get(0).isDisplayed()
	                           && !errorElems.get(0).getText().isEmpty();
	        return isValid || hasError;
	    });

	    // Check for error and throw if exists
	    List<WebElement> errorElems = driver.findElements(errorMsgLocator);
	    if (!errorElems.isEmpty() && errorElems.get(0).isDisplayed()) {
	        throw new AssertionError("Pincode validation failed: " + errorElems.get(0).getText());
	    }

	    System.out.println("Pincode " + pincode + " entered and validated successfully.");
	}

	// add to cart
	public void clickAddToCart() {

		By addToCartBtn = By.xpath("//button[contains(@class,'cmp-product__cart')]");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartBtn));

		// Wait until button becomes enabled
		wait.until(driver -> addToCart.isEnabled());

		js.scrollUntilElementVisible(addToCart);
		js.jsClickWithWait(addToCart);

		System.out.println("Add to Cart button clicked");
	}

	// update qty
	public int updateCartQuantity(int desiredQty) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
				Thread.sleep(1000); // 1-sec each click
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void safeClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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
	public void applyCouponIfAvailable() throws InterruptedException {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // Wait for the coupon button to be visible and clickable
	    WebElement couponBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.cssSelector("button.apply__coupons-btn")));
	    wait.until(ExpectedConditions.elementToBeClickable(couponBtn));

	    // Scroll into view and click safely
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", couponBtn);
	    couponBtn.click();

	    Thread.sleep(2000); 

	    // Fetch all available coupons
	    List<WebElement> coupons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	            By.cssSelector("input.apply__coupons-card-checkbox")));

	    if (!coupons.isEmpty()) {
	        WebElement coupon = coupons.get(0);

	        // Scroll and click
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", coupon);
	        coupon.click();
	        Thread.sleep(2000);

	        // Final Apply button
	        WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("applyCouponBtn")));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", applyBtn);
	        applyBtn.click();

	        Thread.sleep(2000);
	        System.out.println("Coupon applied successfully");
	    } else {
	        System.out.println("No coupons available, continuing flow");
	    }
	}

	// order summary
	public int getFinalOrderSummaryQuantity() {

		WebElement summaryQtyElement = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.summary__card-qty-desp")));

		return Integer.parseInt(summaryQtyElement.getAttribute("data-qty"));
	}

	// total
	public void verifyTotalPayableAmount() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Subtotal (Excl. Tax)
	    double subtotal = extractAmount(
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.cart__subtotal-value")))
	                    .getText());

	    // Coupon Discount (if visible)
	    double discount = 0;
	    List<WebElement> discountRow = driver.findElements(By.id("discount"));

	    if (!discountRow.isEmpty() && discountRow.get(0).isDisplayed()) {
	        discount = extractAmount(
	                discountRow.get(0).findElement(By.cssSelector("span.cart__coupon-discount-value")).getText());
	    }

	    // Taxes
	    double taxes = extractAmount(
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.cart__taxes-value")))
	                    .getText());

	    // Displayed Total
	    double displayedTotal = extractAmount(wait
	            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.cart__total"))).getText());

	    // Expected Total = Subtotal + Taxes − Discount
	    double expectedTotal = subtotal + taxes - discount;

	
	    long expectedDisplayed = (long) expectedTotal;
	    long actualDisplayed = (long) displayedTotal;

	    // Assertion
	    Assert.assertEquals(actualDisplayed, expectedDisplayed, "Total payable amount is incorrectly calculated");

	    System.out.println("Total payable amount verified successfully: ₹" + actualDisplayed);
	}

	private double extractAmount(String amountText) {
	    //  digits and decimal
	    String cleanText = amountText.replaceAll("[^0-9.]", "");
	    return Double.parseDouble(cleanText);
	}
	


	public void selectCategory(String categoryName) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

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

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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

	            // ⏳ wait for page load
	            Thread.sleep(3000);

	            // 🔍 Check for 404 image
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

	            // ✅ Valid product page found
	            System.out.println("Valid product page opened for Shop Now index: " + i);
	            break;

	        } catch (Exception e) {
	            System.out.println("Error while clicking Shop Now index: " + i);
	            e.printStackTrace();
	        }
	    }
	}


}
