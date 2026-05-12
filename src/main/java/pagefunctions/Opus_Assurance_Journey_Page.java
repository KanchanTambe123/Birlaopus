package pagefunctions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;

import commonutilities.CryptoUtils;
import commonutilities.DriverManager;

public class Opus_Assurance_Journey_Page {

	CryptoUtils crypto = new CryptoUtils();
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
	
	public void clickSixthContractorAndValidate() {

	    WebDriver driver = DriverManager.getDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    By selectBtn = By.xpath("//button[normalize-space()='Select this contractor']");
	    By toastMsg = By.xpath("//*[contains(text(),'limit')]");

	    // 🔥 Re-fetch AFTER selecting 5
	    List<WebElement> remaining = wait.until(
	        ExpectedConditions.presenceOfAllElementsLocatedBy(selectBtn)
	    );

	    System.out.println("Remaining Select buttons: " + remaining.size());

	    if (remaining.size() == 0) {
	        throw new RuntimeException("No contractor left to click (6th not available)");
	    }

	    WebElement sixthBtn = remaining.get(0); // this is 6th

	    // Click safely
	    try {
	        wait.until(ExpectedConditions.elementToBeClickable(sixthBtn)).click();
	    } catch (Exception e) {
	        js.executeScript("arguments[0].click();", sixthBtn);
	    }

	    System.out.println("Clicked 6th contractor");

	    // 🔥 Validate toast immediately
	    boolean messageDisplayed = false;

	    for (int i = 0; i < 5; i++) {
	        if (driver.findElements(toastMsg).size() > 0) {
	            messageDisplayed = true;
	            break;
	        }
	        try { Thread.sleep(300); } catch (Exception ignored) {}
	    }

	    Assert.assertTrue(messageDisplayed, "Validation message not displayed");
	}
	
	
	
	public void verifyServiceableStatus(String expectedStatus) {

	    try {
	        // 1️.Get payload
	        String encryptedPayload = DriverManager.waitForLeadPayload(15);

	        // 2️.Get status code
	        int statusCode = DriverManager.waitForLeadStatusCode(15);

	        System.out.println("Encrypted Payload: " + encryptedPayload);
	        System.out.println("Status Code: " + statusCode);

	        //  Validate status code
	        assertStatusCode(200, statusCode);

	        // 3️.Extract 'data'
	        JSONObject wrapperJson = new JSONObject(encryptedPayload);
	        String encryptedData = wrapperJson.getString("data");

	        // 4️.Decrypt
	        String decryptedJson = crypto.decryptData(encryptedData);
	        System.out.println("Decrypted JSON: " + decryptedJson);

	        // 5️.Parse JSON
	        ObjectMapper mapper = new ObjectMapper();
	        Map<String, Object> dataMap = mapper.readValue(decryptedJson, Map.class);
	        Map<String, Object> bodyMap = (Map<String, Object>) dataMap.get("body");

	        //  6️.Get actual serviceable value
	        Object actualValue = bodyMap.get("isAreaServiceable");

	        // Convert expected string → boolean
	        boolean expected = Boolean.parseBoolean(expectedStatus);

	        // Convert actual → boolean safely
	        boolean actual = Boolean.parseBoolean(String.valueOf(actualValue));

	        // 7️ Assertion
	        assertParameter("isAreaServiceable", expected, actual);

	    } catch (Exception e) {
	        e.printStackTrace();
	        ExtentCucumberAdapter.addTestStepLog("Exception during Serviceable validation: " + e.getMessage());
	    }
	}
	private void assertParameter(String paramName, boolean expected, boolean actual) {

	    if (expected != actual) {
	        throw new AssertionError(
	            paramName + " mismatch! Expected: " + expected + ", Actual: " + actual
	        );
	    }

	    System.out.println("✔ " + paramName + " matched: " + actual);
	}
	
	
	private void assertStatusCode(int expected, int actual) {

	    if (expected != actual) {
	        throw new AssertionError(
	            "Expected Status Code: " + expected + ", Actual: " + actual
	        );
	    }

	    System.out.println("Status Code matched: " + actual);
	}
}
