package com.endava.atfproject.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Map<String, String> getLoginCredentials() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", getProperty("login.username"));
        credentials.put("password", getProperty("login.password"));
        return credentials;
    }

}
