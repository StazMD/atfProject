package steps.common;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.MainPage;

public class Hooks {

    private final MainPage mainPage;

    public Hooks() {
        this.mainPage = new MainPage();
    }

    @Before("@UI")
    public void setUp() {
        System.out.println("setUp driver");
        WebDriverFactory.setupDriver();
    }

    @Before("@login")
    public void LoginHook() {
        System.out.println("setUp driver on login");
        WebDriverFactory.setupDriver();
        mainPage.loginUser("TestUser@mail.com", "TestUserTestUser");
    }

    @After("@UI or @login")
    public void tearDown() {
        System.out.println("tearDown quitting driver");
        WebDriverFactory.quitDriver();
    }
}
