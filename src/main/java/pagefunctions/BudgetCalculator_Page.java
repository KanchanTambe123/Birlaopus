package pagefunctions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonutilities.ClickElement;
import commonutilities.CryptoUtils;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class BudgetCalculator_Page {
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	CryptoUtils crypto = new CryptoUtils();

	// Scenario 1
	@FindBy(xpath = "//*[@id=\"spaceName\"]")
	public WebElement spacetext;

	@FindBy(id = "carpetAreaInputField")
	public WebElement carpetAreaInputField;

	@FindBy(id = "pincodeInputField")
	public WebElement pincodeInputField;

	@FindBy(id = "carpetAreaCalculateBtn")
	public WebElement calculateNowButton;

	@FindBy(xpath = "(//button[.//span[normalize-space()='Book a free survey']])[6]")
	public WebElement bookFreeSurveyButton;

	// Scenario 2

	@FindBy(xpath = "(//li[@role='tab' and normalize-space()='Economy'])[2]")
	private WebElement economyTab;

	@FindBy(xpath = "	(//li[@role='tab' and normalize-space()='Luxury'])[2]")
	private WebElement luxuryTab;

	// Scenario 3

	@FindBy(xpath = "//a[@id='surveyFlowClose']")
	public WebElement ThankYouWindowCloseButton;

	// Scenario 7
	@FindBy(xpath = "//div[contains(@class,'editSquare') and @role='button']")
	public WebElement editCarpetAreaBtn;

	@FindBy(id = "recalculateInput")
	public WebElement recalculateInput;

	@FindBy(id = "recalculateBtn")
	public WebElement recalculateBtn;

	@FindBy(xpath = "(//div[contains(@class,'totalPrice')]//p[@class='amt'])[5]")
	public WebElement priceText;

	// Scenarios 8

	@FindBy(xpath = " //div[contains(@class,'error-txt') and contains(text(),'Invalid Input')]")
	public WebElement InvalidPincode;
	
	// Scenarios 9
	@FindBy(xpath = "//div[contains(@class,'error-txt') and contains(text(),'This field is required')]")
	public WebElement EmptyPincodeField;
	// Scenarios 10
	@FindBy(xpath = "(//span[@class='text-title' and text()='View Products'])[3]")
	public WebElement ViewProducts;
	// Scenarios 11
	@FindBy(xpath = "(//p[contains(@class,'dwnldPdf') and contains(text(),'Download estimate')])[1]")
	public WebElement DownloadestimateButton;
	
	@FindBy(xpath = "//input[@id='surveyPincode']")
	public WebElement pincodeSiteDetails;

	@FindBy(xpath = "(//div[@class='button login-form-btn']//button)[9]")
	public WebElement nextSiteDetails;

	


	public BudgetCalculator_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void selectSpaceOptions(String spaceName) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		List<WebElement> spaceOptions = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'cmp-text-button')]//a")));

		boolean isClicked = false;

		for (WebElement option : spaceOptions) {

			if (option.getText().trim().equalsIgnoreCase(spaceName)) {

				// Scroll with offset (avoids header overlap)
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);

				// Small wait for animation to finish
				wait.until(ExpectedConditions.visibilityOf(option));

				try {
					// Try normal Selenium click
					wait.until(ExpectedConditions.elementToBeClickable(option)).click();
				} catch (ElementClickInterceptedException e) {
					// Fallback to JS click
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
				}

				// Wait for active state
				wait.until(ExpectedConditions.attributeContains(option, "class", "active"));

				isClicked = true;
				break;
			}
		}

		if (!isClicked) {
			throw new AssertionError("Space option not clickable: " + spaceName);
		}
	}

	public void verifyTabIsLocked(String tabName) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		WebElement tab = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//li[@role='tab' and normalize-space()='" + tabName + "']")));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Read CSS custom property
		String lockBg = (String) js
				.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('--before-bg');", tab);

		Assert.assertTrue(lockBg != null && lockBg.contains("lockForTabs"),
				tabName + " tab should be locked but lock icon not found");
	}

	public void verifyButtonNotVisibleOnPremiumTab() {

		List<WebElement> buttons = driver
				.findElements(By.xpath("//button[.//span[normalize-space()='Book a free survey']]"));

		// Case 1: Button removed completely (best case)
		if (buttons.isEmpty()) {
			Assert.assertTrue(true, "Book a free survey button is not present on Premium tab");
			return;
		}

		// Case 2: Button exists but must be hidden
		for (WebElement button : buttons) {
			Assert.assertFalse(button.isDisplayed(),
					"'Book a free survey' button should NOT be visible on Premium tab");
		}
	}

	public void verifyButtonVisibilityOnTab(String tabName) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		// 1️⃣ Click tab
		By tabLocator = By.xpath("(//li[@role='tab' and normalize-space()='" + tabName + "'])[2]");

		WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(tabLocator));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
		Thread.sleep(5000);
		// 2️.Get linked tab panel
		String tabPanelId = tab.getAttribute("aria-controls");
		By panelLocator = By.id(tabPanelId);

		WebElement panel = wait.until(ExpectedConditions.visibilityOfElementLocated(panelLocator));

		// 3️⃣ Locate button INSIDE active panel
		By bookSurveyBtn = By.xpath(".//a[normalize-space()='Book a free survey'] | "
				+ ".//button[normalize-space()='Book a free survey']");
		Thread.sleep(5000);
		WebElement button = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(panel, bookSurveyBtn));

		// 4️.Final assertion
		Assert.assertTrue(button.isDisplayed(), "'Book a free survey' button should be visible on " + tabName + " tab");
	}

	public void closeThankYouPopup() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		By closeIcon = By.xpath("//a[@id='surveyFlowClose']//img");

		WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(closeIcon));

		// Scroll into view (important for overlays)
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeBtn);

		// JS click on IMG
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);

		// Wait until popup disappears
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("budgetSurveyFlow")));
	}

	public String getVisiblePrice() {
	    By priceLocator = By.xpath("//div[contains(@class,'estimationDetails__priceContainer-totalPrice')]//p[@class='amt']");

	    try {
	        List<WebElement> prices = DriverManager.getDriver().findElements(priceLocator);

	        for (WebElement price : prices) {
	            if (price.isDisplayed()) {
	                return price.getText().trim();
	            }
	        }
	    } catch (StaleElementReferenceException e) {
	        return "";
	    }

	    return "";
	}

}
