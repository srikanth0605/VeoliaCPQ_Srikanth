package baselibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;


/**
 * 
 * @author Evoke Technologies
 * This Class has Key Utility functions that helps coder to easily call the actions while writing functional test cases 
 * Each Function is represented with Try Catch exception handling with logger reports
 * Each logger reports pass or fail will have screenshots for every action performed
 *
 */

public class KeyUtilities extends EnvironmentSetUp {
    
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 02-Sep-2023
	 * Description : By using this method user can able to pass values to the input fields (Send keys).
	 */
	public void enterValue(String ele, String ElementName) throws IOException {
		try {
			angularWait();
			new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(ele))));
			 Thread.sleep(1000);
			 driver.findElement(By.xpath(ele)).clear();
			 Thread.sleep(1000);
			driver.findElement(By.xpath(ele)).sendKeys(ElementName);
			logger.log(LogStatus.PASS,"Should enter "+ElementName,"Succesfully entered "+ElementName+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,"Should enter "+ElementName,"Failed to enter "+ElementName+" due to:: </br>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
	} 
	
	
	
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 02-Feb-2024
	 * Description : By using this method user can able to pass values to the input fields (Send keys).
	 */
	
	public void jsType(String ele, String ElementName) throws IOException {
		try {
			angularWait();
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(ele))));
			WebElement textBox = driver.findElement(By.xpath(ele));
			 String textToEnter = ElementName;
		        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + textToEnter + "'", textBox);
			logger.log(LogStatus.PASS,"Should enter "+ElementName, "Succesfully entered "+ElementName+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,"Should enter "+ElementName, "Failed to enter "+ElementName+" due to :: </br>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 10-Sep-2023
	 * Description : By using this method user can able to perform click operation if that element is displayed & clickable.
	 */
	public void ifDisplayClick(String ele, String ElementName) throws IOException {
		try {
			angularWait();
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(ele))));		
			driver.findElement(By.xpath(ele)).click();
			logger.log(LogStatus.PASS,"Should click "+ElementName,"Succesfully clicked "+ElementName+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		} catch (Exception e) {
			logger.log(LogStatus.INFO,"Should click "+ElementName,"No need to click on:: "+ElementName+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
   
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 12-Sep-2023
	 * Description : By using this method user can able to perform click operation if that element is clickable.
	 */
	public void Click(String ele, String ElementName) throws IOException {
		try {
			angularWait();
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(ele))));		
			driver.findElement(By.xpath(ele)).click();
			logger.log(LogStatus.PASS,"Should click "+ElementName,"Succesfully clicked "+ElementName+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,"Should click "+ElementName,"Failed to click "+ElementName+" due to:: </br>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
	} 
	
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 14-Sep-2023
	 * Description : By using this method user can able to perform jsClick operation using Javascriptexecutor if that element is not clickable with normal click method.
	 */
	public void jsClick(String ele, String ElementName) throws IOException {
		try {
			angularWait();
			new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(ele))));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath(ele)));
			logger.log(LogStatus.PASS,"Should click "+ElementName, "Clicked On "+ElementName+" successfully"+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,"Should click "+ElementName, "Failed to click on "+ElementName+" due to :: </br>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}		
	}	
    
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 16-Sep-2023
	 * Description : By using this method user can able to verify whether an element is displayed or not.
	 */
	public void verifyElementDisplayed(String ele, String ElementName) throws IOException {
		try {
			angularWait();
			new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));
			driver.findElement(By.xpath(ele)).isDisplayed();
			logger.log(LogStatus.PASS,"Should display "+ElementName,"Succesfully displayed "+ElementName+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));				
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,"Should display "+ElementName,"Failed to display "+ElementName+" due to:: </br>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
	} 
   
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 18-Sep-2023
	 * Description : By using this method user can able to wait strictly up to given time.
	 */
	public void wait(int num) {
		try {
			Thread.sleep(1000*num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	/* Created By : Naveen Nandam
	 * Date of Creation : 02-Dec-2023
	 * Description : By using this method user can able to search for an web element in specific time intervals.
	 */
	
	public WebElement fluentWait(String ele)
	{
		 Wait<WebDriver> wait = new FluentWait <WebDriver>(driver)
	    .withTimeout(Duration.ofSeconds(800))
	    .pollingEvery(Duration.ofSeconds(5))
	    .ignoring(NoSuchElementException.class);

	   WebElement locator = wait.until(new Function<WebDriver, WebElement>() {
	   public WebElement apply(WebDriver driver) {
	   return driver.findElement(By.xpath(ele));
	 }
	 });
			return locator;
	}
	
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 20-Sep-2023
	 * Description : By using this method user can able to perform scroll down opearion on current page up to 250 pixels vertically.
	 */
	public void scrollPageDown() throws Exception {
		Thread.sleep(500);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		Thread.sleep(500);
	}
	
	/* Created By : Naveen Nandam
	 * Date of Creation : 02-Jan-2024
	 * Description : By using this method user can able to perform scroll down opearion on current page as per the pixel number given.
	 */
	public void scrollPageDownCustom(int ele) throws Exception {
		Thread.sleep(500);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,'"+ele+"')");
		Thread.sleep(500);
	}
	
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 22-Sep-2023
	 * Description : By using this method user can able to check whether the given text is present or not on the web page.
	 */

	public void validateByText(String ele, String ElementName) throws IOException {
		try {
			angularWait();
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.textToBe((By.xpath(ele)),ElementName));
			logger.log(LogStatus.PASS,"Should display "+ElementName,"Succesfully displayed "+ElementName+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));						
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,"Should display "+ElementName,"Failed to display "+ElementName+" due to:: </br>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
	} 
	
	/* Created By : Naveen Nandam
	 * Date Of Creation : 08-Nov-2023
	 * Description : By using this method we can perform scroll operation (horizantal/vertical) for specific webelement.
	 */
	
	public void scrollIntoView(String ele) throws IOException
	{
		
		try {
			angularWait();
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath(ele)));
			
			logger.log(LogStatus.PASS, "Should perform scroll operation on desired element", "Succesfully performed scroll operation on desired element"
					+ "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should perform scroll operation operation on desired element", "Failed to  perform scroll operation on desired element "
					+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
	public void scrollIntoViewActions(String ele, String elementName) throws IOException
	{
		try {
			angularWait();
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));
			WebElement element = driver.findElement(By.xpath(ele));
			Actions actions = new Actions(driver);
			actions.scrollToElement(element).perform();
			logger.log(LogStatus.PASS, "Should scroll to "+elementName, "Succesfully scrolled to "+elementName
					+ "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should scroll to "+elementName, "Failed to scroll to "+elementName
					+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 15-Sep-2023
	 * Description : By using this method user can able to whether an element is visble or not.
	 */
	public boolean verifyElementIsVisible(String ele, String ElementName) throws IOException {
		try {
			angularWait();
			new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));
			boolean s = driver.findElement(By.xpath(ele)).isDisplayed();
			logger.log(LogStatus.PASS,"Should display "+ElementName,"Succesfully displayed "+ElementName+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));	
			return s;
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,"Should display "+ElementName,"Failed to display "+ElementName+" due to:: </br>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
		return false;
	} 
	
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 24-Nov-2023
	 * Description : By using this method user can able to extract the text of an web element.
	 */

	public String getText(String ele, String ElementName) throws IOException {
		String text = null;
		try {
			angularWait();	
			new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));
			 text = driver.findElement(By.xpath(ele)).getText();
			logger.log(LogStatus.PASS,"Get the visible Text of "+ElementName,"Succesfully visible text is rendered for "+text+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));						
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,"Get the visible Text of "+ElementName,"Failed to render visible text of "+ElementName+" due to:: </br>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
	
		return text;
	}

	/* Created By : Sushma Chintikindi
	 * Date of Creation : 26-Nov-2023
	 * Description : By using this method user can able to perform mouse hower operation on a specific element.
	 */
	public void mouseHower(String ele, String ElementName) throws IOException {
		try {
			angularWait();	
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));
			Actions act = new Actions(driver);			
			act.moveToElement(driver.findElement(By.xpath(ele))).build().perform();
			logger.log(LogStatus.PASS, "Should mouse hover on " + ElementName, "Succesfully mouse hovered on "
					+ ElementName + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should mouse hover on " + ElementName, "Failed to mouse hover on " + ElementName
					+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 28-Nov-2023
	 * Description : By using this method user can able to move to specific element.
	 */
	public void scrollToElement(String ele,String ElementName) throws IOException {
		try {
			angularWait();	
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));
			 WebElement element = driver.findElement(By.xpath(ele));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			logger.log(LogStatus.PASS, "Scroll to element " + ElementName, "Succesfully scrolled to "
					+ ElementName + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should scroll to " + ElementName, "Failed to scroll to " + ElementName
					+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 16-Nov-2023
	 * Description : By using this method user can able to switch between the browser windows.
	 */
	public void switchBrowser() throws IOException {
		try {
		wait(2);
		Set<String> winids = driver.getWindowHandles(); 		
		Iterator<String> iterate = winids.iterator();
		String firstWindowId = iterate.next();
		System.out.println(firstWindowId);
		String secondWindowId = iterate.next(); 
		System.out.println(secondWindowId);
		driver.switchTo().window(secondWindowId);			
		logger.log(LogStatus.PASS, "Should enter " + secondWindowId,
				"Succesfully entered " + secondWindowId + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
	} catch (Exception e) {
		logger.log(LogStatus.FAIL, "Should enter ", "Failed to enter " 
				+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
	}
	}
	
	/* Created By : Sushma Chintikindi
	 * Date of Creation : 12-Nov-2023
	 * Description : By using this method user can able to close the browser window.
	 */
	public void BrowserClose() throws IOException {
		try {
		driver.close();
		
	} catch (Exception e) {
		logger.log(LogStatus.FAIL, "Should enter ", "Failed to enter " 
				+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
	}
	}
	

	/* Created By : Naveen Nandam
	 * Date of Creation : 18-OCT-2023
	 * Description : By using this method user can able to check whether text displayed on the web page as expected.
	 */
	public void verifyTextDisplayed(String ele, String ElementName) throws IOException {
		String text = null;
		try {
			angularWait();	
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));		
			 text = driver.findElement(By.xpath(ele)).getText().trim();
			 if(text.equals(ElementName))
			logger.log(LogStatus.PASS,"Should display text "+ElementName,"Succesfully displayed text "+ElementName+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));						
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,"Should display text "+ElementName,"Failed to display text "+ElementName+" due to:: </br>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
	/* Created By : Naveen Nandam
	 * Date of Creation : 19-OCT-2023
	 * Description : By using this method user can able to get the web element based on the locator passed.
	 */
	public WebElement getWebElement(String ele,String locator)  {
		
		
		WebElement element = null;
		
		if(locator.equalsIgnoreCase("xpath"))
		{
		   element= driver.findElement(By.xpath(ele));
		}
		else if(locator.equalsIgnoreCase("id"))
		{
		   element= driver.findElement(By.id(ele));
		}
		else if(locator.equalsIgnoreCase("Name"))
		{
			element= driver.findElement(By.name(ele));
			
		}
		return element;
	}
	
	/* Created By : Naveen Nandam
	 * Date of Creation : 19-OCT-2023
	 * Description : By using this method user can know whether particluar element is clickable or not.
	*/
	
	public boolean isElementClickable(String ele, String ElementName) throws IOException
	{
		boolean flagValue = false;
		try {
			 angularWait();	
			 new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));		
			 WebElement element = driver.findElement(By.xpath(ele));
			 
			  if(element.isEnabled())
			  {
			  
			  flagValue =true;
			  logger.log(LogStatus.PASS,  ElementName+" should be clickable" ,  ElementName+" is clickable"
						+ ElementName + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
			  }
			  else
			  {
				  logger.log(LogStatus.FAIL, ElementName+" should be clickable" , ElementName+" is not clickable"
							+ "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
			  }
			
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, ElementName+" should be clickable",  ElementName+" is not clickable"
					+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
		return flagValue;
	}
	
	/* Created By : Naveen Nandam
	 * Date of Creation : 02-Dec-2023
	 * Description : By using this method user can able to scroll to top of the currnet page.
	 */
	
	public void scrollToTop() throws IOException
	{
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.HOME).build().perform();
			logger.log(LogStatus.PASS, "Should scroll to top of the page", "Successfully scrolled to top of the page"
					 +"<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
	
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should scroll to top of the page", "Failed to scroll to top of the page due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
	/* Created By : Naveen Nandam
	 * Date of Creation : 02-Dec-2023
	 * Description : By using this method user can able to scroll to bottom of the current page.
	 */
	
	public void scrollToBottom() throws IOException
	{
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.END).build().perform();
			logger.log(LogStatus.PASS, "Should scroll to bottom of the page", "Successfully scrolled to bottom of the page"
					 +"<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
	
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should scroll to bottom of the page", "Failed to scroll to bottom of the page due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
	
	/* Created By : Naveen Nandam
	 * Date of Creation : 17-OCT-2023
	 * Description : By using this method user can able to perform double click operation on required web element.
	 */
	public void doubleClick(String ele, String ElementName) throws IOException
	{
		try {
			angularWait();	
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));		
			Actions action = new Actions(driver);			
			action.doubleClick(driver.findElement(By.xpath(ele))).perform();
			logger.log(LogStatus.PASS, "Should perform double click operation on " + ElementName, "Succesfully performed double click operation on "
					+ ElementName + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should perform double click operation on " + ElementName, "Failed to  perform double click operation on " + ElementName
					+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
	
	/* Created By : Naveen Nandam
	 * Date of Creation : 11-November-2023
	 * Description : By using method this we can able to switch from current window to iframe.
	 */
	
	public void switchToFrame(String iframe,String iframeName ) throws IOException
	{
		try {
			angularWait();	
		    new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(iframe))));		
			driver.switchTo().frame(driver.findElement(By.xpath(iframe)));
			logger.log(LogStatus.PASS,"Should switch to iframe"+iframeName,"Succesfully switched to iframe "+iframeName+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));	
		}
		catch(Exception e)
		{
			logger.log(LogStatus.FAIL,"Should switch to iframe"+iframeName,"Failed to switch to iframe "+iframeName+" due to :: <br/>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
		
	}
	
	/* Created By : Naveen Nandam
	 * Date of Creation : 11-November-2023
	 * Description : By using this method we can able to switch from iframe window to default content.
	 */
	public void switchToDefault() throws IOException
	{
		try {
			driver.switchTo().defaultContent();
			logger.log(LogStatus.PASS,"Should switch to default content","Succesfully switched to default content "+"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));	
		}
		catch(Exception e)
		{
			logger.log(LogStatus.FAIL,"Should switch to default content","Failed to switch to default content due to :: <br/>"+ e +"<br/>"+logger.addScreenCapture(takeScreenShot(driver)));
		}
		
	}
	
	/* Created By : Naveen Nandam
	 * Date of Creation : 31-January-2024
	 * Description : By using this method we can able to print text messages inside the extent reports.
	 */
	
	public void loggerMethod(String messageTxtOne,String messageTxtTwo ) throws IOException
	{
		try {
			
			logger.log(LogStatus.PASS,"<b>"+ messageTxtOne,"<b>"+messageTxtTwo);	
		}
		catch(Exception e)
		{
			logger.log(LogStatus.PASS,"<b>"+ messageTxtOne,"<b>"+messageTxtTwo+"<br/>");	
		}
		
	}
	

	/* Created By : Naveen Nandam
	 * Date of Creation : 31-January-2024
	 * Description : By using this method we can able to compare a string with a custom regular expression given.
	 */
	public boolean regularExpressionMatcher(String txtToMatch,String regularExp,String elementName) throws IOException
	{
		boolean flag = false;
		try {
			
			Pattern pattern = Pattern.compile(regularExp);
        	Matcher matcher = pattern.matcher(txtToMatch);
        	
        	if(matcher.matches())
        	{
        		 flag = true;
			logger.log(LogStatus.PASS, "Should match regular expression with pattern of given " + elementName, "Regular expression successfully matched with pattern of given "
					+ elementName + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
        	}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should match regular expression with pattern of given " + elementName, "Regular expression and given " +elementName+" are not matched"
					+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
		return flag;
	}
	
	/* Created By : Naveen Nandam
	 * Date of Creation : 31-January-2024
	 * Description : By using this method we can able to clear the data inside an input field.
	 */
	public void clear(String ele,String elementName) throws IOException
	{
       try {
			
    	    angularWait();	
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));		
           driver.findElement(By.xpath(ele)).clear();
			logger.log(LogStatus.PASS, "Should clear the data inside "+ elementName, "Successfully cleared data inside "
					+ elementName + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
        	}
		catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should clear the data inside " + elementName, "Failed to clear the data inside "+elementName+" are not matched"
					+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
	public void deleteText(String ele,String elementName) throws IOException
	{
       try {
			
    	      angularWait();	
			  new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(ele))));		
              WebElement element =  driver.findElement(By.xpath(ele));
              String chordFunction = Keys.chord(Keys.CONTROL, "a");
              element.sendKeys(chordFunction);
              element.sendKeys(Keys.DELETE);
			  logger.log(LogStatus.PASS, "Should clear the data inside "+ elementName, "Successfully cleared data inside "
					+ elementName + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
        	}
		catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should clear the data inside " + elementName, "Failed to clear the data inside "+elementName+" are not matched"
					+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
		}
	}
	
     
	/* Created By : Naveen Nandam
	 * Date of Creation : 1-January-2024
	 * Description : By using this method we can able to select values from drop down by passing visible text.
	 */
	
	public void selectByVisibleText(String element, String textValue,String elementName) throws IOException
	{

	       try {
				
	    	    angularWait();	
				new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated((By.xpath(element))));		
				
				Select objSelect = new Select(driver.findElement(By.xpath(element)));
				objSelect.selectByVisibleText(textValue);
				
				logger.log(LogStatus.PASS, "Should select "+textValue+" from "+elementName+" drop down", "Successfully selected "+textValue+" from "+elementName+" drop down"
						+ "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
	        	}
			catch (Exception e) {
				logger.log(LogStatus.FAIL,  "Should select "+textValue+" from "+elementName+" drop down", "Failed to select "+textValue+" from "+elementName+" drop down"
						+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
			}
	}
	
	public Workbook readExcelData(String excelFilePath,String excelName) throws IOException 
	{
		Workbook workbook = null;
	       try
	       {
	    	     
	    	     FileInputStream fis = new FileInputStream(excelFilePath); 
    		     workbook = new XSSFWorkbook(fis);
    		    
    		    // System.out.println( "The work book is : "+workbook);
				logger.log(LogStatus.PASS, "Should read data from "+excelName+" excel file", "Successfully read data from "+excelName+" excel file"
						+ "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
				
	       }
			catch (Exception e) {
				logger.log(LogStatus.FAIL,  "Should read data from "+excelName+" excel file", "Failed to read data from "+excelName+" excel file"
						+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
			}
	      // System.out.println( "The work book is : "+workbook);
	       return workbook;
		
	}
	
	
	
		public void saveExcelFile(Workbook workbook,String excelFilePath,String excelName) throws IOException {
			
			
			try (FileOutputStream fos = new FileOutputStream(excelFilePath))
			{
				          workbook.write(fos);
		              
						
						logger.log(LogStatus.PASS, "Should save "+excelName+" excel file", "Successfully saved "+excelName+" excel file"
								+ "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
			        	}
					catch (Exception e) {
						logger.log(LogStatus.FAIL,  "Should save "+excelName+" excel file", "Failed to save "+excelName+" excel file"
								+ " due to:: </br>" + e + "<br/>" + logger.addScreenCapture(takeScreenShot(driver)));
					}
		}
		
		/*
		 * This method is used to get the list of elements & to store the path's of
		 * elements in the form of list.
		 */

		public List<WebElement> getElements(String ele) {
			List<WebElement> elements = null;

			try {
				elements = driver.findElements(By.xpath(ele));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return elements;
		}
}
