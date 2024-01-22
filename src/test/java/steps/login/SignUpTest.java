package steps.login;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactListPage;
import pages.SignUpPage;

public class SignUpTest {

    private final SignUpPage signUpPage;
    private final ContactListPage contactListPage;

    public SignUpTest(SignUpPage signUpPage, ContactListPage contactListPage) {
        this.signUpPage = signUpPage;
        this.contactListPage = contactListPage;
    }

    @And("adding user page opening")
    public void addingUserPageOpening() {
        signUpPage.assertSignAddPage();
    }

    @And("[Submit] button is clicked")
    public void submitButtonIsClicked() {
        signUpPage.clickSubmitButton();
    }

    @Then("new user is created")
    public void newUserIsCreated() {
        contactListPage.assertHeader("Contact List", true);
    }

    @And("new user is not created")
    public void newUserIsNotCreated() {
        contactListPage.assertHeader("Contact List", false);
    }

    @And("all fields are populated with correct data")
    public void populateAddUserFields() {
        signUpPage.populateUserFieldsWithData(true, true, true, true);
    }

    @When("{string} field populated with invalid data")
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
