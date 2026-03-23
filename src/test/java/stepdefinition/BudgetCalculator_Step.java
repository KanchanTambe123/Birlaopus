package stepdefinition;

import commonutilities.ActionClass;
import commonutilities.ClickElement;
import commonutilities.CommonMethods;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import config.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.Book_Survey_Form_Page;
import pagefunctions.BudgetCalculator_Page;

public class BudgetCalculator_Step {
	
	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	Book_Survey_Form_Page bs = new Book_Survey_Form_Page();
	BudgetCalculator_Page sp=new BudgetCalculator_Page();
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
		common.SendInput(string,sp.carpetAreaInputField);
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
		common.SendInput(string,sp.pincodeInputField);
		Thread.sleep(2000);
	}
	@Then("the User clicks on the Calculate Now button")
	public void the_user_clicks_on_the_calculate_now_button() throws InterruptedException {
		wait.waitForElementVisible(sp.calculateNowButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(sp.calculateNowButton);
		Thread.sleep(2000);
		js.jsClickWithWait(sp.calculateNowButton);
		Thread.sleep(2000);
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
	public void verify_the_lead_api_parameters_for_budget_calculator_form_icl_lead_context_c_against_value_icl_lead_type_c_against_value_icl_sub_type_against_value_lead_sub_source_against_value(String expectedLeadContext, String expectedLeadType, String expectedSubType, String expectedLeadSubSource) {
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
}
