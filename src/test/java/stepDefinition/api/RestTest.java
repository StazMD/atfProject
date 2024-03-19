package stepDefinition.api;

import api.Assertions;
import api.Requests;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import context.ScenarioContext;
import entity.UserEntity;
import enums.Credentials;
import enums.Entity;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CustomException;
import utils.TestDataGeneratorUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static enums.Credentials.*;

public class RestTest {

    private static final ScenarioContext scenarioContext;
    private static final Logger log;
    private static final ObjectMapper objectMapper;

    private final String firstName = FIRSTNAME.getValue();
    private final String lastName = LASTNAME.getValue();
    private final String email = EMAIL.getValue();
    private final String password = PASSWORD.getValue();

    static {
        scenarioContext = ScenarioContext.INSTANCE;
        log = LogManager.getLogger(RestTest.class);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public UserEntity extractUserData() {
        try {
            return (UserEntity) scenarioContext.getContext(Entity.USER);
        } catch (RuntimeException ex) {
            throw new CustomException("User context failed to extract");
        }
    }

    public UserEntity extractUserData(String email, String password) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    @Given("valid user data")
    public void validUserData(DataTable userData) {
        log.info("Starting processing user data from DataTable.");
        List<Map<String, String>> userCredentials = userData.asMaps();
        var userEntity = new UserEntity(extractUserData());
        for (Map<String, String> credential : userCredentials) {
            Set<String> keys = credential.keySet();
            for (String key : keys) {
                Credentials credentials;
                try {
                    credentials = Credentials.valueOf(key.toUpperCase());
                } catch (IllegalArgumentException ex) {
                    log.error("Provided unknown key: {}", key);
                    throw new CustomException(ex.getMessage());
                }
                String value = credential.get(key);
                String finalValue = value.startsWith(RANDOM.getValue()) ? TestDataGeneratorUtils.getRandomCredentials(key) : value;
                switch (credentials) {
                    case FIRSTNAME -> userEntity.setFirstName(finalValue);
                    case LASTNAME -> userEntity.setLastName(finalValue);
                    case EMAIL -> userEntity.setEmail(finalValue);
                    case PASSWORD -> userEntity.setPassword(finalValue);
                }
                log.info("Set {}: {}", key, finalValue);
            }
        }
        ScenarioContext.INSTANCE.setContext(Entity.USER, userEntity);
        log.debug("Set user data to scenario context");

        log.info("Finished processing user data.");
    }

    @When("a request to create new user was sent")
    public void aRequestToCreateNewUserWasSent() {
        try {
            log.info("Sending request to create a new user");
            String requestBody = objectMapper.writeValueAsString(extractUserData());
            Response response = Requests.postRequest("/users", requestBody, 201);
            String token = response.jsonPath().getString(TOKEN.getValue());
            scenarioContext.setContext(TOKEN, token);
            log.info("New user created successfully, token acquired");
        } catch (Exception ex) {
            log.error("New user creation failed", ex);
            throw new CustomException(ex.getMessage());
        }
    }

    @Then("user was successfully created")
    public void userWasSuccessfullyCreated() {
        try {
            log.info("Verifying if user was successfully created");
            Response response = Requests.getRequest("/users/me", 200);
            Assertions.assertGetUserProfile(response);
            log.info("User creation verified successfully");
        } catch (Exception ex) {
            log.error("Failed to verify user creation", ex);
            throw new CustomException(ex.getMessage());
        }
    }

    @When("a request to update the user's details with next values was sent")
    public void aRequestToUpdateTheUserSDetailsWasSent(DataTable userData) {
        List<Map<String, String>> userCredentials = userData.asMaps();
        for (Map<String, String> userCredential : userCredentials) {
            UserEntity userEntity = new UserEntity(extractUserData());

            if (userCredential.get(firstName) == null || userCredential.get(lastName) == null || userCredential.get(email) == null || userCredential.get(password) == null) {
                log.error("One or more required credentials are empty");
                throw new CustomException("Credentials should not be empty");
            }

            userEntity.setFirstName(userCredential.get(firstName));
            userEntity.setLastName(userCredential.get(lastName));
            userEntity.setEmail(userCredential.get(email));
            userEntity.setPassword(userCredential.get(password));

            ScenarioContext.INSTANCE.setContext(Entity.USER, userEntity);
        }
        try {
            log.info("Sending request to update user's details");
            String requestBody = objectMapper.writeValueAsString(extractUserData());
            Requests.patchRequest("/users/me", requestBody, 200);
            log.info("User's details updated successfully");
        } catch (Exception ex) {
            log.error("Failed to update user's details", ex);
            throw new CustomException(ex.getMessage());
        }
    }

    @Then("the user's details was successfully updated")
    public void getUserDetails() {
        try {
            log.info("Updating user details");
            Response response = Requests.getRequest("/users/me", 200);
            Assertions.assertGetUserProfile(response);
            log.info("User details successfully validated");
        } catch (Exception ex) {
            log.error("Failed to update user details", ex);
            throw new CustomException(ex.getMessage());
        }
    }

    @And("a request to login with user's details was sent")
    public void aRequestToLoginWithUserSDetailsWasSent() {
        try {
            log.info("Attempting to login with user details");
            String requestBody = objectMapper.writeValueAsString(extractUserData(
                    extractUserData().getEmail(),
                    extractUserData().getPassword()
            ));
            Response response = Requests.postRequest("/users/login", requestBody, 200);
            String token = response.jsonPath().getString(TOKEN.getValue());
            scenarioContext.setContext(TOKEN, token);
            log.info("Login successful, token acquired");
        } catch (Exception ex) {
            log.error("Login attempt failed", ex);
            throw new CustomException(ex.getMessage());
        }
    }

    @When("a request to delete user was sent")
    public void aRequestToDeleteTheUserWasSent() {
        log.info("Sending a request to delete the current user.");

        try {
            Response response = Requests.deleteRequest("/users/me", 200);
            log.info("Delete request sent. Status Code: {}", response.getStatusCode());
            if (response.getStatusCode() == 200) {
                log.info("User was successfully deleted.");
            } else {
                log.warn("Expected status code 200, but got: {}", response.getStatusCode());
            }
        } catch (Exception ex) {
            log.error("An error occurred during the delete request: {}", ex.getMessage());
            throw new RuntimeException("Failed to delete user due to an exception.", ex);
        }
    }

    @Then("the user was successfully deleted")
    public void userNotAbleToLogin() {
        log.info("Starting the login attempt to verify user deletion.");

        UserEntity userEntity = extractUserData();
        try {
            String requestBody = objectMapper.writeValueAsString(userEntity);
            log.debug("Request body prepared for login: {}", requestBody);

            Response response = Requests.postRequest("/users/login", requestBody, 401);
            log.info("Login request sent. Status Code: {}", response.getStatusCode());

            Assertions.assertNoAuthentication(response);
            log.info("User was successfully not authenticated, indicating successful deletion.");
        } catch (Exception ex) {
            log.error("An error occurred during the login attempt: {}", ex.getMessage());
            throw new CustomException(ex.getMessage());
        }
    }
}
