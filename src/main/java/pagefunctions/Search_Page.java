package pagefunctions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Search_Page {

	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	// ------------------- Scenario 1 ------------------------

	@FindBy(xpath = "//a[@id='search-icon']//img[@class='cmp-text-btn-icon']")
	public WebElement searchIcon;

	@FindBy(css = "#header-search-input")
	public WebElement searchPanel;

	@FindBy(xpath = "//div[@class='cmp-text']//span[@class='text-description' and contains(text(),'Showing')]")
	public WebElement resultMessage;
	
	@FindBy(xpath = "//span[@class='text-description' and text()='Showing 0 results']")
	public WebElement InvalidresultMessage;
	



	@FindBy(css = "div.product-card")
	public WebElement productCards;
	@FindBy(xpath = "//*[@id='dropdownSearchOption']//div[contains(@class,'trending-search-div')]//ul[contains(@class,'search-options')]//li[contains(@class,'trending-options')]//a[contains(@class,'redirect-trending-search')]")
	public List<WebElement> trendingSearch;

	@FindBy(css = "#dropdownSearchOption div.trending-search-div ul.search-options li.trending-options a.redirect-trending-search")
	public List<WebElement> trendingSearchList;

	@FindBy(xpath = "//*[@id='form-button-2025059837']")
	public WebElement submitButton;

	@FindBy(xpath = "//p[@class='book-banner-popup_thankyou']")
	public WebElement thankYouMsg;

	// ------------------- Scenario 2 ------------------------

	@FindBy(xpath = "//span[normalize-space()='Please enter your Name']")
	public WebElement fullNameBlankFieldErrMsg;

	@FindBy(xpath = "(//span[@class='error'][normalize-space()='Please enter your Mobile Number'])[2]")
	public WebElement mobileNumberBlankFieldErrMsg;

	@FindBy(xpath = "//div[@class='frm__field-group invalid']//span[@class='error'][normalize-space()='Please enter your Pincode']")
	public WebElement pincodeBlankFieldErrMsg;

	@FindBy(xpath = "//div[@class='frm__field-group invalid']//span[@class='error'][normalize-space()='Please enter your Email ID']")
	public WebElement emailIdBlankFieldErrMsg;

	// ------------------- Scenario 3 ------------------------

	@FindBy(xpath = "//span[normalize-space()='Please enter a valid Mobile Number']")
	public WebElement mobileNumberInvalidErrMsg;

	@FindBy(xpath = "//span[normalize-space()='Please enter a valid PIN code']")
	public WebElement pincodeInvalidErrMsg;

	@FindBy(xpath = "//span[normalize-space()='Please enter a valid Email ID']")
	public WebElement emailInvalidErrMsg;

	// ------------------- Scenario 4 ------------------------

	@FindBy(xpath = "//label[@for='field_whatsapp']//span[@class='checkmark']")
	public WebElement checkboxWhatsapp;

	public Search_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	public int getNumberFromText(WebElement element, int timeout) {
		// Wait for visibility
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));

		// Get text
		String text = element.getText().trim();
		System.out.println("Element text: " + text);

		// Extract number
		int number = 0;
		if (text != null && text.matches(".*\\d+.*")) {
			number = Integer.parseInt(text.replaceAll("[^0-9]", ""));
		}

		System.out.println("Extracted number: " + number);
		return number;
	}

	public boolean isTrendingSuggestionVisible(WebElement searchPanel, List<WebElement> trendingList,
			String expectedText, int timeout) {

		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

// Wait for loader to disappear
		By loader = By.cssSelector(".page-loader"); // same as in your error
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

// Now wait for the search panel to be clickable
		wait.until(ExpectedConditions.elementToBeClickable(searchPanel));

// Click the search panel using JS to avoid overlay issues
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchPanel);

// Loop trending suggestions
		for (WebElement item : trendingList) {

			wait.until(ExpectedConditions.visibilityOf(item));

			String text = item.getText().trim();
			System.out.println("Suggestion: " + text);

			if (text.equalsIgnoreCase(expectedText)) {
				return true;
			}
		}
		return false;
	}

	public boolean clickTrendingSuggestion(WebElement searchPanel, List<WebElement> trendingList, String suggestion,
			int timeout) {

		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

// Wait for search panel
		wait.until(ExpectedConditions.visibilityOf(searchPanel));

// Click on search panel
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchPanel);

// Loop through suggestions
		for (WebElement item : trendingList) {

			wait.until(ExpectedConditions.visibilityOf(item));

			String text = item.getText().trim();
			System.out.println("Suggestion: " + text);

			if (text.equalsIgnoreCase(suggestion)) {
// Click suggestion
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", item);
				return true; // Found and clicked
			}
		}
		return false; // Not found
	}

}
