package com.endava.atfproject.steps;

import com.endava.atfproject.pages.DashboardPage;
import com.endava.atfproject.pages.LoginPage;
import com.endava.atfproject.utils.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class CommonSteps {
    LoginPage loginPage = new LoginPage();
    ConfigReader configReader = new ConfigReader();

    @When("username and password are entering on login form")
    public void usernameAndPasswordAreEnteringOnLoginForm() {
        Map<String, String> credentials = configReader.getLoginCredentials();
        loginPage.fillLoginAndPassword(credentials.get("username"), credentials.get("password"));
    }

    @Then("user successfully logged in and dashboard is opening")
    public void userSuccessfullyLoggedInAndDashboardIsOpening() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.asserUserMenuFromTopBarNavigation();
    }

}
