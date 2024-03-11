package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import db.TestData;
import io.cucumber.java.*;
import utils.JPAUtil;
import utils.ReportPortalUtils;
import utils.TestDataGeneratorUtils;


public class Hooks {

    static TestData dbTestData = new TestData();
    static TestDataGeneratorUtils generateUserDate = new TestDataGeneratorUtils();

    @BeforeAll
    public static void setUpBeforeAll() {
        dbTestData.queryUpdateDatabase("DELETE FROM UserEntity");
        ReportPortalUtils.updatePropertiesTestLaunchName();
    }

    @Before()
    public void beforeEachScenario(Scenario scenario) {
        ScenarioContext.INSTANCE.setScenario(scenario);
        generateUserDate.generateUserCredentials();
        generateUserDate.generateContactCredentials();
    }

    @Before("@DB")
    public void beforeEachDatabaseScenario() {
        JPAUtil.getEntityManager();
        dbTestData.valuesAddedToTheDb();
    }

    @After
    public void tearDownContext() {
        ScenarioContext.INSTANCE.clearContext();
    }

    @After("@UI")
    public void afterEachUIScenario() {
        ReportPortalUtils.sendScreenshotToReportPortal();
    }

    @AfterAll
    public static void tearDownAfterAll() {
        WebDriverFactory.quitDriver();
        ScenarioContext.INSTANCE.clearContext();
        JPAUtil.shutdownJpa();
    }
}
