package stepDefinition.UI;

import config.WebDriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import java.io.IOException;
import java.util.List;

public class LogInTest {
    private final HomePage homePage;
    protected WebDriver driver;

    public LogInTest() {
        this.driver = WebDriverFactory.getDriver();
        this.homePage = new HomePage();
    }

    @When("valid login credentials were entered")
    public void validCredentialsWereEntered(DataTable dataTable) throws IOException {
        List<List<String>> credentials = dataTable.asLists(String.class);
        for (List<String> credential : credentials) {
            String email = credential.get(0);
            String password = credential.get(1);
            homePage.loginUser(email, password);
        }
    }

    @Then("user was successfully logged in the application")
    public void userWasSuccessfullyLoggedInTheApplication() {
        homePage.assertHeader("Contact List");
    }
}
