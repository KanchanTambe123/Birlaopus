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
import io.cucumber.java.en.Then;
import pagefunctions.Book_Survey_Form_Page;
import pagefunctions.ColourLetter_SignUp_Page;
import pagefunctions.Painting_Made_Easy_Page;
import pagefunctions.Painting_Service_Form_Page;

public class Painting_Made_Easy_Step {
	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	Painting_Service_Form_Page pf = new Painting_Service_Form_Page();
	ColourLetter_SignUp_Page cl = new ColourLetter_SignUp_Page();
	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	Painting_Made_Easy_Page pm = new Painting_Made_Easy_Page();
	Book_Survey_Form_Page bs = new Book_Survey_Form_Page();

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
	public void user_clicks_on_the_skip_for_now_option_in_the_painting_requirements_question() throws InterruptedException {
		wait.waitForElementVisible(pm.SkipQuestion);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(pm.SkipQuestion);
		Thread.sleep(2000);
		js.jsClickWithWait(pm.SkipQuestion);
		Thread.sleep(2000);
	}
	@Then("User clicks on the Skip for now option in the home configuration question")
	public void user_clicks_on_the_skip_for_now_option_in_the_home_configuration_question() throws InterruptedException {
		wait.waitForElementVisible(pm.SkipQuestion);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(pm.SkipQuestion);
		Thread.sleep(2000);
		js.jsClickWithWait(pm.SkipQuestion);
		Thread.sleep(2000);
	}

}
