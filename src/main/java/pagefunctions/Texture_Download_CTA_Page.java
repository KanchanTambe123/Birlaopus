package pagefunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutilities.ClickElement;
import commonutilities.DriverManager;
import commonutilities.JSExecutor;
import commonutilities.WebDriverWaitHelper;

public class Texture_Download_CTA_Page {
	
	public WebDriver driver;
	WebDriverWaitHelper wait = new WebDriverWaitHelper();
	JSExecutor js = new JSExecutor();
	ClickElement click = new ClickElement();
	
	

	// ------------------- Scenario 1 ------------------------

	@FindBy(xpath = "//a[span[text()='Download now']]")
	public WebElement DownloadnowButton;
	
	
	
	
	
	public Texture_Download_CTA_Page() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

}
