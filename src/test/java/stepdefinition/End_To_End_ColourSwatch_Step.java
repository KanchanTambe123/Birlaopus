package stepdefinition;

import org.testng.Assert;

import commonutilities.ActionClass;
import commonutilities.ClickElement;
import commonutilities.CommonDataGenerator;
import commonutilities.CommonMethods;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import config.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.End_To_End_ColourSwatch_Page;
import pagefunctions.End_to_End_Flow_Page;

public class End_To_End_ColourSwatch_Step {

	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	int cartQuantity;
	
	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	End_to_End_Flow_Page ep = new End_to_End_Flow_Page();
	String fakeFirstName = dataGenerator.generateFakeFirstName();
	String fakeLastName = dataGenerator.generateFakeLastName();
	String fakeMobileNumber = dataGenerator.generateFakeMobileNumber();
	String fakeEmailId = dataGenerator.generateFakeEmail();

	End_To_End_ColourSwatch_Page cp = new End_To_End_ColourSwatch_Page();

	@Then("User selects Colour for colour swatch {string}")
	public void user_selects_colour_for_colour_swatch(String string) {
		cp.selectColourFromSwatch(string);

	}

	@Then("User selects colour swatch quantity {int}")
	public void user_selects_colour_swatch_quantity(int string) {
		cp.selectQuantity(string);
	}

	@When("User click whishlist icon on colour swatch")
	public void user_click_whishlist_icon_on_colour_swatch() {
		cp.clickWishlistIcon();
	}

	@Then("User removes the colour swatch from the wishlist if it is already added {string}")
	public void user_removes_the_colour_swatch_from_the_wishlist_if_it_is_already_added(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("User enters an invalid pincode {string} and check product availability")
	public void user_enters_an_invalid_pincode_and_check_product_availability(String string)
			throws InterruptedException {
		wait.waitForElementVisible(cp.PincodeField);
		js.scrollUntilElementVisible(cp.PincodeField);
		cp.enterInvalidPincodeAndCheck(string);

	}

	@Then("User should see an error message for invalid pincode for colour swatch {string}")
	public void user_should_see_an_error_message_for_invalid_pincode_for_colour_swatch(String expectedMessage) {
		wait.waitForElementVisible(cp.ErrMessageInvalidPincode);
		js.scrollUntilElementVisible(cp.ErrMessageInvalidPincode);
		String actualMessage = cp.ErrMessageInvalidPincode.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "error message mismatch");
	}

	@Then("User click on pincode serviceability check button")
	public void user_click_on_pincode_serviceability_check_button() {
		wait.waitForElementVisible(cp.PincodeCheckButton);
		js.scrollUntilElementVisible(cp.PincodeCheckButton);
		js.jsClickWithWait(cp.PincodeCheckButton);
	}

	@Then("User should see the error message when pincode field is empty {string}")
	public void user_should_see_the_error_message_when_pincode_field_is_empty(String expectedMessage) {
		wait.waitForElementVisible(cp.ErrMessageEmptyPincode);
		js.scrollUntilElementVisible(cp.ErrMessageEmptyPincode);
		String actualMessage = cp.ErrMessageEmptyPincode.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "error message mismatch");
	}
	
	
	@Then("Add to cart button should be disabled")
	public void add_to_cart_button_should_be_disabled() {
		js.scrollUntilElementVisible(cp.AddTocartButtonDisable);
		 Assert.assertTrue(cp.AddTocartButtonDisable.getAttribute("class").contains("disabled"));
	}
	
	@Then("User clicks on {string} tab")
	public void user_clicks_on_tab(String string) {
	  cp.clickTabFromList(string);
	}
	@Then("the User clicks the product option button in favourites")
	public void the_user_clicks_the_product_option_button_in_favourites() {
	    js.scrollUntilElementVisible(cp.productOptionButton);
	    wait.waitForElementVisible(cp.productOptionButton);
	    js.jsClickWithWait(cp.productOptionButton);
	}

@Then("the User selects {string} from the product options in the wishlist")
public void the_user_selects_from_the_product_options_in_the_wishlist(String string) {
    cp.selectOptionFromWishlist(string);
}
@Then("colour swatch should be removed from the wishlist successfully")
public void colour_swatch_should_be_removed_from_the_wishlist_successfully() {
	Assert.assertTrue(
            cp.isProductRemovedFromWishlist(),
            "Product was not removed from the wishlist"
        );
}
}
