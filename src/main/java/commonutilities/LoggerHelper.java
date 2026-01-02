package commonutilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {

	
	private static boolean root = false;
	static String LogProperties = System.getProperty("user.dir") + "/src/main/resources/log4j.properties";

	public static Logger getLogger(Class cls) {
		

		if (root) {
			return Logger.getLogger(cls);
		}

		PropertyConfigurator.configure(LogProperties);
		root = true;
		return Logger.getLogger(cls);
	}

}
