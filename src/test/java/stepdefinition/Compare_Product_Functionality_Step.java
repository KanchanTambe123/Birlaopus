package stepdefinition;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

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
import pagefunctions.Compare_Product_Functionality_Page;
import pagefunctions.End_to_End_Flow_Page;
import pagefunctions.WebsiteLaunch;

public class Compare_Product_Functionality_Step {
	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();

	String firstProduct;
	String secondProduct;

	Compare_Product_Functionality_Page cp = new Compare_Product_Functionality_Page();

	@Given("User is on first product url {string} and adds first product {string} to the comparison list")
	public void user_is_on_first_product_url_and_adds_first_product_to_the_comparison_list(
			String CompareFirstProductUrl, String expectedProductName) throws InterruptedException {
		firstProduct = expectedProductName;
		WebsiteLaunch.webLaunch(CompareFirstProductUrl);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(cp.productTitle);
		Thread.sleep(2000);
		String actualProductName = cp.getProductName();
		System.out.println("Captured Product Name: " + actualProductName);

		if (!actualProductName.contains(expectedProductName)) {
			throw new AssertionError("Expected product: " + expectedProductName + " BUT found: " + actualProductName);
		}
		js.jsClickWithWait(cp.compareIcon);
		Thread.sleep(2000);
	    ExtentCucumberAdapter.addTestStepLog("First Product Url : " + CompareFirstProductUrl);

	}

	@Given("User is on second product url {string} and adds second product {string} to the comparison list")
	public void user_is_on_second_product_url_and_adds_second_product_to_the_comparison_list(
			String CompareSecondProductUrl, String expectedProductName) throws InterruptedException {

		secondProduct = expectedProductName;
		WebsiteLaunch.webLaunch(CompareSecondProductUrl);
		Thread.sleep(2000);
		js.scrollUntilElementVisible(cp.productTitle);
		Thread.sleep(2000);
		String actualProductName = cp.getProductName();
		System.out.println("Captured Product Name: " + actualProductName);

		if (!actualProductName.contains(expectedProductName)) {
			throw new AssertionError("Expected product: " + expectedProductName + " BUT found: " + actualProductName);
		}
		js.jsClickWithWait(cp.compareIcon);
		Thread.sleep(1500);
	    ExtentCucumberAdapter.addTestStepLog("Secound Product Url : " + CompareSecondProductUrl);

	}

	@Then("the compare counter should display {string}")
	public void the_compare_counter_should_display(String string) {
		cp.VerifyCompareCountOnCompareCounter(string);
	}

	@Then("User can view the comparison page with the selected products")
	public void user_can_view_the_comparison_page_with_the_selected_products() throws InterruptedException {
		cp.clickCompareButton();

		Thread.sleep(2000);
		cp.verifyProductsOnCompareCounter(firstProduct, secondProduct);

	}

	@When("User removes {string} from the comparison list")
	public void user_removes_from_the_comparison_list(String string) {
		cp.removeProductFromCompare(string);
	}

	@Then("The comparison list should contain {string}")
	public void the_comparison_list_should_contain(String string) {
		cp.verifyRemainingProduct(string);
	}

	@When("User clicks on the Compare Now button")
	public void user_clicks_on_the_compare_now_button() throws InterruptedException {
		js.jsClickWithWait(cp.CompareButtonPopupPage);
		Thread.sleep(2000);
	}

	@Then("Compare Page should be displayed")
	public void compare_page_should_be_displayed() {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));

		WebElement title = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@itemprop='name' and normalize-space()='Compare']")));

		Assert.assertTrue(title.isDisplayed(), "Compare Page title is not displayed");
		System.out.println("Compare Page is displayed successfully.");

	}

	@Then("All selected products should be visible with their details")
	public void all_selected_products_should_be_visible_with_their_details() {

		cp.validateMinimumProducts(2);

	}

	@Then("User should see the correct product titles on the Compare page.")
	public void user_should_see_the_correct_product_titles_on_the_compare_page() {
		cp.VerifyProductsDetailsOnComparePage(firstProduct, secondProduct);
		;
	}

	@Then("User should see and verify that the product count is {string}")
	public void user_should_see_and_verify_that_the_product_count_is(String string) {
		cp.VerifyCompareCountOnComparePage(string);
		;
	}

}
