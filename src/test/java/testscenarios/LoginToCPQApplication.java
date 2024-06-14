package testscenarios;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import baselibrary.EnvironmentSetUp;
import baselibrary.KeyUtilities;
import pageobjects.LoginPage;

public class LoginToCPQApplication extends KeyUtilities {
	WebDriver driver;
	LoginPage LP = new LoginPage();
	
	@Test
	public void LoginPage() throws IOException, InterruptedException
	{
		enterValue(LP.Username, "medaboyina.durga-mohan.ext@veolia.com");
		Click(LP.NextButton, "Clicked on Next");
		
		Thread.sleep(10000);
		
		Click(LP.Admin,"Clicked on Admin");
		Click(LP.DocumentDesigner,"Clicked on Document Designer");
		Click(LP.POCProgenspecsheetlatest,"Clicked on POCProgen Sheet");
		Click(LP.Search,"UV Light");
		Click(LP.SearchIcon,"Clicked on Search icon");
		Click(LP.GoToButton,"Clicked on GoTo button");
		
		
		
		
		
		
		
	}
	

}
