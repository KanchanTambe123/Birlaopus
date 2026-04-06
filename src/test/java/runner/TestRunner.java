package runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.MessagingException;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import commonutilities.CommonMethods;
import commonutilities.GoogleDriveUploader;
import commonutilities.ReportUtil;
import commonutilities.SendMail;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)



@CucumberOptions(features = {"src/test/resources/features/Painting_Made_Easy.feature"},glue = {
		"stepdefinition" },tags="@test",plugin = { "pretty",
 
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber.json",
				"html:report/html/cucumber.html" }, monochrome = true, dryRun = false) // true=create step

public class TestRunner extends AbstractTestNGCucumberTests {
	CommonMethods common =new CommonMethods(); 

	@DataProvider(parallel = false) // paralle=true
	public Object[][] scenarios() {
		return super.scenarios();
	}
	
	

	

	//@AfterSuite  //pdf and zip
	/*public void after_all() throws IOException, InterruptedException, GeneralSecurityException {

		List<String> recipients = Arrays.asList("kanchan.tambe@deptagency.com", "suyeta.patra@deptagency.com","hemendra.rana@deptagency.com","gaurang.kapadia@deptagency.com","monica.ledwani@deptagency.com","susmita.gawade@deptagency.com");

	    String subject = "Vijaysales Stage Regression Suite Report";

	    String body = "Hi Team,\n\n"
	    	    + "The regression suite for today has been executed successfully, covering end-to-end validations across critical modules.\n\n"
	    	    + "Modules Covered:\n"
	    	    + "• Static Pages – About Us, Privacy Policy, GST, E-waste\n"
	    	    + "• Navigation – Header, Footer, Menu Fly-out, Search\n"
	    	    + "• User Journeys – Home, PDP, PLP, CLP, Configurable Products\n"
	    	    + "• User Accounts – Login, Signup, Profile, Career Application\n"
	    	    + "• Checkout Flow – Cart, Guest/Logged-in Checkout, Coupons, GST\n"
	    	    + "• Components – Auto Suggestion, Brand Details, PDP Exchange, VS+ Warranty\n"
	    	    + "• Support Tools – FAQ, B2B Enquiry, Store Locator\n\n"
	    	    + "Please find the detailed report attached.\n"
	    	    + "Feel free to share any feedback or queries.\n\n"
	    	    + "Best regards,\n"
	    	    + "Kanchan Tambe & Suyeta Patra\n"
	    	    + "Automation Test Engineers";

	    // Paths
	    String baseDir = System.getProperty("user.dir");
	   // String pdfPath = baseDir + "/report/pdf/Vijaysales_extent.pdf";
	    String htmlPath = baseDir + "/report/html/Vijaysales.html";
	    String zipPath = baseDir + "/report/html/Vijaysales.zip";


	    // Compress HTML to ZIP
	    File htmlFile = new File(htmlPath);
	    if (htmlFile.exists()) {
	    	 common.zipFile(htmlPath, zipPath);
	    } else {
	        System.out.println("HTML report not found, skipping zip.");
	    }

	    List<String> attachmentPaths = new ArrayList<>();
	    //attachmentPaths.add(pdfPath);
	    attachmentPaths.add(zipPath); // attach zipped HTMl

	    try {
	        SendMail.sendEmailWithAttachment(recipients, subject, body, attachmentPaths);
	    	GoogleDriveUploader.uploadFileToDrive(attachmentPaths);
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	}*/
	//@AfterSuite
	public void after_all() throws Exception {

	    ReportUtil.readCucumberReport();

	    List<String> recipients = Arrays.asList("kanchan.tambe@deptagency.com");

	    String subject = "BirlaOpus Regression Suite Report";

	    String body = "Hi Team,\n\n"
	            + "The regression suite for today has been executed successfully.\n"
	            + "Please find the detailed report attached.\n\n"
	            + "Execution Summary:\n\n"
                + "Scenarios:\n"
	            + "Total Passed: " + ReportUtil.passed + "\n"
	            + "Total Failed: " + ReportUtil.failed + "\n"
	          

	           

	            + "Best regards,\n"
	            + "Kanchan Tambe\n"
	            + "Automation Test Engineer";

	    List<String> attachmentPaths = Arrays.asList(
	            "./report/pdf/Birlaopus_extent.pdf",
	            "./report/html/Birlaopus.html"
	    );

	    SendMail.sendEmailWithAttachment(recipients, subject, body, attachmentPaths);
	}
}