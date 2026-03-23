package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
import pagefunctions.Create_an_Account_Form_Page;
import pagefunctions.Sign_In_Functionality_Page;

public class Sign_In_Functionality_Step {

	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	String fakeMobileNumber = dataGenerator.generateFakeMobileNumber();

	Sign_In_Functionality_Page sp = new Sign_In_Functionality_Page();
	Create_an_Account_Form_Page cp = new Create_an_Account_Form_Page();

	@When("User clicks on the Sign In button")
	public void user_clicks_on_the_sign_in_button() throws InterruptedException {
		wait.waitForElementVisible(sp.signInButton);
		js.jsClickWithWait(sp.signInButton);
		Thread.sleep(2000);
	}

	@When("User enters valid mobile number on the Sign In page")
	public void user_enters_valid_mobile_number_on_the_sign_in_page() throws InterruptedException {
		wait.waitForElementVisible(sp.signInMobileNumberFiled);
		sp.signInMobileNumberFiled.sendKeys("7019144066");
		Thread.sleep(2000);
	}

	@When("the User clicks on the Sign In button after entering the mobile number")
	public void the_user_clicks_on_the_sign_in_button_after_entering_the_mobile_number() throws InterruptedException {
		js.jsClickWithWait(sp.signButtonAfterMobileNumber);
		Thread.sleep(2000);
	}

	@When("the User should successfully sign in")
	public void the_user_should_successfully_sign_in() {

		wait.waitForElementVisible(sp.welcomeMessage);

		// Assert that the welcome message is displayed
		Assert.assertTrue(sp.welcomeMessage.isDisplayed(), "Welcome message is not visible.");

		System.out.println("Welcome message is visible.");

	}

	@When("User enters invalid mobile number {string} on the Sign In page")
	public void user_enters_invalid_mobile_number_on_the_sign_in_page(String string) throws InterruptedException {
		wait.waitForElementVisible(sp.signInMobileNumberFiled);
		sp.signInMobileNumberFiled.sendKeys(string);
		Thread.sleep(2000);
	}
	@Then("the validation message for an invalid mobile number should be displayed to the user {string}")
	public void the_validation_message_for_an_invalid_mobile_number_should_be_displayed_to_the_user(String  expectedValidationMessage) {
		String actualErrmessage = common.getElementText(sp.MobileNumberErrMsg);
		common.compareText(actualErrmessage, expectedValidationMessage);

	}
	@When("the User clicks on the Sign In button")
	public void the_user_clicks_on_the_sign_in_button() {
	  wait.waitForElementVisible(sp.signButtonAfterMobileNumber);
	  js.scrollUntilElementVisible(sp.signButtonAfterMobileNumber);
	  js.jsClickWithWait(sp.signButtonAfterMobileNumber);
	}

	@Then("Validation message mobile number filed empty should get displayed to user {string}")
	public void validation_message_mobile_number_filed_empty_should_get_displayed_to_user(
			String expectedValidationMessage) {
		String actualErrmessage = common.getElementText(sp.MobileNumberErrMsg);
		common.compareText(actualErrmessage, expectedValidationMessage);
	}

	@When("User clicks on the Go to my profile button")
	public void user_clicks_on_the_go_to_my_profile_button() throws InterruptedException {
		wait.waitForElementVisible(sp.goToMyProfileBtn);
		Thread.sleep(2000);
		js.jsClickWithWait(sp.goToMyProfileBtn);
		wait.waitForElementVisible(sp.signOutBtn);

	}

	@When("User clicks on the Sign Out button")
	public void user_clicks_on_the_sign_out_button() throws InterruptedException {
		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		By pageLoader = By.cssSelector("div.page-loader");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(pageLoader));

		WebElement signOut = wait.until(ExpectedConditions.elementToBeClickable(sp.signOutBtn));

		// Scroll & click via JS 
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", signOut);
		js.executeScript("arguments[0].click();", signOut);

		System.out.println("Clicked on 'Sign Out' button");
		Thread.sleep(4000);

	}

	@Then("User should see a popup with the message Are you sure you want to logout? and selects {string}")
	public void user_should_see_a_popup_with_the_message_are_you_sure_you_want_to_logout_and_selects(String answer)
			throws InterruptedException {
		sp.clickLogoutPopupButton(answer);
		Thread.sleep(2000);
	}

	@Then("User should be logged out successfully")
	public void user_should_be_logged_out_successfully() {

		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));

		// Wait for Sign Out button 
		boolean isLoggedOut = wait.until(ExpectedConditions.invisibilityOf(sp.signOutBtn));

		Assert.assertTrue(isLoggedOut, "User is not logged out successfully.");

		System.out.println("User has been logged out successfully.");
	}
}
