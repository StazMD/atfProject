package stepDefinition.ui;

import enums.Credentials;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.ContactListPage;
import pages.HomePage;
import utils.CustomException;

import java.util.List;
import java.util.Map;

public class LogInTest {
    private final HomePage homePage;
    private final ContactListPage contactListPage;
    private static final Logger log = LogManager.getLogger(LogInTest.class);

    public LogInTest(ContactListPage contactListPage) {
        this.homePage = new HomePage();
        this.contactListPage = contactListPage;
    }

    @When("valid login credentials were entered")
    public void validCredentialsWereEntered(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps();
        for (Map<String, String> credential : credentials) {
            String email = credential.get(Credentials.EMAIL.getValue());
            String password = credential.get(Credentials.PASSWORD.getValue());
            try {
                log.info("Attempting to log in with email: {}", email);
                homePage.loginUser(email, password);
                log.info("Login successful for email: {}", email);
            } catch (Exception ex) {
                log.error("Login failed for email: {}, error: {}", email, ex.getMessage());
                throw new CustomException("Login failed for provided credentials");
            }
        }
    }

    @Then("user was successfully logged in the application")
    public void userWasSuccessfullyLoggedInTheApplication() {
        contactListPage.assertHeader("Contact List");
    }
}
