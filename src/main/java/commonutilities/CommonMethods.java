package commonutilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import config.ConfigReader;

public class CommonMethods {

	public WebDriver driver;
	WebDriverWaitHelper waitHelper;
	SoftAssert softAssert;
	JSExecutor js;
	WebDriverWaitHelper wait;

	// ConfigReader to read the configuration properties
	private static final ConfigReader config = new ConfigReader();

	/**
	 * method to get page title
	 * 
	 * @return String
	 */

	public CommonMethods() {
		driver = DriverManager.getDriver();
		waitHelper = new WebDriverWaitHelper();
		softAssert = new SoftAssert();
		js = new JSExecutor();
	}

	// public void compareText(String actualText, String expectedText) {
	// softAssert.assertEquals(actualText, expectedText, "Text comparison failed!");
	// softAssert.assertAll();
	// }

	public void compareText(String actualText, String expectedText) {
		// Print for debugging purposes
		System.out.println("Comparing actual text: " + actualText);
		System.out.println("Comparing expected text: " + expectedText);

		// Perform soft assertion
		softAssert.assertEquals(actualText, expectedText, "Text comparison failed!");

		// Check if any assertions failed and print message accordingly
		try {
			softAssert.assertAll(); // This will throw AssertionError if any assertions failed
			System.out.println("All assertions passed.");
		} catch (AssertionError e) {
			System.out.println("Some assertions failed. Error: " + e.getMessage());
		}
	}

	public void compareNo(int expected, int expectedText) {
		softAssert.assertEquals(expected, expectedText, "Text comparison failed!");
		softAssert.assertAll();
	}

	public void compareNo1(int expected, String expectedText) {
		softAssert.assertEquals(expected, expectedText, "Text comparison failed!");
		softAssert.assertAll();
	}

	public String getTitle() {

		return driver.getTitle();
	}

	public void refresh() {

		driver.navigate().refresh();
	}

	/**
	 * method to verify the page title
	 * 
	 * @param ExpectedTitle : String : expected title
	 * @return boolean
	 */

	public boolean checkTitle(String ExpectedTitle) {
		String actualTitle = getTitle();
		return actualTitle.equals(ExpectedTitle);

	}

	/**
	 * method to get the WebElement text
	 * 
	 * @param element : WebElement : locator
	 * @return String
	 */

	public String getElementText(WebElement element) {

		waitHelper.waitForElementToBeVisible(element, Integer.parseInt(config.getProb("wait")));
		return element.getText();

	}

	/**
	 * method to send the input
	 * 
	 * @param input   : String : send the input in textbox
	 * @param element : WebElement : element locator
	 */

	public void SendInput(String input, WebElement element) {

		waitHelper.waitForElementToBeVisible(element, Integer.parseInt(config.getProb("wait")));
		element.sendKeys(input);

	}

	/**
	 * method to get javascript pop-up alert text
	 * 
	 * @return String
	 */
	public String getAlertText() {
		return DriverManager.getDriver().switchTo().alert().getText();
	}

	/**
	 * method to check javascript pop-up alert text
	 * 
	 * @param text : String : Text to verify in Alert
	 * @return boolean : get the boolean value [true or false]
	 * 
	 */
	public boolean checkAlertText(String text) {
		String getAlertValue = getAlertText();
		return getAlertValue.equals(text);
	}

	/**
	 * Method to handle alert
	 * 
	 * @param decision : String : Accept or dimiss alert
	 */

	public void handleAlert(String decision) {

		if (decision.equals("accept")) {
			driver.switchTo().alert().accept();
		} else {
			driver.switchTo().alert().dismiss();
		}

	}

	/**
	 * 
	 * @param element             : WebElement : locator
	 * @param by                  :String : select the element from dropdown by text
	 *                            or value
	 * @param ExpectedOptionValue : String : element to select from dropdown
	 * @param shouldbeSelected    : boolean : whether the speficied optoin should be
	 *                            selected or not.
	 * @return boolean : if expected condition matchs it given true or else false
	 */

	public boolean isOptionfromDropDownSelected(WebElement element, String by, String ExpectedOptionValue,
			boolean shouldbeSelected) {

		Select selectList = null;

		try {
			waitHelper.waitForElementToBeVisible(element, Integer.parseInt(config.getProb("wait")));

			// create the selecte object
			selectList = new Select(element);

			// get the actual value
			String actualValue = "";

			if (by.equals("text")) {
				actualValue = selectList.getFirstSelectedOption().getText();
			} else if (by.equals("value")) {
				actualValue = selectList.getFirstSelectedOption().getAttribute("value");
			}

			// check the actual value matchs the expected option value and ShouldBeSelected
			// flag
			return actualValue.equals(ExpectedOptionValue) && shouldbeSelected;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	public boolean isElementClickable(WebElement ele) {

		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
					Duration.ofSeconds(Integer.parseInt(config.getProb("wait"))));
			wait.until(ExpectedConditions.elementToBeClickable(ele));

			waitHelper.waitForElementToBeClickable(ele, Integer.parseInt(config.getProb("wait")));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 
	 * @param element             : WebElement : locator
	 * @param attributeName       :String :
	 * 
	 * @param ExpectedOptionValue : String : element to select from dropdown
	 * @param shouldbeSelected    : boolean : whether the speficied optoin should be
	 *                            selected or not.
	 * @return String : Method to retrieve text from attribute
	 */

	public String getTextFromAttribute(WebElement ele, String attributeName) {
		waitHelper.waitForElementToBeVisible(ele, Integer.parseInt(config.getProb("wait")));
		return ele.getAttribute(attributeName);
	}

	public String getDateFromSystem() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
		return ft.format(date);
	}

	public boolean isElementDisplayed(WebElement ele) {

		return ele.isDisplayed();

	}

	public boolean isElementEnable(WebElement ele) {

		return ele.isEnabled();
	}

	public int calculateAge(String age) {

		LocalDate currentDate = LocalDate.now();
		LocalDate birthDate = LocalDate.parse(age);
		Period period = Period.between(birthDate, currentDate);

		return period.getYears();

	}

	public boolean isElementSelected(WebElement ele) {
		waitHelper.waitForElementToBeVisible(ele, Integer.parseInt(config.getProb("wait")));
		if (ele.getAttribute("class").contains("active")) {
			return true;
		}
		return false;

	}

	public String[] handleChildWindowAndFetchTitleUrl() {
		String parentWindow = driver.getWindowHandle();
		String[] childWindowInfo = new String[2]; // [0] = title, [1] = URL

		try {
			Set<String> allWindows = driver.getWindowHandles();
			while (allWindows.size() <= 1) {
				Thread.sleep(1000);
				allWindows = driver.getWindowHandles();
			}

			// Switch to child window
			for (String windowHandle : allWindows) {
				if (!windowHandle.equals(parentWindow)) {
					driver.switchTo().window(windowHandle);

					// Fetch title and URL
					childWindowInfo[0] = driver.getTitle();
					childWindowInfo[1] = driver.getCurrentUrl();

					driver.close(); // Close child window
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.switchTo().window(parentWindow); // Return to parent
		}

		return childWindowInfo;
	}

	public String switchToChildWindow() {
		String parentWindow = driver.getWindowHandle();

		try {
			// Wait for the new window to appear
			Set<String> allWindows = driver.getWindowHandles();
			while (allWindows.size() <= 1) {
				Thread.sleep(1000); // Wait for child window
				allWindows = driver.getWindowHandles();
			}

			// Switch to the child window
			for (String windowHandle : allWindows) {
				if (!windowHandle.equals(parentWindow)) {
					driver.switchTo().window(windowHandle);
					return parentWindow; // Return parent window handle for switching back later
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return parentWindow;
	}

//	public void selectHeaderLink(String linkName) throws InterruptedException {
//
//		Thread.sleep(3000);
//
//		for (WebElement ele : headerlinkSelection) {
//			String selectionText = ele.getText().trim();
//
//			if (selectionText.equalsIgnoreCase(linkName)) {
//				// ((JavascriptExecutor)
//				// driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				// ele);
//				wait.waitForWebElementVisibility(ele, 30);
//				js.scrollUntilElementVisible(ele);
//				Thread.sleep(500);
//				js.jsClickWithWait(ele);
//				Thread.sleep(3000);
//				break;
//			}
//		}
//
//	}

	/**
	 * Method to select a link from a list of header links based on the link text
	 * 
	 * @param linksList : List<WebElement> : list of header link elements
	 * @param linkText  : String : link text to be matched
	 */
//	public void selectLinkByText(List<WebElement> headerlinkSelection, String linkText) {
//	    try {
//	        Thread.sleep(3000); // Optional wait for page to stabilize
//
//	        for (WebElement link : headerlinkSelection) {
//	            String text = link.getText().trim();
//	            if (text.equalsIgnoreCase(linkText)) {
//	                waitHelper.waitForWebElementVisibility(link, Integer.parseInt(config.getProb("wait")));
//	                
//	                // Scroll into view and click using JS
////	                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
////	                jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
////	                
////	                Thread.sleep(500); // short pause before clicking
////	                jsExecutor.executeScript("arguments[0].click();", link);
//	                js.scrollUntilElementVisible(link);
//	                wait.waitForWebElementVisibility(link, 30);
//					
//					Thread.sleep(500);
//					js.jsClickWithWait(link);
//	                
//	                Thread.sleep(3000); // wait for navigation
//	                break;
//	            }
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        throw new RuntimeException("Failed to click on header link: " + linkText);
//	    }
//	}

	public void selectLinkByText(List<WebElement> footerlinkSelection, String linkText) {
		try {
			Thread.sleep(3000); // Optional wait for page to stabilize

			System.out.println("Looking for link: [" + linkText + "]");
			System.out.println("Found " + footerlinkSelection.size() + " footer links.");

			for (WebElement link : footerlinkSelection) {
				String text = link.getText().trim();
				System.out.println("footer link text: [" + text + "]");

				if (text.equalsIgnoreCase(linkText)) {
					//waitHelper.waitForWebElementVisibility(link, Integer.parseInt(config.getProb("wait")));
					waitHelper.waitForElementToBeVisible(link,Integer.parseInt(config.getProb("wait")));

					js.scrollUntilElementVisible(link);
					waitHelper.waitForElementToBeVisible(link, 30);
					Thread.sleep(500);
					js.jsClickWithWait(link);

					Thread.sleep(3000);
					return;
				}
			}

			throw new RuntimeException("No matching footer link found for: " + linkText);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to click on footer link: " + linkText);
		}
	}

	public void selectLinkByText1(List<WebElement> headerlinkSelection1, String linkText1) {
		try {
			Thread.sleep(3000); // Optional wait for page to stabilize

			System.out.println("Looking for link: [" + linkText1 + "]");
			System.out.println("Found " + headerlinkSelection1.size() + " header links.");

			for (WebElement link1 : headerlinkSelection1) {
				String text = link1.getText().trim();
				System.out.println("Header link text: [" + text + "]");

				if (text.equalsIgnoreCase(linkText1)) {
					waitHelper.waitForElementToBeVisible(link1,Integer.parseInt(config.getProb("wait")));

//				js.scrollUntilElementVisible(link1);
					waitHelper.waitForElementToBeVisible(link1, 30);
					js.jsClickWithWait(link1);
					link1.click();
					return;
				}
			}

			throw new RuntimeException("No matching header link found for: " + linkText1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to click on header link: " + linkText1);
		}
	}

	public void zipFile(String inputFilePath, String outputZipPath) throws IOException {
		try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputZipPath));
				FileInputStream fis = new FileInputStream(inputFilePath)) {

			ZipEntry entry = new ZipEntry(new File(inputFilePath).getName());
			zos.putNextEntry(entry);

			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) >= 0) {
				zos.write(buffer, 0, length);
			}

			zos.closeEntry();
		}
	}
    public String switchToNewWindow() {
        String originalWindow = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getWindowHandles().size() > 1);

        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        return originalWindow;
    }

    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void closeCurrentWindow() {
        driver.close();
    }
    public static void waitForPageLoad(WebDriver driver) throws TimeoutException {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(40)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .toString()
                        .equals("complete")
            );
            System.out.println("Page loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error while waiting for page load: " + e.getMessage());
        }
    }
 
  

    

}
