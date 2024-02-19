package stepDefinition.UI;

import api.ApiStepDef;
import config.WebDriverFactory;
import context.ScenarioContext;
import entity.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.SignUpPage;
import utils.TestDataGeneratorUtils;

import java.io.File;
import java.io.IOException;

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
            throw new RuntimeException("message", ex);
        }
    }

    @And("all fields are submitted with valid data")
    public void populateAddUserFields() {
        User user = extractUserData();
        signUpPage.userFields(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    }

    @And("new user was created")
    public void newUserWasAdded() throws IOException {
        try {
            signUpPage.assertHeader("Coвntact List");
        } catch (Exception ex) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/Screenshots/screenshot.png"));
        }
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
        String expectedErrorMessagePattern = "User validation failed: (firstName|lastName): Path `(firstName|lastName)` \\(`.*`\\) is longer than the maximum allowed length \\(20\\).|" + "User validation failed: email: Email is invalid|" + "User validation failed: password: Path `password` \\(`.*`\\) is shorter than the minimum allowed length \\(7\\).";
    }

    @And("new user is not created")
    public void newUserIsNotCreated() {
        signUpPage.assertHeader("Add User");
        apiStepDef.userNotExisted();
    }

}
