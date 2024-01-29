package steps.common;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@UI")
    public void setUp() {
        System.out.println("setUp driver");
        WebDriverFactory.setupDriver();
    }

    @After("@UI")
    public void tearDown() {
        System.out.println("tearDown quitting driver");
        WebDriverFactory.quitDriver();
    }
}
