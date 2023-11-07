package framework.cucumber;

import framework.dataProvider.ConfigProvider;

public class FileReaderManager {

	private static final FileReaderManager fileReaderManager = new FileReaderManager();
	private static final ConfigProvider configFileReader=new ConfigProvider();

	private FileReaderManager() {
	}

	public static FileReaderManager getInstance() {
		return fileReaderManager;
	}

	public ConfigProvider getConfigReader() {
		return configFileReader;
	}

}
