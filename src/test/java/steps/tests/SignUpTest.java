package steps.tests;

import api.ApiTest;
import api.UserData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.BasePage;
import pages.SignUpPage;

public class SignUpTest {

    private final SignUpPage signUpPage;
    private final BasePage basePage;
    private final ApiTest apiTest;

    public SignUpTest(SignUpPage signUpPage, BasePage basePage, ApiTest apiTest) {
        this.signUpPage = signUpPage;
        this.basePage = basePage;
        this.apiTest = apiTest;
    }

    UserData userData = new SignUpPage().generateValidUserData();
    UserData invalidUserData = new SignUpPage().generateInvalidData(true, true, true, true);

    @And("[Submit] button is clicked")
    public void submitButtonIsClicked() throws InterruptedException {
        signUpPage.clickSubmitButton();
    }

    @And("all fields are submitted with valid data")
    public void populateAddUserFields() {
        signUpPage.fillUserData(userData);
    }

    @And("new user was created")
    public void newUserWasAdded() {
        basePage.assertHeader("Contact List", true);
        apiTest.getUserProfile(userData);
    }

    @And("{string} field submitted with invalid data")
    public void fieldSubmittedWithInvalidData(String fieldName) {
        boolean invalidFirstName = fieldName.equals("firstname");
        boolean invalidLastName = fieldName.equals("lastname");
        boolean invalidEmail = fieldName.equals("email");
        boolean invalidPassword = fieldName.equals("password");

        signUpPage.generateInvalidData(invalidFirstName, invalidLastName, invalidEmail, invalidPassword);
//        signUpPage.fillUserData(invalidUserData);
    }

    @Then("error is displaying")
    public void errorIsAppearing() {
        signUpPage.errorAlert();
    }

    @And("new user is not created")
    public void newUserIsNotCreated() {
        basePage.assertHeader("Contact List", false);
        apiTest.getUserProfile(userData);
    }

}
