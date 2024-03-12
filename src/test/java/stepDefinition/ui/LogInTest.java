package stepDefinition.ui;

import config.WebDriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.ContactListPage;
import pages.HomePage;

import java.util.List;
import java.util.Map;

public class LogInTest {
    private final HomePage homePage;
    private final ContactListPage contactListPage;
    protected WebDriver driver;

    public LogInTest(ContactListPage contactListPage) {
        this.driver = WebDriverFactory.getDriver();
        this.homePage = new HomePage();
        this.contactListPage = contactListPage;
    }

    @When("valid login credentials were entered")
    public void validCredentialsWereEntered(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> credential : credentials) {
            String email = credential.get("email");
            String password = credential.get("password");
            homePage.loginUser(email, password);
        }
    }

    @Then("user was successfully logged in the application")
    public void userWasSuccessfullyLoggedInTheApplication() {
        contactListPage.assertHeader("Contact List");
    }
}
