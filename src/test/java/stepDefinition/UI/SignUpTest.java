package stepDefinition.UI;

import api.ApiStepDef;
import config.WebDriverFactory;
import context.ScenarioContext;
import entity.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.SignUpPage;
import utils.ExceptionUtils;
import utils.TestDataGeneratorUtils;

public class SignUpTest {

    protected WebDriver driver;
    private final SignUpPage signUpPage;
    private final ApiStepDef apiStepDef;
    private static final Logger log = LogManager.getLogger(SignUpTest.class);

    ScenarioContext scenarioContext = ScenarioContext.INSTANCE;

    public SignUpTest(SignUpPage signUpPage, ApiStepDef apiStepDef) {
        this.signUpPage = signUpPage;
        this.apiStepDef = apiStepDef;
        this.driver = WebDriverFactory.getDriver();
    }

    public User extractUserData() {
        try {
            return (User) scenarioContext.getContext("user");
        } catch (RuntimeException ex) {
            throw new ExceptionUtils("message");
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

    @And("{string} submitted with invalid data")
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
        String errorMessage = signUpPage.errorText();
        log.info("Verifying that error message {} is presented", errorMessage);
    }

    @And("new user is not created")
    public void newUserIsNotCreated() {
        signUpPage.assertHeader("Add User");
        apiStepDef.userNotExisted();
    }
}
