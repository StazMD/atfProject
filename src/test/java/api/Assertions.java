package api;

import context.ScenarioContext;
import entity.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ExceptionUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assertions {
    private static final Logger log = LogManager.getLogger(Assertions.class);

    public User extractUserData() {
        try {
            return (User) ScenarioContext.INSTANCE.getContext("user");
        } catch (RuntimeException ex) {
            throw new ExceptionUtils("User context not found");
        }
    }

    public void assertUser(Response response) {
        User user = extractUserData();
        JsonPath jsonPath = response.jsonPath();

        String responseFirstName = jsonPath.getString("user.firstName");
        assertEquals(user.getFirstName(), responseFirstName);
        log.info("Response firstname '{}' matches with test data", responseFirstName);

        String responseLastName = jsonPath.getString("user.lastName");
        assertEquals(user.getLastName(), responseLastName);
        log.info("Response lastname '{}' matches with test data", responseLastName);

        String responseEmail = jsonPath.getString("user.email");
        assertEquals(user.getEmail(), responseEmail);
        log.info("Response email '{}' matches with test data", responseEmail);
    }

    public void assertGetUserProfile(Response response) {
        User user = extractUserData();
        JsonPath jsonPath = response.jsonPath();

        String responseFirstName = jsonPath.getString("firstName");
        assertEquals(user.getFirstName(), responseFirstName);
        log.info("Response firstname '{}' matches with test data '{}'", responseFirstName, user.getFirstName());

        String responseLastName = jsonPath.getString("lastName");
        assertEquals(user.getLastName(), responseLastName);
        log.info("Response lastname '{}' matches with test data '{}'", responseLastName, user.getLastName());

        String responseEmail = jsonPath.getString("email");
        assertEquals(user.getEmail().toLowerCase(), responseEmail);
        log.info("Response email '{}' matches with test data '{}'", responseEmail, user.getEmail().toLowerCase());
    }

    public void assertNoAuthentication(Response response) {
        int jsonPath = response.getStatusCode();

        log.info("Verifying that user not authenticated");
        assertEquals(jsonPath, 401);
    }

    public void assertUpdatedUser(Response response) {
        User user = extractUserData();
        JsonPath jsonPath = response.jsonPath();

        String actualFirstName = jsonPath.getString("firstName");
        String actualLastName = jsonPath.getString("lastName");

        log.info("Firstname was updated to '{}' and matches with response", actualFirstName);
        assertEquals(user.getFirstName(), actualFirstName);
        log.info("Lastname was updated to '{}' and matches with response", actualLastName);
        assertEquals(user.getLastName(), actualLastName);
    }
}
