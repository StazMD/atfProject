package stepDefinition.API;

import api.Assertions;
import api.Requests;
import context.ScenarioContext;
import entity.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ExceptionUtils;
import utils.TestDataGeneratorUtils;

import java.util.List;
import java.util.Map;

public class RestTest {

    private final Assertions assertions = new Assertions();
    private static final ScenarioContext scenarioContext;
    private static final Logger log = LogManager.getLogger(RestTest.class);

    //TODO why static in {}?
    static {
        scenarioContext = ScenarioContext.INSTANCE;
    }

    public User extractUserData() {
        try {
            return (User) scenarioContext.getContext("user");
        } catch (RuntimeException ex) {
            throw new ExceptionUtils("User context failed to extract");
        }
    }

//    @When("new user is created")
//    public void newUserIsCreated() {
//        apiStepDef.createUser();
//    }
//
//    @Then("new user is able to login")
//    public void newUserIsAbleToLogin() {
//        apiStepDef.loginUser();
//    }
//
//    @And("new user profile could be retrieved")
//    public void newUserProfileCouldBeRetrieved() {
//        apiStepDef.getUserProfile();
//    }

//    @When("user is updated")
//    public void userIsUpdated() {
//        apiStepDef.updateUser();
//    }
//
//    @And("user is deleted")
//    public void userIsDeleted() {
//        apiStepDef.deleteUser();
//    }
//
//    @Then("user not existed")
//    public void userNotExisted() {
//        apiStepDef.userNotExisted();
//    }

    @Given("valid user data")
    public void validUserData(DataTable userData) {
        log.info("Starting processing user data from DataTable.");

        List<Map<String, String>> userCredentials = userData.asMaps(String.class, String.class);
        for (Map<String, String> userCredential : userCredentials) {
            User user = new User();

            if (userCredential.get("firstName") == null || userCredential.get("lastName") == null || userCredential.get("email") == null || userCredential.get("password") == null) {
                throw new ExceptionUtils("Credentials should not be empty");
            }

            String firstName = userCredential.get("firstName");
            user.setFirstName(firstName.equals("[randomFirstName]") ? TestDataGeneratorUtils.getRandomFirstName() : firstName);
            log.debug("Set firstName: " + user.getFirstName());

            String lastName = userCredential.get("lastName");
            user.setLastName(lastName.equals("[randomLastName]") ? TestDataGeneratorUtils.getRandomLastName() : lastName);
            log.debug("Set lastName: " + user.getLastName());

            String email = userCredential.get("email");
            user.setEmail(email.equals("[randomEmail]") ? TestDataGeneratorUtils.getRandomEmail() : email);
            log.debug("Set email: " + user.getEmail());

            String password = userCredential.get("password");
            user.setPassword(password.equals("[randomPassword]") ? TestDataGeneratorUtils.getRandomPassword() : password);
            log.debug("Set password: " + user.getPassword());

            ScenarioContext.INSTANCE.setContext("user", user);
            log.debug("Set user data to scenario context");
        }

        log.info("Finished processing user data.");
    }


    @When("a request to create new user was sent")
    public void aRequestToCreateNewUserWasSent() {
        User user = extractUserData();
        String requestBody = String.format("{\"firstName\": \"%s\",\"lastName\": \"%s\",\"email\": \"%s\",\"password\": \"%s\"}", user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());

        Response response = Requests.postRequest("/users", requestBody, 201);
        String token = response.jsonPath().getString("token");
        scenarioContext.setContext("token", token);
    }

    @Then("user was successfully created")
    public void userWasSuccessfullyCreated() {
        Response response = Requests.getRequest("/users/me", 200);
        assertions.assertGetUserProfile(response);
    }

    @When("a request to update the user's details was sent")
    public void aRequestToUpdateTheUserSDetailsWasSent(DataTable userData) {

        List<Map<String, String>> userCredentials = userData.asMaps(String.class, String.class);
        for (Map<String, String> userCredential : userCredentials) {
            User user = new User();

            if (userCredential.get("firstName") == null || userCredential.get("lastName") == null || userCredential.get("email") == null || userCredential.get("password") == null) {
                throw new ExceptionUtils("Credentials should not be empty");
            }

            String firstName = userCredential.get("firstName");
            user.setFirstName(firstName);
            log.debug("Set firstName: " + user.getFirstName());

            String lastName = userCredential.get("lastName");
            user.setLastName(lastName);
            log.debug("Set lastName: " + user.getLastName());

            String email = userCredential.get("email");
            user.setEmail(email);
            log.debug("Set email: " + user.getEmail());

            String password = userCredential.get("password");
            user.setPassword(password);
            log.debug("Set password: " + user.getPassword());

            ScenarioContext.INSTANCE.setContext("user", user);
            log.debug("Set user data to scenario context");
        }
        User user = extractUserData();

        String requestBody = String.format("{\"firstName\": \"%s\",\"lastName\": \"%s\",\"email\": \"%s\",\"password\": \"%s\"}", user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());

        Response response = Requests.patchRequest("/users/me", requestBody, 200);

    }

    @Then("the user's details was successfully updated")
    public void theUserSDetailsWasSuccessfullyUpdated() {
        Response response = Requests.getRequest("/users/me", 200);
        assertions.assertGetUserProfile(response);
    }

    @And("a request to login with user's details was sent")
    public void aRequestToLoginWithUserSDetailsWasSent() {
        User user = extractUserData();

        String requestBody = String.format("{\"email\": \"%s\",\"password\": \"%s\"}", user.getEmail(), user.getPassword());

        Response response = Requests.postRequest("/users/login", requestBody, 200);

        String token = response.jsonPath().getString("token");
        scenarioContext.setContext("token", token);
    }
}

