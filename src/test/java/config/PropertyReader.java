package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import utils.CustomException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final Logger log = LogManager.getLogger(PropertyReader.class);
    private static final String APPLICATION_FILE_PATH = "src/test/resources/application.properties";
    private static Properties properties;

    private PropertyReader() {
    }

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream input = new FileInputStream(APPLICATION_FILE_PATH)) {
                properties.load(input);
                log.info("Properties file loaded successfully from {}", APPLICATION_FILE_PATH);
            } catch (Exception ex) {
                log.error("Failed to load properties file from {}. Error: {}", APPLICATION_FILE_PATH, ex.getMessage());
                throw new CustomException(ex.getMessage(), ex);
            }
            log.debug("Properties file already loaded, reusing existing properties.");
        }
    }

    @NotNull
    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        var propertyValue = properties.getProperty(key);
        if (propertyValue == null) {
            log.error("Property '{}' not found in {}", key, APPLICATION_FILE_PATH);
            throw new CustomException("Property '" + key + "' not found in " + APPLICATION_FILE_PATH);
        }
        log.debug("Retrieved property '{}': '{}'", key, propertyValue);
        return propertyValue;
    }
}
