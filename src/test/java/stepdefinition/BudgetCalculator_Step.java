package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonutilities.ActionClass;
import commonutilities.ClickElement;
import commonutilities.CommonMethods;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import config.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.Book_Survey_Form_Page;
import pagefunctions.BudgetCalculator_Page;

public class BudgetCalculator_Step {
	String priceBefore;
	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	Book_Survey_Form_Page bs = new Book_Survey_Form_Page();
	BudgetCalculator_Page sp = new BudgetCalculator_Page();

	@When("the User scrolls to the Budget Calculator")
	public void the_user_scrolls_to_the_budget_calculator() {
		js.scrollUntilElementVisible(sp.spacetext);

	}

	@Then("the User selects the space name to paint {string}")
	public void the_user_selects_the_space_name_to_paint(String string) throws InterruptedException {
		sp.selectSpaceOptions(string);
		Thread.sleep(2000);
	}

	@Then("the User enters a valid carpet area {string}")
	public void the_user_enters_a_valid_carpet_area(String string) throws InterruptedException {
		wait.waitForElementVisible(sp.carpetAreaInputField);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(sp.carpetAreaInputField);
		Thread.sleep(2000);
		sp.carpetAreaInputField.clear();
		Thread.sleep(2000);
		common.SendInput(string, sp.carpetAreaInputField);
		Thread.sleep(2000);
	}

	@Then("the User enters a valid serviceable pincode {string}")
	public void the_user_enters_a_valid_serviceable_pincode(String string) throws InterruptedException {
		wait.waitForElementVisible(sp.pincodeInputField);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(sp.pincodeInputField);
		Thread.sleep(2000);
		sp.pincodeInputField.clear();
		Thread.sleep(2000);
		common.SendInput(string, sp.pincodeInputField);
		Thread.sleep(2000);
	}

	@Then("the User clicks on the Calculate Now button")
	public void the_user_clicks_on_the_calculate_now_button() throws InterruptedException {
		wait.waitForElementVisible(sp.calculateNowButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(sp.calculateNowButton);
		Thread.sleep(2000);
		js.jsClickWithWait(sp.calculateNowButton);
		Thread.sleep(6000);
		
	}

	@Then("the calculation result correctly reflects the entered carpet area and pincode")
	public void the_calculation_result_correctly_reflects_the_entered_carpet_area_and_pincode() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("click on Budget Calculator book free survey button")
	public void click_on_budget_calculator_book_free_survey_button() throws InterruptedException {

		wait.waitForElementVisible(sp.bookFreeSurveyButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(sp.bookFreeSurveyButton);
		Thread.sleep(2000);
		js.jsClickWithWait(sp.bookFreeSurveyButton);
		Thread.sleep(2000);
	}

	@Then("verify the lead API parameters for Budget Calculator form: iclLeadContextC against value {string},  iclLeadTypeC against value {string},  iclSubType against value {string},  leadSubSource against value {string}")
	public void verify_the_lead_api_parameters_for_budget_calculator_form_icl_lead_context_c_against_value_icl_lead_type_c_against_value_icl_sub_type_against_value_lead_sub_source_against_value(
			String expectedLeadContext, String expectedLeadType, String expectedSubType, String expectedLeadSubSource) {
		bs.verifyLeadApiParameters(expectedLeadContext, expectedLeadType, expectedSubType, expectedLeadSubSource);
	}

	@Then("the Economy and Luxury categories should be displayed as locked")
	public void the_economy_and_luxury_categories_should_be_displayed_as_locked() {
		sp.verifyTabIsLocked("Economy");
		sp.verifyTabIsLocked("Luxury");
	}

	@Then("the User clicks on the Thank You window close icon")
	public void the_user_clicks_on_the_thank_you_window_close_icon() throws InterruptedException {
		sp.closeThankYouPopup();
	}

	@Then("the Book a free survey button should not be visible on the Premium tab")
	public void the_book_a_free_survey_button_should_not_be_visible_on_the_premium_tab() {
		sp.verifyButtonNotVisibleOnPremiumTab();
	}

	@Then("the Book a free survey button should be visible on the Economy tab")
	public void the_book_a_free_survey_button_should_be_visible_on_the_economy_tab() throws InterruptedException {
		sp.verifyButtonVisibilityOnTab("Economy");

	}

	@Then("the Book a free survey button should be visible on the Luxury tab")
	public void the_book_a_free_survey_button_should_be_visible_on_the_luxury_tab() throws InterruptedException {
		sp.verifyButtonVisibilityOnTab("Luxury");
	}

	@Then("the User verifies the edit button is clickable and clicks on the carpet area edit button")
	public void the_user_verifies_the_edit_button_is_clickable_and_clicks_on_the_carpet_area_edit_button()
			throws InterruptedException {

		wait.waitForElementVisible(sp.editCarpetAreaBtn);

		js.scrollUntilElementVisible(sp.editCarpetAreaBtn);
		Thread.sleep(2000);
		// Verify button is clickable
		if (sp.editCarpetAreaBtn.isDisplayed() && sp.editCarpetAreaBtn.isEnabled()) {

			js.jsClickWithWait(sp.editCarpetAreaBtn);
			Thread.sleep(2000);
			System.out.println("Edit button is clickable - PASS");

		} else {
			throw new AssertionError("Edit button is not clickable - FAIL");
		}
	}

	@Then("the User enters a valid Recalculate estimate carpet area {string}")
	public void the_user_enters_a_valid_recalculate_estimate_carpet_area(String string) throws InterruptedException {
		wait.waitForElementVisible(sp.recalculateInput);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(sp.recalculateInput);
		Thread.sleep(2000);

		common.SendInput(string, sp.recalculateInput);
		Thread.sleep(2000);
	}

	@Then("the User clicks on the Recalculate Estimate button and verifies the price is updated")
	public void the_user_clicks_on_the_recalculate_estimate_button_and_verifies_the_price_is_updated()
			throws InterruptedException {
		wait.waitForElementVisible(sp.recalculateBtn);
		js.jsClickWithWait(sp.recalculateBtn);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(sp.priceText);
		Thread.sleep(2000);
		wait.<Boolean>until(driver -> {
			String currentPrice = sp.getVisiblePrice();
			return !currentPrice.equals(priceBefore); // returns Boolean
		}, 10);

		String priceAfter = sp.getVisiblePrice();
		System.out.println("Price After: " + priceAfter);

		// 6️⃣ Verify
		if (!priceBefore.equals(priceAfter)) {
			System.out.println("Price updated successfully - PASS");
		} else {
			throw new AssertionError("Price not updated - FAIL");
		}
	}

	@When("the User enters a invalid pincode {string}")
	public void the_user_enters_a_invalid_pincode(String string) throws InterruptedException {
		wait.waitForElementVisible(sp.pincodeInputField);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(sp.pincodeInputField);
		Thread.sleep(2000);
		sp.pincodeInputField.clear();
		Thread.sleep(2000);
		common.SendInput(string, sp.pincodeInputField);
		Thread.sleep(2000);
	}

	@Then("the User should see the error message {string}")
	public void the_user_should_see_the_error_message(String expectedMsg) {
		wait.waitForElementVisible(sp.InvalidPincode); //

		js.scrollUntilElementVisible(sp.InvalidPincode); //

		String actualMsg = common.getElementText(sp.InvalidPincode);

		common.compareText(actualMsg.trim(), expectedMsg.trim());
	}

	@Then("the user should see the error message {string} when an alphabet is entered in the pincode field.")
	public void the_user_should_see_the_error_message_when_an_alphabet_is_entered_in_the_pincode_field(
			String expectedMsg) {
		wait.waitForElementVisible(sp.EmptyPincodeField);

		js.scrollUntilElementVisible(sp.EmptyPincodeField);

		String actualMsg = common.getElementText(sp.EmptyPincodeField);

		common.compareText(actualMsg.trim(), expectedMsg.trim());
	}

	@Then("the User clicks on view products")
	public void the_user_clicks_on_view_products() throws InterruptedException {
		wait.waitForElementVisible(sp.ViewProducts);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(sp.ViewProducts);
		Thread.sleep(2000);
		js.jsClickWithWait(sp.ViewProducts);
		Thread.sleep(2000);
	}

	@When("the User scrolls to the Download Estimate button")
	public void the_user_scrolls_to_the_download_estimate_button() {
		js.scrollUntilElementVisible(sp.DownloadestimateButton);
		
	}

	@Then("the Download Estimate should be visible")
	public void the_download_estimate_should_be_visible() {
		  wait.waitForElementVisible(sp.DownloadestimateButton);
		  }

	@Then("the Download Estimate should be clickable")
	public void the_download_estimate_should_be_clickable() throws InterruptedException {
		
     

 		// Check if clickable
 		WebElement element =    sp.DownloadestimateButton;

 		boolean isClickable = element.isDisplayed();

 		Assert.assertTrue(isClickable, "Download Estimate button is NOT clickable");
		((JavascriptExecutor) DriverManager.getDriver())
 	    .executeScript("arguments[0].click();", element);
         Thread.sleep(6000);
	}
	
	
	@Then("User enters unserviceable Pin code on budget calculator form {string}")
	public void user_enters_unserviceable_pin_code_on_budget_calculator_form(String string) throws InterruptedException {


	js.scrollUntilElementVisible(sp.pincodeSiteDetails);
	wait.waitForElementVisible(sp.pincodeSiteDetails);
	sp.pincodeSiteDetails.clear();
	sp.pincodeSiteDetails.sendKeys(string);
	
	
		}
	
	
	@Then("User clicks on the Next button on the share site details section")
	public void user_clicks_on_the_next_button_on_the_share_site_details_section() {
	   js.scrollUntilElementVisible(sp.nextSiteDetails);	
	   js.jsClickWithWait(sp.nextSiteDetails);
	   
	   }

	}

