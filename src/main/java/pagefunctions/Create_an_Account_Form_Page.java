package pagefunctions;

import java.time.Duration;
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
	@FindBy(xpath = "//div[contains(@class,'otp-input')]//input")
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
	
	@FindBy(xpath = "//a[contains(@class,'remove-user')]")
	public WebElement deleteAccount;
	

	public Create_an_Account_Form_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void enterOtpAndSubmitProfile(String otp) {
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	    wait.until(ExpectedConditions.visibilityOfAllElements(otpNoInputFiled));

	    int otpIndex = 0;

	    for (WebElement field : otpNoInputFiled) {
	        if (field.isDisplayed() && field.isEnabled()) {
	            field.click();
	            field.clear();
	            field.sendKeys(String.valueOf(otp.charAt(otpIndex)));
	            otpIndex++;

	            if (otpIndex == otp.length()) {
	                break;
	            }
	        }
	    }

	    wait.until(ExpectedConditions.elementToBeClickable(otpVerifyButton)).click();

	}
	public String generateBypassOtp(String mobileNumber) {

	    mobileNumber = mobileNumber.replaceAll("\\D", ""); // Keep only digits

	    // If +91 is included, keep only last 10 digits
	    if (mobileNumber.length() > 10) {
	        mobileNumber = mobileNumber.substring(mobileNumber.length() - 10);
	    }

	    String firstTwo = mobileNumber.substring(0, 2);
	    String lastTwo = mobileNumber.substring(mobileNumber.length() - 2);

	    String otp = lastTwo + firstTwo;

	    System.out.println("Mobile : " + mobileNumber);
	    System.out.println("OTP : " + otp);

	    return otp;
	}
	
	public void enterBypassOtpAndVerify(String mobileNumber) {

	    String otp = generateBypassOtp(mobileNumber);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	    // Wait only for the FIRST OTP field (OTP UI is dynamic)
	    WebElement firstOtpField = wait.until(
	            ExpectedConditions.visibilityOf(otpNoInputFiled.get(0))
	    );

	    firstOtpField.clear();
	    firstOtpField.sendKeys(otp);

	    wait.until(ExpectedConditions.elementToBeClickable(otpVerifyButton));
	    otpVerifyButton.click();
	}

	
	
	public void clickLogoutPopupButton(String answer) {
	    WebDriver driver = DriverManager.getDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    String buttonXpath;

	    if (answer.equalsIgnoreCase("Yes")) {
	        buttonXpath = "//a[contains(@class,'delete-user')]//span[normalize-space()='Yes']";
	    } else {
	        buttonXpath = "//a[contains(@class,'delete-user-no')]//span[normalize-space()='No']";
	    }

	    WebElement button = wait.until(
	            ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));

	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", button);
	    js.executeScript("arguments[0].click();", button);

	    System.out.println("Clicked '" + answer + "' on logout popup");
	}
	public void enterOtpAndSubmit(String otp) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	    WebElement otpField = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//input[@name='otpInput']")));

	    otpField.clear();
	    otpField.sendKeys(otp);

	    WebElement verifyButton = wait.until(
	            ExpectedConditions.elementToBeClickable(otpVerifyButton));

	    verifyButton.click();

	    System.out.println("OTP entered and Verify button clicked.");
	}

}
