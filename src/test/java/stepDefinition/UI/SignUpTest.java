package stepDefinition.UI;

import api.ApiStepDef;
import context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.SignUpPage;
import utils.TestDataGeneratorUtils;

public class SignUpTest {

    private final SignUpPage signUpPage;
    private final ApiStepDef apiStepDef;

    public SignUpTest(SignUpPage signUpPage, ApiStepDef apiStepDef) {
        this.signUpPage = signUpPage;
        this.apiStepDef = apiStepDef;
    }

    @And("all fields are submitted with valid data")
    public void populateAddUserFields() {
        signUpPage.generateValidUserData();
        signUpPage.fillUserData();
    }

    @And("new user was created")
    public void newUserWasAdded() {
        signUpPage.assertHeader("Contact List");
        apiStepDef.loginUser();
        apiStepDef.getUserProfile();
    }

    @And("{string} field submitted with invalid data")
    public void fieldSubmittedWithInvalidData(String fieldName) {
        ScenarioContext scenarioContext = ScenarioContext.INSTANCE;
        signUpPage.generateValidUserData();
        switch (fieldName) {
            case "firstName":
                scenarioContext.setContext("firstName", TestDataGeneratorUtils.getNegativeRandomFirstName());
                break;
            case "lastName":
                scenarioContext.setContext("lastName", TestDataGeneratorUtils.getNegativeRandomLastName());
                break;
            case "email":
                scenarioContext.setContext("email", TestDataGeneratorUtils.getNegativeRandomEmail());
                break;
            case "password":
                scenarioContext.setContext("password", TestDataGeneratorUtils.getNegativeRandomPassword());
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
        apiStepDef.userNotExisted();
    }

}
