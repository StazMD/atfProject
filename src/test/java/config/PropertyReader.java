package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    //TODO find a way to overwrite application url path, to pickup different config files (profiles)
    private static final String APPLICATION_FILE_PATH = "src/test/resources/application.properties";
    private static Properties properties;

    private PropertyReader() {
    }

    //TODO find out what synchronized stands for
    private static synchronized void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream input = new FileInputStream(APPLICATION_FILE_PATH)) {
                properties.load(input);
            } catch (Exception ex) {
                //TODO add logger
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
