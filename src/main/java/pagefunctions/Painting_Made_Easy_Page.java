package pagefunctions;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
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

public class Painting_Made_Easy_Page {

	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();
	Book_Survey_Form_Page bs = new Book_Survey_Form_Page();
	// Scenario 1

	@FindBy(xpath = "//div[contains(@class,'login-form-btn')]//button[normalize-space()='Schedule']")
	public WebElement ScheduleButton;

	@FindBy(xpath = "//div[contains(@class,'text')]//p[normalize-space()='Select Time']")
	public WebElement SelectTimeText;

	@FindBy(xpath = "//input[@name='carpetArea']")
	public WebElement CarpetAreaFiled;

	@FindBy(xpath = "//span[normalize-space()='Schedule Visit']")
	public WebElement ScheduleVisitText;

	// Scenario 2
	@FindBy(xpath = "//button[@id='doLaterBtnScreenBreak']")
	public WebElement DoLetterButton;
	
	// Scenario 3
	
	@FindBy(xpath = "(//a[@id='projectDetailsSkip'])[2]")
	public WebElement SkipQuestion;
	
	

	// Scenario 4

	@FindBy(xpath = "//div[contains(@class,'cmp-teaser__description')][.//h4[normalize-space()='Coming soon'] and .//p[contains(normalize-space(),'Painting services are currently not available')]]")
	public WebElement unserviceablePinPopup;

	public Painting_Made_Easy_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	// date
	public void selectAvailableDate() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Wait until all date elements are present
		List<WebElement> dates = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[contains(@class,'preferredDatesDiv')]//div[contains(@class,'toSelectDate')]")));

		// small wait
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		if (dates.size() >= 2) {
			WebElement secondDate = dates.get(1);

			// JS click
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", secondDate);
			return;
		}

		throw new RuntimeException("Less than 2 dates available to select.");
	}

	// time
	public void selectTimeSlotByText(String timeSlotText) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement slot = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[contains(@class,'timeSlot') and not(contains(@class,'disabled'))]"
						+ "//p[normalize-space()='" + timeSlotText + "']")));

		// Scroll to slot
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", slot);

		// clickable
		wait.until(ExpectedConditions.elementToBeClickable(slot));

		// Click
		js.executeScript("arguments[0].click();", slot);
	}

	public void selectHomeConfigurationType(String bhkType) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		List<WebElement> bhkOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@class='container responsivegrid bhkQuestions']/div/div")));

		for (WebElement option : bhkOptions) {
			if (option.getText().trim().equalsIgnoreCase(bhkType)) {
				option.click();
				return;
			}
		}

		throw new RuntimeException("Home configuration type not found: " + bhkType);
	}

	public void clickScheduleButton() {

		By scheduleBtn = By.xpath("//button[normalize-space()='Schedule']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement scheduleButton = wait.until(ExpectedConditions.presenceOfElementLocated(scheduleBtn));

		wait.until(ExpectedConditions.elementToBeClickable(scheduleButton));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scheduleButton);
		js.executeScript("arguments[0].click();", scheduleButton);
	}

	public void enterSurveyPinCode(String pinCode, WebElement pinInputElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Wait
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("surveyPincode")));

		// Scroll
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		// Wait
		wait.until(driver1 -> element.isDisplayed() && element.isEnabled());

		// Clear
		element.clear();

		// Enter the Pin code
		element.sendKeys(pinCode);
	}

	
	
}
