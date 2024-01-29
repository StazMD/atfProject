package steps.tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.MainPage;

public class LogInTest {

    private final MainPage mainPage;
    private final BasePage basePage;

    public LogInTest(MainPage mainPage, BasePage basePage) {
        this.mainPage = mainPage;
        this.basePage = basePage;
    }

    @When("valid {string} and {string} were entered")
    public void validCredentialsWereEntered(String userEmail, String userPassword) {
        mainPage.loginUser(userEmail, userPassword);
    }

    @Then("user was successfully logged in")
    public void userWasSuccessfullyLoggedIn() {
        mainPage.loginUser("testuser@mail.com", "TestUserTestUser");
        basePage.assertHeader("Contact List");
    }

    @Then("user was successfully logged in the application")
    public void userWasSuccessfullyLoggedInTheApplication() {
        basePage.assertHeader("Contact List");
    }
}
