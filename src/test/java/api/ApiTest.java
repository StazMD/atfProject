package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiTest {
    public static void main(String[] args) {
        // Установка базового URI
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

        // Создание объекта запроса
        UserRegistration user = new UserRegistration("Test", "User", "test@fake.com", "myPassword");

        // Выполнение POST запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(200)  // Проверяем, что статус код ответа 200
                .extract()
                .response();

        // Десериализация ответа
        ApiResponse userResponse = response.as(ApiResponse.class);

        // Получение токена
        String token = userResponse.getToken();

        // Выводим токен или используем его для последующих запросов
        System.out.println("Token: " + token);
    }
}

