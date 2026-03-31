package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonutilities.CommonDataGenerator;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.Opus_Assurance_Journey_Page;

public class Opus_Assurance_Journey_Step {
	public WebDriver driver;
//	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	private String firstName;
	
	Opus_Assurance_Journey_Page op = new Opus_Assurance_Journey_Page();
	JSExecutor js = new JSExecutor();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	String fakeFirstName = dataGenerator.generateFakeFirstName();
	String fakeLastName = dataGenerator.generateFakeLastName();
	String fakeEmail = dataGenerator.generateFakeEmail();

	@When("User clicks on Register Now Cta on home page")
	public void user_clicks_on_register_now_cta_on_home_page() throws InterruptedException {
	    js.jsClickWithWait(op.registerNowCta);
	    Thread.sleep(5000);
	}

	@When("User enter valid paintable area {string}")
	public void user_enter_valid_paintable_area(String string) throws InterruptedException {
	    js.scrollUntilElementVisible(op.paintableAreaField);
//	    js.sendKeysUsingJS(op.paintableAreaField, string);
	    op.paintableAreaField.sendKeys(string);
	    Thread.sleep(5000);
	}

	@When("User click on Next button")
	public void user_click_on_next_button() throws InterruptedException {
		js.scrollUntilElementVisible(op.nextCta);
//		op.paintableAreaField.click();
//	    js.jsClickWithWait(op.nextCta);
		Thread.sleep(5000);
		op.nextCta.click();
	    }

	@Then("User should click on Yet to Start Cta")
	public void user_should_click_on_yet_to_start_cta() {
	    js.jsClickWithWait(op.yetToStart);
	}

	@Then("User click on Pre-register now Cta")
	public void user_click_on_pre_register_now_cta() {
	    js.jsClickWithWait(op.preRegisterCta);
	}
	
	@Then("User clicks on the Create an account option")
	public void user_clicks_on_the_create_an_account_option() {
		op.CreateAccountOption.click();
	}
	@Then("User enter valid first name")
	public void user_enter_valid_first_name() throws InterruptedException {
		firstName = fakeFirstName;
		wait.waitForElementVisible(op.firstNameField);
		op.firstNameField.sendKeys(fakeFirstName);
		Thread.sleep(2000);
	}

	@Then("User enter valid last name")
	public void user_enter_valid_last_name() throws InterruptedException {
		wait.waitForElementVisible(op.lastNameField);
		js.scrollUntilElementVisible(op.lastNameField);
		op.lastNameField.sendKeys(fakeLastName);
		Thread.sleep(2000);
	}
	
	@Then("User enter valid email id")
	public void user_enter_valid_email_id() throws InterruptedException {
		js.scrollUntilElementVisible(op.emailIdField);
		op.emailIdField.sendKeys(fakeEmail);
		Thread.sleep(2000);
	}

	@Then("User enter valid pin code {string}")
	public void user_enter_valid_pin_code(String string) {
		js.scrollUntilElementVisible(op.pincodeField);
	    op.pincodeField.sendKeys(string);
	}

	@Then("User click on the Save Details button")
	public void user_click_on_the_save_details_button() throws InterruptedException {
		js.scrollUntilElementVisible(op.submitButtonCta);
		op.submitButtonCta.click();
	    Thread.sleep(5000);
	}
	
	@Then("User should see an message We re coming soon! {string}")
	public void user_should_see_an_message_we_re_coming_soon(String expectedMessage) throws InterruptedException {
		wait.waitForElementVisible(op.weAreComingSoonText);
		String actualMessage = op.weAreComingSoonText.getText();

		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
		Thread.sleep(5000);	
		}
	

	
	@Then("User enter invalid first name {string}")
	public void user_enter_invalid_first_name(String string) { 		
	wait.waitForElementVisible(op.firstNameField);
    js.sendKeysUsingJS(op.firstNameField, string);
		//op.firstNameField.sendKeys(string);
	}

	@Then("User enter invalid last name {string}")
	public void user_enter_invalid_last_name(String string) {
//		wait.waitForElementVisible(op.lastNameField);
//		js.sendKeysUsingJS(op.lastNameField, string);
		op.lastNameField.sendKeys(string);
	}

	@Then("User enter invalid email id {string}")
	public void user_enter_invalid_email_id(String string) throws InterruptedException {
		js.scrollUntilElementVisible(op.emailIdField);
		wait.waitForElementToBeVisible(op.emailIdField, 10);
//		js.sendKeysUsingJS(op.emailIdField, string);
		op.emailIdField.sendKeys("testtestgmail.com");
		Thread.sleep(3000);
	}

	@Then("User enter invalid pin code {string}")
	public void user_enter_invalid_pin_code(String string) throws InterruptedException {
		js.scrollUntilElementVisible(op.pincodeField);
		wait.waitForElementToBeVisible(op.pincodeField, 10);
		js.sendKeysUsingJS(op.pincodeField, string);
//		op.pincodeField.sendKeys(string);
		Thread.sleep(3000);
	}

	@Then("User should see an error message for email id {string}")
	public void user_should_see_an_error_message_for_email_id(String expectedMessage) throws InterruptedException {
		wait.waitForElementVisible(op.emailIdErrorMsg);
		String actualMessage = op.emailIdErrorMsg.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
//		Thread.sleep(5000);	
	}

	@Then("User should see an error message for pin code {string}")
	public void user_should_see_an_error_message_for_pin_code(String expectedMessage) throws InterruptedException {
		wait.waitForElementVisible(op.pincodeErrorMsg);
		String actualMessage = op.pincodeErrorMsg.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
//		Thread.sleep(5000);	
	}
	
	@Then("User should see an error message for last name {string}")
	public void user_should_see_an_error_message_for_last_name(String expectedMessage) {
		wait.waitForElementVisible(op.lastNameErrorMsg);
		String actualMessage = op.lastNameErrorMsg.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
	}
	
		
	@Then("User should see an error message for first name {string}")
	public void user_should_see_an_error_message_for_first_name(String expectedMessage) {
		wait.waitForElementVisible(op.lastNameErrorMsg);
		String actualMessage = op.lastNameErrorMsg.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
	}
	
	@Then("User enter invalid otp {string}")
	public void user_enter_invalid_otp(String string) {
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement firstOtpField = wait.until(ExpectedConditions.visibilityOf(op.otpNoInputFiled.get(0)));

//		firstOtpField.clear();
		firstOtpField.sendKeys(string);
	}

	@Then("User click on verify otp cta")
	public void user_click_on_verify_otp_cta() {
		wait.waitForElementToBeClickable(op.verifyButton, 10);
	    op.verifyButton.click();
	}

	@Then("User should see an error message for otp {string}")
	public void user_should_see_an_error_message_for_otp(String expectedMessage) {
	wait.waitForElementVisible(op.otpFieldErrorMsg);
	js.scrollUntilElementVisible(op.otpFieldErrorMsg);
	String actualMessage = op.otpFieldErrorMsg.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
	}
	@When("User enter paintable area {string}")
	public void user_enter_paintable_area(String string) throws InterruptedException {
		 js.scrollUntilElementVisible(op.paintableAreaField);
//		    js.sendKeysUsingJS(op.paintableAreaField, string);
		    op.paintableAreaField.sendKeys(string);
		    Thread.sleep(5000);
	}
	@Then("User should see an error message for paintable area  {string}")
	public void user_should_see_an_error_message_for_paintable_area(String expectedMessage)
	{
		wait.waitForElementVisible(op.ErrMessagePaintingCarpetArea);
		js.scrollUntilElementVisible(op.ErrMessagePaintingCarpetArea);
		String actualMessage = op.ErrMessagePaintingCarpetArea.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "error message mismatch");
	}
}
