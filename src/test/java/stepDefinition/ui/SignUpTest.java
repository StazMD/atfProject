package stepDefinition.ui;

import context.ScenarioContext;
import entity.UserEntity;
import enums.Entity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.SignUpPage;
import stepDefinition.api.RestTest;
import stepDefinition.db.DbTest;
import utils.CustomException;
import utils.TestDataGeneratorUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpTest {

    private final SignUpPage signUpPage;
    private final RestTest restTest;
    private final DbTest dbTest;
    private static final Logger log = LogManager.getLogger(SignUpTest.class);

    ScenarioContext scenarioContext = ScenarioContext.INSTANCE;

    public SignUpTest(SignUpPage signUpPage, RestTest restTest, DbTest dbTest) {
        this.signUpPage = signUpPage;
        this.restTest = restTest;
        this.dbTest = dbTest;
    }

    public UserEntity extractUserData() {
        try {
            return (UserEntity) scenarioContext.getContext(Entity.USER);
        } catch (RuntimeException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    @And("all fields are submitted with valid data")
    public void populateAddUserFields() {
        log.info("Populating sign up page with valid user data");
        UserEntity userEntity = extractUserData();
        signUpPage.userFields(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
        log.info("Successfully populated sign up fields with user data: {}, {}, {}, {}",
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                "password[PROTECTED]");
    }

    @And("new user was created")
    public void newUserWasAdded() {
        log.info("Verifying new user creation");
        signUpPage.assertHeader("Contact List");
        log.info("Contact List header verified");

        log.info("Attempting to login with new user's details");
        restTest.aRequestToLoginWithUserSDetailsWasSent();

        log.info("Verifying user's details");
        restTest.getUserDetails();

        log.info("Checking new user creation in database");
        dbTest.getUserEntityFromDatabase();

        log.info("New user verification complete");
    }

    @And("{string} submitted with invalid data")
    public void fieldSubmittedWithInvalidData(String fieldName) {
        log.info("Attempting to submit invalid data for: {}", fieldName);
        UserEntity userEntity = new UserEntity(extractUserData());
        switch (fieldName) {
            case "firstName" -> userEntity.setFirstName(TestDataGeneratorUtils.getNegativeRandomFirstName());
            case "lastName" -> userEntity.setLastName(TestDataGeneratorUtils.getNegativeRandomLastName());
            case "email" -> userEntity.setEmail(TestDataGeneratorUtils.getNegativeRandomEmail());
            case "password" -> userEntity.setPassword(TestDataGeneratorUtils.getNegativeRandomPassword());
        }

        signUpPage.userFields(userEntity);
//        signUpPage.userFields(
//                userEntity.getFirstName(),
//                userEntity.getLastName(),
//                userEntity.getEmail(),
//                userEntity.getPassword()
//        );

        scenarioContext.setContext(Entity.USER, userEntity);
        log.info("Submitted invalid data for {}", fieldName);
    }

    @And("error for {string} is displaying")
    public void errorIsAppearing(String fieldName) {
        UserEntity userEntity = extractUserData();
        String errorMessage = signUpPage.errorText();
        Map<String, String> fieldErrorMessages = Map.of(
                "firstName", String.format("User validation failed: firstName: Path `firstName` (`%s`) is longer than the maximum allowed length (20).", userEntity.getFirstName()),
                "lastName", String.format("User validation failed: lastName: Path `lastName` (`%s`) is longer than the maximum allowed length (20).", userEntity.getLastName()),
                "email", "User validation failed: email: Email is invalid",
                "password", String.format("User validation failed: password: Path `password` (`%s`) is shorter than the minimum allowed length (7).", userEntity.getPassword())
        );
        String expectedErrorMessage = fieldErrorMessages.getOrDefault(fieldName, "Unexpected field name");
        assertThat(errorMessage).isEqualTo(expectedErrorMessage);
        log.info("Error message '{}' is presented for field '{}'", errorMessage, fieldName);
    }

    @Then("new user with {string} is not created")
    public void newUserIsNotCreated(String fieldName) {
        try {
            log.info("Verifying that a new user with invalid {} is not created", fieldName);

            signUpPage.assertHeader("Add User");
            log.info("Confirmed user remains on the 'Add User' page, indicating failure to create new user with invalid {}", fieldName);

            restTest.userNotAbleToLogin();
            log.info("Login attempt with {} failed as expected, indicating no such user was created", fieldName);

            dbTest.assertThatUserWasNotCreated(fieldName);
            log.info("Database verification passed: no new user was created with invalid {}", fieldName);

        } catch (RuntimeException ex) {
            log.error("Verification failed for non-creation of user with invalid {}: {}", fieldName, ex.getMessage());
            throw new CustomException("Error during verification of non-creation of user with invalid " + fieldName, true, ex);
        }
    }
}
