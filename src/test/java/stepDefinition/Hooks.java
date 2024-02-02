package stepDefinition;

import config.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    //TODO initialize scenarioContext
    @Before("@UI")
    public void setUp() {
        System.out.println("setUp driver");
        WebDriverFactory.setupDriver();
    }

    @Before("@API")
    public void setUpApi() {
        System.out.println("API");
//        WebDriverFactory.setupDriver();
    }


    @After("@UI")
    public void tearDown() {
        System.out.println("tearDown quitting driver");
        WebDriverFactory.quitDriver();
    }
}
