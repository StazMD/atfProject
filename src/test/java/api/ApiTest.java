package api;

import config.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiTest {

    private final String URL = PropertyReader.getProperty("url");

    public void getUserProfile(UserData userData) {
        String email = userData.getEmail();
        String password = userData.getPassword();
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

        assertThat(userData)
                .as("Verify User Data")
                .satisfies(user -> {
                    assertThat(user.getFirstName()).isEqualTo(response.jsonPath().getString("firstName"));
                    assertThat(user.getLastName()).isEqualTo(response.jsonPath().getString("lastName"));
                    assertThat(user.getEmail()).isEqualTo(response.jsonPath().getString("email"));
                });
    }

    public void userNotExisted(UserData userData) {
        String email = userData.getEmail();
        String password = userData.getPassword();
        String requestBody = String.format("{\"email\": \"%s\",\"password\": \"%s\"}", email, password);
        System.out.println(requestBody);
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

