package stepDefinition.UI;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

import java.util.List;

public class LogInTest {

    @When("valid credentials were entered")
    public void validCredentialsWereEntered(DataTable dataTable) {
        HomePage homePage = new HomePage();
        List<List<String>> credentials = dataTable.asLists(String.class);
        for (List<String> credential : credentials) {
            String email = credential.get(0);
            String password = credential.get(1);
            homePage.loginUser(email, password);
            homePage.submitButton();
        }
    }

    @Then("user was successfully logged in")
    public void userWasSuccessfullyLoggedIn() {
        HomePage homePage = new HomePage();
        homePage.loginUser("testuser@mail.com", "TestUserTestUser");
        homePage.assertHeader("Contact List");
    }

    @Then("user was successfully logged in the application")
    public void userWasSuccessfullyLoggedInTheApplication() {
        HomePage homePage = new HomePage();
        homePage.assertHeader("Contact List");
    }
}
