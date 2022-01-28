package framework.stepdefinitions;

import org.openqa.selenium.WebDriver;
import framework.cucumber.DriverManager;
import framework.pageobjectmanager.PageObjectManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;


public class AbstractSteps  {
	private static DriverManager webDriverManager;
	protected static PageObjectManager pageObjectManager;
	static WebDriver driver;
	static ThreadLocal<WebDriver> threadLocalDriver=new ThreadLocal<>();

	@BeforeMethod
	public static void startDriver() throws Exception {
		webDriverManager = new DriverManager();
		driver=webDriverManager.getDriverInstance();
		threadLocalDriver.set(driver);
		pageObjectManager = new PageObjectManager(getDriver());
		
	}

	public DriverManager getWebDriverManager() {
		return webDriverManager;
	}

	public PageObjectManager getPageObjectManager()
	{
		return pageObjectManager;
	}

	public synchronized static WebDriver getDriver()
	{
		return threadLocalDriver.get();
	}

	public static void stopDriver() {
		getDriver().close();
		getDriver().quit();
	}
	public static void terminateDriver()  {
		//getDriver().close();
		//getDriver().quit();
		threadLocalDriver.remove();
	}
	

}