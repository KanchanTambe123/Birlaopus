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

import commonutilities.DriverManager;

public class Opus_Assurance_Journey_Page {
	
	public WebDriver driver;
	
	@FindBy(xpath = "(//a[@class='cmp-teaser__action-link'])[1]")
	public WebElement registerNowCta;
	
	@FindBy(xpath = "//input[@id='paintableArea']")
	public WebElement paintableAreaField;
	
	@FindBy(xpath = "(//div[@class='button login-form-btn'])[8]")
	public WebElement nextCta;
	
	@FindBy(xpath = "(//label[@class='cmp-form-options__field-label'])[2]")
	public WebElement yetToStart;
	
	@FindBy(xpath = "(//button[@type='SUBMIT'])[9]")
	public WebElement preRegisterCta;
	
	@FindBy(xpath = "//span[normalize-space()='Create an account']")
	public WebElement CreateAccountOption;
	
	@FindBy(xpath = "//input[@id='warrantyFirstName']")
	public WebElement firstNameField;
	
	@FindBy(id="warrantyLastName")
	public WebElement lastNameField;
	
	@FindBy(xpath = "//input[@id='warrantyEmail']")
	public WebElement emailIdField;
	
	@FindBy(xpath = "//input[@id='warrantyPincode']")
	public WebElement pincodeField;
	
	@FindBy(xpath = "(//div[@class='button login-form-btn']//button)[14]")
	public WebElement submitButtonCta;
	
	@FindBy(xpath = "(//div[@class='cmp-teaser__description']//h4)[1]")
	public WebElement weAreComingSoonText;
	
	@FindBy(xpath = "(//div[@class='error-txt dsp-block'])[1]")
	public WebElement lastNameErrorMsg;
	
	@FindBy(xpath = "(//div[@class='cmp-container-inner']//div[@class='error-txt dsp-block'])[1]")
	public WebElement emailIdErrorMsg;
	
	@FindBy(xpath = "(//div[normalize-space()='Invalid Input.'])[1]")
	public WebElement pincodeErrorMsg;
	
	@FindBy(xpath = "//div[@class='container responsivegrid common-form-wrap verify-otp dsp-block']//div[@class='cmp-form-text form-feilds']//input")
	public List<WebElement> otpNoInputFiled;
	
    @FindBy(xpath = "//div[contains(@class,'api-error') and contains(text(),'OTP')]")
	public WebElement otpFieldErrorMsg;
	
	@FindBy(xpath = "//button[@id='verify-otp-btn']")
	public WebElement verifyButton;
	
	
	
	@FindBy(xpath = "//div[contains(@class,'error-txt') and contains(normalize-space(.),'Paintable area should be more than')]")
	public WebElement ErrMessagePaintingCarpetArea;
	
	//Scenarios 7
	@FindBy(xpath = "//span[normalize-space()='Start a New Project']/parent::a")
	public WebElement startNewProjectButton;
	
	@FindBy(xpath = "//input[@id='userValue']")
	public WebElement siteDetailsProjectName;
	
	@FindBy(xpath = "(//div[contains(@class,'login-form-btn')]//button[text()='Submit'])[3]")
	public WebElement submitButtonEnterDetails;

	
	@FindBy(xpath = "(//div[contains(@class,'login-form-btn')]//button[text()='Submit'])[1]")
	public WebElement submitButtonSiteDetails;
	
	@FindBy(xpath = "//button[@id='nextBtnScreenBreak']")
	public WebElement nextButtonFewMoreDetails;
	
	@FindBy(xpath = "//input[@id='surveyPincode']")
	public WebElement pincodeSiteDetails;
	
	@FindBy(xpath = "//a[.//span[normalize-space()='Sign Up for PaintCraft']]")
	public WebElement SignUpPaintCraftButton;

	@FindBy(xpath = "//a[.//span[normalize-space()='Find a Contractor Near You']]")
	public WebElement FindContractorbutton;
	
	@FindBy(xpath = "//span[contains(@class,'text-title') and contains(text(),'Thank you for signing up')]")
	public WebElement ConfirmationMessage;
	
	@FindBy(xpath = "//input[@id='warrantyPincode' and @name='pinCode']")
	public WebElement warrantyPincode;
	
	//Scenarios 8

	
	@FindBy(xpath = "(//span[normalize-space()='Next']/parent::a)[2]")
	public WebElement NextButtonFindContractor;

	@FindBy(xpath = "(//div[contains(@class,'opus-contractor-wrap')]//button[contains(@class,'assurance-btn')])[1]")
	public WebElement selectFirstContractor;

	
	//Scenarios 9
	
	

	@FindBy(xpath = "//input[@id='warrantyEmail']")
	public WebElement warrantyEmail;
	
	@FindBy(xpath = "//button[@id='form-button-640282442']")
	public WebElement submitButtoncreateAccount;

	@FindBy(xpath = "//span[@class='no-account' and normalize-space()='Create an account']")
	public WebElement CreateAnaccountOptioNlink ;
	


	@FindBy(xpath = "//a[contains(@class,'enroll-assurance') and .//span[normalize-space()='Tell us more about your site']]")
	public WebElement TellUsMoreaAoutYourSiteButton ;
	

	@FindBy(xpath = "//input[@id='flatNo']")
	public WebElement flatNoInputField;
	
	@FindBy(id = "surveyPropertyName")
	public WebElement propertyNameInputField;
	
	
	@FindBy(xpath = "(//div[@id='renderAddressDataForAssurance']//div[@class='searched-address'])[1]")
	public WebElement clickFirstAddress;
	
	@FindBy(xpath = "//span[contains(text(),'Confirm') and contains(text(),'address')]")
	public WebElement confirmAddAddressBtn;
	
	
	
	
	


	
	
	
	
	
	
	public  Opus_Assurance_Journey_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	public void selectAvailableTimeSlot() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // Get all available (not disabled) time slots
	    List<WebElement> slots = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	        By.xpath("//div[contains(@class,'timeSlot') and not(contains(@class,'disabled'))]")
	    ));

	    if (slots.size() > 0) {

	        WebElement slot = slots.get(0); // pick first available

	        // Scroll into view
	        ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].scrollIntoView({block:'center'});", slot);

	        // Wait until clickable
	        wait.until(ExpectedConditions.elementToBeClickable(slot));

	        // Click
	        slot.click();

	        System.out.println("Clicked on available time slot: " + slot.getText());

	    } else {
	        throw new RuntimeException("No available time slots found");
	    }
	}
	
	// date
	public void selectAvailableDate() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Wait until all date elements are present
		List<WebElement> dates = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[contains(@class,'preferredDatesDiv')]//div[contains(@class,'toSelectDate')]")));

		// small wait
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		if (dates.size() >= 2) {
			WebElement secondDate = dates.get(1);

			// JS click
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", secondDate);
			return;
		}

		throw new RuntimeException("Less than 2 dates available to select.");
	}
}
