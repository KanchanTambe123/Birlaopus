package pagefunctions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class Sign_In_Functionality_Page {
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();
	
	
	
	
	//senaros1

    @FindBy(id = "profile-icon")
    public WebElement profileIcon;

    @FindBy(xpath = "//div[@class='button sign-in-btn-wrapper']")
    public WebElement signInButton;

    @FindBy(id = "sign-in-btn")
    public WebElement signButtonAfterMobileNumber;

    @FindBy(xpath = "(//div[@class='button login-form-btn']//button[text()='Verify OTP'])[1]")
    public WebElement verifyOtpButton;
    
    @FindBy(id = "sign-in-input")
    public WebElement signInMobileNumberFiled;
    
    
    @FindBy(xpath = "//span[contains(@class,'text-title') and contains(text(),'Welcome')]")
    public WebElement welcomeMessage;
    
    //Scenarios 2,3
    @FindBy(xpath = "//div[@class='error-txt dsp-block']")
    public WebElement MobileNumberErrMsg;
    //Scenarios 4
    @FindBy(xpath = "//span[contains(text(),'Go to my profile')]")
    public WebElement goToMyProfileBtn;

    @FindBy(xpath = "//li[@role='tab' and text()='Log out']")
    public WebElement signOutBtn;
    
    
    @FindBy(xpath =  "(//span[@class='cmp-text-btn-text'][normalize-space()='Yes'])[2]")
    public WebElement signOutYesBtn;
    

    


	
	
	public Sign_In_Functionality_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

    private By mobileField = By.xpath("//input[@id='sign-in-input']");
	 public void enterMobile(String mobileNumber) {
	        driver.findElement(mobileField).sendKeys(mobileNumber);
	        System.out.println("Entered mobile: " + mobileNumber);
	    }
	 
	 
	
	 public void clickLogoutPopupButton(String answer) {
		    WebDriver driver = DriverManager.getDriver();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		    JavascriptExecutor js = (JavascriptExecutor) driver;

		    String className = answer.equalsIgnoreCase("Yes") ? "logout-user" : "logout-user-no";
		    String buttonXpath = String.format("//a[contains(@class,'%s')]//span[text()='%s']", className, answer);

		    WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(buttonXpath)));

		    // Scroll and click using JS
		    js.executeScript("arguments[0].scrollIntoView(true);", button);
		    js.executeScript("arguments[0].click();", button);

		    System.out.println("Clicked '" + answer + "' on logout popup");
		}


}
