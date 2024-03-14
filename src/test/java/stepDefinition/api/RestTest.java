package stepDefinition.api;

import api.Assertions;
import api.Requests;
import context.ScenarioContext;
import entity.UserEntity;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RestTest {

    private static final ScenarioContext scenarioContext;
    private static final Logger log = LogManager.getLogger(RestTest.class);

    static {
        scenarioContext = ScenarioContext.INSTANCE;
    }

    public UserEntity extractUserData() {
        try {
            return (UserEntity) scenarioContext.getContext("user");
        } catch (RuntimeException ex) {
            throw new CustomException("User context failed to extract");
        }
    }

    @Given("valid user data")
    public void validUserData(DataTable userData) {
        log.info("Starting processing user data from DataTable.");

        List<Map<String, String>> userCredentials = userData.asMaps(String.class, String.class);
        List<String> requiredFields = Arrays.asList("firstName", "lastName", "email", "password");

        for (Map<String, String> userCredential : userCredentials) {
            UserEntity userEntity = new UserEntity();

            for (String requiredField : requiredFields) {
                String value = userCredential.get(requiredField);
                if (value == null) {
                    throw new CustomException("Updated credentials should not be empty");
                }

                String finalValue = value.startsWith("[random") ? TestDataGeneratorUtils.getRandomCredentials(requiredField) : value;
                switch (requiredField) {
                    case "firstName" -> userEntity.setFirstName(finalValue);
                    case "lastName" -> userEntity.setLastName(finalValue);
                    case "email" -> userEntity.setEmail(finalValue);
                    case "password" -> userEntity.setPassword(finalValue);
                }
                log.info("Set " + requiredField + ": " + finalValue);
            }

            ScenarioContext.INSTANCE.setContext("user", userEntity);
            log.debug("Set user data to scenario context");

            log.info("Finished processing user data.");
        }
    }

    @When("a request to create new user was sent")
    public void aRequestToCreateNewUserWasSent() {
        UserEntity userEntity = extractUserData();
        String requestBody = String.format("{" +
                        "\"firstName\": \"%s\"," +
                        "\"lastName\": \"%s\"," +
                        "\"email\": \"%s\"," +
                        "\"password\": \"%s\"}",
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword());
        //JSONObject -> .toString
        Response response = Requests.postRequest("/users", requestBody, 201);
        String token = response.jsonPath().getString("token");
        scenarioContext.setContext("token", token);
    }

    @Then("user was successfully created")
    public void userWasSuccessfullyCreated() {
        Response response = Requests.getRequest("/users/me", 200);
        Assertions.assertGetUserProfile(response);
    }

    @When("a request to update the user's details with next values was sent")
    public void aRequestToUpdateTheUserSDetailsWasSent(DataTable userData) {

        List<Map<String, String>> userCredentials = userData.asMaps(String.class, String.class);
        for (Map<String, String> userCredential : userCredentials) {
            UserEntity userEntity = new UserEntity();

            if (userCredential.get("firstName") == null || userCredential.get("lastName") == null || userCredential.get("email") == null || userCredential.get("password") == null) {
                throw new CustomException("Credentials should not be empty");
            }

            String firstName = userCredential.get("firstName");
            userEntity.setFirstName(firstName);
            log.debug("Set firstName: " + userEntity.getFirstName());

            String lastName = userCredential.get("lastName");
            userEntity.setLastName(lastName);
            log.debug("Set lastName: " + userEntity.getLastName());

            String email = userCredential.get("email");
            userEntity.setEmail(email);
            log.debug("Set email: " + userEntity.getEmail());

            String password = userCredential.get("password");
            userEntity.setPassword(password);
            log.debug("Set password: " + userEntity.getPassword());

            ScenarioContext.INSTANCE.setContext("user", userEntity);
            log.debug("Set user data to scenario context");
        }
        UserEntity userEntity = extractUserData();

        String requestBody = String.format("{" +
                        "\"firstName\": \"%s\"," +
                        "\"lastName\": \"%s\"," +
                        "\"email\": \"%s\"," +
                        "\"password\": \"%s\"}",
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword());

        Requests.patchRequest("/users/me", requestBody, 200);
    }

    @Then("the user's details was successfully updated")
    public void getUserDetails() {
        Response response = Requests.getRequest("/users/me", 200);
        Assertions.assertGetUserProfile(response);
    }

    @And("a request to login with user's details was sent")
    public void aRequestToLoginWithUserSDetailsWasSent() {
        UserEntity userEntity = extractUserData();

        String requestBody = String.format("{" +
                        "\"email\": \"%s\"," +
                        "\"password\": \"%s\"" +
                        "}",
                userEntity.getEmail(),
                userEntity.getPassword());

        Response response = Requests.postRequest("/users/login", requestBody, 200);

        String token = response.jsonPath().getString("token");
        scenarioContext.setContext("token", token);
    }

    @When("a request to delete user was sent")
    public void aRequestToDeleteTheUserWasSent() {
        Requests.deleteRequest("/users/me", 200);
    }

    @Then("the user was successfully deleted")
    public void userNotAbleToLogin() {
        UserEntity userEntity = extractUserData();

        String requestBody = String.format("{\"email\": \"%s\",\"password\": \"%s\"}",
                userEntity.getEmail(),
                userEntity.getPassword()
        );

        Response response = Requests.postRequest("/users/login", requestBody, 401);
        Assertions.assertNoAuthentication(response);
    }
}
