package stepDefinition.UI;

import api.StepDefinitions;
import context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.SignUpPage;
import utils.TestDataGeneratorUtils;

import java.util.Map;

public class SignUpTest {

    private final SignUpPage signUpPage;
    private final StepDefinitions stepDefinitions;

    public SignUpTest(SignUpPage signUpPage, StepDefinitions stepDefinitions) {
        this.signUpPage = signUpPage;
        this.stepDefinitions = stepDefinitions;
    }

    @And("all fields are submitted with valid data")
    public void populateAddUserFields() {
        signUpPage.generateValidUserData();
        signUpPage.fillUserData();
    }

    @And("new user was created")
    public void newUserWasAdded() {
        signUpPage.assertHeader("Contact List");
        stepDefinitions.loginUser();
        stepDefinitions.getUserProfile();
    }

    @And("{string} field submitted with invalid data")
    public void fieldSubmittedWithInvalidData(String fieldName) {
        Map<String, Object> userDetails = ScenarioContext.getScenarioData();
        signUpPage.generateValidUserData();
        switch (fieldName) {
            case "firstName":
                userDetails.put("firstName", TestDataGeneratorUtils.getNegativeRandomFirstName());
                break;
            case "lastName":
                userDetails.put("lastName", TestDataGeneratorUtils.getNegativeRandomLastName());
                break;
            case "email":
                userDetails.put("email", TestDataGeneratorUtils.getNegativeRandomEmail());
                break;
            case "password":
                userDetails.put("password", TestDataGeneratorUtils.getNegativeRandomPassword());
                break;
        }

        signUpPage.fillUserData();
    }

    @Then("error is displaying")
    public void errorIsAppearing() {
        signUpPage.errorAlert();
    }

    @And("new user is not created")
    public void newUserIsNotCreated() {
        signUpPage.assertHeader("Add User");
        stepDefinitions.userNotExisted();
    }

}
