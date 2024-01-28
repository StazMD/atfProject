package steps.tests;

import api.ApiTest;
import api.UserData;
import io.cucumber.java.en.When;

public class UsersTest {

    private final ApiTest apiTest = new ApiTest();

    @When("get user profile with {string}, {string}, {string} and {string}")
    public void getUserProfile(String firstname, String lastname, String email, String password) {
        UserData userData = new UserData(firstname, lastname, email, password);
        apiTest.getUserProfile(userData);
    }
}
