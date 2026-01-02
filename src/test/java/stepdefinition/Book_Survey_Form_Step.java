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
import pagefunctions.End_to_End_Flow_Page;

public class Book_Survey_Form_Step {
	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	int cartQuantity;
	CommonDataGenerator dataGenerator = new CommonDataGenerator();

	String fakeFirstName = dataGenerator.generateFakeFirstName();
	String fakeLastName = dataGenerator.generateFakeLastName();
	String fakeMobileNumber = dataGenerator.generateFakeMobileNumber();
	String fakeEmailId = dataGenerator.generateFakeEmail();

	End_to_End_Flow_Page ep = new End_to_End_Flow_Page();
	Book_Survey_Form_Page bs = new Book_Survey_Form_Page();

	@Then("User clicks on the Book a Free Survey button")
	public void user_clicks_on_the_book_a_free_survey_button() throws InterruptedException {
		wait.waitForElementVisible(bs.BookfreesurveyButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(bs.BookfreesurveyButton);
		Thread.sleep(2000);
		js.jsClickWithWait(bs.BookfreesurveyButton);
		Thread.sleep(2000);
	}

	@Then("User clicks on the Next button on the Book a Free Survey form")
	public void user_clicks_on_the_next_button_on_the_book_a_free_survey_form() throws InterruptedException {
		wait.waitForElementVisible(bs.SiteDetailsNextButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(bs.SiteDetailsNextButton);
		Thread.sleep(2000);
		js.jsClickWithWait(bs.SiteDetailsNextButton);
		Thread.sleep(2000);
	}

	@Then("User clicks on the Next button on the Share Few Details section")
	public void user_clicks_on_the_next_button_on_the_share_few_details_section() throws InterruptedException {
		wait.waitForElementVisible(bs.FewDeailsNextButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(bs.FewDeailsNextButton);
		Thread.sleep(2000);
		js.jsClickWithWait(bs.FewDeailsNextButton);
		Thread.sleep(2000);
	}

	@Then("User selects the painting requirement type {string}")
	public void user_selects_the_painting_requirement_type(String string) throws InterruptedException {
		bs.selectRequirementType(string);
		Thread.sleep(2000);
	}

	@Then("User clicks on the project details next button")
	public void user_clicks_on_the_project_details_next_button() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));

		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView({block:'center'});",
				bs.projectDetailsNextButton);

		wait.until(ExpectedConditions.elementToBeClickable(bs.projectDetailsNextButton));

		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();",
				bs.projectDetailsNextButton);

	}

	@Then("User select bhk type {string} and enter the carpet area {string}")
	public void user_select_bhk_type_and_enter_the_carpet_area(String string, String string2)
			throws InterruptedException {
		bs.selectBhkType(string);
		wait.waitForElementVisible(bs.carpetAreaInputFiled);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(bs.carpetAreaInputFiled);
		Thread.sleep(2000);
		bs.carpetAreaInputFiled.clear();
		Thread.sleep(2000);
		common.SendInput(string2, bs.carpetAreaInputFiled);

	}

	@Then("User enters the carpet area {string}")
	public void user_enters_the_carpet_area(String string) throws InterruptedException {

		wait.waitForElementVisible(bs.carpetAreaInputFiled);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(bs.carpetAreaInputFiled);
		Thread.sleep(2000);
		bs.carpetAreaInputFiled.clear();
		Thread.sleep(2000);
		common.SendInput(string, bs.carpetAreaInputFiled);
	}

	@Then("User clicks on the Skip for now option in the Project Requirements section")
	public void user_clicks_on_the_skip_for_now_option_in_the_project_requirements_section()
			throws InterruptedException {
		wait.waitForElementVisible(bs.projectDetailsSkipOption);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(bs.projectDetailsSkipOption);
		Thread.sleep(2000);
		js.jsClickWithWait(bs.projectDetailsSkipOption);
		Thread.sleep(2000);
	}

	@Then("A survey booking confirmation message should be displayed successfully")
	public void a_survey_booking_confirmation_message_should_be_displayed_successfully() {
		wait.waitForElementVisible(bs.confirmationMsg);
		Assert.assertTrue(bs.confirmationMsg.isDisplayed(), "Survey booking confirmation message is not displayed");
	}

	@Then("User clicks on the Skip for now option in the carpet area section")
	public void user_clicks_on_the_skip_for_now_option_in_the_carpet_area_section() throws InterruptedException {
		wait.waitForElementVisible(bs.projectDetailsSkipOption);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(bs.projectDetailsSkipOption);
		Thread.sleep(2000);
		js.jsClickWithWait(bs.projectDetailsSkipOption);
		Thread.sleep(2000);

	}
	
	@Then("User clicks on the Back button in the Book a Free Survey form")
	public void user_clicks_on_the_back_button_in_the_book_a_free_survey_form() throws InterruptedException {
		wait.waitForElementVisible(bs.surveyStepBackButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(bs.surveyStepBackButton);
		Thread.sleep(2000);
		js.jsClickWithWait(bs.surveyStepBackButton);
		Thread.sleep(2000);
	}
	@Then("User should be redirected to the previous step of the Book a Free Survey form")
	public void user_should_be_redirected_to_the_previous_step_of_the_book_a_free_survey_form() {
	   wait.waitForElementVisible(bs.previousStepLocator);

	   Assert.assertTrue(
			   bs.previousStepLocator.isDisplayed(),
		        "User is NOT redirected to the previous step of the Book a Free Survey form"
		    );
	}

}
