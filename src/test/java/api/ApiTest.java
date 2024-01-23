package api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {

    @Test
    public void testApiWithJwtToken() {
        TokenService tokenService = new TokenService();
        String token = tokenService.getJwtToken("TestUser@mail.com", "TestUserTestUser");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://thinking-tester-contact-list.herokuapp.com/users/me") // Измените на ваш protected endpoint
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

}

