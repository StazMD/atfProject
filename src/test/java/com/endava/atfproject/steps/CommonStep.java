package com.endava.atfproject.steps;

import com.endava.atfproject.config.PropertyReader;
import com.endava.atfproject.pages.DashboardPage;
import com.endava.atfproject.pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonStep {

    public volatile static PropertyReader getInstance;
    private final String credentialsUsername;
    private final String credentialsPassword;

    public CommonStep() {
        this.getInstance = getInstance;
        this.credentialsUsername = getInstance.getProperty("login.username");
        this.credentialsPassword = getInstance.getProperty("login.password");
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
