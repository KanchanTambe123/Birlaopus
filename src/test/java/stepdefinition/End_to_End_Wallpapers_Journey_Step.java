package stepdefinition;

import org.testng.Assert;

import commonutilities.ActionClass;
import commonutilities.ClickElement;
import commonutilities.CommonMethods;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.End_To_End_ColourSwatch_Page;
import pagefunctions.End_to_End_Flow_Page;
import pagefunctions.End_to_End_Wallpapers_Journey_Page;
import pagefunctions.WebsiteLaunch;

public class End_to_End_Wallpapers_Journey_Step {
	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	End_to_End_Wallpapers_Journey_Page ew = new End_to_End_Wallpapers_Journey_Page();
	End_to_End_Flow_Page ep = new End_to_End_Flow_Page();
	End_To_End_ColourSwatch_Page cp = new End_To_End_ColourSwatch_Page();

	@Given("User is on wallpaper Product page {string}")
	public void user_is_on_wallpaper_product_page(String WallpaperProductUrl) {
		WebsiteLaunch.webLaunch(WallpaperProductUrl);
	}


	@Then("the User selects a wallpaper shade {string}")
	public void the_user_selects_a_wallpaper_shade(String string) {
		wait.waitForElementVisible(ew.availableShadesHeading);
		js.scrollUntilElementVisible(ew.availableShadesHeading);

		ew.selectShadeByProductCode(string);

	}

	@When("User select product main navigation L1 {string}, sub navigation L2 {string} and L3 wallpaper name {string} through navigation bar")
	public void user_select_product_main_navigation_l1_sub_navigation_l2_and_l3_wallpaper_name_through_navigation_bar(
			String navMenu, String navTab, String productName) throws InterruptedException {
		wait.waitForElementVisible(ep.ProductTitle);
		Thread.sleep(3000);
		ew.hoverAndClickProduct(navMenu, navTab, productName);
	}

	@Then("User enters valid pincode {string} and check product availability")
	public void user_enters_valid_pincode_and_check_product_availability(String string) throws InterruptedException {
		ew.enterPincodeAndCheck(string);
		Thread.sleep(2000);
	}

	@Then("User click on add to cart button on wallpaper section")
	public void user_click_on_add_to_cart_button_on_wallpaper_section() {
		js.scrollUntilElementVisible(ew.AddToCartButton);
		wait.waitForElementVisible(ew.AddToCartButton);
		js.jsClickWithWait(ew.AddToCartButton);
	}

	@Then("User click on View cart & Checkout button on wallpaper section")
	public void user_click_on_view_cart_checkout_button_on_wallpaper_section() {
		js.scrollUntilElementVisible(ew.ViewCheckoutButton);
		wait.waitForElementVisible(ew.ViewCheckoutButton);
		js.jsClickWithWait(ew.ViewCheckoutButton);
	}
	
	@When("User click whishlist icon on Wallpapers")
	public void user_click_whishlist_icon_on_wallpapers() {
		ew.clickWishlistIcon();
	}
	
	
	@Then("User should see an error message for invalid pincode for Wallpapers {string}")
	public void user_should_see_an_error_message_for_invalid_pincode_for_wallpapers(String  expectedMessage) {
		wait.waitForElementVisible(ew.ErrorInvalidPincodeMessage);
		js.scrollUntilElementVisible(ew.ErrorInvalidPincodeMessage);
		String actualMessage = ew.ErrorInvalidPincodeMessage.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "error message mismatch");
	}
	
	@Then("User click on pincode serviceability check button on wallpaper")
	public void user_click_on_pincode_serviceability_check_button_on_wallpaper() throws InterruptedException {
	  js.scrollUntilElementVisible(ew.PincodeCheckButton);
	  wait.waitForElementVisible(ew.PincodeCheckButton);
	  js.jsClickWithWait(ew.PincodeCheckButton);
	  Thread.sleep(2000);
	}
	
	@Then("User enters a valid pincode {string} on wallpaper")
	public void user_enters_a_valid_pincode_on_wallpaper(String string) {
	    js.scrollUntilElementVisible(ew.PincodeInputFiled);
	    wait.waitForElementVisible(ew.PincodeInputFiled);
	    ew.PincodeInputFiled.sendKeys(string);
	}

}
