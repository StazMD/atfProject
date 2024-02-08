package api;

import context.ScenarioContext;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestDataGeneratorUtils;

import java.util.Map;


public class StepDefinitions {

    private final Assertions assertions;
    private final Map<String, Object> userDetails;

    public StepDefinitions() {
        this.assertions = new Assertions();
        this.userDetails = ScenarioContext.getScenarioData();
        Logger logger = LoggerFactory.getLogger(StepDefinitions.class);
    }

    public void extractUserData() {
        userDetails.put("firstName", (String) ScenarioContext.getContext("firstName"));
        userDetails.put("lastName", (String) ScenarioContext.getContext("lastName"));
        userDetails.put("email", (String) ScenarioContext.getContext("email"));
        userDetails.put("password", (String) ScenarioContext.getContext("password"));
    }

    public void createUser() {
        extractUserData();
        String requestBody = String
                .format("{\"firstName\": \"%s\",\"lastName\": \"%s\",\"email\": \"%s\",\"password\": \"%s\"}",
                        userDetails.get("firstName"),
                        userDetails.get("lastName"),
                        userDetails.get("email"),
                        userDetails.get("password"));

        Response response = Requests.postRequest("/users", requestBody, 201);
        assertions.assertUserDetails(response);
    }

    public void getUserProfile() {
        Response response = Requests.getRequest("/users/me", 200);
        assertions.assertUserDetails(response);
    }

    public void loginUser() {
        extractUserData();

        String requestBody = String
                .format("{\"email\": \"%s\",\"password\": \"%s\"}",
                        userDetails.get("email"),
                        userDetails.get("password"));

        Response response = Requests.postRequest("/users/login", requestBody, 200);
        assertions.assertUserDetails(response);
    }

    public void updateUser() {
        String updatedFirstName = TestDataGeneratorUtils.getRandomFirstName();
        String updatedLastName = TestDataGeneratorUtils.getRandomLastName();

        String requestBody = String
                .format("{\"firstName\": \"%s\",\"lastName\": \"%s\"}", updatedFirstName, updatedLastName);

        Response response = Requests.patchRequest("/users/me", requestBody, 200);

        userDetails.put("firstName", updatedFirstName);
        userDetails.put("lastName", updatedLastName);

        assertions.assertUpdatedData(response);
    }

    public void deleteUser() {
        Response response = Requests.deleteRequest("/users/me", 200);
    }

    public void userNotExisted() {
        Response response = Requests.getRequest("/users/me", 401);
        assertions.assertNoAuthentication(response);
    }

}
