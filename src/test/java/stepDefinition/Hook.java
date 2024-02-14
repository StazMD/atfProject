package stepDefinition;

import config.PropertyReader;
import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestDataGeneratorUtils;


public class Hook {

    private final String homePageUrl = PropertyReader.getProperty("browser.homepage-url");
    private final Logger log = LoggerFactory.getLogger(Hook.class);

    @Before()
    public void setUpCredentials() {
        log.info("Generate credentials");
        new TestDataGeneratorUtils().generateUserCredentials();
        new TestDataGeneratorUtils().generateContactCredentials();
    }

    @Before("@UI")
    public void setUpWebDriver() {
        log.info("Setup webDriver");
        WebDriverFactory.getDriver();
        WebDriverFactory.getDriver().manage().window().maximize();
        WebDriverFactory.getDriver().get(homePageUrl);
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
