package api;

import config.PropertyReader;
import context.ScenarioContext;
import enums.ConfigKeys;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static enums.Credentials.TOKEN;
import static io.restassured.RestAssured.given;

public class Requests {

    private final static ScenarioContext scenarioContext = ScenarioContext.INSTANCE;
    private static final String URL = PropertyReader.getProperty(ConfigKeys.SERVER_URL.getKey());

    public static Response postRequest(String path, String requestBody, int statusCode) {
        return given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .post(URL + path)
                .then()
                .log().all()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }

    public static Response getRequest(String path, int statusCode) {
        return given().header("Authorization", scenarioContext.getContext(TOKEN))
                .contentType(ContentType.JSON).when()
                .get(URL + path)
                .then()
                .log().all()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }

    public static void patchRequest(String path, String requestBody, int statusCode) {
        given().header("Authorization", scenarioContext.getContext(TOKEN))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(URL + path)
                .then()
                .log().all()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }

    public static Response deleteRequest(String path, int statusCode) {
        return given().header("Authorization", scenarioContext.getContext(TOKEN))
                .when()
                .delete(URL + path)
                .then()
                .log().all()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }
}
