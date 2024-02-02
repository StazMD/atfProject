package stepDefinition.UI;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MainPage;

public class LogInTest {

    private final MainPage mainPage;

    public LogInTest(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    @When("valid {string} and {string} were entered")
    public void validCredentialsWereEntered(String userEmail, String userPassword) {
        mainPage.loginUser(userEmail, userPassword);
    }

    @Then("user was successfully logged in")
    public void userWasSuccessfullyLoggedIn() {
        mainPage.loginUser("testuser@mail.com", "TestUserTestUser");
        mainPage.assertHeader("Contact List");
    }

    @Then("user was successfully logged in the application")
    public void userWasSuccessfullyLoggedInTheApplication() {
        mainPage.assertHeader("Contact List");
    }
}
