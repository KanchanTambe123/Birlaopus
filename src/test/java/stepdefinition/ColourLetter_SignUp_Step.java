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
import pagefunctions.WebsiteLaunch;

public class ColourLetter_SignUp_Step {

	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();

	ColourLetter_SignUp_Page cl = new ColourLetter_SignUp_Page();
	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	String fakeEmailId = dataGenerator.generateFakeEmail();
	End_to_End_Flow_Page ep = new End_to_End_Flow_Page();

	@Given("User is on the Colour Letter Page {string}")
	public void user_is_on_the_colour_letter_page(String colourletterUrl) {
		WebsiteLaunch.webLaunch(colourletterUrl);
	}

	@When("User enters a valid email ID in the newsletter subscription field")
	public void user_enters_a_valid_email_id_in_the_newsletter_subscription_field() throws InterruptedException {
		js.scrollUntilElementVisible(cl.EmailIdFiled);
		Thread.sleep(2000);
		cl.EmailIdFiled.sendKeys(fakeEmailId);
		Thread.sleep(2000);
	}

	@When("User clicks on the Sign up for colour letter button")
	public void user_clicks_on_the_sign_up_for_colour_letter_button() throws InterruptedException {
		js.jsClickWithWait(cl.SignupButton);
		Thread.sleep(2000);
	}

	@Then("the User should see a success message confirming the newsletter subscription {string} {string}")
	public void the_user_should_see_a_success_message_confirming_the_newsletter_subscription(String expectedTitle,
			String expectedMessage) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));

		wait.until(ExpectedConditions.visibilityOf(cl.SuccessHeader));
		wait.until(ExpectedConditions.visibilityOf(cl.SuccessDescription));

		String actualTitle = cl.SuccessHeader.getText().trim();
		String actualDescription = cl.SuccessDescription.getText().trim();

		Assert.assertEquals(expectedTitle, actualTitle, "Header mismatch!");
		Assert.assertEquals(expectedMessage, actualDescription, "Description mismatch!");

		System.out.println("SUCCESS: Newsletter subscription message validated.");
		Thread.sleep(2000);
	}

	@Then("User closes the newsletter success popup")
	public void user_closes_the_newsletter_success_popup() throws InterruptedException {
		js.jsClickWithWait(cl.CloseButton);
		System.out.println("Popup closed successfully");
		Thread.sleep(2000);

		/*
		 * if (newsletterPage.closeButtons.size() > 1) {
		 * newsletterPage.closeButtons.get(1).click(); // Click 2nd Close button
		 * System.out.println("Clicked second Close button"); } else {
		 * newsletterPage.closeButtons.get(0).click(); // Fallback
		 * System.out.println("Clicked first Close button"); }
		 */
	}

	@When("User enters an invalid email ID {string} in the newsletter subscription field")
	public void user_enters_an_invalid_email_id_in_the_newsletter_subscription_field(String string)
			throws InterruptedException {
		js.scrollUntilElementVisible(cl.EmailIdFiled);
		Thread.sleep(2000);
		cl.EmailIdFiled.sendKeys(string);
		Thread.sleep(2000);
	}

	@Then("An error message should be displayed for invalid email ID {string}")
	public void an_error_message_should_be_displayed_for_invalid_email_ID(
			String expectedValidationMessage) {
		String actualFullNameErr = common.getElementText(cl.ErrorMessage);
		common.compareText(actualFullNameErr, expectedValidationMessage);
	}

	@When("User leaves the email field empty")
	public void user_leaves_the_email_field_empty() throws InterruptedException {
		js.scrollUntilElementVisible(cl.EmailIdFiled);
		Thread.sleep(2000);
		cl.EmailIdFiled.clear();
	}
	@Then("An error message should be displayed for the empty email field {string}")
	public void an_error_message_should_be_displayed_for_the_empty_email_field(String expectedValidationMessage) {
		String actualFullNameErr = common.getElementText(cl.ErrorMessage);
		common.compareText(actualFullNameErr, expectedValidationMessage);
	}

}


