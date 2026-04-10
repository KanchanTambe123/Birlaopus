package pagefunctions;

import org.openqa.selenium.WebDriver;

import commonutilities.DriverManager;
import config.AppConfig;

import config.ConfigReader;

public class WebsiteLaunch extends DriverManager {

	WebDriver driver;

	public WebsiteLaunch() {
		driver = DriverManager.getDriver();
	}

	private static final ConfigReader config = new ConfigReader();

	public static void webLaunch(String url) {

		 switch (url) {
         case "birlaopusHomeUrl":
        		DriverManager.getDriver().navigate().to(AppConfig.birlaopusHomeUrl);
    			break;
     
         case "BrandUrl":
        		DriverManager.getDriver().navigate().to(AppConfig.BrandUrl);
    			break;
    			
    			
         case "textureUrl":
     		DriverManager.getDriver().navigate().to(AppConfig.textureUrl);
 			break;
 			
         case "colourletterUrl":
      		DriverManager.getDriver().navigate().to(AppConfig.colourletterUrl);
  			break;
  			
         case "CompareFirstProductUrl":
       		DriverManager.getDriver().navigate().to(AppConfig.CompareFirstProductUrl);
   			break;
   			
         case "CompareSecondProductUrl":
        		DriverManager.getDriver().navigate().to(AppConfig.CompareSecondProductUrl);
    			break;
    			
         case "BecomedealerUrl":
     		DriverManager.getDriver().navigate().to(AppConfig.BecomedealerUrl);
 			break;
 			
         case "BecontractorUrl":
      		DriverManager.getDriver().navigate().to(AppConfig.BecontractorUrl);
  			break;
    			
         case "AssuranceUrl":
       		DriverManager.getDriver().navigate().to(AppConfig.AssuranceUrl);
   			break;
     			

    		
 			
 			
    			
    			
	}
}
	
}