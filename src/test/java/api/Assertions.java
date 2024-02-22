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
        log.info("Response firstname '{}' matches with test data", responseFirstName);
        assertEquals(responseFirstName, user.getFirstName());

        String responseLastName = jsonPath.getString("user.lastName");
        log.info("Response lastname '{}' matches with test data", responseLastName);
        assertEquals(responseLastName, user.getLastName());

        String responseEmail = jsonPath.getString("user.email");
        log.info("Response email '{}' matches with test data", responseEmail);
        assertEquals(responseEmail, user.getEmail());
    }

    public void assertGetUserProfile(Response response) {
        User user = extractUserData();
        JsonPath jsonPath = response.jsonPath();

        String responseFirstName = jsonPath.getString("firstName");
        log.info("Response firstname '{}' matches with test data", responseFirstName);
        assertEquals(responseFirstName, user.getFirstName());

        String responseLastName = jsonPath.getString("lastName");
        log.info("Response lastname '{}' matches with test data", responseLastName);
        assertEquals(responseLastName, user.getLastName());

        String responseEmail = jsonPath.getString("email");
        log.info("Response email '{}' matches with test data", responseEmail);
        assertEquals(responseEmail, user.getEmail());
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
        assertEquals(actualFirstName, user.getFirstName());
        log.info("Lastname was updated to '{}' and matches with response", actualLastName);
        assertEquals(actualLastName, user.getLastName());
    }
}
