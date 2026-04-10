package stepdefinition;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import commonutilities.ActionClass;
import commonutilities.ClickElement;
import commonutilities.CommonDataGenerator;
import commonutilities.CommonMethods;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pagefunctions.Book_Survey_Form_Page;
import pagefunctions.ColourLetter_SignUp_Page;
import pagefunctions.Create_an_Account_Form_Page;
import pagefunctions.Painting_Made_Easy_Page;
import pagefunctions.Painting_Service_Form_Page;
import pagefunctions.Sign_In_Functionality_Page;

public class Painting_Made_Easy_Step {
	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	Painting_Service_Form_Page pf = new Painting_Service_Form_Page();
	ColourLetter_SignUp_Page cl = new ColourLetter_SignUp_Page();

	Painting_Made_Easy_Page pm = new Painting_Made_Easy_Page();
	Book_Survey_Form_Page bs = new Book_Survey_Form_Page();
	Sign_In_Functionality_Page sp = new Sign_In_Functionality_Page();
	Create_an_Account_Form_Page cfp = new Create_an_Account_Form_Page();

	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	String fakeFirstName = dataGenerator.generateFakeFirstName();
	String fakeLastName = dataGenerator.generateFakeLastName();
	String fakeEmail = dataGenerator.generateFakeEmail();

	@Then("verify the lead API parameters for Painting Made Easy form: iclLeadContextC against value {string},  iclLeadTypeC against value {string},  iclSubType against value {string},  leadSubSource against value {string}")
	public void verify_the_lead_api_parameters_for_painting_made_easy_form_icl_lead_context_c_against_value_icl_lead_type_c_against_value_icl_sub_type_against_value_lead_sub_source_against_value(
			String expectedLeadContext, String expectedLeadType, String expectedSubType, String expectedLeadSubSource) {
		bs.verifyLeadApiParameters(expectedLeadContext, expectedLeadType, expectedSubType, expectedLeadSubSource);
	}

	@Then("User select home configuration type {string}")
	public void user_select_home_configuration_type(String string) {
		pm.selectHomeConfigurationType(string);
	}

	@Then("User selects a schedule visit date and timeslot {string}")
	public void user_selects_a_schedule_visit_date_and_timeslot(String string) throws InterruptedException {
		wait.waitForElementVisible(pm.ScheduleVisitText);
		pm.selectAvailableDate();

		pm.selectTimeSlotByText(string);
		Thread.sleep(3000);

	}

	@Then("User clicks on the Schedule button")
	public void user_clicks_on_the_schedule_button() throws InterruptedException {
		pm.clickScheduleButton();
		Thread.sleep(2000);
	}

	@Then("User clicks on the I’ll do it later option")
	public void user_clicks_on_the_i_ll_do_it_later_option() throws InterruptedException {
		wait.waitForElementVisible(pm.DoLetterButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(pm.DoLetterButton);
		Thread.sleep(2000);
		js.jsClickWithWait(pm.DoLetterButton);
		Thread.sleep(2000);
	}

	@Then("User enters unserviceable Pin code {string}")
	public void user_enters_unserviceable_pin_code(String string) throws InterruptedException {

		pm.enterSurveyPinCode(string, bs.surveyPincodeInputfiled);
	}

	@Then("the unserviceable pin code message should be displayed")
	public void the_unserviceable_pin_code_message_should_be_displayed() {
		wait.waitForElementVisible(pm.unserviceablePinPopup);
		Assert.assertTrue(pm.unserviceablePinPopup.isDisplayed(),
				"unserviceable pincode pop up message is not displayed");
	}

	@Then("User clicks on the Skip for now option in the painting requirements question")
	public void user_clicks_on_the_skip_for_now_option_in_the_painting_requirements_question()
			throws InterruptedException {
		wait.waitForElementVisible(pm.SkipQuestion);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(pm.SkipQuestion);
		Thread.sleep(2000);
		js.jsClickWithWait(pm.SkipQuestion);
		Thread.sleep(2000);
	}

	@Then("User clicks on the Skip for now option in the home configuration question")
	public void user_clicks_on_the_skip_for_now_option_in_the_home_configuration_question()
			throws InterruptedException {
		wait.waitForElementVisible(pm.SkipQuestion);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(pm.SkipQuestion);
		Thread.sleep(2000);
		js.jsClickWithWait(pm.SkipQuestion);
		Thread.sleep(2000);
	}

	@Then("User enter invalid mobile number {string}")
	public void user_enter_invalid_mobile_number(String string) {
		wait.waitForElementVisible(cfp.CreateAccountMobileNumberFiled);
		cfp.CreateAccountMobileNumberFiled.sendKeys(string);

	}

	@Then("User should see an message Please enter valid mobile number {string}")
	public void user_should_see_an_message_please_enter_valid_mobile_number(String expectedMessage)
			throws InterruptedException {

		wait.waitForElementVisible(pm.mobileNoErrorMsg);
		String actualMessage = pm.mobileNoErrorMsg.getText();

		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
		Thread.sleep(5000);
	}

	@Then("User enter invalid first name on painting made easy {string}")
	public void user_enter_invalid_first_name_on_painting_made_easy(String string) {
		wait.waitForElementToBeVisible(pm.firstNameField, 10);
		pm.firstNameField.sendKeys(string);
	}

	@Then("User enter valid last name on painting made easy")
	public void user_enter_valid_last_name_on_painting_made_easy() throws InterruptedException {
		wait.waitForElementVisible(pm.lastNameField);
		js.scrollUntilElementVisible(pm.lastNameField);
		pm.lastNameField.sendKeys(fakeLastName);
		Thread.sleep(2000);

	}

	@Then("User enter valid email id on painting made easy")
	public void user_enter_valid_email_id_on_painting_made_easy() throws InterruptedException {
		wait.waitForElementVisible(pm.emailIDField);
		js.scrollUntilElementVisible(pm.emailIDField);
		pm.emailIDField.sendKeys(fakeEmail);
		Thread.sleep(2000);
	}

	@Then("User clicks on save details button")
	public void user_clicks_on_save_details_button() {
		wait.waitForElementToBeClickable(pm.saveDetailsCta, 10);
		pm.saveDetailsCta.click();
	}

	@Then("User enter valid first name on painting made easy")
	public void user_enter_valid_first_name_on_painting_made_easy() throws InterruptedException {
		wait.waitForElementVisible(pm.firstNameField);
		js.scrollUntilElementVisible(pm.firstNameField);
		pm.firstNameField.sendKeys(fakeFirstName);
		Thread.sleep(2000);
	}

	@Then("User enter invalid last name on painting made easy {string}")
	public void user_enter_invalid_last_name_on_painting_made_easy(String string) {
		wait.waitForElementToBeVisible(pm.lastNameField, 10);
		pm.lastNameField.sendKeys(string);
	}

	@Then("User enter invalid email id on painting made easy {string}")
	public void user_enter_invalid_email_id_on_painting_made_easy(String string) {
		wait.waitForElementToBeVisible(pm.emailIDField, 10);
		pm.emailIDField.sendKeys(string);
	}

	@Then("User enter invalid mobile number on sign in {string}")
	public void user_enter_invalid_mobile_number_on_sign_in(String string) {
		wait.waitForElementVisible(pm.mobileNoSignInField);
		pm.mobileNoSignInField.sendKeys(string);
	}

	@Then("User enter valid otp {string}")
	public void user_enter_valid_otp(String string) throws InterruptedException {
		wait.waitForElementToBeVisible(pm.otpField, 10);
		pm.otpField.sendKeys(string);
		Thread.sleep(5000);
	}

	@Then("User click on verify button")
	public void user_click_on_verify_button() throws InterruptedException {
		wait.waitForElementToBeClickable(pm.verifyButton, 10);
		pm.verifyButton.click();
		Thread.sleep(5000);
	}
	@Then("User empty flat no field.")
	public void user_empty_flat_no_field() {
		wait.waitForElementVisible(pm.flatNoField);

		pm.flatNoField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		pm.flatNoField.sendKeys(Keys.BACK_SPACE);  
		pm.flatNoField.sendKeys(Keys.TAB); 
	}
	@Then("User empty property name field.")
	public void user_empty_property_name_field() {
		wait.waitForElementVisible(pm.propertyNameField);
		
		pm.propertyNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		pm.propertyNameField.sendKeys(Keys.BACK_SPACE);  
		pm.propertyNameField.sendKeys(Keys.TAB); 
	}


 @Then("User should see an error message for flat no. {string}")
	public void user_should_see_an_error_message_for_flat_no(String expectedMessage) throws InterruptedException {
		wait.waitForElementVisible(pm.flatNoErrorMsg);
		String actualMessage = pm.flatNoErrorMsg.getText();

		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
		Thread.sleep(5000);
	}

	@Then("User should see an error message for property name {string}")
	public void user_should_see_an_error_message_for_property_name(String expectedMessage) throws InterruptedException {
		wait.waitForElementVisible(pm.propertyNameErrorMsg);
		String actualMessage = pm.propertyNameErrorMsg.getText();

		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
		Thread.sleep(5000);
	}
	
	@Given("User enter valid mobile number on sign in {string}")
	public void user_enter_valid_mobile_number_on_sign_in(String string) throws InterruptedException {
		wait.waitForElementVisible(sp.signInMobileNumberFiled);
		Thread.sleep(1000); // small stabilization
		sp.signInMobileNumberFiled.click();
		sp.signInMobileNumberFiled.sendKeys(string);
		Thread.sleep(1000);
	}
	@Then("User click on sign in button")
	public void user_click_on_sign_in_button() throws InterruptedException {
		js.jsClickWithWait(sp.signButtonAfterMobileNumber);
		Thread.sleep(2000);
	}
	@Then("User click next button on just a few more details")
	public void user_click_next_button_on_just_a_few_more_details() {
		js.scrollUntilElementVisible(pm.justFewMoreDetailsNextButton);
		wait.waitForElementVisible(pm.justFewMoreDetailsNextButton);
		pm.justFewMoreDetailsNextButton.click();
	}

	@Then("User click next button on tell us about your project")
	public void user_click_next_button_on_tell_us_about_your_project() {
		
		js.scrollUntilElementVisible(pm.tellUsAboutYourProjectNextButton);
		wait.waitForElementVisible(pm.tellUsAboutYourProjectNextButton);
		pm.tellUsAboutYourProjectNextButton.click();
	}

	@Then("User should see an error message as please select an option {string}")
	public void user_should_see_an_error_message_as_please_select_an_option(String expectedMessage) throws InterruptedException {

	    Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
	            .withTimeout(Duration.ofSeconds(10))
	            .pollingEvery(Duration.ofMillis(200));

	    boolean isMessagePresent = wait.until(driver ->
	            driver.getPageSource().contains(expectedMessage)
	    );

	    Assert.assertTrue(isMessagePresent, "Toast message not found: " + expectedMessage);

	 
	}
	
	@Then("User click on next button on Site Details")
	public void user_click_on_next_button_on_site_details() {
	  wait.waitForElementVisible(pm.nextButton);
	  js.scrollUntilElementVisible(pm.nextButton);
	  js.jsClickWithWait(pm.nextButton);
	}

}
