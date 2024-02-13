package stepDefinition.API;

import api.ApiStepDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RestTest {

    ApiStepDef apiStepDef = new ApiStepDef();

    @When("new user is created")
    public void newUserIsCreated() {
        apiStepDef.createUser();
    }

    @Then("new user is able to login")
    public void newUserIsAbleToLogin() {
        apiStepDef.loginUser();
    }

    @And("new user profile could be retrieved")
    public void newUserProfileCouldBeRetrieved() {
        apiStepDef.getUserProfile();
    }

    @When("user is updated")
    public void userIsUpdated() {
        apiStepDef.updateUser();
    }

    @And("user is deleted")
    public void userIsDeleted() {
        apiStepDef.deleteUser();
    }

    @Then("user not existed")
    public void userNotExisted() {
        apiStepDef.userNotExisted();
    }

}
