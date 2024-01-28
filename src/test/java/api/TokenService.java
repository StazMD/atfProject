package api;

import config.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenService {

    private static final String URL = PropertyReader.getProperty("url");

    public static String getJwtToken(String email, String password) {
        RestAssured.defaultParser = Parser.JSON;
        String requestBody = String.format("{\"email\": \"%s\",\"password\": \"%s\"}", email, password);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(URL + "/users/login");

        String token = response.path("token");
        System.out.println(token);

        return response.path("token");
    }
}




