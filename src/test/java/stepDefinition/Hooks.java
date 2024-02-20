package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import utils.ReportPortalUtils;
import utils.TestDataGeneratorUtils;


public class Hooks {

    @BeforeAll
    public static void setUpBeforeAll() {
        ReportPortalUtils.updateLaunchName();
    }

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
