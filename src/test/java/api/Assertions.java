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

    public static User extractUserData() {
        try {
            return (User) ScenarioContext.INSTANCE.getContext("user");
        } catch (RuntimeException ex) {
            throw new ExceptionUtils("User context not found");
        }
    }

    public static void assertGetUserProfile(Response response) {
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

    public static void assertNoAuthentication(Response response) {
        int jsonPath = response.getStatusCode();

        log.info("Verifying that user not authenticated");
        assertEquals(jsonPath, 401);
    }
}
