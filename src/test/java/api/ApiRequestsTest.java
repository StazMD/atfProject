package api;

import config.PropertyReader;
import context.TestContext;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.TestDataGeneratorUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiRequestsTest {

    private final String URL = PropertyReader.getProperty("url");

    public Map<String, String> extractUserData() {
        Map<String, String> userData = new HashMap<>();
        userData.put("firstname", (String) TestContext.getContext("firstname"));
        userData.put("lastname", (String) TestContext.getContext("lastname"));
        userData.put("email", (String) TestContext.getContext("email"));
        userData.put("password", (String) TestContext.getContext("password"));
        return userData;
    }

    private Response postRequest(String path, String requestBody, int statusCode) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + path)
                .then().log().all()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }

    private Response getRequest(String path, String token, int statusCode) {
        return given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .get(URL + path)
                .then().log().all()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }

    private Response patchRequest(String path, String requestBody, String token, int statusCode) {
        return given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(URL + path)
                .then().log().all()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }

    public void createUser() {
        Map<String, String> userDetails = extractUserData();
        String requestBody = String
                .format("{\"firstName\": \"%s\",\"lastName\": \"%s\",\"email\": \"%s\",\"password\": \"%s\"}",
                        userDetails.get("firstname"),
                        userDetails.get("lastname"),
                        userDetails.get("email"),
                        userDetails.get("password"));

        Response response = postRequest("/users", requestBody, 201);

        assertUserDetails(response, userDetails);
    }

    public void getUserProfile() {
        Map<String, String> userDetails = extractUserData();
        String token = TokenService.getJwtToken(userDetails.get("email"),
                userDetails.get("password"));

        Response response = getRequest("/users/me", token, 200);

        assertUserDetails(response, userDetails);
    }

    public void ableToLogin() {
        Map<String, String> userDetails = extractUserData();
        String requestBody = String
                .format("{\"email\": \"%s\",\"password\": \"%s\"}",
                        userDetails.get("email"),
                        userDetails.get("password"));

        Response response = postRequest("/users/login", requestBody, 200);

        assertUserDetails(response, userDetails);
    }

    public void updateUser() {
        Map<String, String> userDetails = extractUserData();
        String token = TokenService.getJwtToken(userDetails.get("email"), userDetails
                .get("password"));
        String newFirstName = TestDataGeneratorUtils.getRandomFirstName();
        String newLastName = TestDataGeneratorUtils.getRandomLastName();
        String requestBody = String
                .format("{\"firstName\": \"%s\",\"lastName\": \"%s\"}", newFirstName, newLastName);

        Response response = patchRequest("/users/me", requestBody, token, 200);
        userDetails.put("firstname", newFirstName);
        userDetails.put("lastname", newLastName);

        assertUserDetails(response, userDetails);
    }

    private void assertUserDetails(Response response, Map<String, String> userDetails) {
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.getString("token");

        String actualUserId = jsonPath.getString("user._id") != null ? jsonPath.getString("user._id") : jsonPath.getString("_id");
        String actualFirstName = jsonPath.getString("user.firstName") != null ? jsonPath.getString("user.firstName") : jsonPath.getString("firstName");
        String actualLastName = jsonPath.getString("user.lastName") != null ? jsonPath.getString("user.lastName") : jsonPath.getString("lastName");
        String actualEmail = jsonPath.getString("user.email") != null ? jsonPath.getString("user.email") : jsonPath.getString("email");
        String actualVersion = jsonPath.getString("user.__v") != null ? jsonPath.getString("user.__v") : jsonPath.getString("__v");
        boolean isValid = token != null ? !token.isEmpty() : true;

        assertThat(actualUserId).as("User ID is not null").isNotNull();
        assertThat(actualFirstName).as("First Name should match").isEqualTo(userDetails.get("firstname"));
        assertThat(actualLastName).as("Last Name should match").isEqualTo(userDetails.get("lastname"));
        assertThat(actualEmail).as("Email should match").isEqualTo(userDetails.get("email"));
        assertThat(actualVersion).as("Version is not null").isNotNull();
        assertThat(isValid).as(token != null ? "Token should not be empty" : "Token is not present, but it's ok").isTrue();
    }
}