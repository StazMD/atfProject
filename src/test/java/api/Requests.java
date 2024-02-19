package api;

import config.PropertyReader;
import context.ScenarioContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class Requests {

    private final static ScenarioContext scenarioContext = ScenarioContext.INSTANCE;
    private static final String URL = PropertyReader.getProperty("browser.homepage-url");
    private static final Logger log = LogManager.getLogger(Requests.class);

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
        return given().header("Authorization", "Bearer " + scenarioContext.getContext("token"))
                .contentType(ContentType.JSON).when()
                .get(URL + path)
                .then()
                .log().all()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }

    static Response patchRequest(String path, String requestBody, int statusCode) {
        return given().header("Authorization", "Bearer " + scenarioContext.getContext("token"))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(URL + path)
                .then()
                .log().all()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }

    static Response deleteRequest(String path, int statusCode) {
        return given().header("Authorization", "Bearer " + scenarioContext.getContext("token"))
                .when()
                .delete(URL + path)
                .then()
                .log().all()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }
}
