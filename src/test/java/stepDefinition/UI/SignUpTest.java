package stepDefinition.UI;

import api.ApiStepDef;
import context.ScenarioContext;
import entity.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.SignUpPage;
import utils.TestDataGeneratorUtils;

public class SignUpTest {

    private final SignUpPage signUpPage;
    private final ApiStepDef apiStepDef;

    ScenarioContext scenarioContext = ScenarioContext.INSTANCE;

    public SignUpTest(SignUpPage signUpPage, ApiStepDef apiStepDef) {
        this.signUpPage = signUpPage;
        this.apiStepDef = apiStepDef;
    }

    public User extractUserData() {
        try {
            return (User) scenarioContext.getContext("user");
        } catch (RuntimeException ex) {
            throw new RuntimeException("message", ex);
        }
    }

    @And("all fields are submitted with valid data")
    public void populateAddUserFields() {
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
        User user = extractUserData();
        switch (fieldName) {
            case "firstName":
                user.setFirstName(TestDataGeneratorUtils.getNegativeRandomFirstName());
                break;
            case "lastName":
                user.setLastName(TestDataGeneratorUtils.getNegativeRandomLastName());
                break;
            case "email":
                user.setEmail(TestDataGeneratorUtils.getNegativeRandomEmail());
                break;
            case "password":
                user.setPassword(TestDataGeneratorUtils.getNegativeRandomPassword());
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
