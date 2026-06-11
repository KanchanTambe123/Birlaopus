package pagefunctions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class Compare_Product_Functionality_Page {

	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	// Page Elements

	// ------------------- Scenario 1 ------------------------

	@FindBy(id = "pip-compare")
	public WebElement compareIcon;

	@FindBy(css = ".compare-card .compare-para-txt")
	public List<WebElement> comparePopupProductNames;

	@FindBy(css = ".cmp-compare-name")
	public List<WebElement> comparePageProductNames;

	@FindBy(xpath = "//a[contains(@class,'compare-btn-an') and text()='Compare']")
	public WebElement CompareButtonPopupPage;

	public Compare_Product_Functionality_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[contains(@class,'text-title')]")
	public WebElement productTitle;

	public String getProductName() {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(productTitle));
		return productTitle.getText().trim();
	}

           //on compare counter product count
	public void VerifyCompareCountOnCompareCounter(String expectedCount) {

		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.compare-card")));

		// fetch cards
		List<WebElement> compareCards = driver.findElements(By.cssSelector("div.compare-card"));

		int actualCount = compareCards.size();
		int expected = Integer.parseInt(expectedCount);

		System.out.println("Compare Counter | Expected: " + expected + " | Actual: " + actualCount);

		if (actualCount != expected) {
			throw new AssertionError(" Compare count mismatch! Expected: " + expected + ", but found: " + actualCount);
		}

		System.out.println(" Compare counter validation successful.");
	}

          //on compare counter titles
	public void verifyProductsOnCompareCounter(String firstProduct, String secondProduct) {

		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Wait for compare product names
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p.compare-para-txt")));

		// Collect product names
		List<WebElement> titles = driver.findElements(By.cssSelector("p.compare-para-txt"));

		List<String> names = titles.stream().map(e -> e.getText().trim()).collect(Collectors.toList());

		System.out.println("Compare Page Products: " + names);

		// Validate both products
		if (!names.contains(firstProduct) || !names.contains(secondProduct)) {
			throw new AssertionError("Compare Page Missing Product(s)! Expected: " + firstProduct + ", " + secondProduct
					+ " | Found: " + names);
		}

		System.out.println("Both products found on Compare Page.");
	}

	public List<String> getComparedProductNames() {

		List<WebElement> cards = driver
				.findElements(By.cssSelector("section.compare-popup .compare-body-cards .product-name"));

		return cards.stream().map(WebElement::getText).collect(Collectors.toList());
	}

          //compare counter
	public void clickCompareButton() throws InterruptedException {
		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		By compareButton = By.xpath("//a[@class='compare-popup-btn-title']");

		wait.until(ExpectedConditions.visibilityOfElementLocated(compareButton));

		WebElement btn = driver.findElement(compareButton);

		// fully opened
		Thread.sleep(800);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

		Thread.sleep(500);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
	}

     //on compare counter
	public void removeProductFromCompare(String productName) {
		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		// All product cards on compare page
		List<WebElement> productCards = driver.findElements(By.cssSelector(".compare-card"));

		boolean productFound = false;

		for (WebElement card : productCards) {

			String name = card.findElement(By.cssSelector(".compare-para-txt")).getText().trim();

			if (name.equalsIgnoreCase(productName)) {
				productFound = true;

				System.out.println("Removing Product from Compare: " + name);

				WebElement removeBtn = card.findElement(By.cssSelector(".close-img.close-compare-card"));

				removeBtn.click();

				// Wait until card disappears
				wait.until(ExpectedConditions.stalenessOf(card));

				System.out.println("Removed successfully: " + name);
				break;
			}
		}

		if (!productFound) {
			throw new AssertionError("Product '" + productName + "' not found on Compare Page!");
		}
	}

        //on compare counter
	public void verifyRemainingProduct(String remainingProduct) {

		List<WebElement> names = driver.findElements(By.cssSelector(".compare-para-txt"));

		List<String> productNames = names.stream().map(e -> e.getText().trim()).collect(Collectors.toList());

		if (!productNames.contains(remainingProduct)) {
			throw new AssertionError(
					"Remaining product mismatch! Expected: " + remainingProduct + " but found: " + productNames);
		}

		System.out.println("Remaining product verified: " + remainingProduct);
	}

	public List<String> getVisibleComparedProducts() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Wait for visibility
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p.detail-para-txt")));

		List<WebElement> products = driver.findElements(By.cssSelector("p.detail-para-txt"));

		if (products.size() == 0) {
			throw new AssertionError("No products found on Compare Page!");
		}

		List<String> names = products.stream().map(e -> e.getText().trim()).collect(Collectors.toList());

		System.out.println("Products visible on Compare Page: " + names);

		return names;
	}

	public void validateMinimumProducts(int expectedMin) {
		List<String> names = getVisibleComparedProducts();
		if (names.size() < expectedMin) {
			throw new AssertionError("Less than " + expectedMin + " products displayed! Found: " + names);
		}
	}

	// on compare page details titles
	public void VerifyProductsDetailsOnComparePage(String firstProduct, String secondProduct) {

		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p.detail-para-txt")));

		// product names
		List<WebElement> titles = driver.findElements(By.cssSelector("p.detail-para-txt"));

		List<String> names = titles.stream().map(e -> e.getText().trim()).collect(Collectors.toList());

		System.out.println("Compare Page Products: " + names);

		// Validate both products
		if (!names.contains(firstProduct) || !names.contains(secondProduct)) {
			throw new AssertionError("Compare Page Missing Product(s)! Expected: " + firstProduct + ", " + secondProduct
					+ " | Found: " + names);
		}

		System.out.println("Both products found on Compare Page.");
	}

	// on compare page product count
	public void VerifyCompareCountOnComparePage(String expectedCount) {

		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Correct selector
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.color-card")));

		// Fetch product cards
		List<WebElement> compareCards = driver.findElements(By.cssSelector("div.color-card"));

		int actualCount = compareCards.size();
		int expected = Integer.parseInt(expectedCount);

		System.out.println("Compare Counter | Expected: " + expected + " | Actual: " + actualCount);

		if (actualCount != expected) {
			throw new AssertionError("Compare count mismatch! Expected: " + expected + ", but found: " + actualCount);
		}

		System.out.println("Compare counter validation successful.");
	}

}
