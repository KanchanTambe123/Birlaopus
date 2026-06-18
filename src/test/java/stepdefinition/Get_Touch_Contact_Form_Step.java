package stepdefinition;

import commonutilities.ActionClass;
import commonutilities.ClickElement;
import commonutilities.CommonDataGenerator;
import commonutilities.CommonMethods;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import config.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.End_to_End_Flow_Page;
import pagefunctions.Get_Touch_Contact_Form_Page;

public class Get_Touch_Contact_Form_Step {

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
	Get_Touch_Contact_Form_Page gp = new Get_Touch_Contact_Form_Page();

	@When("User hovers over the Get in Touch popup")
	public void user_hovers_over_the_get_in_touch_popup() throws InterruptedException {

		wait.waitForElementVisible(gp.getTouchPopup);
		Thread.sleep(2000);
		js.mousehover(gp.getTouchPopup);
		Thread.sleep(2000);

	}

	@When("User clicks on the Get in Touch option")
	public void user_clicks_on_the_get_in_touch_option() throws InterruptedException {
		wait.waitForElementVisible(gp.getInTouchBtn);
		Thread.sleep(2000);
		js.jsClickWithWait(gp.getInTouchBtn);
		Thread.sleep(2000);

	}

	@When("User updates the mandatory field Name")
	public void user_updates_the_mandatory_field_name() throws InterruptedException {
		wait.waitForElementVisible(gp.nameInput);
		js.scrollUntilElementVisible(gp.nameInput);
		gp.nameInput.clear();
		Thread.sleep(2000);
		common.SendInput(fakeFirstName, gp.nameInput);
	}

	@When("User updates the mandatory field Email")
	public void user_updates_the_mandatory_field_email() throws InterruptedException {
		wait.waitForElementVisible(gp.emailInput);
		js.scrollUntilElementVisible(gp.emailInput);
		gp.emailInput.clear();
		Thread.sleep(2000);
		common.SendInput(fakeEmailId, gp.emailInput);
	}

	@When("User updates the mandatory field Phone Number")
	public void user_updates_the_mandatory_field_phone_number() throws InterruptedException {
		wait.waitForElementVisible(gp.phoneInput);
		js.scrollUntilElementVisible(gp.phoneInput);
		gp.phoneInput.clear();
		Thread.sleep(2000);
		common.SendInput(fakeMobileNumber, gp.phoneInput);
	}

	@When("User updates the mandatory field Pincode {string}")
	public void user_updates_the_mandatory_field_pincode(String string) throws InterruptedException {
		wait.waitForElementVisible(gp.pincodeInput);
		js.scrollUntilElementVisible(gp.pincodeInput);
		gp.pincodeInput.clear();
		Thread.sleep(2000);
		common.SendInput(string, gp.pincodeInput);
	}

	@When("User clicks submits the Get in Touch form")
	public void user_clicks_submits_the_get_in_touch_form() throws InterruptedException {
		wait.waitForElementVisible(gp.submitBtn);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(gp.submitBtn);
		js.jsClickWithWait(gp.submitBtn);
		Thread.sleep(6000);
	}

	@Then("validation message for invalid pincode input field should get displayed {string}")
	public void validation_message_for_invalid_pincode_input_field_should_get_displayed(String string) {
		js.scrollUntilElementVisible(gp.ErrMsg);
		String Errmsg = common.getElementText(gp.ErrMsg);
		common.compareText(Errmsg, string);
	}

	@Then("validation message for empty pincode input field should get displayed {string}")
	public void validation_message_for_empty_pincode_input_field_should_get_displayed(String string) {
		js.scrollUntilElementVisible(gp.ErrMsg);
		String Errmsg = common.getElementText(gp.ErrMsg);
		common.compareText(Errmsg, string);
	}
	
	
	@Then("validation message for all input field should get displayed {string}")
	public void validation_message_for_all_input_field_should_get_displayed(String string) {
		js.scrollUntilElementVisible(gp.ErrMsg);
		String Errmsg = common.getElementText(gp.ErrMsg);
		common.compareText(Errmsg, string);
	}
	

}
