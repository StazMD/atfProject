package api;

import context.ScenarioContext;
import entity.User;
import io.restassured.response.Response;
import utils.ExceptionUtils;
import utils.TestDataGeneratorUtils;


public class ApiStepDef {

    private final Assertions assertions = new Assertions();
    private static final ScenarioContext scenarioContext;

    //TODO why static in {}?
    static {
        scenarioContext = ScenarioContext.INSTANCE;
    }

    //TODO pico-container

    //TODO try-catch
    public User extractUserData() {
        try {
            return (User) scenarioContext.getContext("user");
        } catch (RuntimeException ex) {
            throw new ExceptionUtils("message");
        }
    }

    public void createUser() {
        User user = extractUserData();
        String requestBody = String.format("{\"firstName\": \"%s\",\"lastName\": \"%s\",\"email\": \"%s\",\"password\": \"%s\"}", user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());

        Response response = Requests.postRequest("/users", requestBody, 201);
        String token = response.jsonPath().getString("token");
        scenarioContext.setContext("token", token);
        assertions.assertUser(response);
    }

    public void getUserProfile() {
        Response response = Requests.getRequest("/users/me", 200);
        assertions.assertGetUserProfile(response);
    }

    public void loginUser() {
        User user = extractUserData();

        String requestBody = String.format("{\"email\": \"%s\",\"password\": \"%s\"}", user.getEmail(), user.getPassword());

        Response response = Requests.postRequest("/users/login", requestBody, 200);
        String token = response.jsonPath().getString("token");
        scenarioContext.setContext("token", token);
        assertions.assertUser(response);
    }

    public void updateUser() {
        User user = extractUserData();

        String updatedFirstName = TestDataGeneratorUtils.getRandomFirstName();
        String updatedLastName = TestDataGeneratorUtils.getRandomLastName();

        String requestBody = String.format("{\"firstName\": \"%s\",\"lastName\": \"%s\"}", updatedFirstName, updatedLastName);

        Response response = Requests.patchRequest("/users/me", requestBody, 200);

        user.setFirstName(updatedFirstName);
        user.setLastName(updatedLastName);


        assertions.assertUpdatedUser(response);
    }

    public void deleteUser() {
        Requests.deleteRequest("/users/me", 200);
    }

    public void userNotExisted() {
        User user = extractUserData();

        String requestBody = String.format("{\"email\": \"%s\",\"password\": \"%s\"}", user.getEmail(), user.getPassword());

        Response response = Requests.postRequest("/users/login", requestBody, 401);
        assertions.assertNoAuthentication(response);
    }

}
