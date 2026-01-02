package commonutilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ReadPropertiesFile {
	private Properties properties;

    public ReadPropertiesFile(String filePath) {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    // Get browser type
    public String getBrowserType() {
        return properties.getProperty("browserType");
    }

    
}


