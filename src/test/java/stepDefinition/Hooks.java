package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestDataGeneratorUtils;


public class Hooks {

    Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before()
    public void setUpNewUserCredentials() {
        log.info("Generate credentials");
        new TestDataGeneratorUtils().generateUserCredentials();
        new TestDataGeneratorUtils().generateContactCredentials();
    }

    @Before("@UI")
    public void setUpWebDriver() {
        log.info("Setup webDriver");
        WebDriverFactory.setupDriver();
    }

    @After("@UI")
    public void tearDownWebDriver() {
        log.info("Tearing down webDriver");
        WebDriverFactory.quitDriver();
    }

    @After()
    public void tearDownContext() {
        log.info("Clearing context");
        ScenarioContext.INSTANCE.clearContext();
    }
}
