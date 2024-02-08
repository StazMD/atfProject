package stepDefinition.UI;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MainPage;

import java.util.List;

public class LogInTest {

    private final MainPage mainPage;

    public LogInTest(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    @When("valid credentials were entered")
    public void validCredentialsWereEntered(DataTable credentials) {
        List<List<String>> data = credentials.asLists(String.class);
        String email = data.get(1).get(0);
        String password = data.get(1).get(1);
        mainPage.loginUser(email, password);
        mainPage.clickSubmitButton();
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
