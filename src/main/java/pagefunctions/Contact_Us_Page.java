package pagefunctions;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class Contact_Us_Page {
	
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();
	
	
	// Page Elements
	
		// ------------------- Scenario 1 ------------------------
	@FindBy(id = "whoAreYou")
	private WebElement whoAreYouDropdown;
	
	@FindBy(id = "howCanWehelpYou")
	public WebElement howCanWeHelpYouDropdown;
	
	@FindBy(xpath = "//input[@id='postcode']")
	public WebElement pincode;
	
	@FindBy(xpath = "//input[@id='emailId']")
	public WebElement emailId;
	
	@FindBy(xpath = "//input[@id='concern']")
	public WebElement concern;
	
	@FindBy(xpath = "//button[@id='form-button-1596746362']")
	public WebElement submitButton;
	
	@FindBy(xpath = "//button[@id='form-button-1632095208']")
	public WebElement nextButton;
	
	public  Contact_Us_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public void selectWhoAreYou(String optionText) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	        // Wait until dropdown is visible
	        wait.until(ExpectedConditions.visibilityOf(whoAreYouDropdown));

	        // Scroll to dropdown
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                whoAreYouDropdown);

	        // Wait until clickable
	        wait.until(ExpectedConditions.elementToBeClickable(whoAreYouDropdown));

	        // Select option
	        Select select = new Select(whoAreYouDropdown);
	        select.selectByVisibleText(optionText.trim());

	        System.out.println("Selected 'Who are you': " + optionText);

	    } catch (Exception e) {
	        throw new RuntimeException("Unable to select 'Who are you' option: " + optionText, e);
	    }
	}
	public void selecthowCanWeHelpYou(String optionText) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    wait.until(ExpectedConditions.visibilityOf(howCanWeHelpYouDropdown));

	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({block:'center'});", howCanWeHelpYouDropdown);

	    wait.until(ExpectedConditions.elementToBeClickable(howCanWeHelpYouDropdown));

	    Select select = new Select(howCanWeHelpYouDropdown);
	    select.selectByVisibleText(optionText);
	}
}
