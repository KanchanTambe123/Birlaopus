package pagefunctions;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonutilities.ActionClass;
import commonutilities.ClickElement;
import commonutilities.CommonDataGenerator;
import commonutilities.CommonMethods;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import config.ConfigReader;

public class All_Lets_Connect_Form_Page {
	
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();
	
	
	//brand form
	  @FindBy(xpath = "//input[@aria-label='Phone Number' and @type='text']")
	    public WebElement phoneNumberField;
	  
	    @FindBy(xpath = "//input[@aria-label='Name' and @type='text']")
	    public WebElement nameField;
	    @FindBy(xpath = "//input[@aria-label='Email ID' and @type='text']")
	    public WebElement emailField;
	    
	    @FindBy(xpath = "//label[text()='How can we help you?']/following-sibling::select")
	    private WebElement helpDropdown;

	    // Pincode field
	    @FindBy(xpath = "//input[@aria-label='Pincode' and @type='text']")
	    public WebElement pincodeField;
	    
	    // Submit button
	    @FindBy(xpath = "//button[@type='SUBMIT' and contains(text(),'Submit')]")
	    public WebElement submitButton;
	    @FindBy(xpath = "//div[contains(@class,'onloadDiscountPopup')]//img[contains(@src,'vector')]")
	    public WebElement discountPopupCloseBtn;
	
	    

	    @FindBy(xpath = "//div[contains(@class,'cmp-teaser__description')]//h4[normalize-space()='Thank You']")
	    public WebElement thankYouHeader;

	    @FindBy(xpath = "//div[contains(@class,'cmp-teaser__description')]//p[normalize-space()='For Reaching out to us !']")
	    public WebElement thankYouDescription;
	    
	    //Scenario2
	    @FindBy(xpath = "//div[contains(@class,'error-txt') and normalize-space()='This field is required']")
	    public WebElement  mandatoryFieldErrors;
	    
	    //Scenario3
	    @FindBy(xpath = "//button[normalize-space()='Book a free consultation']")
	    public WebElement  BookfreeConsultationButton;
	    
	    
	    @FindBy(xpath = "(//input[@name='pinCode'])[6]")
	    public WebElement  PincodeInputFiledBookfreeConsultation;
	    

	    @FindBy(xpath = "(//button[normalize-space()='Continue'])[1]")
	    public WebElement  ContinueButtonBookfreeConsultation;
	   
	    @FindBy(xpath = "//button[@id='button-39d73f9229']")
	    public WebElement  BackButtonBookfreeConsultation;
	    
	    @FindBy(xpath = "//div[contains(@class,'error-txt') and contains(normalize-space(),'valid Pincode')]")
	    public WebElement  invalidPincodeErrMsgBookfreeConsultation;
	    
	



	

	public All_Lets_Connect_Form_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	

	
	public void selectHelpOption(String optionText) {

	    WebDriverWait wait = new WebDriverWait(
	            DriverManager.getDriver(), Duration.ofSeconds(20));

	    wait.until(ExpectedConditions.visibilityOf(helpDropdown));

	    Select select = new Select(helpDropdown);

	    try {
	        select.selectByVisibleText(optionText.trim());
	    } catch (NoSuchElementException e) {
	        throw new NoSuchElementException(
	            "Help option not found in dropdown: " + optionText);
	    }
	}


	public void killDiscountOverlay() {
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    js.executeScript(
	        "document.querySelectorAll(\"a.cmp-image__link\").forEach(e => e.remove());" +
	        "document.querySelectorAll(\"div.cmp-image\").forEach(e => e.remove());" +
	        "document.body.style.overflow='auto';"
	    );

	    System.out.println("Discount overlay forcibly removed");
	}

	public void selectWhoAreYou(String userType) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement dropdown = wait.until(
	        ExpectedConditions.elementToBeClickable(By.id("whoAreYou"))
	    );
	    Select select = new Select(dropdown);
	    select.selectByVisibleText(userType);
	}


	public void selectHowCanWeHelpYou(String optionText) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement dropdown = wait.until(
	        ExpectedConditions.elementToBeClickable(By.id("howCanWehelpYou"))
	    );

	    Select select = new Select(dropdown);
	    select.selectByVisibleText(optionText);
	}


}
