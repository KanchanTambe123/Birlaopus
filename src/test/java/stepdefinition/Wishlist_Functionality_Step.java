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
import pagefunctions.End_to_End_Flow_Page;
import pagefunctions.Sign_In_Functionality_Page;
import pagefunctions.Wishlist_Functionality_Page;

public class Wishlist_Functionality_Step {

	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	int cartQuantity;
	CommonDataGenerator dataGenerator = new CommonDataGenerator();
	Sign_In_Functionality_Page sp = new Sign_In_Functionality_Page();
	String fakeFirstName = dataGenerator.generateFakeFirstName();
	String fakeLastName = dataGenerator.generateFakeLastName();
	String fakeMobileNumber = dataGenerator.generateFakeMobileNumber();
	String fakeEmailId = dataGenerator.generateFakeEmail();

	End_to_End_Flow_Page ep = new End_to_End_Flow_Page();
	Wishlist_Functionality_Page wf = new Wishlist_Functionality_Page();

	@Then("User click whishlist icon on top page")
	public void user_click_whishlist_icon_on_top_page() throws InterruptedException {
		wait.waitForElementVisible(wf.filledHeartIcon);
		js.topOfPage();
		Thread.sleep(2000);
		js.jsClickWithWait(wf.filledHeartIcon);
		Thread.sleep(8000);
	}

	@Then("User removes the product from the wishlist if it is already added {string}")
	public void user_removes_the_product_from_the_wishlist_if_it_is_already_added(String string)
			throws InterruptedException {
		wf.selectProductOptionIfVisible(string);
		Thread.sleep(6000);
	}

	@When("User click whishlist icon on product")
	public void user_click_whishlist_icon_on_product() throws InterruptedException {
		wait.waitForElementVisible(wf.WhishlistIcon);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(wf.WhishlistIcon);
		Thread.sleep(2000);
		js.jsClickWithWait(wf.WhishlistIcon);
		Thread.sleep(6000);
	}

	@Then("Product should be added to the wishlist successfully")
	public void product_should_be_added_to_the_wishlist_successfully() {
		Assert.assertTrue(wf.isProductAddedToWishlist(), "Wishlist icon is NOT filled");
	}

	@Then("Product should be removed from the wishlist successfully")
	public void product_should_be_removed_from_the_wishlist_successfully() {
		Assert.assertTrue(wf.isProductRemovedFromWishlist(), "Wishlist icon is NOT filled");
	}

}
