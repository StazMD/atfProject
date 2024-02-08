package stepDefinition.API;

import api.StepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.TestDataGeneratorUtils;

public class RestTest {

    StepDefinitions stepDefinitions = new StepDefinitions();

    @When("new user is created")
    public void newUserIsCreated() {
        new TestDataGeneratorUtils().generateCredentials();
        stepDefinitions.createUser();
    }

    @Then("new user is able to login")
    public void newUserIsAbleToLogin() {
        stepDefinitions.loginUser();
    }

    @And("new user profile could be retrieved")
    public void newUserProfileCouldBeRetrieved() {
        stepDefinitions.getUserProfile();
    }

    @When("user is updated")
    public void userIsUpdated() {
        stepDefinitions.updateUser();
    }

    @And("user is deleted")
    public void userIsDeleted() {
        stepDefinitions.deleteUser();
    }

    @Then("user not existed")
    public void userNotExisted() {
        stepDefinitions.userNotExisted();
    }

}
