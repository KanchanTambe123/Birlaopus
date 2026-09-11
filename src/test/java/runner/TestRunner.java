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
import commonutilities.ReportWithTime;
import commonutilities.CommonMethods;
import commonutilities.GoogleDriveUploader;
import commonutilities.ReportUtil;
import commonutilities.SendMail;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)


@CucumberOptions(
	    features = {"src/test/resources/features"},
	    glue = {"stepdefinition"},
	    //tags = "@test",
	    plugin = {
	        "pretty",
	        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	        "json:target/cucumber.json",   //
	        "html:report/html/cucumber.html",
	        "rerun:target/failed.txt"//failed only
	    },
	    monochrome = true,
	    dryRun = false
	) // true=create step

public class TestRunner extends AbstractTestNGCucumberTests {
	CommonMethods common =new CommonMethods(); 

	@DataProvider(parallel = false) // paralle=true
	public Object[][] scenarios() {
		return super.scenarios();
	}
	
	

	@AfterSuite//html zip all
	public void after_all() throws Exception {

	    ReportUtil.readCucumberReport();
	    ReportWithTime.createTimestampReports();
		 //List<String> recipients = Arrays.asList("kanchan.tambe@deptagency.com");

	    List<String> recipients = Arrays.asList(
	            "kanchan.tambe@deptagency.com",
	            "hemendra.rana@deptagency.com",
	            "shital.mandhare@deptagency.com",
	            "khushali.shukla@deptagency.com",
	            "monica.ledwani@deptagency.com",
	            "jalpa.jmody@deptagency.com"
	    );

	    String subject = "BirlaOpus Prod Regression Suite Report";

	    String body = "Hi Team,\n\n"
	            + "The regression suite for today has been executed successfully.\n"
	            + "Please find the attached execution report.\n\n"

	            + "Execution Summary:\n"
	            + "Total Passed: " + ReportUtil.passed + "\n"
	            + "Total Failed: " + ReportUtil.failed + "\n\n"

	            + "Modules Covered:\n"
	            + "• Scope 1 – Core Functional Regression\n"
	            + "• Scope 2 – End-to-End Journey Validation\n\n"

	            + "Please review the attached report and let me know if you have any questions.\n\n"

	            + "Best regards,\n"
	            + "Kanchan Tambe\n"
	            + "Automation Test Engineer";

	    // Original reports
	    List<String> reportPaths = ReportWithTime.getAttachmentPaths();

	    // Create one ZIP
	    String zipFilePath = "./report/BirlaOpus_Report.zip";

	    common.zipMultipleFiles(reportPaths, zipFilePath);

	    List<String> attachmentPaths = new ArrayList<>();
	    attachmentPaths.add(zipFilePath);

	    try {

	        // Send Email
	        SendMail.sendEmailWithAttachment(recipients, subject, body, attachmentPaths);

	        // Upload ZIP to Google Drive
	        GoogleDriveUploader.uploadFileToDrive(attachmentPaths);

	        System.out.println("Email sent successfully.");
	        System.out.println("Google Drive upload completed.");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	/*@AfterSuite//PDF HTML
	public void after_all() throws Exception {
	
	    
	    ReportUtil.readCucumberReport();
	    ReportWithTime.createTimestampReports();
		//List<String> recipients = Arrays.asList("kanchan.tambe@deptagency.com");

	    List<String> recipients = Arrays.asList(
	    	    "kanchan.tambe@deptagency.com",
	    	    "hemendra.rana@deptagency.com",
	    	    "shital.mandhare@deptagency.com",
	    	    "khushali.shukla@deptagency.com",
	    	    
	    	    "monica.ledwani@deptagency.com",
	    	    "jalpa.jmody@deptagency.com"
	    	);
	    String subject = "BirlaOpus Regression Suite Report";
     
	    String body = "Hi Team,\n\n"
	            + "The regression suite for today has been executed successfully.\n"
	            + "Please find the attached execution report.\n\n"

	            + "Execution Summary:\n"
	            + "Total Passed: " + ReportUtil.passed + "\n"
	            + "Total Failed: " + ReportUtil.failed + "\n\n"

	            + "Modules Covered:\n"
	            + "Scope 1 – Core Functional Regression\n"
	            + "Scope 2 – End-to-End Journey Validation\n\n"

	            + "Please review the attached report and let me know if you have any questions.\n\n"

	            + "Best regards,\n"
	            + "Kanchan Tambe\n"
	            + "Automation Test Engineer";

	           
   SendMail.sendEmailWithAttachment(recipients, subject, body,  ReportWithTime.getAttachmentPaths());
	}*/
	
}