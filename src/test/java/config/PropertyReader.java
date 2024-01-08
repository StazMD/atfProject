package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final String APPLICATION_FILE_PATH = "src/test/resources/application.properties";
    private static Properties properties;

    private PropertyReader() {
    }

    //method should be static so that it can be called without creating an instance of PropertyReader
    private static synchronized void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream input = new FileInputStream(APPLICATION_FILE_PATH)) {
                properties.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }
}
