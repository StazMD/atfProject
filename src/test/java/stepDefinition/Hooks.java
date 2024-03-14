package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stepDefinition.db.DbTest;
import utils.EntityManagerUtil;
import utils.ReportPortalUtils;
import utils.TestDataGeneratorUtils;


public class Hooks {

    static DbTest dbDbTest = new DbTest();
    static TestDataGeneratorUtils generateUserDate = new TestDataGeneratorUtils();
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void setUpBeforeAll() {
        ReportPortalUtils.updatePropertiesTestLaunchName();
    }

    @Before(order = 1)
    public void beforeEachScenario(Scenario scenario) {
        ScenarioContext.INSTANCE.setScenario(scenario);
        log.info("Scenario '{}' started.", scenario.getName());
        generateUserDate.generateUserCredentials();
        generateUserDate.generateContactCredentials();
    }

    @Before("@DB")
    public void beforeEachDatabaseScenario() {
        dbDbTest.queryUpdateDatabase("DELETE FROM UserEntity");
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
