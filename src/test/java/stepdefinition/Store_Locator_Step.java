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
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.Search_Page;
import pagefunctions.Store_Locator_Page;

public class Store_Locator_Step {

	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();

	Search_Page sp = new Search_Page();

	Store_Locator_Page sl = new Store_Locator_Page();
	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	String fakeEmailId = dataGenerator.generateFakeEmail();

	@When("User clicks on the Find a Store option")
	public void user_clicks_on_the_find_a_store_option() throws InterruptedException {
		wait.waitForElementVisible(sl.storeLocatorBtn);
		Thread.sleep(2000);
		js.jsClickWithWait(sl.storeLocatorBtn);
		Thread.sleep(2000);
	}

	@Then("User enters a valid pincode {string}")
	public void user_enters_a_valid_pincode(String string) throws InterruptedException {
		wait.waitForElementVisible(sl.pincodeInput);
		js.scrollUntilElementVisible(sl.pincodeInput);
		sl.pincodeInput.clear();
		Thread.sleep(3000);
		common.SendInput(string, sl.pincodeInput);
	}
	@Then("User enters a invalid pincode {string}")
	public void user_enters_a_invalid_pincode(String string) throws InterruptedException {
		wait.waitForElementVisible(sl.pincodeInput);
		js.scrollUntilElementVisible(sl.pincodeInput);
		sl.pincodeInput.clear();
		Thread.sleep(3000);
		common.SendInput(string, sl.pincodeInput);
	}


	@Then("User clicks on the submit button")
	public void user_clicks_on_the_submit_button() throws InterruptedException {
		wait.waitForElementVisible(sl.submitBtn);
		Thread.sleep(3000);
		js.scrollUntilElementVisible(sl.submitBtn);
		js.jsClickWithWait(sl.submitBtn);
		Thread.sleep(4000);
	}

	@Then("store locator results, including the store count text and filter dropdown, should be displayed successfully")
	public void store_locator_results_including_the_store_count_text_and_filter_dropdown_should_be_displayed_successfully() {
		sl.StoreLocatorResults();
	}

	@Then("User selects the first store and clicks the Get Number button")
	public void user_selects_the_first_store_and_clicks_the_get_number_button() {
		sl.clickFirstGetNumberButton();
	}

	@Then("User clicks on the continue button")
	public void user_clicks_on_the_continue_button() throws InterruptedException {
		wait.waitForElementVisible(sl.continueButton);
		Thread.sleep(3000);
		js.scrollUntilElementVisible(sl.continueButton);
		js.jsClickWithWait(sl.continueButton);
		Thread.sleep(4000);
	}

	@Then("User enters a valid email ID on the Store Locator lead details form")
	public void user_enters_a_valid_email_id_on_the_store_locator_lead_details_form() throws InterruptedException {
		wait.waitForElementVisible(sl.emailInputField);
		js.scrollUntilElementVisible(sl.emailInputField);
		sl.emailInputField.clear();
		Thread.sleep(3000);
		common.SendInput(fakeEmailId, sl.emailInputField);
	}

	@Then("The confirmation message should be displayed successfully")
	public void the_confirmation_message_should_be_displayed_successfully() {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOf(sl.successTitleText));

		Assert.assertTrue(sl.successTitleText.isDisplayed(), "Confirmation success message is not displayed");
	}

	@Then("User clicks on the submit button on lead details form")
	public void user_clicks_on_the_submit_button_on_lead_details_form() throws InterruptedException {
		wait.waitForElementVisible(sl.submitButtonLeadDetailsForm);
		Thread.sleep(3000);
		js.scrollUntilElementVisible(sl.submitButtonLeadDetailsForm);
		js.jsClickWithWait(sl.submitButtonLeadDetailsForm);
		Thread.sleep(4000);
	}
}
