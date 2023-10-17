package framework.cucumber;

import framework.dataProvider.ConfigProvider;
import framework.enums.DriverType;
import framework.enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverManager {
    private WebDriver driver;
    private RemoteWebDriver remoteDriver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public DriverManager() throws Exception {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriverInstance() throws Exception {
        if (driver == null) {
            driver = startDriver();
            threadLocalDriver.set(driver);
        }
        return threadLocalDriver.get();
    }

    private WebDriver startDriver() throws Exception {
        switch (environmentType) {
            case LOCAL:
                driver = createLocalDriver();
                break;
            case REMOTE:
                driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() throws Exception {
        String hubURL;
        // Browser_Stack_Configuration
        boolean browserStack = ConfigProvider.getAsBoolean("browserStackMode");
        if (browserStack)
            hubURL = "https://" + ConfigProvider.getAsString("user_name") + ":"
                    + ConfigProvider.getAsString("access_key") + "@hub-cloud.browserstack.com/wd/hub";
        else
            hubURL = ConfigProvider.getAsString("hub_url");
        /// HeadLess Mode
        boolean headless = ConfigProvider.getAsBoolean("headLess");
        /// Architecture Selection

        switch (driverType) {
            case FIREFOX:
                FirefoxOptions firefoxOptions = setupFirefox(headless);
                remoteDriver = new RemoteWebDriver(new URL(hubURL), firefoxOptions);
                break;
            case CHROME:
                ChromeOptions options = setupChrome(headless);
                remoteDriver = new RemoteWebDriver(new URL(hubURL), options);
                break;
            case INTERNETEXPLORER:
                InternetExplorerOptions ieOptions = setupInternetExplorer();
                remoteDriver = new RemoteWebDriver(new URL(hubURL), ieOptions);
                break;
            default:
                System.out.println("Please Update Browser In Properties");
        }

        return remoteDriver;
    }


    private WebDriver createLocalDriver() throws Exception {
        /// HeadLess Mode
        boolean headless = ConfigProvider.getAsBoolean("headLess");
        /// Architecture Selection

        switch (driverType) {
            case FIREFOX:
                FirefoxOptions firefoxOptions = setupFirefox(headless);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case CHROME:
                ChromeOptions options = setupChrome(headless);
                driver = new ChromeDriver(options);
                break;
            case INTERNETEXPLORER:
                InternetExplorerOptions ieOptions = setupInternetExplorer();
                driver = new InternetExplorerDriver(ieOptions);
                break;
            default:
                System.out.println("Please Update Browser In Properties");
        }

        return driver;
    }

    public FirefoxOptions setupFirefox(boolean headless) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (headless)
            firefoxOptions.addArguments("--headless=new");
        WebDriverManager.firefoxdriver().setup();
        return firefoxOptions;
    }

    public ChromeOptions setupChrome(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("disable-infobars");
        if (headless)
            options.addArguments("--headless=new");
        WebDriverManager.chromedriver().setup();
        return options;
    }

    public InternetExplorerOptions setupInternetExplorer() {
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.enablePersistentHovering();
        ieOptions.ignoreZoomSettings();
        ieOptions.requireWindowFocus();
        ieOptions.introduceFlakinessByIgnoringSecurityDomains();
        WebDriverManager.iedriver().setup();
        return ieOptions;
    }
}