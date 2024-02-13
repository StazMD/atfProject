package stepDefinition.UI;

import api.ApiStepDef;
import context.ScenarioContext;
import entity.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.SignUpPage;
import utils.TestDataGeneratorUtils;

public class SignUpTest {

    private final SignUpPage signUpPage;
    private final ApiStepDef apiStepDef;
    private static final Logger log = LoggerFactory.getLogger(SignUpTest.class);

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
        User user = extractUserData();
        signUpPage.userFields(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
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
        signUpPage.userFields(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    }

    @Then("error is displaying")
    public void errorIsAppearing() {
        String actualErrorMessage = signUpPage.errorText();
        log.info("Verifying that error message {} is presented", actualErrorMessage);
        String expectedErrorMessagePattern = "User validation failed: (firstName|lastName): Path `(firstName|lastName)` \\(`.*`\\) is longer than the maximum allowed length \\(20\\).|" +
                "User validation failed: email: Email is invalid|" +
                "User validation failed: password: Path `password` \\(`.*`\\) is shorter than the minimum allowed length \\(7\\).";
        Assertions.assertThat(actualErrorMessage).matches(expectedErrorMessagePattern);
    }

    @And("new user is not created")
    public void newUserIsNotCreated() {
        signUpPage.assertHeader("Add User");
        apiStepDef.userNotExisted();
    }

}
