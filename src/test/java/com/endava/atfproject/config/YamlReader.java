package com.endava.atfproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class YamlReader {
    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    private class UsersWrapper {
        private Map<String, User> users; //users in application.yml
    }

    private class User {
        private String username;
        private String password;
    }

    private static synchronized UsersWrapper readUsers() {
        try {
            return mapper.readValue(new File("src/test/resources/application.yml"), UsersWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, User> getUsers() {
        UsersWrapper usersWrapper = readUsers();
        return usersWrapper != null ? usersWrapper.users : null; //condition ? then : else
    }
}
