package framework.stepdefinitions;

import framework.cucumber.DriverManager;
import framework.pageobjectmanager.PageObjectManager;
import framework.utilities.BasePageObject;
import org.openqa.selenium.WebDriver;


public class AbstractSteps {
    private DriverManager webDriverManager;
    protected static PageObjectManager pageObjectManager;
    static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    static BasePageObject basePageObject;


    public  void startDriver() throws Exception {
        webDriverManager = new DriverManager();
        WebDriver driver = webDriverManager.getDriverInstance();
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