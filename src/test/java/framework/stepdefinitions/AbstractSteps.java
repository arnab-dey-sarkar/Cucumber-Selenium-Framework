package framework.stepdefinitions;

import org.openqa.selenium.WebDriver;

import framework.cucumber.DriverManager;
import framework.pageobjectmanager.PageObjectManager;

public class AbstractSteps {
	private DriverManager webDriverManager;
	protected PageObjectManager pageObjectManager;
	static WebDriver driver;
	ThreadLocal<WebDriver> threadLocalDriver=new ThreadLocal<>();

	public void startDriver() throws Exception {
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
	public WebDriver getDriver()
	{
		return threadLocalDriver.get();
	}
	public void stopDriver()
	{
		threadLocalDriver.get().close();
		threadLocalDriver.get().quit();
	}
	

}