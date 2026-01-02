package pagefunctions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class Get_Touch_Contact_Form_Page {
	
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();
	
	
	
	
	//scenarios1

	@FindBy(xpath = "//div[contains(@class,'getTouch-popup')]")
	public WebElement getTouchPopup;
    
    
    @FindBy(xpath = "//a[contains(@class,'get-in-touch') and contains(@class,'gettouch-div')]")
    public WebElement getInTouchBtn;
    
    @FindBy(xpath= "//input[contains(@class,'cmp-form-text__input') and @aria-label='Name']")
    public WebElement nameInput;
    
    
    @FindBy(xpath = "//input[contains(@class,'cmp-form-text__input') and @aria-label='Email ID']")
    public WebElement emailInput;
    
    @FindBy(xpath = "//input[contains(@class,'cmp-form-text__input') and @aria-label='Phone Number']")
    public WebElement phoneInput;
    
    @FindBy(xpath = "//input[contains(@class,'cmp-form-text__input') and @aria-label='Pincode']")
    public WebElement pincodeInput;

    
   @FindBy(xpath = "(//*[@type='SUBMIT'])[15]")
    public WebElement submitBtn;
    

   
   //Scenarios 2
   
   @FindBy(xpath = "(//*[@class='error-txt dsp-block'])")
    public WebElement ErrMsg;
  


    
    

    
    
	
	
	
	
	public  Get_Touch_Contact_Form_Page  () {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	
	


}
