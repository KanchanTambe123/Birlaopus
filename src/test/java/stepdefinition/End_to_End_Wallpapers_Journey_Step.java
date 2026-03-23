package stepdefinition;

import commonutilities.ActionClass;
import commonutilities.ClickElement;
import commonutilities.CommonMethods;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;
import config.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.End_to_End_Flow_Page;
import pagefunctions.End_to_End_Wallpapers_Journey_Page;

public class End_to_End_Wallpapers_Journey_Step {
	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();
	 End_to_End_Wallpapers_Journey_Page ew=new  End_to_End_Wallpapers_Journey_Page();
	 End_to_End_Flow_Page ep = new End_to_End_Flow_Page();
	 
	 
	 
	@Then("the User selects a wallpaper shade {string}")
	public void the_user_selects_a_wallpaper_shade(String string) {
		wait.waitForElementVisible(ew.availableShadesHeading);
		js.scrollUntilElementVisible(ew.availableShadesHeading);
		
		ew.selectShadeByProductCode(string);
		
	  
	}
	
	
	
	@When("User select product main navigation L1 {string}, sub navigation L2 {string} and L3 wallpaper name {string} through navigation bar")
	public void user_select_product_main_navigation_l1_sub_navigation_l2_and_l3_wallpaper_name_through_navigation_bar(String navMenu,
			String navTab,
			String productName) throws InterruptedException {
		wait.waitForElementVisible(ep.ProductTitle);
		Thread.sleep(3000);
		ew.hoverAndClickProduct(navMenu,
				navTab,
				productName);
	}
	
	
	@Then("User enters valid pincode {string} and check product availability")
	public void user_enters_valid_pincode_and_check_product_availability(String string) {
	   ew.enterPincodeAndCheck(string);
	}



}
