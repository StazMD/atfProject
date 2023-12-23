package com.endava.atfproject.steps;

import com.endava.atfproject.config.YamlReader;
import com.endava.atfproject.pages.DashboardPage;
import com.endava.atfproject.pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class CommonStep {

    private static final Logger logger = LogManager.getLogger(CommonStep.class);

    public CommonStep() {
        Map<String, YamlReader.User> users = YamlReader.getUsers();
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
