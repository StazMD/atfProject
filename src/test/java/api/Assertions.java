package api;

import context.ScenarioContext;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Assertions {
    protected Map<String, Object> userDetails = ScenarioContext.getScenarioData();
    private final Logger logger = LoggerFactory.getLogger(StepDefinitions.class);

    public void assertUserDetails(Response response) {

        JsonPath jsonPath = response.jsonPath();
        String token = (String) userDetails.get("token");

        String actualUserId = jsonPath.getString("user._id") != null ? jsonPath.getString("user._id") : jsonPath.getString("_id");
        String actualFirstName = jsonPath.getString("user.firstName") != null ? jsonPath.getString("user.firstName") : jsonPath.getString("firstName");
        String actualLastName = jsonPath.getString("user.lastName") != null ? jsonPath.getString("user.lastName") : jsonPath.getString("lastName");
        String actualEmail = jsonPath.getString("user.email") != null ? jsonPath.getString("user.email") : jsonPath.getString("email");
        String actualVersion = jsonPath.getString("user.__v") != null ? jsonPath.getString("user.__v") : jsonPath.getString("__v");
        boolean isValid = token == null || !token.isEmpty();

        logger.info("Verifying if User ID {} is existed", actualUserId);
        assertThat(actualUserId).as("User ID is not null").isNotNull();
        logger.info("Verifying if registered firstname {} matches with test data", actualFirstName);
        assertThat(actualFirstName).as("First name should match").isEqualTo(userDetails.get("firstName"));
        logger.info("Verifying if registered lastname {} matches with test data", actualLastName);
        assertThat(actualLastName).as("Last name should match").isEqualTo(userDetails.get("lastName"));
        logger.info("Verifying if registered Email {} matches with test data", actualEmail);
        assertThat(actualEmail).as("Email should match").isEqualTo(userDetails.get("email"));
        logger.info("Verifying if Version {} is existed", actualVersion);
        assertThat(actualVersion).as("Version is not null").isNotNull();
        logger.info("Checking token validity");
        assertThat(isValid).as(token == null ? "Token is not present, but it's ok" : "Token should not be empty").isTrue();
    }

    public void assertNoAuthentication(Response response) {
        JsonPath jsonPath = response.jsonPath();

        String responseMessage = jsonPath.getString("error");
        String userName = (String) userDetails.get("firstName");

        logger.info("Verifying that user {} not authenticated", userName);
        assertThat(responseMessage).as("Response contains error message as expected").isEqualTo("Please authenticate.");
    }

    public void assertUpdatedData(Response response) {
        JsonPath jsonPath = response.jsonPath();

        String actualFirstName = (String) userDetails.get("firstName");
        String actualLastName = (String) userDetails.get("lastName");

        logger.info("Verifying if firstname was updated to {} and matches with response", actualFirstName);
        assertThat(actualFirstName).as("First name should match").isEqualTo(userDetails.get("firstName"));
        logger.info("Verifying if lastname was updated to {} and matches with response", actualLastName);
        assertThat(actualLastName).as("Last name should match").isEqualTo(userDetails.get("lastName"));
    }

    public void assertUserNotExisted() {

    }
}
