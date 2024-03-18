package api;

import context.ScenarioContext;
import entity.UserEntity;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CustomException;

import static org.assertj.core.api.Assertions.assertThat;

public class Assertions {
    private static final Logger log = LogManager.getLogger(Assertions.class);

    public static UserEntity extractUserData() {
        try {
            return (UserEntity) ScenarioContext.INSTANCE.getContext("user");
        } catch (RuntimeException ex) {
            throw new CustomException("User context not found");
        }
    }

    public static void assertGetUserProfile(Response response) {
        UserEntity userEntity = extractUserData();
        JsonPath jsonPath = response.jsonPath();

        String responseFirstName = jsonPath.getString("firstName");
        assertThat(userEntity.getFirstName()).isEqualTo(responseFirstName);
        log.info("Response firstname '{}' matches with test data '{}'",
                responseFirstName,
                userEntity.getFirstName());

        String responseLastName = jsonPath.getString("lastName");
        assertThat(userEntity.getLastName()).isEqualTo(responseLastName);
        log.info("Response lastname '{}' matches with test data '{}'",
                responseLastName,
                userEntity.getLastName());

        String responseEmail = jsonPath.getString("email");
        assertThat(userEntity.getEmail().toLowerCase()).isEqualTo(responseEmail);

        log.info("Response email '{}' matches with test data '{}'",
                responseEmail,
                userEntity.getEmail().toLowerCase());
    }

    public static void assertNoAuthentication(Response response) {
        int jsonPath = response.getStatusCode();

        log.info("Verifying that user not authenticated");
        assertThat(jsonPath).isEqualTo(401);
    }
}
