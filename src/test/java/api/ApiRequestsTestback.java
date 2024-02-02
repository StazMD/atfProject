package api;

import config.PropertyReader;
import context.TestContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiRequestsTestback {

    private final String URL = PropertyReader.getProperty("url");

    public Map<String, String> extractUserData() {
        Map<String, String> userData = new HashMap<>();
        userData.put("firstname", (String) TestContext.getContext("firstname"));
        userData.put("lastname", (String) TestContext.getContext("lastname"));
        userData.put("email", (String) TestContext.getContext("email"));
        userData.put("password", (String) TestContext.getContext("password"));
        return userData;
    }

    public void createUser() {
        Map<String, String> userDetails = extractUserData();
        String firstname = userDetails.get("firstname");
        String lastname = userDetails.get("lastname");
        String email = userDetails.get("email");
        String password = userDetails.get("password");

        String requestBody = String.format("{\"firstName\": \"%s\",\"lastName\": \"%s\",\"email\": \"%s\",\"password\": \"%s\"}", firstname, lastname, email, password);

        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + "/users")
                .then().log().all()
                .assertThat().statusCode(201)
                .extract().response();

        assertThat(response.jsonPath().getString("user._id")).isNotNull();
        assertThat(response.jsonPath().getString("user.firstName")).isEqualTo(firstname);
        assertThat(response.jsonPath().getString("user.lastName")).isEqualTo(lastname);
        assertThat(response.jsonPath().getString("user.email")).isEqualTo(email);
        assertThat(response.jsonPath().getInt("user.__v")).isNotNull();
        assertThat(response.jsonPath().getString("token")).isNotNull();

    }

    public void getUserProfile() {
        Map<String, String> userDetails = extractUserData();
        String firstname = userDetails.get("firstname");
        String lastname = userDetails.get("lastname");
        String email = userDetails.get("email");
        String password = userDetails.get("password");

        String token = TokenService.getJwtToken(email, password);

        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .get(URL + "/users/me")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        assertThat(response.jsonPath().getString("_id")).isNotNull();
        assertThat(response.jsonPath().getString("firstName")).isEqualTo(firstname);
        assertThat(response.jsonPath().getString("lastName")).isEqualTo(lastname);
        assertThat(response.jsonPath().getString("email")).isEqualTo(email);
        assertThat(response.jsonPath().getInt("__v")).isNotNull();

    }

    public void ableToLogin() {
        Map<String, String> userDetails = extractUserData();

        String firstname = userDetails.get("firstname");
        String lastname = userDetails.get("lastname");
        String email = userDetails.get("email");
        String password = userDetails.get("password");

        String requestBody = String.format("{\"email\": \"%s\",\"password\": \"%s\"}", email, password);

        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + "/users/login")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        assertThat(response.jsonPath().getString("user._id")).isNotNull();
        assertThat(response.jsonPath().getString("user.firstName")).isEqualTo(firstname);
        assertThat(response.jsonPath().getString("user.lastName")).isEqualTo(lastname);
        assertThat(response.jsonPath().getString("user.email")).isEqualTo(email);
        assertThat(response.jsonPath().getInt("user.__v")).isNotNull();
        assertThat(response.jsonPath().getString("token")).isNotNull();
    }

    public void userNotExisted() {
        Map<String, String> userDetails = extractUserData();
        String email = userDetails.get("email");
        String password = userDetails.get("password");

        String requestBody = String.format("{\"email\": \"%s\",\"password\": \"%s\"}", email, password);

        RestAssured.defaultParser = Parser.JSON;
        given()
                .header("Accept", "application/json")
                .body(requestBody)
                .when()
                .post(URL + "/users/login")
                .then().log().all()
                .assertThat().statusCode(401);
    }
}




