package api;

import context.ScenarioContext;
import entity.User;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestDataGeneratorUtils;


public class ApiStepDef {

    Logger log = LoggerFactory.getLogger(ApiStepDef.class);
    private final Assertions assertions = new Assertions();
    private static final ScenarioContext scenarioContext;

    //TODO why?
    static {
        scenarioContext = ScenarioContext.INSTANCE;
    }

    //TODO pico-container

    //TODO try-catch
    public User extractUserData() {
        try {
            return (User) scenarioContext.getContext("user");
        } catch (RuntimeException ex) {
            throw new RuntimeException("message", ex);
        }
    }

    public void createUser() {
        User user = extractUserData();
        String requestBody = String
                .format("{\"firstName\": \"%s\",\"lastName\": \"%s\",\"email\": \"%s\",\"password\": \"%s\"}",
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword());

        Response response = Requests.postRequest("/users", requestBody, 201);
        assertions.assertUser(response);
    }

    public void getUserProfile() {
        Response response = Requests.getRequest("/users/me", 200);
        assertions.assertGetUserProfile(response);
    }

    public void loginUser() {
        User user = extractUserData();

        String requestBody = String
                .format("{\"email\": \"%s\",\"password\": \"%s\"}",
                        user.getEmail(),
                        user.getPassword());

        Response response = Requests.postRequest("/users/login", requestBody, 200);
        assertions.assertUser(response);
    }

    public void updateUser() {
        User user = extractUserData();

        String updatedFirstName = TestDataGeneratorUtils.getRandomFirstName();
        String updatedLastName = TestDataGeneratorUtils.getRandomLastName();

        String requestBody = String
                .format("{\"firstName\": \"%s\",\"lastName\": \"%s\"}", updatedFirstName, updatedLastName);

        Response response = Requests.patchRequest("/users/me", requestBody, 200);

        user.setFirstName(updatedFirstName);
        user.setLastName(updatedLastName);

        assertions.assertUpdatedUser(response);
    }

    public void deleteUser() {
        Response response = Requests.deleteRequest("/users/me", 200);
    }

    public void userNotExisted() {
        Response response = Requests.getRequest("/users/me", 401);
        assertions.assertNoAuthentication(response);
    }

}