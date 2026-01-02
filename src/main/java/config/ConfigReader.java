package config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private Properties prob;
	private FileInputStream fis = null;

	
	/**
	 * Constructor to initalize the config reader and load the properties from config peropties
	 */
	public ConfigReader() {
		//initialize properties Object
		prob = new Properties();
		
		//path of config.properties
		String configFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + "config.properties";
		try {
			//create fileinputstream to read from config.properties file
			fis = new FileInputStream(configFilePath);
			
			//load the properties from the inputstram into the properties object
			prob.load(fis);

		} catch (Exception e) {
			
			//handle the any type of exception
			e.getMessage();
		} finally {
			
			//close the inputstream in the finally block to ensure resources are released 
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e2) {
					//handle any exception that may occure while file closing.
					e2.printStackTrace();
				}
			}
		}
	}

	public String getProb(String key) {
		return prob.getProperty(key);
	}


	
	
}
