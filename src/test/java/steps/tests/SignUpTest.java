package steps.tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.SignUpPage;

public class SignUpTest {

    private final SignUpPage signUpPage;
    private final BasePage basePage;

    public SignUpTest(SignUpPage signUpPage, BasePage basePage) {
        this.signUpPage = signUpPage;
        this.basePage = basePage;
    }

    @And("[Submit] button is clicked")
    public void submitButtonIsClicked() throws InterruptedException {
        signUpPage.clickSubmitButton();
    }

    @Then("new user is created")
    public void newUserIsCreated() {
        basePage.assertHeader("Contact List", true);
    }

    @And("new user is not created")
    public void newUserIsNotCreated() {
        basePage.assertHeader("Contact List", false);
    }

    @And("all fields are submitted with valid data")
    public void populateAddUserFields() {
        signUpPage.populateUserFieldsWithData(true, true, true, true);
    }

    @When("{string} field submitted with invalid data")
    public void populateUserFieldsWithInvalidData(String fieldName) {
        boolean invalidFirstName = fieldName.equals("firstname");
        boolean invalidLastName = fieldName.equals("lastname");
        boolean invalidEmail = fieldName.equals("email");
        boolean invalidPassword = fieldName.equals("password");

        signUpPage.populateUserFieldsWithData(!invalidFirstName, !invalidLastName, !invalidEmail, !invalidPassword);
    }

    @Then("error is displaying")
    public void errorIsAppearing() {
        signUpPage.errorAlert();
    }

}
