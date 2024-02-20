package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utils.ReportPortalUtils;
import utils.ScreenshotUtils;
import utils.TestDataGeneratorUtils;


public class Hooks {

    @BeforeAll
    public static void setUpBeforeAll() {
        ReportPortalUtils.updatePropertiesTestLaunchName();
    }

    @Before
    public void setUp() {
        new TestDataGeneratorUtils().generateUserCredentials();
        new TestDataGeneratorUtils().generateContactCredentials();
    }

    @Before("@UI")
    public void beforeScenario(Scenario scenario) {
        ScenarioContext.setScenario(scenario);
    }

    @After
    public void tearDownContext() {
        ScenarioContext.INSTANCE.clearContext();
    }

    @After("@UI")
    public void afterEachScenario() {
        ScreenshotUtils.takeScenarioScreenshot();
        WebDriverFactory.quitDriver();
    }

}
