package stepDefinition;

import config.PropertyReader;
import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.TestDataGeneratorUtils;


public class Hook {

    protected WebDriver driver;

    public Hook() {
        this.driver = WebDriverFactory.getDriver();
    }

    private final String homePageUrl = PropertyReader.getProperty("browser.homepage-url");
    private final Logger log = LogManager.getLogger(Hook.class);

    @Before()
    public void setUpCredentials() {
        log.info("Starting to generate user and contact credentials");
        new TestDataGeneratorUtils().generateUserCredentials();
        log.debug("User credentials generated successfully");
        new TestDataGeneratorUtils().generateContactCredentials();
        log.debug("Contact credentials generated successfully");
    }

    @Before("@UI")
    public void setUpWebDriver() {
//        log.info("Initializing WebDriver");
//        WebDriverFactory.getDriver();
//        log.debug("WebDriver initialized");
        driver.manage().window().maximize();
        log.debug("Browser window maximized");
        driver.get(homePageUrl);
        log.info("Navigated to the home page URL: {}", homePageUrl);
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
        log.debug("Test context cleared successfully");
    }
}
