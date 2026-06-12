package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import io.cucumber.java.en.When;
import pagefunctions.ColourLetter_SignUp_Page;
import pagefunctions.End_to_End_Flow_Page;
import pagefunctions.Painting_Service_Form_Page;

public class Painting_Service_Form_Step {

	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	Painting_Service_Form_Page pf = new Painting_Service_Form_Page();
	ColourLetter_SignUp_Page cl = new ColourLetter_SignUp_Page();
	CommonDataGenerator dataGenerator = new CommonDataGenerator();

	End_to_End_Flow_Page ep = new End_to_End_Flow_Page();
	String fakeFirstName = dataGenerator.generateFakeFirstName();
	String fakeLastName = dataGenerator.generateFakeLastName();
	String fakeMobileNumber = dataGenerator.generateFakeMobileNumber();
	String fakeEmailId = dataGenerator.generateFakeEmail();

	String previousPageUrl;

	@When("User enters a valid name in the Painting Service Form")
	public void user_enters_a_valid_name_in_the_painting_service_form() throws InterruptedException {
		js.scrollUntilElementVisible(pf.DetailSection);
		Thread.sleep(2000);
		wait.waitForElementVisible(pf.NameFiled);
		js.scrollUntilElementVisible(pf.NameFiled);
		pf.NameFiled.clear();
		Thread.sleep(2000);
		common.SendInput(fakeFirstName, pf.NameFiled);
	}

	@When("User enters a valid mobile number in the Painting Service Form")
	public void user_enters_a_valid_mobile_number_in_the_painting_service_form() throws InterruptedException {
		wait.waitForElementVisible(pf.MobileNumberFiled);
		js.scrollUntilElementVisible(pf.MobileNumberFiled);
		pf.MobileNumberFiled.clear();
		Thread.sleep(2000);
		common.SendInput(fakeMobileNumber, pf.MobileNumberFiled);
	}

	@When("User clicks on the Sign up for free button")
	public void user_clicks_on_the_sign_up_for_free_button() throws InterruptedException {
		wait.waitForElementVisible(pf.SignupfreeButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(pf.SignupfreeButton);
		Thread.sleep(2000);
		js.jsClickWithWait(pf.SignupfreeButton);
		Thread.sleep(2000);
	}

	@When("User enters a valid pin code {string} in the Painting Service Form")
	public void user_enters_a_valid_pin_code_in_the_painting_service_form(String string) throws InterruptedException {
		wait.waitForElementVisible(pf.PincodeField);
		js.scrollUntilElementVisible(pf.PincodeField);
		pf.PincodeField.clear();
		Thread.sleep(2000);
		common.SendInput(string, pf.PincodeField);
	}

	@When("User clicks on the Submit button in the Painting Service Form")
	public void user_clicks_on_the_submit_button_in_the_painting_service_form() throws InterruptedException {
		wait.waitForElementVisible(pf.submitButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(pf.submitButton);
		Thread.sleep(2000);
		js.jsClickWithWait(pf.submitButton);
		Thread.sleep(2000);
	}

	@When("User clicks on the Back button")
	public void user_clicks_on_the_back_button() throws InterruptedException {

		previousPageUrl = DriverManager.getDriver().getCurrentUrl();
		wait.waitForElementVisible(pf.BackButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(pf.BackButton);
		Thread.sleep(2000);
		js.jsClickWithWait(pf.BackButton);
		Thread.sleep(2000);
	}

	@Then("User should be navigated to the previous page")
	public void user_should_be_navigated_to_the_previous_page() {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(40));

		wait.until(ExpectedConditions.urlToBe(previousPageUrl));

		Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), previousPageUrl,
				"User did NOT navigate to the previous page");
	}

	@Then("User enters an invalid pin code {string} in the Painting Service Form")
	public void user_enters_an_invalid_pin_code_in_the_painting_service_form(String string)
			throws InterruptedException {
		wait.waitForElementVisible(pf.PincodeField);
		js.scrollUntilElementVisible(pf.PincodeField);
		pf.PincodeField.clear();
		Thread.sleep(2000);
		common.SendInput(string, pf.PincodeField);
	}

	@Then("An error message should be displayed for invalid pin code {string}")
	public void an_error_message_should_be_displayed_for_invalid_pin_code (String string) {
		wait.waitForElementVisible(pf.ErrMessageInvalidPincode);
		String Errmsg = common.getElementText(pf.ErrMessageInvalidPincode);
		common.compareText(Errmsg, string);
	}

	@Then("validation message for Pincode empty input fields should get displayed {string}")
	public void validation_message_for_pincode_empty_input_fields_should_get_displayed(String string) {
		wait.waitForElementVisible(pf.ErrMessageInvalidPincode);
		String Errmsg = common.getElementText(pf.ErrMessageInvalidPincode);
		common.compareText(Errmsg, string);
	}
	@When("User clicks on Painting made easy")
	public void user_clicks_on_painting_made_easy() throws InterruptedException {
		
		wait.waitForElementVisible(pf.PaintingmadeEasySection);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(pf.PaintingmadeEasySection);
		Thread.sleep(2000);
		js.jsClickWithWait(pf.PaintingmadeEasySection);
		Thread.sleep(2000);
	}
	@Then("User clicks on Get free quote")
	public void user_clicks_on_get_free_quote() throws InterruptedException {
		wait.waitForElementVisible(pf.GetfreequoteButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(pf.GetfreequoteButton);
		Thread.sleep(2000);
		js.jsClickWithWait(pf.GetfreequoteButton);
		Thread.sleep(2000);
	}
}
