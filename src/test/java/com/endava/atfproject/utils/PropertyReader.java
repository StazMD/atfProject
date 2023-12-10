package com.endava.atfproject.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;

    public PropertyReader() {
        loadProperties();
    }
    private void loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/application.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
