package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Users {
    @Test
    public void simpleGetTest() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com/users/me";
        given().get(baseURI)
                .then()
                .log().all();
    }
}



