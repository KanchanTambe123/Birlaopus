package stepdefinition;

import java.time.Duration;

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
import pagefunctions.Cart_Functionality_Page;
import pagefunctions.ColourLetter_SignUp_Page;
import pagefunctions.End_to_End_Flow_Page;

public class Cart_Functionality_Step {

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

	Cart_Functionality_Page cf = new Cart_Functionality_Page();

	@Then("Product should be added to the cart successfully")
	public void product_should_be_added_to_the_cart_successfully() {
		int initialCartCount = cf.getCartItemCount();

		cf.waitForCartCountToIncrease(initialCartCount);

		int updatedCartCount = cf.getCartItemCount();

		Assert.assertTrue(updatedCartCount > initialCartCount,
				"Cart count did not increase after adding product to cart");
	}

	@Then("Cart count should be updated to {string}")
	public void cart_count_should_be_updated_to(String expectedCount) {
		int expected = Integer.parseInt(expectedCount);

		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));

		wait.until(driver -> cf.getCartItemCount() == expected);

		int actual = cf.getCartItemCount();

		Assert.assertEquals(actual, expected, "Cart count is not updated correctly in header");
	}

	@Then("User selects and clicks on the product")
	public void user_selects_and_clicks_on_the_product() {
		cf.selectSecondVisibleProduct();
	}

}
