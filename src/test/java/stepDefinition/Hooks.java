package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.TestDataGeneratorUtils;


public class Hooks {

    private final Logger log = LogManager.getLogger(Hooks.class);

    @Before()
    public void setUp() {
        new TestDataGeneratorUtils().generateUserCredentials();
        new TestDataGeneratorUtils().generateContactCredentials();
    }

    @After("@UI")
    public void tearDownWebDriver() {
        WebDriverFactory.quitDriver();
    }

    @After()
    public void tearDownContext() {
        ScenarioContext.INSTANCE.clearContext();
    }
}
