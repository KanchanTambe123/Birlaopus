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
import org.testng.Assert;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class Store_Locator_Page {

	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	
	//Scenarios 1
	@FindBy(xpath = "//a[@class='findstore-div']")
	public WebElement storeLocatorBtn;
	
	@FindBy(xpath = "//input[@id='pincode-input']")
	public WebElement pincodeInput;

	@FindBy(xpath =  "//*[@id=\"form-button-525153950\"]")
	public WebElement submitBtn;
	
	//Scenarios 2
	@FindBy(xpath = "(//div[contains(@class,'opusText') and contains(@class,'form-message')]//span[contains(@class,'text-title')])[1]")
	public WebElement successTitleText;

	@FindBy(xpath = "//button[@id='continue' and @type='SUBMIT']")
	public WebElement continueButton;

	
	@FindBy(xpath = "//input[@id='emailWF' and @type='text']")
	public WebElement emailInputField;
	
	@FindBy(xpath = "//button[@id='submitContractor' and @type='SUBMIT']")
	public WebElement submitButtonLeadDetailsForm;



	public Store_Locator_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void StoreLocatorResults() {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(80));

		//  store count text
		WebElement storeCountText = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//p[contains(@class,'stores-count') and contains(text(),'stores near you')]")));

		//  visible dropdown
		WebElement storeDropdown = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'custom-dropdown')])[1]")));

		// Validate UI visibility
		Assert.assertTrue(storeCountText.isDisplayed(), "Store count text not displayed");
		Assert.assertTrue(storeDropdown.isDisplayed(), "Store filter dropdown not displayed");

		// Safely parse store count
		String text = storeCountText.getText(); // e.g., "10 stores near you"
		String countOnly = text.replaceAll("[^0-9]", ""); // remove non-numeric characters
		int count = Integer.parseInt(countOnly);

		Assert.assertTrue(count > 0, "No stores found");
	}
	
	
	
	public void clickFirstGetNumberButton() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    List<WebElement> getNumberButtons = wait.until(
	        ExpectedConditions.presenceOfAllElementsLocatedBy(
	            By.xpath("//button[contains(@class,'share-btn') and contains(@class,'open-dealer-contract-lead')]")
	        )
	    );

	    if (getNumberButtons.isEmpty()) {
	        throw new RuntimeException("No 'Get Number' buttons found.");
	    }

	    WebElement firstButton = getNumberButtons.get(0);

	    // Scroll 
	    js.executeScript(
	        "arguments[0].scrollIntoView({block:'center'});",
	        firstButton
	    );

	    // wait
	    try { Thread.sleep(300); } catch (InterruptedException ignored) {}

	    //JS click (bypasses overlay interception)
	    js.executeScript("arguments[0].click();", firstButton);
	}


}
