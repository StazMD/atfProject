package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.TestDataGeneratorUtils;


public class Hooks {

    protected WebDriver driver;

    public Hooks() {
        this.driver = WebDriverFactory.getDriver();
    }

    private final Logger log = LogManager.getLogger(Hooks.class);

    @Before()
    public void setUp() {
        log.info("Starting to generate user and contact credentials");
        new TestDataGeneratorUtils().generateUserCredentials();
        log.info("User credentials generated successfully");
        new TestDataGeneratorUtils().generateContactCredentials();
        log.info("Contact credentials generated successfully");
    }

    @After("@UI")
    public void tearDownWebDriver() {
        log.info("Terminating WebDriver");
        WebDriverFactory.quitDriver();
    }

    @After()
    public void tearDownContext() {
        log.info("Starting to clear test context");
        ScenarioContext.INSTANCE.clearContext();
    }
}
