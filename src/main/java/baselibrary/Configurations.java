package baselibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Configurations {

	public static WebDriver 		driver;	
	protected NgWebDriver 		ngDriver;
	protected WebDriverWait 	wait;
	public  static  ExtentReports 	reports;
	public   static ExtentTest 		logger;
	public    DateFormat 		dateformat  		= new SimpleDateFormat("MM-dd-yyyy HHmmssSSS");
	public    Date 				date 				= new Date();	
	public    String 			reportLocation;	
	public    Properties 		prop;

	/*
	 * To Get the details from Configuration file
	 */
	public void initialize() throws IOException {
		try {						
			prop = new Properties();
			File file = new File(".//Config.properties");
			FileInputStream fileInput = new FileInputStream(file);
			prop.load(fileInput);
			fileInput.close();			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();				
			Assert.assertEquals(true, false);				
		}		
	}

	/*
	 * Start the report
	 */
	public void startTest(String description) {
		logger = reports.startTest(description);
	}

	/*
	 * Capture the Screenshot
	 */
	public String takeScreenShot(WebDriver driver) throws IOException  {
		UUID uuid = UUID.randomUUID();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
		FileUtils.copyFile(scrFile, new File(reportLocation+uuid+".png"));
		return uuid+".png";		
	} 

	/*
	 * Angular Wait
	 */
	public void angularWait() {
		try {
			Thread.sleep(500);
			ngDriver.waitForAngularRequestsToFinish();
			Thread.sleep(500);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * End the Report
	 */
	public void endWebReports() {
		if (reports != null) {
			reports.endTest(logger);
			reports.flush();
		}
	}
}
