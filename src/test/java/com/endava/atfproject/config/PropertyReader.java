package com.endava.atfproject.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader instance;
    private Properties properties;

    private PropertyReader() {
        loadProperties();
    }

    public static synchronized PropertyReader getInstance() {
        if (instance == null) {
            instance = new PropertyReader();
        }
        return instance;
    }

    private void loadProperties() {
        //make properties singleton
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
