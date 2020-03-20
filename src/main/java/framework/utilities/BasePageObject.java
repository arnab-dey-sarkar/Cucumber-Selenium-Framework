package framework.utilities;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class BasePageObject 
{
	public static WebDriver driver;
	
	
	public BasePageObject(WebDriver driver) {
		this.driver=driver;
	}
	//sets the inputvalue in textfields
	public static void setInputvalue(String locator,String value)
	{
		driver.findElement(By.xpath(locator)).sendKeys(value);
	}
	//clicks an element in a webpage
	public static void clickElement(String locator)
	{
		driver.findElement(By.xpath(locator)).click();
	}
	//returns true if the element is displayed
	public static boolean isDisplayed(String locator)
	{
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}
	//refreshes the webpage
	public static void reFresh()
	{
		driver.navigate().refresh();
	}
	//mouse hovers over the given element
	public static void  hover(String locator)
	{
		Actions actions=new Actions(driver);
		WebElement element=driver.findElement(By.xpath(locator));
		actions.moveToElement(element).perform();
	}
	//switches to Active Element
	public static void switchtoActiveElement()
	{
		driver.switchTo().activeElement();
	}
	//retrieves  the text of the given element
	public static String getText(String locator)
	{
		String text=driver.findElement(By.xpath(locator)).getText();
		return text;
	}
	//implicit wait for a given time
	public static void sleep(long time) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
	}
	//helps switch to a frame or iframe
	public static void switchToFrame(String frameid)
	{
		driver.switchTo().frame(frameid);
	}
	//returns true if an element is present in a webpage
	public static boolean isPresent(String locator)
	{
		int count=driver.findElements(By.xpath(locator)).size();
		if( count>0)
			return true;
		else
			return false;
	}
//takes the screenshot and stores it in Screenshots folder
	public static String TakeScreenshot() throws Exception{
		String fileWithPath="Screenshots\\Screenshot"+System.currentTimeMillis()+".png";	
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
        return fileWithPath;
    }
	//scrolls down until the given element is visible in the webpage
	public static void scrollUntilElementVisible(String locator) throws InterruptedException
	{
		WebElement element = driver.findElement(By.xpath(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(5000);
	}
	//switches to default content
	public static void switchToDefaultContent()
	{
		driver.switchTo().defaultContent();
	}
	//navigates to an URL
	public static void NavigateTo(String url)
	{
		driver.navigate().to(url);
	}
	//maximizes the browser window
	public static void Maximize()
	{
		driver.manage().window().maximize();
	}
	//switches to new tab or window
	public static String switchTab()
	{
		Set<String> windowhandles=driver.getWindowHandles();
		String mainwindowhandle=driver.getWindowHandle();
		for(String i:windowhandles)
		{
			if(i!=mainwindowhandle)
				driver.switchTo().window(i);
		}
		return mainwindowhandle;
	}
	public void Ele_presence_Wait(String locator)
	{
		WebDriverWait  wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
	}
	public void Ele_visibility_Wait(String locator)
	{
		WebDriverWait  wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
}
