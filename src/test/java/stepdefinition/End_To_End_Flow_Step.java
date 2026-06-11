package stepdefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import pagefunctions.End_to_End_Flow_Page;
import pagefunctions.WebsiteLaunch;

public class End_To_End_Flow_Step {

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

	@Given("User is on BirlaOpus HomePage {string}")
	public void user_is_on_birla_opus_home_page(String birlaopusHomeUrl) {
		WebsiteLaunch.webLaunch(birlaopusHomeUrl);
	}

	@When("User select product main navigation L1 {string}, sub navigation L2 {string} and L3 product name {string} through navigation bar")
	public void user_select_product_main_navigation_l1_sub_navigation_l2_and_l3_product_name_through_navigation_bar(
			String string, String string2, String string3) throws InterruptedException {
		wait.waitForElementVisible(ep.ProductTitle);
		Thread.sleep(3000);
		ep.hoverAndClickProduct(string, string2, string3);
		Thread.sleep(3000);
	}

	@Then("User click on Shop now button")
	public void user_click_on_shop_now_button() throws InterruptedException {
		wait.waitForElementVisible(ep.shopNowButton);
		Thread.sleep(1000);
		js.scrollUntilElementVisible(ep.shopNowButton);
		Thread.sleep(1000);
		js.jsClickWithWait(ep.shopNowButton);
		Thread.sleep(2000);
	}

	@Then("User selects Colour {string}")
	public void user_selects_Colour(String string) throws InterruptedException {
		wait.waitForElementVisible(ep.ProductColourSection);
		Thread.sleep(3000);
		js.scrollUntilElementVisible(ep.ProductColourSection);
		Thread.sleep(3000);
		ep.selectColorByName(string);
		Thread.sleep(4000);
	}

	@Then("User selects quantity {int} of {string} Ltr pack")
	public void user_selects_quantity_of_ltr_pack(Integer quantity, String litrePack) throws InterruptedException {
		wait.waitForElementVisible(ep.SelectQuantityTitle);
		Thread.sleep(3000);
		js.scrollUntilElementVisible(ep.SelectQuantityTitle);
		ep.selectQuantity(litrePack, quantity);
		Thread.sleep(2000);
	}

	@Then("User enters a valid pincode {string} and check product availability")
	public void user_enters_a_valid_pincode_and_check_product_availability(String string) throws InterruptedException {
		js.scrollUntilElementVisible(ep.PincodeField);
		ep.enterPincodeAndCheck(string);
		Thread.sleep(2000);
	}

	@Then("User click on add to cart button")
	public void user_click_on_add_to_cart_button() throws InterruptedException {
		wait.waitForElementVisible(ep.AddToCartButton);
		Thread.sleep(2000);
		ep.clickAddToCart();
		Thread.sleep(2000);
	}

	@Then("User clicks on the close icon")
	public void user_clicks_on_the_close_icon() throws InterruptedException {
		wait.waitForElementVisible(ep.closeiconLoginPopup);
		Thread.sleep(2000);
		js.jsClickWithWait(ep.closeiconLoginPopup);
		Thread.sleep(2000);
	}

	@Then("User click on View cart & Checkout button")
	public void user_click_on_view_cart_checkout_button() throws InterruptedException {
		wait.waitForElementVisible(ep.ViewcartCheckoutButton);
		js.scrollUntilElementVisible(ep.ViewcartCheckoutButton);
		Thread.sleep(2000);
		js.jsClickWithWait(ep.ViewcartCheckoutButton);
		Thread.sleep(2000);
	}

	@Then("User increase the product quantity {string}")
	public void user_increase_the_product_quantity(String quantityStr) throws InterruptedException {
		int desiredQuantity = Integer.parseInt(quantityStr); // convert String to int
		ep.updateCartQuantity(desiredQuantity);
		cartQuantity = ep.updateCartQuantity(desiredQuantity);
	}

	@Then("User clicks the cart icon on the header and removes the product from the cart if available")
	public void user_clicks_the_cart_icon_on_the_header_and_removes_the_product_from_the_cart_if_available()
			throws InterruptedException {
		wait.waitForElementVisible(ep.cartIcon);
		Thread.sleep(2000);
		js.jsClickWithWait(ep.cartIcon);
		Thread.sleep(4000);
		ep.removeProductFromCartIfAvailable();
		Thread.sleep(4000);

	}

	@Then("User clicks on the Proceed to Enter Address button")
	public void user_clicks_on_the_proceed_to_enter_address_button() throws InterruptedException {
		wait.waitForElementVisible(ep.ProceedenteraddressButton);
		Thread.sleep(2000);
		js.jsClickWithWait(ep.ProceedenteraddressButton);
		Thread.sleep(3000);

	}

	@Then("User clicks on the Apply button, verifies the availability of coupon vouchers, and applies a coupon if available")
	public void user_clicks_on_the_apply_button_verifies_the_availability_of_coupon_vouchers_and_applies_a_coupon_if_available()
			throws InterruptedException {

		Thread.sleep(3000);
		ep.applyCouponIfAvailable();
		Thread.sleep(3000);
	}

	@Then("User verifies the product quantity in the final order summary {string}")
	public void user_verifies_the_product_quantity_in_the_final_order_summary(String expectedQuantityStr) {
		// Get the final quantity from the order summary
		int expectedQuantity = Integer.parseInt(expectedQuantityStr); // convert to int
		int finalQty = ep.getFinalOrderSummaryQuantity();

		// Assert that it matches the expected quantity from the feature file
		Assert.assertEquals(finalQty, expectedQuantity,
				"Mismatch between expected quantity and final order summary quantity");

		System.out.println("Final order summary quantity verified: " + finalQty);
	}

	@Then("the total payable amount should be correctly calculated and displayed")
	public void the_total_payable_amount_should_be_correctly_calculated_and_displayed() {


	// Call verification method
	ep.verifyTotalPayableAmount();
	
	

	}


	@Then("User clicks on the Edit button to update the shipping details.")
	public void user_clicks_on_the_edit_button_to_update_the_shipping_details() throws InterruptedException {
		wait.waitForElementVisible(ep.ShippingEditButton);
		Thread.sleep(3000);
		js.scrollUntilElementVisible(ep.ShippingEditButton);
		Thread.sleep(3000);
		js.jsClickWithWait(ep.ShippingEditButton);
		Thread.sleep(3000);
	}

	@Then("User enters a valid first name for the updated shipping address")
	public void user_enters_a_valid_first_name_for_the_updated_shipping_address() throws InterruptedException {
		wait.waitForElementVisible(ep.FirstNameInputFiled);
		js.scrollUntilElementVisible(ep.FirstNameInputFiled);
		ep.FirstNameInputFiled.clear();
		Thread.sleep(2000);
		common.SendInput(fakeFirstName, ep.FirstNameInputFiled);
	}

	@Then("User enters a valid last name for the updated shipping address")
	public void user_enters_a_valid_last_name_for_the_updated_shipping_address() throws InterruptedException {
		wait.waitForElementVisible(ep.LastNameInputFiled);
		js.scrollUntilElementVisible(ep.LastNameInputFiled);
		ep.LastNameInputFiled.clear();
		Thread.sleep(2000);
		common.SendInput(fakeLastName, ep.LastNameInputFiled);
	}

	@Then("User enters a valid mobile number for the updated shipping address")
	public void user_enters_a_valid_mobile_number_for_the_updated_shipping_address() throws InterruptedException {
		wait.waitForElementVisible(ep.MobileNumberInputFiled);
		js.scrollUntilElementVisible(ep.MobileNumberInputFiled);
		ep.MobileNumberInputFiled.clear();
		Thread.sleep(2000);
		common.SendInput(fakeMobileNumber, ep.MobileNumberInputFiled);
	}

	@Then("User clicks on the popup Close button")
	public void user_clicks_on_the_popup_close_button() throws InterruptedException {
		wait.waitForElementVisible(ep.PopupClose);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(ep.PopupClose);
		Thread.sleep(2000);
		js.jsClickWithWait(ep.PopupClose);
		Thread.sleep(4000);
	}

	@Then("User clicks on the Submit button to update the details.")
	public void user_clicks_on_the_submit_button_to_update_the_details() throws InterruptedException {
		wait.waitForElementVisible(ep.SubmitButtonForSaveAdress);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(ep.SubmitButtonForSaveAdress);
		Thread.sleep(2000);
		js.jsClickWithWait(ep.SubmitButtonForSaveAdress);
		Thread.sleep(2000);
	}

	@Then("User enters a valid shipping address {string}")
	public void user_enters_a_valid_shipping_address(String string) throws InterruptedException {
		wait.waitForElementVisible(ep.AddressInputFiled);
		js.scrollUntilElementVisible(ep.AddressInputFiled);
		ep.AddressInputFiled.clear();
		Thread.sleep(2000);
		common.SendInput(string, ep.AddressInputFiled);
	}

	@Then("User clicks on the Proceed to Shipment button")
	public void user_clicks_on_the_proceed_to_shipment_button() throws InterruptedException {
		wait.waitForElementVisible(ep.ProceedShipmentbutton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(ep.ProceedShipmentbutton);
		Thread.sleep(2000);
		js.jsClickWithWait(ep.ProceedShipmentbutton);
		Thread.sleep(6000);

	}

	@Then("User clicks on the Proceed to payment button")
	public void user_clicks_on_the_proceed_to_payment_button() throws InterruptedException {
		wait.waitForElementVisible(ep.ProceedPaymentbutton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(ep.ProceedPaymentbutton);
		Thread.sleep(2000);
		js.jsClickWithWait(ep.ProceedPaymentbutton);
		Thread.sleep(8000);
	}

	@Then("the validation message should be displayed as {string}")
	public void the_validation_message_should_be_displayed_as(String expectedValidationMessage) {

		String actualFullNameErr = common.getElementText(ep.minimumCartValueMessage);
		common.compareText(actualFullNameErr, expectedValidationMessage);
	}

	@Then("User clicks on continue shopping button")
	public void user_clicks_on_continue_shopping_button() throws InterruptedException {
		wait.waitForElementVisible(ep.ContinueShoppingButton);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(ep.ContinueShoppingButton);
		Thread.sleep(2000);
		js.jsClickWithWait(ep.ContinueShoppingButton);
		Thread.sleep(6000);
	}

	@Then("User select product category {string}")
	public void user_select_product_category(String string) throws InterruptedException {

		ep.selectCategory(string);
		Thread.sleep(6000);
	}

	@Then("User select product and click shop now button")
	public void user_select_product_and_click_shop_now_button() throws InterruptedException {
		ep.scrollAndClickValidShopNow();
		Thread.sleep(4000);
	}

}
