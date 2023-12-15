package com.endava.atfproject.steps;

import com.endava.atfproject.config.PropertyReader;
import com.endava.atfproject.pages.DashboardPage;
import com.endava.atfproject.pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonStep {

    private final String credentialsAdminUsername;
    private final String credentialsAdminPassword;
    private final String credentialsUserUsername;
    private final String credentialsUserPassword;
    private static final Logger logger = LogManager.getLogger(CommonStep.class);


    public CommonStep() {
        this.credentialsAdminUsername = PropertyReader.getProperty("login.admin.username");
        this.credentialsAdminPassword = PropertyReader.getProperty("login.admin.password");
        this.credentialsUserUsername = PropertyReader.getProperty("login.user.username");
        this.credentialsUserPassword = PropertyReader.getProperty("login.user.password");
    }

    @When("user {string} logged in")
    public void userLoggedIn(String user) {
        LoginPage loginPage = new LoginPage();
        if (user.equals("admin")) {
            loginPage.loginWithCredentials(credentialsAdminUsername, credentialsAdminPassword);
        } else if (user.equals("user")) {
            loginPage.loginWithCredentials(credentialsUserUsername, credentialsUserPassword);
        } else {
            logger.warn("Incorrect user {}!", user);
        }
    }

    @Then("user successfully logged in")
    public void verifyUserSuccessfullyLoggedIn() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.assertUserSuccessfullyLoggedIn();
    }
}
