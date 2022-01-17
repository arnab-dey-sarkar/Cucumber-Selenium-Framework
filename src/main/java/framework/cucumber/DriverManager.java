package framework.cucumber;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import framework.dataProvider.ConfigProvider;
import framework.enums.DriverType;
import framework.enums.EnvironmentType;
import io.github.bonigarcia.wdm.Architecture;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	private WebDriver driver;
	private RemoteWebDriver remoteDriver;
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

	private WebDriver createRemoteDriver() throws Exception {
		String hubURL = new String();
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
		Architecture archType = Architecture.DEFAULT;
		String arch = ConfigProvider.getAsString("arch.version");
		if (arch.equalsIgnoreCase("32"))
			archType = Architecture.X32;
		else if (arch.equalsIgnoreCase("64"))
			archType = Architecture.X64;

		switch (driverType) {
		case FIREFOX:
			FirefoxOptions foptions = new FirefoxOptions();
			foptions.isJavascriptEnabled();
			foptions.setHeadless(headless);
			WebDriverManager.firefoxdriver().architecture(archType)
					.version(ConfigProvider.getAsString("firefox.version")).setup();
			remoteDriver = new RemoteWebDriver(new URL(hubURL), foptions);
			break;
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-notifications");
			options.addArguments("disable-infobars");
			options.setHeadless(headless);
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			dc.setCapability(ChromeOptions.CAPABILITY, options);
			WebDriverManager.chromedriver().architecture(archType).version(ConfigProvider.getAsString("chrome.version"))
					.setup();
			remoteDriver = new RemoteWebDriver(new URL(hubURL), dc);
			break;
		case INTERNETEXPLORER:
			InternetExplorerOptions ieoptions = new InternetExplorerOptions();
			ieoptions.enablePersistentHovering();
			ieoptions.ignoreZoomSettings();
			ieoptions.requireWindowFocus();
			ieoptions.introduceFlakinessByIgnoringSecurityDomains();
			ieoptions.isJavascriptEnabled();
			ieoptions.enableNativeEvents();
			WebDriverManager.iedriver().architecture(archType).version(ConfigProvider.getAsString("ie.version"))
					.setup();
			remoteDriver = new RemoteWebDriver(new URL(hubURL), ieoptions);
			break;
		default:
			System.out.println("Please Update Browser In Properties");
		}

		return remoteDriver;
	}

	@SuppressWarnings("deprecation")
	private WebDriver createLocalDriver() throws Exception {
		/// HeadLess Mode
		boolean headless = ConfigProvider.getAsBoolean("headLess");
		/// Architecture Selection
		Architecture archType = Architecture.DEFAULT;
		String arch = ConfigProvider.getAsString("arch.version");
		if (arch.equalsIgnoreCase("32"))
			archType = Architecture.X32;
		else if (arch.equalsIgnoreCase("64"))
			archType = Architecture.X64;

		switch (driverType) {
		case FIREFOX:
			FirefoxOptions foptions = new FirefoxOptions();
			foptions.isJavascriptEnabled();
			foptions.setHeadless(headless);
			WebDriverManager.firefoxdriver().architecture(archType)
					.version(ConfigProvider.getAsString("firefox.version")).setup();
			driver = new FirefoxDriver();
			break;
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-notifications");
			options.setHeadless(headless);
			WebDriverManager.chromedriver().architecture(archType).version(ConfigProvider.getAsString("chrome.version"))
					.setup();
			driver = new ChromeDriver(options);
			break;
		case INTERNETEXPLORER:
			InternetExplorerOptions ieoptions = new InternetExplorerOptions();
			ieoptions.enablePersistentHovering();
			ieoptions.ignoreZoomSettings();
			ieoptions.requireWindowFocus();
			ieoptions.introduceFlakinessByIgnoringSecurityDomains();
			ieoptions.isJavascriptEnabled();
			ieoptions.enableNativeEvents();
			WebDriverManager.iedriver().architecture(archType).version(ConfigProvider.getAsString("ie.version"))
					.setup();
			driver = new InternetExplorerDriver(ieoptions);
			break;
		default:
			System.out.println("Please Update Browser In Properties");
		}

		return driver;
	}
}