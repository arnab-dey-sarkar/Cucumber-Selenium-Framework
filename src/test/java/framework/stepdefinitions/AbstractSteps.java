package framework.stepdefinitions;

import org.openqa.selenium.WebDriver;

import framework.cucumber.DriverManager;
import framework.pageobjectmanager.PageObjectManager;

public class AbstractSteps {
	private DriverManager webDriverManager;
	protected PageObjectManager pageObjectManager;
	static WebDriver driver;

	public void startDriver() {
		webDriverManager = new DriverManager();
		driver=webDriverManager.getDriverInstance();
		pageObjectManager = new PageObjectManager(driver);
		
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
		return driver;
	}
	public void stopDriver()
	{
		driver.close();
		driver.quit();
	}
	

}