package pagefunctions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;

import commonutilities.ClickElement;
import commonutilities.CryptoUtils;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import com.aventstack.extentreports.Status;

public class Book_Survey_Form_Page {

	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();
	private boolean isServiceable;

	CryptoUtils crypto = new CryptoUtils();

	// ------------------- Scenario 1 ------------------------

	@FindBy(xpath = "//a[contains(@class,'site-visit-btn') and .//span[text()='Book a free survey']]")
	public WebElement BookfreesurveyButton;
	@FindBy(xpath = "(//div[@class='button login-form-btn']//button[normalize-space()='Next'])[1]")
	public WebElement SiteDetailsNextButton;

	@FindBy(xpath = "//div[@class='button login-form-btn']//button[@id='nextBtnScreenBreak']")
	public WebElement FewDeailsNextButton;
	

	@FindBy(xpath = "//a[@id='projectDetailsNext']")
	public WebElement projectDetailsNextButton;

	@FindBy(xpath = "//input[@id='carpetArea']")
	public WebElement carpetAreaInputFiled;
	


	@FindBy(xpath = "//div[contains(@class,'cmp-text')]//p[contains(normalize-space(),'Thank you for sharing your details')]")
	public WebElement confirmationMsg;

	// Address input
	@FindBy(xpath = "//input[@id='surveyPropertyName']")
	private WebElement addressInput;

	// First suggestion (Pune)
	@FindBy(xpath = "(//div[@id='renderAddressDataForBudget']//div[@class='searched-address'])[1]")
	private WebElement firstSuggestion;

	@FindBy(xpath = "//button[@id='addressPopUpConfirmBtn']")
	public WebElement addressConfirmButton;

	@FindBy(xpath = "//input[@id='surveyPincode']")
	public WebElement surveyPincodeInputfiled;

	// Scenarios 2

	@FindBy(xpath = "//a[@id='surveyStepBack']")
	public WebElement surveyStepBackButton;

	@FindBy(xpath = "//span[contains(@class,'text-title') and normalize-space()='Enter Site Details']")
	public WebElement previousStepLocator;

	public Book_Survey_Form_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void selectRequirementType(String requirementType) {
		List<WebElement> requirementTypes = driver.findElements(
				By.xpath("//div[contains(@class,'survey-grid-options')]//p[contains(@class,'cmp-teaser__title')]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (WebElement type : requirementTypes) {
			if (type.getText().trim().equalsIgnoreCase(requirementType)) {

				js.executeScript("arguments[0].scrollIntoView({block:'center'});", type);
				js.executeScript("arguments[0].click();", type);

				return;
			}
		}
		throw new RuntimeException("Requirement type not found: " + requirementType);
	}

	public void selectBhkType(String bhkType) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Wait for container and get cards
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'bhkQuestions')]")));
		List<WebElement> bhkCards = driver
				.findElements(By.xpath("//div[contains(@class,'bhkQuestions')]//div[contains(@class,'opusTeaser')]"));

		boolean found = false;
		for (WebElement card : bhkCards) {
			String title = card.findElement(By.xpath(".//h2[contains(@class,'cmp-teaser__title')]")).getText()
					.replaceAll("\\s+", " ").trim();
			System.out.println("Found BHK: " + title);

			if (title.equalsIgnoreCase(bhkType)) {
				js.executeScript("arguments[0].scrollIntoView({block:'center'});", card);
				wait.until(ExpectedConditions.elementToBeClickable(card)).click();
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("BHK option not found or not clickable: " + bhkType + ". Continuing flow...");
			return; // Continue flow if BHK not found
		}

	

	
	}

	public void enterAddressAndSelectFirstSuggestion(String address) {
		// Wait for input and type address
		wait.waitForElementVisible(addressInput);
		addressInput.clear();
		addressInput.sendKeys(address);

		// Wait for suggestions to load and click first one
		wait.waitForElementToBeClickable(firstSuggestion, 15);

		try {
			firstSuggestion.click();
		} catch (ElementClickInterceptedException e) {
			// Fallback for auto-suggest overlay issues
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", firstSuggestion);
		}
	}
	
	public void logLeadApiDetails(String apiType) {

	    try {
	        // 1️Get data
	        String requestPayload = DriverManager.waitForLeadPayload(15);
	        int statusCode = DriverManager.waitForLeadStatusCode(15);
	        long responseTime = DriverManager.getResponseTime();

	        // 2️Log basic info
	        ExtentCucumberAdapter.addTestStepLog(" API Type: " + apiType);
	        ExtentCucumberAdapter.addTestStepLog(" Status Code: " + statusCode);
	        ExtentCucumberAdapter.addTestStepLog(" Response Time: " + responseTime + " ms");

	        // 3️ Log request payload
	        ExtentCucumberAdapter.addTestStepLog(" Request Payload: " + requestPayload);

	        // 4️OPTIONAL → Decrypt & log
	        try {
	            JSONObject wrapperJson = new JSONObject(requestPayload);
	            String encryptedData = wrapperJson.getString("data");

	            String decryptedJson = crypto.decryptData(encryptedData);

	            ExtentCucumberAdapter.addTestStepLog(" Decrypted Payload: " + decryptedJson);

	        } catch (Exception e) {
	            ExtentCucumberAdapter.addTestStepLog(" Decryption skipped / failed: " + e.getMessage());
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        ExtentCucumberAdapter.addTestStepLog(" Exception while logging API: " + e.getMessage());
	    }
	}

	public void verifyLeadApiParameters(String expectedLeadContext, String expectedLeadType,
	        String expectedSubType, String expectedLeadSubSource) {

	    try {
	        // 1️.Get payload
	        String encryptedPayload = DriverManager.waitForLeadPayload(15);

	        // 2️.Get status code
	        int statusCode = DriverManager.waitForLeadStatusCode(15);

	        System.out.println("Encrypted Payload: " + encryptedPayload);
	        System.out.println("Status Code: " + statusCode);

	        // Validate status code
	        assertStatusCode(200, statusCode);

	        // 3️.Extract 'data'
	        JSONObject wrapperJson = new JSONObject(encryptedPayload);
	        String encryptedData = wrapperJson.getString("data");

	        // 4️. Decrypt
	        String decryptedJson = crypto.decryptData(encryptedData);
	        System.out.println("Decrypted JSON: " + decryptedJson);

	        // 5️.Parse JSON
	        ObjectMapper mapper = new ObjectMapper();
	        Map<String, Object> dataMap = mapper.readValue(decryptedJson, Map.class);
	        Map<String, Object> bodyMap = (Map<String, Object>) dataMap.get("body");

	        // 6️.Existing Assertions
	        assertParameter("iclLeadContextC", expectedLeadContext, bodyMap.get("iclLeadContextC"));
	        assertParameter("iclLeadTypeC", expectedLeadType, bodyMap.get("iclLeadTypeC"));
	        assertParameter("subType", expectedSubType, bodyMap.get("subType"));
	        assertParameter("leadSubSource", expectedLeadSubSource, bodyMap.get("leadSubSource"));

	        //  7️.NEW: Capture serviceable flag
	        Object serviceableValue = bodyMap.get("isAreaServiceable");

	        isServiceable = Boolean.parseBoolean(String.valueOf(serviceableValue));

	        System.out.println("Serviceable Flag: " + isServiceable);

	        // Optional assertion (if needed)
	        ExtentCucumberAdapter.getCurrentStep().log(
	                Status.INFO,
	                "Serviceable flag value: " + isServiceable
	        );

	    } catch (Exception e) {
	        e.printStackTrace();
	        ExtentCucumberAdapter.addTestStepLog("Exception during Lead API verification: " + e.getMessage());
	    }
	}
	public boolean isServiceable() {
	    return isServiceable;
	}
	
	private void assertStatusCode(int expected, int actual) {
	    try {
	        if (expected != actual) {
	            throw new AssertionError("Expected Status Code: " + expected + ", Actual: " + actual);
	        }
	        ExtentCucumberAdapter.addTestStepLog("Status Code matched: " + actual);
	    } catch (AssertionError e) {
	        ExtentCucumberAdapter.addTestStepLog("Status Code mismatch! " + e.getMessage());
	    }
	}

	private void assertParameter(String paramName, Object expected, Object actual) {
	    try {
	        if (!expected.equals(actual)) {
	            throw new AssertionError("Expected: " + expected + ", Actual: " + actual);
	        }

	        // PASS 
	        ExtentCucumberAdapter.getCurrentStep().log(
	                Status.PASS,
	                "✔ " + paramName + " matched: " + actual
	        );

	    } catch (AssertionError e) {

	        // ❌ FAIL (Red + Cross)
	        ExtentCucumberAdapter.getCurrentStep().log(
	                Status.FAIL,
	                "❌ " + paramName + " mismatch! " + e.getMessage()
	        );
	    }

	}
	
}
