package steps.tests;

import api.ApiTest;
import api.UserData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.BasePage;
import pages.SignUpPage;
import utils.TestDataGeneratorUtils;

public class SignUpTest {

    private final SignUpPage signUpPage;
    private final BasePage basePage;
    private final ApiTest apiTest;

    public SignUpTest(SignUpPage signUpPage, BasePage basePage, ApiTest apiTest) {
        this.signUpPage = signUpPage;
        this.basePage = basePage;
        this.apiTest = apiTest;
    }

    UserData userData = SignUpPage.generateValidUserData();

    @And("all fields are submitted with valid data")
    public void populateAddUserFields() {
        signUpPage.fillUserData(userData);
    }

    @And("new user was created")
    public void newUserWasAdded() {
        basePage.assertHeader("Contact List");
        apiTest.getUserProfile(userData);
    }

    @And("{string} field submitted with invalid data")
    public void fieldSubmittedWithInvalidData(String fieldName) {
        switch (fieldName) {
            case "firstname":
                userData.setFirstName(TestDataGeneratorUtils.getNegativeRandomFirstName());
                break;
            case "lastname":
                userData.setLastName(TestDataGeneratorUtils.getNegativeRandomLastName());
                break;
            case "email":
                userData.setEmail(TestDataGeneratorUtils.getNegativeRandomEmail());
                break;
            case "password":
                userData.setPassword(TestDataGeneratorUtils.getNegativeRandomPassword());
                break;
        }

        signUpPage.fillUserData(userData);
    }

    @Then("error is displaying")
    public void errorIsAppearing() {
        signUpPage.errorAlert();
    }

    @And("new user is not created")
    public void newUserIsNotCreated() {
        basePage.assertHeader("Add User");
        apiTest.userNotExisted(userData);
    }

}
