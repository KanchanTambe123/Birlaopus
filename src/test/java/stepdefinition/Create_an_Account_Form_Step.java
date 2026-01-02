package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.By;
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
import io.cucumber.java.en.When;
import pagefunctions.ColourLetter_SignUp_Page;
import pagefunctions.Create_an_Account_Form_Page;
import pagefunctions.End_to_End_Flow_Page;
import pagefunctions.Painting_Service_Form_Page;

public class Create_an_Account_Form_Step {

	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	Painting_Service_Form_Page pf = new Painting_Service_Form_Page();
	ColourLetter_SignUp_Page cl = new ColourLetter_SignUp_Page();
	Create_an_Account_Form_Page cp = new Create_an_Account_Form_Page();
	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	private String firstName;
	End_to_End_Flow_Page ep = new End_to_End_Flow_Page();
	String fakeFirstName = dataGenerator.generateFakeFirstName();
	String fakeLastName = dataGenerator.generateFakeLastName();
	String fakeMobileNumber = dataGenerator.generateFakeMobileNumber();

	@When("User clicks on the profile icon")
	public void user_clicks_on_the_profile_icon() throws InterruptedException {
		Thread.sleep(2000);
		js.jsClickWithWait(cp.profileIcon);
		Thread.sleep(2000);

	}

	@Then("User clicks on the Create an Account option")
	public void user_clicks_on_the_create_an_account_option() throws InterruptedException {
		js.jsClickWithWait(cp.CreateAccountOption);
		Thread.sleep(2000);

	}

	@Then("User enters valid mobile number on the Create an Account")
	public void user_enters_valid_mobile_number_on_the_create_an_account() throws InterruptedException {
		wait.waitForElementVisible(cp.CreateAccountMobileNumberFiled);
		cp.CreateAccountMobileNumberFiled.sendKeys(fakeMobileNumber);
		Thread.sleep(2000);
	}

	@Then("User clicks on the Create an Account button")
	public void user_clicks_on_the_create_an_account_button() throws InterruptedException {
		js.jsClickWithWait(cp.CreateAccountButton);
		Thread.sleep(2000);
	}

	@Then("User enters valid OTP and clicks on the Verify OTP button")
	public void user_enters_valid_otp_and_clicks_on_the_verify_otp_button() throws InterruptedException {
		Thread.sleep(2000);
		cp.enterOtpAndSubmit("1111");
		Thread.sleep(2000);
	}

	@Then("User enters valid first name")
	public void user_enters_valid_first_name() throws InterruptedException {
		firstName = fakeFirstName;
		wait.waitForElementVisible(cp.firstNameInput);
		cp.firstNameInput.sendKeys(fakeFirstName);
		Thread.sleep(2000);
	}

	@Then("User enters valid last name")
	public void user_enters_valid_last_name() throws InterruptedException {
		wait.waitForElementVisible(cp.lastNameInput);
		js.scrollUntilElementVisible(cp.lastNameInput);
		cp.lastNameInput.sendKeys(fakeLastName);
		Thread.sleep(2000);
	}

	@Then("User clicks on the Save Details button")
	public void user_clicks_on_the_save_details_button() throws InterruptedException {
		js.scrollUntilElementVisible(cp.saveDetailButton);
		Thread.sleep(2000);
		js.jsClickWithWait(cp.saveDetailButton);
	}

	@Then("User should see the welcome message with name")
	public void user_should_see_the_welcome_message_with_name() {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));

		WebElement welcomeName = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[contains(text(),'Welcome')]/span[@class='dynamic-name']")));


		wait.until(d -> !welcomeName.getText().trim().isEmpty());

		String displayedName = welcomeName.getText().trim();
		Assert.assertEquals(displayedName, firstName,
				"Welcome message does not contain the correct user name. Expected: " + firstName + ", Actual: "
						+ displayedName);
	}

	@Then("User enters an already registered mobile number on the Create an Account page")
	public void user_enters_an_already_registered_mobile_number_on_the_create_an_account_page()
			throws InterruptedException {
		wait.waitForElementVisible(cp.CreateAccountMobileNumberFiled);
		cp.CreateAccountMobileNumberFiled.sendKeys("8208025065");
		Thread.sleep(2000);
	}

	@Then("User should see an error message indicating the User already exists {string}")
	public void user_should_see_an_error_message_indicating_the_user_already_exists(String expectedMessage) {

		String actualMessage = cp.duplicateUserErrorMsg.getText().trim();

		Assert.assertEquals(actualMessage, expectedMessage, "Duplicate user error message mismatch");
	}

	@Then("User enters bypass OTP and clicks on the Verify OTP button")
	public void user_enters_bypass_otp_and_clicks_on_the_verify_otp_button() {

		cp.enterBypassOtpAndVerify(fakeMobileNumber);

	}
	


}
