package com.endava.atfproject.steps;

import com.endava.atfproject.BaseRunner;
import com.endava.atfproject.pages.DashboardPage;
import com.endava.atfproject.pages.LoginPage;
import com.endava.atfproject.utils.PropertyReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonStep extends BaseRunner {
    PropertyReader config = new PropertyReader();
    String credentialsUsername = config.getProperty("login.username");
    String credentialsPassword = config.getProperty("login.password");

    @When("username and password are entering on login form")
    public void credentialsEnteredOnLoginForm() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithCredentials(credentialsUsername, credentialsPassword);
    }

    @Then("user successfully logged in and dashboard is opening")
    public void userSuccessfullyLoggedInAndDashboardIsOpening() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.asserUserMenuFromTopBarNavigation();
    }

}
