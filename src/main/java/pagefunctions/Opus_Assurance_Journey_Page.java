package pagefunctions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutilities.DriverManager;

public class Opus_Assurance_Journey_Page {
	
	public WebDriver driver;
	
	@FindBy(xpath = "(//a[@class='cmp-teaser__action-link'])[1]")
	public WebElement registerNowCta;
	
	@FindBy(xpath = "//input[@id='paintableArea']")
	public WebElement paintableAreaField;
	
	@FindBy(xpath = "(//div[@class='button login-form-btn'])[8]")
	public WebElement nextCta;
	
	@FindBy(xpath = "(//label[@class='cmp-form-options__field-label'])[2]")
	public WebElement yetToStart;
	
	@FindBy(xpath = "(//button[@type='SUBMIT'])[9]")
	public WebElement preRegisterCta;
	
	@FindBy(xpath = "//span[normalize-space()='Create an account']")
	public WebElement CreateAccountOption;
	
	@FindBy(xpath = "//input[@id='warrantyFirstName']")
	public WebElement firstNameField;
	
	@FindBy(id="warrantyLastName")
	public WebElement lastNameField;
	
	@FindBy(xpath = "//input[@id='warrantyEmail']")
	public WebElement emailIdField;
	
	@FindBy(xpath = "//input[@id='warrantyPincode']")
	public WebElement pincodeField;
	
	@FindBy(xpath = "(//div[@class='button login-form-btn']//button)[14]")
	public WebElement submitButtonCta;
	
	@FindBy(xpath = "(//div[@class='cmp-teaser__description']//h4)[1]")
	public WebElement weAreComingSoonText;
	
	@FindBy(xpath = "(//div[@class='error-txt dsp-block'])[1]")
	public WebElement lastNameErrorMsg;
	
	@FindBy(xpath = "(//div[@class='cmp-container-inner']//div[@class='error-txt dsp-block'])[1]")
	public WebElement emailIdErrorMsg;
	
	@FindBy(xpath = "(//div[normalize-space()='Invalid Input.'])[1]")
	public WebElement pincodeErrorMsg;
	
	@FindBy(xpath = "//div[@class='container responsivegrid common-form-wrap verify-otp dsp-block']//div[@class='cmp-form-text form-feilds']//input")
	public List<WebElement> otpNoInputFiled;
	
    @FindBy(xpath = "//div[contains(@class,'api-error') and contains(text(),'OTP')]")
	public WebElement otpFieldErrorMsg;
	
	@FindBy(xpath = "//button[@id='verify-otp-btn']")
	public WebElement verifyButton;
	
	
	
	@FindBy(xpath = "//div[contains(@class,'error-txt') and contains(normalize-space(.),'Paintable area should be more than')]")
	public WebElement ErrMessagePaintingCarpetArea;
	
	
	
	public  Opus_Assurance_Journey_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

}
