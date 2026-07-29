package pagefunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class Painting_Service_Form_Page {
	
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();
	
	//Scenario 1
	
	@FindBy(xpath = "(//input[@id='shortFormName'])[1]")
	public WebElement NameFiled;
	
	@FindBy(xpath =  "(//input[@id='shortFormNumber'])[1]")
	public WebElement MobileNumberFiled;
	
	
	@FindBy(xpath =  "(//button[normalize-space()='Sign up for free'])[1]")
	public WebElement SignupfreeButton;


	@FindBy(xpath =  "//div[@id='a_trustworthy_transformation_by_our_painting_service']")
	public WebElement DetailSection;
	
	
	@FindBy(xpath =  "(//button[@type='SUBMIT'])[11]")
	public WebElement submitButton;
	
	
	 // Pincode input field
    @FindBy(xpath = "(//input[@id='shortFormNumber'])[1]")
    public WebElement PincodeField;
	
    
    @FindBy(xpath = " (//button[contains(@class,'cmp-button') and .//span[text()='Back']])[1]")
    public WebElement BackButton;
    
    @FindBy(xpath = "(//div[@class='error-txt dsp-block'])[1]")
    public WebElement ErrMessageInvalidPincode;
    
    @FindBy(xpath = "//a[normalize-space()='Painting made easy']")
    public WebElement PaintingmadeEasySection;
    
    @FindBy(xpath = "//a[normalize-space()='Get a free quote']")
    public WebElement GetfreequoteButton;
    
    @FindBy(xpath = "//div[@class='cmp-teaser__description'][.//h4='Sorry' and .//p[contains(text(),'expanding rapidly')]]")
    public WebElement UnservicablePincodePopUpMessage;
    
    
  
    




	
  
	
	
	
	public  Painting_Service_Form_Page () {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

}
