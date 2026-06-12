package stepdefinition;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonutilities.ActionClass;
import commonutilities.ClickElement;

import commonutilities.CommonMethods;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.Search_Page;

public class Search_Step {

	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();

	Search_Page sp = new Search_Page();

	@When("User clicks on the search icon")
	public void user_clicks_on_the_search_icon() {
		// Wait until search icon is visible
		wait.waitForElementToBeVisible(sp.searchIcon, 30);

		// Then click with timeout
		js.jsClickWithWait(sp.searchIcon);

		wait.waitForElementToBeVisible(sp.searchPanel, 30);
	}

	@Then("The search panel should appear")
	public void the_search_panel_should_appear() throws InterruptedException {
		wait.waitForElementToBeVisible(sp.searchPanel, 30);

		boolean st = common.isElementDisplayed(sp.searchPanel);

		System.out.println(st);
		Thread.sleep(2000);
	}

	@When("User enters the product name {string} in the search box")
	public void user_enters_the_product_name_in_the_search_box(String productName) throws InterruptedException {
		sp.searchPanel.sendKeys(productName);
		sp.searchPanel.sendKeys(Keys.ENTER);
		Thread.sleep(4000);
	}
	
	@Then("User should be redirected to the search results page, and the results count should be displayed as greater than {int}")
	public void user_should_be_redirected_to_the_search_results_page_and_the_results_count_should_be_displayed_as_greater_than(Integer minCount) {
		int count = sp.getNumberFromText(sp.resultMessage, 30);

		Assert.assertTrue("Expected results count > " + minCount + ", but was: " + count, count > minCount);
	}

	@When("The results count should be greater than {int}")
	public void the_results_count_should_be_greater_than(Integer minCount) {
		int count = sp.getNumberFromText(sp.resultMessage, 30);

		Assert.assertTrue("Expected results count > " + minCount + ", but was: " + count, count > minCount);
	}

	@Then("User should see a message for the invalid keyword containing {string}")
	public void user_should_see_a_message_for_the_invalid_keyword_containing(String string)
			throws InterruptedException {
		Thread.sleep(5000);

		js.scrollUntilElementVisible(sp.InvalidresultMessage);
		Thread.sleep(3000);
		String actualErrMsg = common.getElementText(sp.InvalidresultMessage);
		common.compareText(actualErrMsg, string);
	}

	@Then("User should see the trending search {string}")
	public void user_should_see_the_trending_search(String expectedSearch) throws InterruptedException {
		boolean found = sp.isTrendingSuggestionVisible(sp.searchPanel, sp.trendingSearch, expectedSearch, 20);

		Assert.assertTrue("Expected trending search '" + expectedSearch + "' to be visible.", found);

		Thread.sleep(2000);
	}
	@When("User click on serch box then selects the suggestion {string}")
	public void user_click_on_serch_box_then_selects_the_suggestion(String suggestion) throws InterruptedException {
		boolean clicked = sp.clickTrendingSuggestion(sp.searchPanel, sp.trendingSearchList, suggestion, 20);

		Assert.assertTrue("Trending suggestion '" + suggestion + "' not found.", clicked);
		Thread.sleep(2000);
	}
	@When("User selects the suggestion {string}")
	public void user_selects_the_suggestion(String suggestion) throws InterruptedException {
		boolean clicked = sp.clickTrendingSuggestion(sp.searchPanel, sp.trendingSearchList, suggestion, 20);

		Assert.assertTrue("Trending suggestion '" + suggestion + "' not found.", clicked);
		Thread.sleep(2000);
	}

	@Then("User should be redirected to the respective results page for {string}")
	public void user_should_be_redirected_to_the_respective_results_page_for(String suggestion)
			throws InterruptedException {
		new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30)).until(webDriver -> "complete"
				.equals((String) ((JavascriptExecutor) webDriver).executeScript("return document.readyState")));

		String currentUrl = DriverManager.getDriver().getCurrentUrl();
		System.out.println(currentUrl);

		Assert.assertTrue("Expected to be redirected to page for '" + suggestion + "', but URL is: " + currentUrl,
				currentUrl.toLowerCase().contains(suggestion.toLowerCase().replace(" ", "-")));
		Thread.sleep(2000);
	}

}
