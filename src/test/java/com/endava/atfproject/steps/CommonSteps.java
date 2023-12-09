package com.endava.atfproject.steps;

import com.endava.atfproject.pages.DashboardPage;
import com.endava.atfproject.pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class CommonSteps {

    @When("username and password are entering on login form")
    public void credentialsEnteredOnLoginForm() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithCredentials("admin","1Defectdojo@demo#appsec");
    }

    @Then("user successfully logged in and dashboard is opening")
    public void userSuccessfullyLoggedInAndDashboardIsOpening() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.asserUserMenuFromTopBarNavigation();
    }

}
