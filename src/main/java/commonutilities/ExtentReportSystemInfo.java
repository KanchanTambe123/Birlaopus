package commonutilities;

import org.openqa.selenium.Capabilities; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;

public class ExtentReportSystemInfo {

	
	
	public static void SystemInfo(WebDriver driver) {
		//create instance of extent report
		ExtentReports extent=ExtentService.getInstance();
		try {
			//Set System info such as OS details
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			
			//get browser capabilities using WebDriver 
			Capabilities caps=((RemoteWebDriver)driver).getCapabilities();
			
			//Set browser info in the report
			extent.setSystemInfo("Browser Name", caps.getBrowserName());
			extent.setSystemInfo("Browser Version", caps.getBrowserVersion());
			
		} catch (Exception e) {
			//handle any exception that may occur during report generation
			e.printStackTrace();
		}finally {
			
			//close the extent report instance to release resources
			if(extent!=null) {
				extent.flush();
			}
			
		}
	}
}
