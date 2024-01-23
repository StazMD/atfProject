package api;

import config.PropertyReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenService {

    private final String URL = PropertyReader.getProperty("url");

    public String getJwtToken(String email, String password) {
        String requestBody = String.format("{\"email\": \"%s\",\"password\": \"%s\"}", email, password);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(URL + "/users/login");

        return response.path("token");
    }
}




