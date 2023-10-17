package framework.stepdefinitions;

import framework.utilities.BasePageObject;
import org.openqa.selenium.WebDriver;
import framework.cucumber.DriverManager;
import framework.pageobjectmanager.PageObjectManager;
import org.testng.annotations.*;


public class AbstractSteps {
    private DriverManager webDriverManager;
    protected static PageObjectManager pageObjectManager;
    private WebDriver driver;
    static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    static BasePageObject basePageObject;


    public  void startDriver() throws Exception {
        webDriverManager = new DriverManager();
        driver = webDriverManager.getDriverInstance();
        threadLocalDriver.set(driver);
        pageObjectManager = new PageObjectManager(getDriver());
    }

    public DriverManager getWebDriverManager() {
        return webDriverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public synchronized  WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public synchronized void stopDriver()
    {
        getDriver().close();
        getDriver().quit();
        threadLocalDriver.remove();
    }
}