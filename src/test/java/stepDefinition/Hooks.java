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
    public void setUpBeforeAll() {
        ReportPortalUtils.updatePropertiesTestLaunchName();
    }

    @Before
    public void setUp() {
        new TestDataGeneratorUtils().generateUserCredentials();
        new TestDataGeneratorUtils().generateContactCredentials();
    }

    @After
    public void tearDownContext() {
        ScenarioContext.INSTANCE.clearContext();
    }

    @After("@UI")
    public void afterEachScenario() {
        ReportPortalUtils.sendScreenshotToReportPortal();
        WebDriverFactory.quitDriver();
    }
}
