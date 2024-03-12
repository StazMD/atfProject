package stepDefinition.ui;

import config.WebDriverFactory;
import context.ScenarioContext;
import entity.UserEntity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.SignUpPage;
import stepDefinition.api.RestTest;
import stepDefinition.db.dbTest;
import utils.ExceptionUtils;
import utils.TestDataGeneratorUtils;

public class SignUpTest {

    protected WebDriver driver;
    private final SignUpPage signUpPage;
    private final RestTest restTest;
    private final dbTest dbTest;
    private static final Logger log = LogManager.getLogger(SignUpTest.class);

    ScenarioContext scenarioContext = ScenarioContext.INSTANCE;

    public SignUpTest(SignUpPage signUpPage, RestTest restTest, dbTest dbTest) {
        this.signUpPage = signUpPage;
        this.restTest = restTest;
        this.driver = WebDriverFactory.getDriver();
        this.dbTest = dbTest;
    }

    public UserEntity extractUserData() {
        try {
            return (UserEntity) scenarioContext.getContext("user");
        } catch (RuntimeException ex) {
            throw new ExceptionUtils("message");
        }
    }

    @And("all fields are submitted with valid data")
    public void populateAddUserFields() {
        UserEntity userEntity = extractUserData();
        signUpPage.userFields(userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), userEntity.getPassword());
    }

    @And("new user was created")
    public void newUserWasAdded() {
        signUpPage.assertHeader("Contact List");
        restTest.aRequestToLoginWithUserSDetailsWasSent();
        restTest.getUserDetails();
        dbTest.getUserEntityFromDatabase();
    }

    @And("{string} submitted with invalid data")
    public void fieldSubmittedWithInvalidData(String fieldName) {
        UserEntity userEntity = extractUserData();
        switch (fieldName) {
            case "firstName":
                userEntity.setFirstName(TestDataGeneratorUtils.getNegativeRandomFirstName());
                break;
            case "lastName":
                userEntity.setLastName(TestDataGeneratorUtils.getNegativeRandomLastName());
                break;
            case "email":
                userEntity.setEmail(TestDataGeneratorUtils.getNegativeRandomEmail());
                break;
            case "password":
                userEntity.setPassword(TestDataGeneratorUtils.getNegativeRandomPassword());
                break;
        }
        signUpPage.userFields(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }

    @Then("error is displaying")
    public void errorIsAppearing() {
        String errorMessage = signUpPage.errorText();
        log.info("Error message '{}' is presented", errorMessage);
    }

    @And("new user with {string} is not created")
    public void newUserIsNotCreated(String fieldName) {
        signUpPage.assertHeader("Add User");
        restTest.userNotAbleToLogin();
        dbTest.assertThatUserWasNotCreated(fieldName);
    }
}
