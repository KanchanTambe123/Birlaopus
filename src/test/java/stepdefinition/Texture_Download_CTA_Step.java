package stepdefinition;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pagefunctions.Search_Page;
import pagefunctions.Texture_Download_CTA_Page;
import pagefunctions.WebsiteLaunch;

public class Texture_Download_CTA_Step {
	ConfigReader config = new ConfigReader();
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	ClickElement click = new ClickElement();
	CommonMethods common = new CommonMethods();
	ActionClass actionClass = new ActionClass();
	JSExecutor js = new JSExecutor();

	Search_Page sp = new Search_Page();
	
	Texture_Download_CTA_Page td=new Texture_Download_CTA_Page();
	   public String downloadDir = System.getProperty("user.dir") + "/downloads/";
	    public File downloadedFile;
	
	@Given("User is on BirlaOpus TexturePage {string}")
	public void user_is_on_birla_opus_texture_page(String textureUrl) {
		WebsiteLaunch.webLaunch(textureUrl);
	}
	@And("User clicks on the Download now CTA for the Latest Patterns")
	public void user_clicks_on_the_download_now_cta_for_the_latest_patterns() throws InterruptedException {
	  js.scrollUntilElementVisible(td.DownloadnowButton);
	  
	  Thread.sleep(2000);
	  js.jsClickWithWait(td.DownloadnowButton);
	  
	  Thread.sleep(3000);
	}
	
	
	
	@Then("The Texture PDF should open successfully")
	public void the_texture_pdf_should_open_successfully () throws Exception {
		
		  Thread.sleep(2000); // allow time for navigation OR download

		    String currentUrl = DriverManager.getDriver().getCurrentUrl();
		    System.out.println("After clicking CTA, current URL: " + currentUrl);

		    // PDF opens in browser (URL ends with .pdf)
		    if (currentUrl.contains(".pdf")) {
		        System.out.println("PDF opened in browser successfully.");
		        return;
		    }
		    
		    
		    
		  


		  
	}
	
	

	}


