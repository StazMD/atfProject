package api;

import context.ScenarioContext;
import entity.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Assertions {
    private static final Logger log = LogManager.getLogger(Assertions.class);

    public User extractUserData() {
        try {
            return (User) ScenarioContext.INSTANCE.getContext("user");
        } catch (RuntimeException ex) {
            throw new RuntimeException("message", ex);
        }
    }

    public void assertUser(Response response) {
        User user = extractUserData();
        JsonPath jsonPath = response.jsonPath();

        String responseFirstName = jsonPath.getString("user.firstName");
        log.info("Response firstname '{}' matches with test data", responseFirstName);
        assertThat(responseFirstName)
                .isEqualTo(user.getFirstName());

        String responseLastName = jsonPath.getString("user.lastName");
        log.info("Response lastname '{}' matches with test data", responseLastName);
        assertThat(responseLastName)
                .isEqualTo(user.getLastName());

        String responseEmail = jsonPath.getString("user.email");
        log.info("Response email '{}' matches with test data", responseEmail);
        assertThat(responseEmail)
                .isEqualTo(user.getEmail());
    }

    public void assertGetUserProfile(Response response) {
        User user = extractUserData();
        JsonPath jsonPath = response.jsonPath();

        String responseFirstName = jsonPath.getString("firstName");
        log.info("Response firstname '{}' matches with test data", responseFirstName);
        assertThat(responseFirstName)
                .isEqualTo(user.getFirstName());

        String responseLastName = jsonPath.getString("lastName");
        log.info("Response lastname '{}' matches with test data", responseLastName);
        assertThat(responseLastName)
                .isEqualTo(user.getLastName());

        String responseEmail = jsonPath.getString("email");
        log.info("Response email '{}' matches with test data", responseEmail);
        assertThat(responseEmail)
                .isEqualTo(user.getEmail());
    }

    public void assertNoAuthentication(Response response) {
        JsonPath jsonPath = response.jsonPath();
        String responseMessage = jsonPath.getString("error");

        log.info("Verifying that user not authenticated");
        assertThat(responseMessage).isEqualTo("Please authenticate.");
    }

    public void assertUpdatedUser(Response response) {
        User user = extractUserData();
        JsonPath jsonPath = response.jsonPath();

        String actualFirstName = user.getFirstName();
        String actualLastName = user.getLastName();

        log.info("Firstname was updated to '{}' and matches with response", actualFirstName);
        assertThat(actualFirstName).isEqualTo(user.getFirstName());
        log.info("Lastname was updated to '{}' and matches with response", actualLastName);
        assertThat(actualLastName).isEqualTo(user.getLastName());
    }

    public void assertUserNotExisted() {

    }
}
