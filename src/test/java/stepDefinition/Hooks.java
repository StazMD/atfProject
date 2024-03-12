package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.*;
import stepDefinition.db.dbTest;
import utils.EntityManagerUtil;
import utils.ReportPortalUtils;
import utils.TestDataGeneratorUtils;


public class Hooks {

    static dbTest dbDbTest = new dbTest();
    static TestDataGeneratorUtils generateUserDate = new TestDataGeneratorUtils();

    @BeforeAll
    public static void setUpBeforeAll() {
        dbDbTest.queryUpdateDatabase("DELETE FROM UserEntity");
        ReportPortalUtils.updatePropertiesTestLaunchName();
    }

    @Before(order = 1)
    public void beforeEachScenario(Scenario scenario) {
        ScenarioContext.INSTANCE.setScenario(scenario);
        generateUserDate.generateUserCredentials();
        generateUserDate.generateContactCredentials();
    }

    @Before("@DB")
    public void beforeEachDatabaseScenario() {
        EntityManagerUtil.getEntityManager();
        dbDbTest.valuesAddedToTheDb();
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
        EntityManagerUtil.shutdownJpa();
    }
}
