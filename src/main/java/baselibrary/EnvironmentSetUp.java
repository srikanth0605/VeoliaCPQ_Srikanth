package baselibrary;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.helpers.SubstituteLogger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import com.paulhammant.ngwebdriver.NgWebDriver;
import com.relevantcodes.extentreports.ExtentReports;

//import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Evoke Technologies
 * This Class has browser invoke function and reports invocation facility that invokes desired browsers
 * Once the execution done the After Class helps in reports generation and closing active browsers
 *
 */

public class EnvironmentSetUp extends Configurations {

	/*
	 * Initializing the Browser
	 */
	@BeforeClass
	public void invokeBrowser() throws IOException, Exception {
		initialize();
		
		if(prop.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			reportLocation = System.getProperty("user.dir")+"\\Reports\\VeoliaCPQ_"+dateformat.format(date) + "\\";
			reports = new ExtentReports(reportLocation + "\\VeoliaCPQ_"+dateformat.format(date) + ".html");
			File CONF = new File(".//extent-config.xml");
			reports.loadConfig(CONF);
		     //WebDriverManager.chromedriver().setup();  
			//WebDriverManager.chromedriver().driverVersion("121.0").setup();
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
		    options.addArguments("disable-extensions");
			options.addArguments("--remote-allow-origins=*");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--start-maximized");          
			driver = new ChromeDriver(options);  
			ngDriver = new NgWebDriver((JavascriptExecutor) driver);			
			driver.get(prop.getProperty("URL"));   		
		}else if(prop.getProperty("Browser").equalsIgnoreCase("Firefox")){
			reportLocation = System.getProperty("user.dir")+"\\Reports\\VeoliaCPQ_"+dateformat.format(date) + "\\";
			reports = new ExtentReports(reportLocation + "\\VeoliaCPQ_"+dateformat.format(date) + ".html");
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("URL"));			
		}else if(prop.getProperty("Browser").equalsIgnoreCase("IE")){
			reportLocation = System.getProperty("user.dir")+"\\Reports\\VeoliaCPQ_"+dateformat.format(date) + "\\";
			reports = new ExtentReports(reportLocation + "\\VeoliaCPQ_"+dateformat.format(date) + ".html");
			//WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("URL"));
		}
	}

	/*
	 * End the Report
	 */
	public void tearDown() {
		reports.endTest(logger);
		reports.flush();
	}

	/*
	 * Close the Browser
	 */
	@AfterClass
	public void closeBrowser() {	
		try {
			if (driver!=null) {
				driver.quit();				
			}
			if(reports!=null) {				
				reports.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
