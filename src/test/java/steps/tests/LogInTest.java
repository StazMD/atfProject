package steps.tests;

import config.PropertyReader;
import io.cucumber.java.en.And;
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
        String email = PropertyReader.getProperty("userEmail");
        String password = PropertyReader.getProperty("userPassword");
        mainPage.loginUser(email, password);
    }

    @Then("user was successfully logged in")
    public void userWasSuccessfullyLoggedIn() {
        basePage.assertHeader("Contact List", true);
    }

    @And("user is logged in with email {string} and password {string}")
    public void userIsLoggedInWithEmailAndPassword(String email, String password) {
        mainPage.loginUser(email, password);
    }
}
