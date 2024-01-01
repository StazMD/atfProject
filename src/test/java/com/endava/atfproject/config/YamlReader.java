package com.endava.atfproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class YamlReader {
    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    @Data
    private class Config {
        private String browser;
        private String url;
        private Map<String, User> users;

        @Data
        private class User {
            private String username;
            private String password;
        }

        private static synchronized Config readConfig() {
            try {
                return mapper.readValue(new File("src/test/resources/application.yml"), Config.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public static String getProperties() {
            Config propertiesWrapper = readConfig();
            return propertiesWrapper.toString();
        }

        public static Map<String, User> getUsers() {
            Config usersWrapper = readConfig();
            return usersWrapper != null ? usersWrapper.users : null; //condition ? then : else
        }
    }
}
