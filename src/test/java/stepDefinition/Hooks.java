package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before("@UI")
    public void setUp() {
        System.out.println("Setting Up UI");
        WebDriverFactory.setupDriver();
    }

    @Before("@API")
    public void setUpApi() {
        System.out.println("Setting Up API");
        ScenarioContext.getScenarioData();
    }

    @After("@UI")
    public void tearDown() {
        System.out.println("Tearing down UI");
        WebDriverFactory.quitDriver();
    }

    @After("@API")
    public void tearDownApi() {
        System.out.println("Tearing down API");
        ScenarioContext.getScenarioData().clear();
    }
}
