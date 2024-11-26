package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	private Properties properties;
	
	public ConfigReader() {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			properties = new Properties();
			properties.load(fis);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
