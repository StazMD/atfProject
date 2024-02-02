package stepDefinition.API;

import api.ApiRequestsTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.TestDataGeneratorUtils;

public class RestTest {

    ApiRequestsTest apiRequestsTest = new ApiRequestsTest();

    @When("new user is created")
    public void newUserIsCreated() {
        new TestDataGeneratorUtils().generateCredentials();
        apiRequestsTest.createUser();
    }

    @Then("new user is able to login")
    public void newUserIsAbleToLogin() {
        apiRequestsTest.ableToLogin();
    }

    @And("new user profile could be retrieved")
    public void newUserProfileCouldBeRetrieved() {
        apiRequestsTest.getUserProfile();
    }

    @And("user is updated")
    public void userIsUpdated() {
        apiRequestsTest.updateUser();
    }
}
