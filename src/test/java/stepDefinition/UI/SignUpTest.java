package stepDefinition.UI;

import api.ApiRequestsTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.SignUpPage;

public class SignUpTest {

    private final SignUpPage signUpPage;
    private final ApiRequestsTest apiRequestsTest;

    public SignUpTest(SignUpPage signUpPage, ApiRequestsTest apiRequestsTest) {
        this.signUpPage = signUpPage;
        this.apiRequestsTest = apiRequestsTest;
    }


    @And("all fields are submitted with valid data")
    public void populateAddUserFields() {
//        UserData userData = signUpPage.generateValidUserData();
//        signUpPage.generateValidUserData();
//        signUpPage.fillUserData(userData);
    }

    @And("new user was created")
    public void newUserWasAdded() {
//        UserData userData = signUpPage.generateValidUserData();
        signUpPage.assertHeader("Contact List");
//        apiTest.getUserProfileFromEndpoint(userData);
    }

    @And("{string} field submitted with invalid data")
    public void fieldSubmittedWithInvalidData(String fieldName) {
//        UserData userData = signUpPage.generateValidUserData();
        switch (fieldName) {
            case "firstname":
//                userData.setFirstName(TestDataGeneratorUtils.getNegativeRandomFirstName());
                break;
            case "lastname":
//                userData.setLastName(TestDataGeneratorUtils.getNegativeRandomLastName());
                break;
            case "email":
//                userData.setEmail(TestDataGeneratorUtils.getNegativeRandomEmail());
                break;
            case "password":
//                userData.setPassword(TestDataGeneratorUtils.getNegativeRandomPassword());
                break;
        }

//        signUpPage.fillUserData(userData);
    }

    @Then("error is displaying")
    public void errorIsAppearing() {
        signUpPage.errorAlert();
    }

    @And("new user is not created")
    public void newUserIsNotCreated() {
//        UserData userData = signUpPage.generateValidUserData();
        signUpPage.assertHeader("Add User");
//        apiTest.userNotExisted(userData);
    }

}
