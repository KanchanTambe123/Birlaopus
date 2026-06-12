package pagefunctions;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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

public class Create_an_Account_Form_Page {

	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	// ------------------- Scenario 1 ------------------------

	@FindBy(id = "profile-icon")
	public WebElement profileIcon;

	@FindBy(xpath = "//span[normalize-space()='Create an Account']")
	public WebElement CreateAccountOption;

	@FindBy(xpath = "//input[@id='create-account-input']")
	public WebElement CreateAccountMobileNumberFiled;

	@FindBy(xpath = "	//button[@id='create-account-btn']")
	public WebElement CreateAccountButton;

	// otp filed
	@FindBy(css = "input.cmp-form-text__input[name='otpInput']")
	private List<WebElement> otpNoInputFiled;

	@FindBy(xpath = "//form[@id='headerSearchContainer']//button[@id='verify-otp-btn']")
	public WebElement otpVerifyButton;

	@FindBy(id = "fName")
	public WebElement firstNameInput;

	@FindBy(id = "lName")
	public WebElement lastNameInput;

	@FindBy(xpath = "//button[@id='save-detail-btn']")
	public WebElement saveDetailButton;

	@FindBy(xpath = "//input[@name='first-name']/following-sibling::div[contains(@class,'error-txt')]")
	public WebElement firstNameErrorMsg;

	@FindBy(xpath = "//input[@name='last-name']/following-sibling::div[contains(@class,'error-txt')]")
	public WebElement lastNameErrorMsg;

	@FindBy(css = "span.text-title")
	public WebElement welcomeMessage;

	// ------------------- Scenario 2 ------------------------
	@FindBy(css = "div.api-error")
	public WebElement duplicateUserErrorMsg;
	
	
	// ------------------- Scenario 3 ------------------------
	
	@FindBy(xpath = "//button[@id='form-button-640282442']")
	public WebElement submitButton;
	

	public Create_an_Account_Form_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void enterOtpAndSubmit(String otp) {

		// Wait only for the first OTP input
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement firstOtpField = wait.until(ExpectedConditions.visibilityOf(otpNoInputFiled.get(0)));

		firstOtpField.clear();
		firstOtpField.sendKeys(otp);

		wait.until(ExpectedConditions.elementToBeClickable(otpVerifyButton));
		otpVerifyButton.click();
	}
	public String generateBypassOtp(String mobileNumber) {

	    if (mobileNumber == null || mobileNumber.length() < 4) {
	        throw new IllegalArgumentException("Invalid mobile number for OTP bypass");
	    }

	    String lastTwo = mobileNumber.substring(mobileNumber.length() - 2);
	    String firstTwo = mobileNumber.substring(0, 2);

	    return lastTwo + firstTwo;   // e.g. 25 + 98 = 2598
	}

	
	public void enterBypassOtpAndVerify(String mobileNumber) {

	    String otp = generateBypassOtp(mobileNumber);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    // Wait only for the FIRST OTP field (OTP UI is dynamic)
	    WebElement firstOtpField = wait.until(
	            ExpectedConditions.visibilityOf(otpNoInputFiled.get(0))
	    );

	    firstOtpField.clear();
	    firstOtpField.sendKeys(otp);

	    wait.until(ExpectedConditions.elementToBeClickable(otpVerifyButton));
	    otpVerifyButton.click();
	}



}
