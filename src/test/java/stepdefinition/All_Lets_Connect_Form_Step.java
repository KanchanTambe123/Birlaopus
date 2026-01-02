package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.All_Lets_Connect_Form_Page;
import pagefunctions.ColourLetter_SignUp_Page;
import pagefunctions.End_to_End_Flow_Page;
import pagefunctions.WebsiteLaunch;

public class All_Lets_Connect_Form_Step {

	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();

	ColourLetter_SignUp_Page cl = new ColourLetter_SignUp_Page();
	CommonDataGenerator dataGenerator = new CommonDataGenerator();

	End_to_End_Flow_Page ep = new End_to_End_Flow_Page();
	All_Lets_Connect_Form_Page lp = new All_Lets_Connect_Form_Page();

	String fakeFirstName = dataGenerator.generateFakeFirstName();
	String fakeLastName = dataGenerator.generateFakeLastName();
	String fakeMobileNumber = dataGenerator.generateFakeMobileNumber();
	String fakeEmailId = dataGenerator.generateFakeEmail();

	@Given("User is on the Birla Opus Brands page  {string}")
	public void user_is_on_the_birla_opus_brands_page(String BrandUrl) {
		WebsiteLaunch.webLaunch(BrandUrl);
		lp.killDiscountOverlay();
	}

	@And("User navigates to the Let’s Connect form section")
	public void user_navigates_to_the_let_s_connect_form_section() throws InterruptedException {

		wait.waitForElementVisible(lp.phoneNumberField);
		js.scrollUntilElementVisible(lp.phoneNumberField);
		Thread.sleep(2000);
	}

	@Given("User enters a valid Mobile Number on the Let’s Connect form Brands")
	public void user_enters_a_valid_mobile_number_on_the_let_s_connect_form_brands() throws InterruptedException {
		wait.waitForElementVisible(lp.phoneNumberField);
		js.scrollUntilElementVisible(lp.phoneNumberField);
		lp.phoneNumberField.clear();
		Thread.sleep(2000);
		common.SendInput(fakeMobileNumber, lp.phoneNumberField);
	}

	@Given("User enters a valid Name on the Let’s Connect form Brands")
	public void user_enters_a_valid_name_on_the_let_s_connect_form_brands() throws InterruptedException {
		wait.waitForElementVisible(lp.nameField);
		js.scrollUntilElementVisible(lp.nameField);
		lp.nameField.clear();
		Thread.sleep(2000);
		common.SendInput(fakeFirstName, lp.nameField);
	}

	@When("User selects {string} from the How can we help you? dropdown")
	public void user_selects_from_the_how_can_we_help_you_dropdown(String string) throws InterruptedException {
		lp.selectHelpOption(string);
		Thread.sleep(2000);
	}

	@When("User enters a valid Pincode on the Let’s Connect form Brands {string}")
	public void user_enters_a_valid_pincode_on_the_let_s_connect_form_brands(String string)
			throws InterruptedException {
		wait.waitForElementVisible(lp.pincodeField);
		js.scrollUntilElementVisible(lp.pincodeField);
		lp.pincodeField.clear();
		Thread.sleep(2000);
		common.SendInput(string, lp.pincodeField);
	}

	@When("User clicks on the Submit button")
	public void user_clicks_on_the_submit_button() throws InterruptedException {
		wait.waitForElementVisible(lp.submitButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(lp.submitButton);
		Thread.sleep(2000);
		js.jsClickWithWait(lp.submitButton);
		Thread.sleep(4000);
	}

	@When("User should see the acknowledgment message after successful submission")
	public void user_should_see_the_acknowledgment_message_after_successful_submission() {
		wait.waitForElementVisible(lp.thankYouHeader);
		wait.waitForElementVisible(lp.thankYouDescription);

		Assert.assertTrue(lp.thankYouHeader.isDisplayed(), "'Thank You' header is not displayed");
		Assert.assertTrue(lp.thankYouDescription.isDisplayed(), "Acknowledgment description is not displayed");

		Assert.assertEquals(lp.thankYouHeader.getText().trim(), "Thank You");
		Assert.assertEquals(lp.thankYouDescription.getText().trim(), "For Reaching out to us !");
	}

	@Given("validation message for Mobile Number input fields should get displayed {string}")
	public void validation_message_for_mobile_number_input_fields_should_get_displayed(String string) {
		js.scrollUntilElementVisible(lp.mandatoryFieldErrors);
		String Errmsg = common.getElementText(lp.mandatoryFieldErrors);
		common.compareText(Errmsg, string);
	}

	@Given("validation message for Name input fields should get displayed {string}")
	public void validation_message_for_name_input_fields_should_get_displayed(String string) {
		js.scrollUntilElementVisible(lp.mandatoryFieldErrors);
		String Errmsg = common.getElementText(lp.mandatoryFieldErrors);
		common.compareText(Errmsg, string);
	}

	@Given("validation message for HelpOption input fields should get displayed {string}")
	public void validation_message_for_help_option_input_fields_should_get_displayed(String string) {
		js.scrollUntilElementVisible(lp.mandatoryFieldErrors);
		String Errmsg = common.getElementText(lp.mandatoryFieldErrors);
		common.compareText(Errmsg, string);
	}

	@Given("validation message for Pincode input fields should get displayed {string}")
	public void validation_message_for_pincode_input_fields_should_get_displayed(String string) {
		js.scrollUntilElementVisible(lp.mandatoryFieldErrors);
		String Errmsg = common.getElementText(lp.mandatoryFieldErrors);
		common.compareText(Errmsg, string);
	}

	@Given("User is on the Birla Opus Become a dealer page  {string}")
	public void user_is_on_the_birla_opus_become_a_dealer_page(String BecomedealerUrl) {
		WebsiteLaunch.webLaunch(BecomedealerUrl);
	}

	@Given("User enters a valid Pincode on the Become a dealer Let’s Connect form  {string}")
	public void user_enters_a_valid_pincode_on_the_become_a_dealer_let_s_connect_form(String string)
			throws InterruptedException {
		wait.waitForElementVisible(lp.pincodeField);
		js.scrollUntilElementVisible(lp.pincodeField);
		lp.pincodeField.clear();
		Thread.sleep(2000);
		common.SendInput(string, lp.pincodeField);
	}

	@When("User selects Become a dealer Let’s Connect form {string} from the Who are you ? dropdown")
	public void user_selects_become_a_dealer_let_s_connect_form_from_the_who_are_you_dropdown(String string) {
		lp.selectWhoAreYou(string);
	}

	@When("User selects Become a dealer Let’s Connect form {string} from the How can we help you? dropdown")
	public void user_selects_become_a_dealer_let_s_connect_form_from_the_how_can_we_help_you_dropdown(String string) {
		lp.selectHowCanWeHelpYou(string);
	}

}
