package framework.dataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import framework.enums.DriverType;
import framework.enums.EnvironmentType;

public class ConfigProvider {

	private static Properties properties;
	private final static String propertyFilePath = "./src/test/resources/properties";

	public DriverType getBrowser() throws Exception {

		String browserName = ConfigProvider.getAsString("browser");
		if (browserName == null || browserName.equals("firefox"))
			return DriverType.FIREFOX;
		else if (browserName.equalsIgnoreCase("chrome"))
			return DriverType.CHROME;
		else if (browserName.equals("ie"))
			return DriverType.INTERNETEXPLORER;
		else
			throw new RuntimeException(
					"Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}

	public EnvironmentType getEnvironment() throws Exception {
		String environmentName = ConfigProvider.getAsString("environment");
		if (environmentName == null || environmentName.equalsIgnoreCase("local"))
			return EnvironmentType.LOCAL;
		else if (environmentName.equals("remote"))
			return EnvironmentType.REMOTE;
		else
			throw new RuntimeException(
					"Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	}

	public static String getAsString(String property) throws Exception {
		File dir = new File(propertyFilePath);
		File[] files = dir.listFiles((dir1, name) -> name.endsWith(".properties"));
		for (File f : files) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(f));
				properties = new Properties();
				try {
					properties.load(reader);
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
			}
			if (properties.getProperty(property) == null)
				continue;
			else
				return properties.getProperty(property);
		}
		throw new Exception("Propery Not Found");

	}
	public static int getAsInt(String property) throws NumberFormatException, Exception
	{
		return Integer.parseInt(ConfigProvider.getAsString(property));
	}
}
