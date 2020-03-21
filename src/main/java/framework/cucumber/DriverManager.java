package framework.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import framework.dataProvider.ConfigProvider;
import framework.enums.DriverType;
import framework.enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class DriverManager {
	private WebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;

	public DriverManager() throws Exception {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}

	public WebDriver getDriverInstance() throws Exception {
		if (driver == null)
			driver = startDriver();
		return driver;
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

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	private WebDriver createLocalDriver() throws Exception {
		switch (driverType) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().version(ConfigProvider.getAsString("firefox.version")).setup();
			driver = new FirefoxDriver();
			break;
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().version(ConfigProvider.getAsString("chrome.version")).setup();
			driver = new ChromeDriver(options);
			break;
		case INTERNETEXPLORER:
			WebDriverManager.iedriver().version(ConfigProvider.getAsString("ie.version")).setup();
			driver = new InternetExplorerDriver();
			break;
		}

		return driver;
	}

	public void closeDriver() {
		driver.close();
		driver.quit();
	}

}