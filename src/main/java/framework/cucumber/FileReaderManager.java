package framework.cucumber;

import framework.dataProvider.ConfigProvider;

public class FileReaderManager {

	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigProvider configFileReader;

	private FileReaderManager() {
	}

	public static FileReaderManager getInstance() {
		return fileReaderManager;
	}

	public ConfigProvider getConfigReader() {
		return (configFileReader == null) ? new ConfigProvider() : configFileReader;
	}

}
