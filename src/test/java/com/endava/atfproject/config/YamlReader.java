package com.endava.atfproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class YamlReader {

    public class Users {
        private Map<String, User> users; //users in application.yml
    }

    public class User {
        private String username;
        private String password;
    }

    public static Users readUsers(String filePath) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(new File(filePath), Users.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
