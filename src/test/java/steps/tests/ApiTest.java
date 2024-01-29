package steps.tests;

import api.UserData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.TestDataGeneratorUtils;

public class ApiTest {

    private final api.ApiTest apiTest;
    UserData userData = new UserData();

    public ApiTest(api.ApiTest apiTest) {
        this.apiTest = apiTest;
    }

    @When("new user is created")
    public void newUserIsCreated() {
        UserData userdata = TestDataGeneratorUtils.generateCredentials();
        apiTest.getUserProfile(userData);
    }

    @Then("new user is able to login")
    public void newUserIsAbleToLogin() {
    }

    @And("new user profile could be retrieved")
    public void newUserProfileCouldBeRetrieved() {
    }
}
