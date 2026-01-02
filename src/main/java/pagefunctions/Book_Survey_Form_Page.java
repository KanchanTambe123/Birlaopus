package pagefunctions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class Book_Survey_Form_Page {

	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();

	// ------------------- Scenario 1 ------------------------

	@FindBy(xpath = "//span[normalize-space()='Book a free survey']")
	public WebElement BookfreesurveyButton;
	@FindBy(xpath = "(//div[@class='button login-form-btn']//button[normalize-space()='Next'])[1]")
	public WebElement SiteDetailsNextButton;

	@FindBy(xpath = "//div[@class='button login-form-btn']//button[@id='nextBtnScreenBreak']")
	public WebElement FewDeailsNextButton;
	@FindBy(xpath = "//a[@id='projectDetailsSkip']")
	public WebElement projectDetailsSkipOption;

	@FindBy(xpath = "//a[@id='projectDetailsNext']")
	public WebElement projectDetailsNextButton;
	
	@FindBy(xpath = "//input[@id='carpetArea']")
	public WebElement carpetAreaInputFiled;
	
	@FindBy(xpath = "//div[contains(@class,'cmp-text')]//p[contains(normalize-space(),'Thank you for sharing your details')]")
	public WebElement confirmationMsg;

	//Scenarios 2
	
	
	@FindBy(xpath = "//a[@id='surveyStepBack']")
	public WebElement surveyStepBackButton;
	
	
	@FindBy(xpath = "//span[contains(@class,'text-title') and normalize-space()='Enter Site Details']")
	public WebElement previousStepLocator;
	


	

	public Book_Survey_Form_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void selectRequirementType(String requirementType) {
		List<WebElement> requirementTypes = driver.findElements(
				By.xpath("//div[contains(@class,'survey-grid-options')]//h2[contains(@class,'cmp-teaser__title')]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (WebElement type : requirementTypes) {
			if (type.getText().trim().equalsIgnoreCase(requirementType)) {

				js.executeScript("arguments[0].scrollIntoView({block:'center'});", type);
				js.executeScript("arguments[0].click();", type);

				return;
			}
		}
		throw new RuntimeException("Requirement type not found: " + requirementType);
	}
	
	public void selectBhkType(String bhkType) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Wait for container and get cards
	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'bhkQuestions')]")));
	    List<WebElement> bhkCards = driver.findElements(
	            By.xpath("//div[contains(@class,'bhkQuestions')]//div[contains(@class,'opusTeaser')]"));

	    boolean found = false;
	    for (WebElement card : bhkCards) {
	        String title = card.findElement(By.xpath(".//h2[contains(@class,'cmp-teaser__title')]"))
	                           .getText().replaceAll("\\s+", " ").trim();
	        System.out.println("Found BHK: " + title);

	        if (title.equalsIgnoreCase(bhkType)) {
	            js.executeScript("arguments[0].scrollIntoView({block:'center'});", card);
	            wait.until(ExpectedConditions.elementToBeClickable(card)).click();
	            found = true;
	            break;
	        }
	    }

	    if (!found) {
	        System.out.println("BHK option not found or not clickable: " + bhkType + ". Continuing flow...");
	        return; // Continue flow if BHK not found
	    }

	    // Wait for carpet area input field
	    WebElement carpetArea = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.id("carpetArea"))
	    );

	    // Scroll to carpet area
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", carpetArea);
	    System.out.println("Scrolled to carpet area input field");
	}




}
