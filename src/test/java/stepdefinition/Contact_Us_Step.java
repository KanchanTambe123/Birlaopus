package stepdefinition;

import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

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
import pagefunctions.Book_Survey_Form_Page;
import pagefunctions.ColourLetter_SignUp_Page;
import pagefunctions.Contact_Us_Page;
import pagefunctions.End_to_End_Flow_Page;
import pagefunctions.WebsiteLaunch;

public class Contact_Us_Step {

	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();

	ColourLetter_SignUp_Page cl = new ColourLetter_SignUp_Page();
	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	String fakeEmailId = dataGenerator.generateFakeEmail();

	Contact_Us_Page cs = new Contact_Us_Page();
	End_to_End_Flow_Page ep = new End_to_End_Flow_Page();
	Book_Survey_Form_Page bs = new Book_Survey_Form_Page();

	@Given("User is on the Contact us form {string}")
	public void user_is_on_the_contact_us_form(String ContactUsUrl) {
		WebsiteLaunch.webLaunch(ContactUsUrl);
		String currentUrl = DriverManager.getDriver().getCurrentUrl();

		ExtentCucumberAdapter.addTestStepLog("Contact us URL : " + currentUrl);
	}

	@When("User selects Who Are You as {string}")
	public void user_selects_who_are_you_as(String string) {
		cs.selectWhoAreYou(string);
	}

	@Then("User selects How can we help you ? as {string}")
	public void user_selects_how_can_we_help_you_as(String string) {
		cs.selecthowCanWeHelpYou(string);

	}

	@Then("User enters a valid serviceable pincode in the Contact Us form {string}")
	public void user_enters_a_valid_serviceable_pincode_in_the_contact_us_form(String string)
			throws InterruptedException {
		wait.waitForElementVisible(cs.pincode);
		js.scrollUntilElementVisible(cs.pincode);
		Thread.sleep(2000);
		cs.pincode.clear();
		Thread.sleep(2000);
		common.SendInput(string, cs.pincode);
	
	}

	@Then("User enters a valid email ID in the Contact Us form")
	public void user_enters_a_valid_email_id_in_the_contact_us_form() throws InterruptedException {
		wait.waitForElementVisible(cs.emailId);
		js.scrollUntilElementVisible(cs.emailId);
		Thread.sleep(2000);
		cs.emailId.clear();
		Thread.sleep(2000);
		common.SendInput(fakeEmailId, cs.emailId);
	}

	@Then("User enter valid Concern and Queries as {string}")
	public void user_enter_valid_concern_and_queries_as(String string) throws InterruptedException {
		wait.waitForElementVisible(cs.concern);
		js.scrollUntilElementVisible(cs.concern);
		Thread.sleep(2000);
		cs.concern.clear();
		Thread.sleep(2000);
		common.SendInput(string, cs.concern);
	}

	@Then("User clicks on the Submit button in the Contact Us form")
	public void user_clicks_on_the_submit_button_in_the_contact_us_form() throws InterruptedException {
		wait.waitForElementVisible(cs.submitButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(cs.submitButton);
		Thread.sleep(2000);
		js.jsClickWithWait(cs.submitButton);
		Thread.sleep(2000);
	}

	@Then("verify the lead API parameters for contact us form : iclLeadContextC against value {string},  iclLeadTypeC against value {string},  iclSubType against value {string},  leadSubSource against value {string}")
	public void verify_the_lead_api_parameters_for_contact_us_form_icl_lead_context_c_against_value_icl_lead_type_c_against_value_icl_sub_type_against_value_lead_sub_source_against_value(
			String expectedLeadContext, String expectedLeadType, String expectedSubType, String expectedLeadSubSource) {
		bs.verifyLeadApiParameters(expectedLeadContext, expectedLeadType, expectedSubType, expectedLeadSubSource);

	}

	@Then("User click next button on Address section page for contact us form")
	public void user_click_next_button_on_address_section_page_for_contact_us_form() throws InterruptedException {
		wait.waitForElementVisible(cs.nextButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(cs.nextButton);
		Thread.sleep(2000);
		js.jsClickWithWait(cs.nextButton);
		Thread.sleep(2000);
	}

	@Then("contact us form confirmation message should be displayed successfully {string}")
	public void contact_us_form_confirmation_message_should_be_displayed_successfully(String string) {
		wait.waitForElementVisible(bs.confirmationMsg);
		Assert.assertTrue(bs.confirmationMsg.isDisplayed(), "confirmation message is not displayed");
	}

	@Then("User enters a unserviceable pincode in the Contact Us form {string}")
	public void user_enters_a_unserviceable_pincode_in_the_contact_us_form(String string) throws InterruptedException {
		wait.waitForElementVisible(cs.pincode);
		js.scrollUntilElementVisible(cs.pincode);
		Thread.sleep(2000);
		cs.pincode.clear();
		Thread.sleep(2000);
		common.SendInput(string, cs.pincode);
	}

}
