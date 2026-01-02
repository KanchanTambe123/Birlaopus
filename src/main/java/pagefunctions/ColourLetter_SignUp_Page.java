package pagefunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class ColourLetter_SignUp_Page {

	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	// Page Elements
	
	// ------------------- Scenario 1 ------------------------
	@FindBy(xpath = "//label[contains(text(),'Enter your email ID')]/preceding-sibling::input")
	public WebElement EmailIdFiled;

	@FindBy(xpath = "//button[normalize-space()='Sign up for colour letter']")
	public WebElement SignupButton;

	@FindBy(xpath = "//div[@class='cmp-teaser__description']/h4[text()='Thank You']")
	public WebElement SuccessHeader;

	@FindBy(xpath = "//div[@class='cmp-teaser__description']/p[contains(text(),'For Subscribing')]")
	public WebElement SuccessDescription;

	// Close Button at Bottom
	@FindBy(xpath = "(//a[contains(@class,'cmp-teaser__action-link') and text()='Close'])[2]")
	public WebElement CloseButton;
	
	// ------------------- Scenario 2,3------------------------
	@FindBy(xpath = "//div[@class='error-txt dsp-block']")
	public WebElement ErrorMessage;

	public ColourLetter_SignUp_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

}
