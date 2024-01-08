package steps.login;

import config.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactListPage;
import pages.MainPage;

public class LogInTest {

    private final MainPage mainPage;
    private final ContactListPage contactListPage;

    public LogInTest(MainPage mainPage, ContactListPage contactListPage) {
        this.mainPage = mainPage;
        this.contactListPage = contactListPage;
    }

    @When("valid {string} and {string} were entered")
    public void validCredentialsWereEntered(String userEmail, String userPassword) {
        String email = PropertyReader.getProperty("userEmail");
        String password = PropertyReader.getProperty("userPassword");
        mainPage.loginUser(email, password);
    }

    @Then("user was successfully logged in")
    public void userWasSuccessfullyLoggedIn() {
        contactListPage.assertHeader("Contact List", true);
    }

    @And("user is logged in with email {string} and password {string}")
    public void userIsLoggedInWithEmailAndPassword(String email, String password) {
        mainPage.loginUser(email, password);
    }
}
