package com.endava.atfproject.steps;

import com.endava.atfproject.pages.DashboardPage;
import com.endava.atfproject.pages.LoginPage;
import com.endava.atfproject.utils.PropertyReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonStep {

    private final String credentialsUsername;
    private final String credentialsPassword;

    public CommonStep() {
        PropertyReader config = new PropertyReader();
        this.credentialsUsername = config.getProperty("login.username");
        this.credentialsPassword = config.getProperty("login.password");
    }

    @When("username and password are entering on login form")
    public void enterCredentialsOnLoginForm() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithCredentials(credentialsUsername, credentialsPassword);
    }

    @Then("user successfully logged in")
    public void verifyUserSuccessfullyLoggedIn() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.assertUserSuccessfullyLoggedIn();
    }

}
