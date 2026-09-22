package stepdefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import commonutilities.CommonDataGenerator;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.Book_Survey_Form_Page;
import pagefunctions.Opus_Assurance_Journey_Page;
import pagefunctions.Painting_Made_Easy_Page;
import pagefunctions.Sign_In_Functionality_Page;
import pagefunctions.WebsiteLaunch;

public class Opus_Assurance_Journey_Step {
	public WebDriver driver;
//	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	private String firstName;
	Painting_Made_Easy_Page pm = new Painting_Made_Easy_Page();
	Opus_Assurance_Journey_Page op = new Opus_Assurance_Journey_Page();
	JSExecutor js = new JSExecutor();
	
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	String fakeFirstName = dataGenerator.generateFakeFirstName();
	String fakeLastName = dataGenerator.generateFakeLastName();
	String fakeEmail = dataGenerator.generateFakeEmail();
	Book_Survey_Form_Page bs = new Book_Survey_Form_Page();
	String fakeProjectName = dataGenerator.generateProjectName();
	Sign_In_Functionality_Page sp = new Sign_In_Functionality_Page();

	@When("User clicks on Register Now Cta on home page")
	public void user_clicks_on_register_now_cta_on_home_page() throws InterruptedException {
		js.jsClickWithWait(op.registerNowCta);
		Thread.sleep(5000);
	}

	@Given("User is on Opus Assurance Journey {string}")
	public void user_is_on_opus_assurance_journey(String AssuranceUrl) {
		WebsiteLaunch.webLaunch(AssuranceUrl);
		 String currentUrl = DriverManager.getDriver().getCurrentUrl();

		    ExtentCucumberAdapter.addTestStepLog("  Opus Assurance URL : " + currentUrl);
	}

	@When("User enter valid paintable area {string}")
	public void user_enter_valid_paintable_area(String string) throws InterruptedException {
		wait.waitForElementVisible(op.paintableAreaField);
		js.scrollUntilElementVisible(op.paintableAreaField);
//	    js.sendKeysUsingJS(op.paintableAreaField, string);
		op.paintableAreaField.sendKeys(string);
		Thread.sleep(5000);
	}

	@When("User click on Next button")
	public void user_click_on_next_button() throws InterruptedException {
		js.scrollUntilElementVisible(op.nextCta);
//		op.paintableAreaField.click();
//	    js.jsClickWithWait(op.nextCta);
		Thread.sleep(5000);
		op.nextCta.click();
	}

	@Then("User should click on Yet to Start Cta")
	public void user_should_click_on_yet_to_start_cta() {
	wait.waitForElementVisible(op.yetToStart);
		js.jsClickWithWait(op.yetToStart);
	}

	@Then("User click on Pre-register now Cta")
	public void user_click_on_pre_register_now_cta() {
		wait.waitForElementVisible(op.preRegisterCta);
		js.jsClickWithWait(op.preRegisterCta);
	}

	@Then("User clicks on the Create an account option")
	public void user_clicks_on_the_create_an_account_option() {
		op.CreateAccountOption.click();
	}

	@Then("User enter valid first name")
	public void user_enter_valid_first_name() throws InterruptedException {
		firstName = fakeFirstName;
		wait.waitForElementVisible(op.firstNameField);
		op.firstNameField.sendKeys(fakeFirstName);
		Thread.sleep(2000);
	}

	@Then("User enter valid last name")
	public void user_enter_valid_last_name() throws InterruptedException {
		wait.waitForElementVisible(op.lastNameField);
		js.scrollUntilElementVisible(op.lastNameField);
		op.lastNameField.sendKeys(fakeLastName);
		Thread.sleep(2000);
	}

	@Then("User enter valid email id")
	public void user_enter_valid_email_id() throws InterruptedException {
		js.scrollUntilElementVisible(op.emailIdField);
		op.emailIdField.sendKeys(fakeEmail);
		Thread.sleep(2000);
	}

	@Then("User enter valid pin code {string}")
	public void user_enter_valid_pin_code(String string) {
		js.scrollUntilElementVisible(op.pincodeField);
		op.pincodeField.sendKeys(string);
	}
	@Then("User enter unserviceable pincode {string}")
	public void user_enter_unserviceable_pincode(String string) {
		js.scrollUntilElementVisible(op.pincodeField);
		op.pincodeField.sendKeys(string);
	}

	@Then("User click on the Save Details button")
	public void user_click_on_the_save_details_button() throws InterruptedException {
		js.scrollUntilElementVisible(op.submitButtonCta);
		op.submitButtonCta.click();
		Thread.sleep(5000);
	}

	@Then("User should see an message We re coming soon! {string}")
	public void user_should_see_an_message_we_re_coming_soon(String expectedMessage) throws InterruptedException {
		wait.waitForElementVisible(op.weAreComingSoonText);
		String actualMessage = op.weAreComingSoonText.getText();

		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
		Thread.sleep(5000);
	}

	@Then("User enter invalid first name {string}")
	public void user_enter_invalid_first_name(String string) {
		wait.waitForElementVisible(op.firstNameField);
		js.sendKeysUsingJS(op.firstNameField, string);
		// op.firstNameField.sendKeys(string);
	}
	@Then("User click on verify otp cta")
	public void user_click_on_verify_otp_cta() {
		wait.waitForElementToBeClickable(op.verifyButton, 30);
		js.jsClickWithWait(op.verifyButton);
	}


	@Then("User enter invalid last name {string}")
	public void user_enter_invalid_last_name(String string) {
//		wait.waitForElementVisible(op.lastNameField);
//		js.sendKeysUsingJS(op.lastNameField, string);
		op.lastNameField.sendKeys(string);
	}

	@Then("User enter invalid email id {string}")
	public void user_enter_invalid_email_id(String string) throws InterruptedException {
		js.scrollUntilElementVisible(op.emailIdField);
		wait.waitForElementToBeVisible(op.emailIdField, 30);
//		js.sendKeysUsingJS(op.emailIdField, string);
		op.emailIdField.sendKeys("testtestgmail.com");
		Thread.sleep(3000);
	}

	@Then("User enter invalid pin code {string}")
	public void user_enter_invalid_pin_code(String string) throws InterruptedException {
		js.scrollUntilElementVisible(op.pincodeField);
		wait.waitForElementToBeVisible(op.pincodeField, 30);
		js.sendKeysUsingJS(op.pincodeField, string);
//		op.pincodeField.sendKeys(string);
		Thread.sleep(3000);
	}

	@Then("User should see an error message for email id {string}")
	public void user_should_see_an_error_message_for_email_id(String expectedMessage) throws InterruptedException {
		wait.waitForElementVisible(op.emailIdErrorMsg);
		String actualMessage = op.emailIdErrorMsg.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
//		Thread.sleep(5000);	
	}

	@Then("User should see an error message for pin code {string}")
	public void user_should_see_an_error_message_for_pin_code(String expectedMessage) throws InterruptedException {
		wait.waitForElementVisible(op.pincodeErrorMsg);
		String actualMessage = op.pincodeErrorMsg.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
//		Thread.sleep(5000);	
	}

	@Then("User should see an error message for last name {string}")
	public void user_should_see_an_error_message_for_last_name(String expectedMessage) {
		wait.waitForElementVisible(op.lastNameErrorMsg);
		String actualMessage = op.lastNameErrorMsg.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
	}

	@Then("User should see an error message for first name {string}")
	public void user_should_see_an_error_message_for_first_name(String expectedMessage) {
		wait.waitForElementVisible(op.lastNameErrorMsg);
		String actualMessage = op.lastNameErrorMsg.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
	}

	@Then("User enter invalid otp {string}")
	public void user_enter_invalid_otp(String string) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement firstOtpField = wait.until(ExpectedConditions.visibilityOf(op.otpNoInputFiled.get(0)));

//		firstOtpField.clear();
		firstOtpField.sendKeys(string);
	}
	@Then("User enter wrong otp {string}")
	public void user_enter_wrong_otp(String string) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement firstOtpField = wait.until(ExpectedConditions.visibilityOf(op.otpNoInputFiled.get(0)));

//		firstOtpField.clear();
		firstOtpField.sendKeys(string);
	}


	@Then("User should see an error message for otp {string}")
	public void user_should_see_an_error_message_for_otp(String expectedMessage) {
		wait.waitForElementVisible(op.otpFieldErrorMsg);
		js.scrollUntilElementVisible(op.otpFieldErrorMsg);
		String actualMessage = op.otpFieldErrorMsg.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
	}

	@When("User enter paintable area {string}")
	public void user_enter_paintable_area(String string) throws InterruptedException {
		js.scrollUntilElementVisible(op.paintableAreaField);
//		    js.sendKeysUsingJS(op.paintableAreaField, string);
		op.paintableAreaField.sendKeys(string);
		Thread.sleep(5000);
	}

	@Then("User should see an error message for paintable area  {string}")
	public void user_should_see_an_error_message_for_paintable_area(String expectedMessage) {
		wait.waitForElementVisible(op.ErrMessagePaintingCarpetArea);
		js.scrollUntilElementVisible(op.ErrMessagePaintingCarpetArea);
		String actualMessage = op.ErrMessagePaintingCarpetArea.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "error message mismatch");
	}

	@Then("User click on start new project")
	public void user_click_on_start_new_project() throws InterruptedException {
		js.scrollUntilElementVisible(op.startNewProjectButton);
		wait.waitForElementVisible(op.startNewProjectButton);
		js.jsClickWithWait(op.startNewProjectButton);
		Thread.sleep(4000);
	}

	@Then("User enter Site Details project name")
	public void user_enter_site_details_project_name() throws InterruptedException {

		js.scrollUntilElementVisible(op.siteDetailsProjectName);
		wait.waitForElementVisible(op.siteDetailsProjectName);
		op.siteDetailsProjectName.click();
		Thread.sleep(6000);
		op.siteDetailsProjectName.clear();
		Thread.sleep(6000);
		op.siteDetailsProjectName.sendKeys(fakeProjectName);
		Thread.sleep(6000);
	}

	@Then("User click submit button on Site Details")
	public void user_click_submit_button_on_site_details() throws InterruptedException {

		js.scrollUntilElementVisible(op.submitButtonSiteDetails);
		wait.waitForElementVisible(op.submitButtonSiteDetails);
		js.jsClickWithWait(op.submitButtonSiteDetails);
        Thread.sleep(6000);
	}
	
	@Then("User click submit button on Address section page for opus assurance")
	public void user_click_submit_button_on_address_section_page_for_opus_assurance() throws InterruptedException {
		js.scrollUntilElementVisible(op.submitButtonSiteDetails);
		wait.waitForElementVisible(op.submitButtonSiteDetails);
		js.jsClickWithWait(op.submitButtonSiteDetails);
         Thread.sleep(6000);
		
	}


	@Then("User click submit button on Address section page")
	public void user_click_submit_button_on_address_section_page() throws InterruptedException {

		js.scrollUntilElementVisible(op.submitButtonSiteDetails);
		wait.waitForElementVisible(op.submitButtonSiteDetails);
		js.jsClickWithWait(op.submitButtonSiteDetails);
        Thread.sleep(6000);
	}

	@Then("User selects a schedule visit date and timeslot")
	public void user_selects_a_schedule_visit_date_and_timeslot() throws InterruptedException {
		op.selectAvailableDate();
		Thread.sleep(4000);
		op.selectAvailableTimeSlot();
	}

	@Then("User enter valid pin code on enter details {string}")
	public void user_enter_valid_pin_code_on_enter_details(String string) throws InterruptedException {
	
		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Find the element 
		WebElement pinCode = wait.until(
		        ExpectedConditions.presenceOfElementLocated(By.id("warrantyPincode")));

		// Scroll directly to the element
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", pinCode);

		// Wait a moment for the scroll animation/render
		Thread.sleep(1000);

		// If a sticky header overlaps it
		js.executeScript("window.scrollBy(0,-100);");

		// Use JavaScript to set the value
		js.executeScript("arguments[0].value='500002';", pinCode);

		// Trigger input/change events
		js.executeScript(
		    "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
		    "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
		    pinCode);
	}

	@Then("User enter update pin code on site details {string}")
	public void user_enter_update_pin_code_on_site_details(String string) throws InterruptedException {

		js.scrollUntilElementVisible(op.pincodeSiteDetails);
		wait.waitForElementVisible(op.pincodeSiteDetails);
		op.pincodeSiteDetails.click();
		op.pincodeSiteDetails.sendKeys(Keys.CONTROL + "a");
		op.pincodeSiteDetails.sendKeys(Keys.DELETE);
		op.pincodeSiteDetails.sendKeys(string);
	}
	
	
	@Then("User enter unserviceable pincode on site details {string}")
	public void user_enter_unserviceable_pincode_on_site_details(String string) {
		js.scrollUntilElementVisible(op.pincodeSiteDetails);
		wait.waitForElementVisible(op.pincodeSiteDetails);
		op.pincodeSiteDetails.click();
		op.pincodeSiteDetails.sendKeys(Keys.CONTROL + "a");
		op.pincodeSiteDetails.sendKeys(Keys.DELETE);
		op.pincodeSiteDetails.sendKeys(string);
	}

	@Then("User click next button on Just a Few More Details")
	public void user_click_next_button_on_just_a_few_more_details() {
		js.scrollUntilElementVisible(op.nextButtonFewMoreDetails);
		wait.waitForElementVisible(op.nextButtonFewMoreDetails);
		js.jsClickWithWait(op.nextButtonFewMoreDetails);
	}

	@Then("User click on verify otp button")
	public void user_click_on_verify_otp_button() throws InterruptedException {
		wait.waitForElementToBeClickable(op.verifyButton, 30);
		op.verifyButton.click();
		Thread.sleep(2000);
		
	}

	@Then("User click on submit button on enter details")
	public void user_click_on_submit_button_on_enter_details() throws InterruptedException {

		js.scrollUntilElementVisible(op.submitButtonEnterDetails);
		wait.waitForElementVisible(op.submitButtonEnterDetails);
		js.jsClickWithWait(op.submitButtonEnterDetails);
		//wait.waitForElementVisible(op.siteDetailsProjectName);
	}

	@Then("User clcik on Sign Up for PaintCraft button")
	public void user_clcik_on_sign_up_for_paint_craft_button() throws InterruptedException {
		js.scrollUntilElementVisible(op.SignUpPaintCraftButton);
		wait.waitForElementVisible(op.SignUpPaintCraftButton);
		js.jsClickWithWait(op.SignUpPaintCraftButton);
		Thread.sleep(5000);
	}

	@Then("Birla Opus Assurance confirmation message should be displayed successfully {string}")
	public void birla_opus_assurance_confirmation_message_should_be_displayed_successfully(String string) {
		wait.waitForElementVisible(op.ConfirmationMessage);
		String actualText = op.ConfirmationMessage.getText().trim();
		System.out.println("Confirmation Message: " + actualText);

		Assert.assertTrue(actualText.contains("Thank you for signing up"),
				"Confirmation message not displayed properly");
	}

	@Then("Birla Opus Assurance confirmation message should be displayed successfully")
	public void birla_opus_assurance_confirmation_message_should_be_displayed_successfully() {

	}

	@Then("User clcik on Find Contractor button")
	public void user_clcik_on_find_contractor_button() throws InterruptedException {
		js.scrollUntilElementVisible(op.FindContractorbutton);
		wait.waitForElementVisible(op.FindContractorbutton);
		js.jsClickWithWait(op.FindContractorbutton);
		Thread.sleep(5000);
	}

	@Then("User selects a contractor as needed")
	public void user_selects_a_contractor_as_needed() throws InterruptedException {
		wait.waitForElementVisible(op.selectFirstContractor);
		js.scrollUntilElementVisible(op.selectFirstContractor);
		js.jsClickWithWait(op.selectFirstContractor);
		Thread.sleep(3000);
	}

	@Then("User click on Next button in Find Contractor section")
	public void user_click_on_next_button_in_find_contractor_section() throws InterruptedException {
		js.scrollUntilElementVisible(op.NextButtonFindContractor);
		wait.waitForElementVisible(op.NextButtonFindContractor);
		js.jsClickWithWait(op.NextButtonFindContractor);
		Thread.sleep(3000);
	}

	@Then("verify the lead API parameters for opus assurance journey: iclLeadContextC against value {string},  iclLeadTypeC against value {string},  iclSubType against value {string},  leadSubSource against value {string}")
	public void verify_the_lead_api_parameters_for_opus_assurance_journey_icl_lead_context_c_against_value_icl_lead_type_c_against_value_icl_sub_type_against_value_lead_sub_source_against_value(
			String expectedLeadContext, String expectedLeadType, String expectedSubType, String expectedLeadSubSource) {
		bs.verifyLeadApiParameters(expectedLeadContext, expectedLeadType, expectedSubType, expectedLeadSubSource);
	}

	@Then("User clicks on tell us more about your site button")
	public void user_clicks_on_tell_us_more_about_your_site_button() {
		wait.waitForElementVisible(op.TellUsMoreaAoutYourSiteButton);
		js.scrollUntilElementVisible(op.TellUsMoreaAoutYourSiteButton);
		js.jsClickWithWait(op.TellUsMoreaAoutYourSiteButton);
	}

	@Then("User clicks on submit button on the Create an Account")
	public void user_clicks_on_submit_button_on_the_create_an_account() {
		js.scrollUntilElementVisible(op.submitButtoncreateAccount);
		wait.waitForElementVisible(op.submitButtoncreateAccount);
		js.jsClickWithWait(op.submitButtoncreateAccount);

	}

	@Then("User clicks create an Account option")
	public void user_clicks_create_an_account_option() {
		js.scrollUntilElementVisible(op.CreateAnaccountOptioNlink);
		wait.waitForElementVisible(op.CreateAnaccountOptioNlink);
		js.jsClickWithWait(op.CreateAnaccountOptioNlink);
	}

	@Then("User enter flat number or bulding name {string}")
	public void user_enter_flat_number_or_bulding_name(String string) throws InterruptedException {
		js.scrollUntilElementVisible(op.flatNoInputField);
		op.flatNoInputField.sendKeys(string);
		Thread.sleep(2000);
	}

	@Then("User enter proerty name {string}")
	public void user_enter_proerty_name(String string) throws InterruptedException {

		js.scrollUntilElementVisible(op.propertyNameInputField);

		op.propertyNameInputField.sendKeys(string);
		wait.waitForElementVisible(op.clickFirstAddress);
		js.jsClickWithWait(op.clickFirstAddress);

	}

	@Then("User should see the heading {string}")
	public void user_should_see_the_heading(String expectedHeading) {
		String heading = DriverManager.getDriver()
				.findElement(By.xpath("//h4[normalize-space()=\"We're coming soon!\"]")).getText();

		if (!heading.equals(expectedHeading)) {
			throw new AssertionError("Heading mismatch\nExpected: " + expectedHeading + "\nActual: " + heading);
		}
	}

	@Then("User should see the message {string}")
	public void user_should_see_the_message(String expectedText) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(40));

		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Birla Opus Assurance is currently not available in your location')]")));

		// Wait until text is NOT empty
		wait.until(driver -> !element.getText().trim().isEmpty());

		String actualText = element.getText().trim();

		System.out.println("Actual Text: " + actualText);

		if (!actualText.contains(expectedText)) {
			throw new AssertionError("Message mismatch\nExpected: " + expectedText + "\nActual: " + actualText);
		}
	}

	@Then("User clicks on confirm and add address details")
	public void user_clicks_on_confirm_and_add_address_details() {
		js.scrollUntilElementVisible(op.confirmAddAddressBtn);
		wait.waitForElementVisible(op.confirmAddAddressBtn);

		js.jsClickWithWait(op.confirmAddAddressBtn);
	}

	@Then("User should be displayed login page")
	public void user_should_be_displayed_login_page() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

			// Locator for "Let's get started" text
			By loginHeader = By.xpath("//*[contains(text(),\"Let's get started\")]");

			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));

			Assert.assertTrue(element.isDisplayed(), "Login page is not displayed");

		} catch (Exception e) {
			throw new AssertionError("Login page validation failed: " + e.getMessage());
		}
	}

	@Then("User Login page should be displayed successfully")
	public void user_login_page_should_be_displayed_successfully() {
		WebDriver driver = DriverManager.getDriver();

		if (driver == null) {
			throw new RuntimeException("Driver is NULL - not initialized in Hooks");
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		By loginText = By.xpath("//*[contains(text(),\"Let's get started\")]");

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginText));

		Assert.assertTrue(element.isDisplayed(), "Login page not displayed");
	}

	@Then("User selects {int} contractors")
	public void user_selects_contractors(Integer count) {

		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		By selectBtn = By.xpath("//button[normalize-space()='Select this contractor']");

		for (int i = 1; i <= count; i++) {

			List<WebElement> buttons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(selectBtn));

			if (buttons.size() == 0) {
				throw new RuntimeException("No more contractors available to select");
			}

			WebElement btn = buttons.get(0); // always click first available

			wait.until(ExpectedConditions.elementToBeClickable(btn)).click();

			System.out.println("Selected contractor: " + i);
		}
	}

	@Then("User should not be able to select more than {int} contractors and validation message should be displayed")
	public void user_should_not_be_able_to_select_more_than_contractors_and_validation_message_should_be_displayed(
			Integer limit) {
		op.clickSixthContractorAndValidate();
	}

	@Then("User should be displayed validation message Contractor section {string}")
	public void user_should_be_displayed_validation_message_contractor_section(String expectedMessage)
			throws InterruptedException {
		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		By messageLocator = By.xpath("//*[contains(text(),'" + expectedMessage + "')]");

		boolean messageDisplayed = false;

		for (int i = 0; i < 5; i++) {
			if (driver.findElements(messageLocator).size() > 0) {
				messageDisplayed = true;
				break;
			}
			Thread.sleep(600);
		}

		Assert.assertTrue(messageDisplayed, "Validation message not displayed: " + expectedMessage);
	}

	@Then("verify the lead API parameters for opus assurance journey: isAreaServiceable against value {string}")
	public void verify_the_lead_api_parameters_for_opus_assurance_journey_is_area_serviceable_against_value(
			String string) {
		op.verifyServiceableStatus(string);
	}
	@Then("User should not be able to select more than {int} contractors and validation message should be displayed {string}")
	public void user_should_not_be_able_to_select_more_than_contractors_and_validation_message_should_be_displayed(Integer int1, String expectedMessage) {
		  op.verifyToastMessageContractors(expectedMessage);
	}

@When("User enters a valid mobile number on the Sign In page after creating an account")
public void user_enters_a_valid_mobile_number_on_the_sign_in_page_after_creating_an_account() throws InterruptedException {
	wait.waitForElementVisible(sp.signInMobileNumberFiled);
	Thread.sleep(1000); // small stabilization
	sp.signInMobileNumberFiled.click();
	sp.signInMobileNumberFiled.sendKeys("8375978223");
	Thread.sleep(1000);
}

}