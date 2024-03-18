package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ReportPortalUtils;
import utils.TestDataGeneratorUtils;


public class Hooks {

    //    private static final DbTest dbDbTest = new DbTest();
    private static final TestDataGeneratorUtils generateUserDate = new TestDataGeneratorUtils();
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void setUpBeforeAll() {
        ReportPortalUtils.updatePropertiesTestLaunchName();
    }

    @Before(order = 1)
    public void beforeEach(Scenario scenario) {
        ScenarioContext.INSTANCE.setScenario(scenario);
        generateUserDate.generateUserCredentials();
        generateUserDate.generateContactCredentials();
        log.info("Scenario '{}' started.", scenario.getName());
    }

//    @Before("@DB")
//    public void beforeEachDatabase() {
//        dbDbTest.queryUpdateDatabase("DELETE FROM UserEntity");
//        EntityManagerUtil.getEntityManager();
//        dbDbTest.valuesAddedToTheDb();
//    }

    @After
    public void afterEach() {
        ScenarioContext.INSTANCE.clearContext();
    }

    @After("@UI")
    public void afterEachUi() {
        ReportPortalUtils.sendScreenshotToReportPortal();
    }

    @AfterAll
    public static void tearDownAfterAll() {
        WebDriverFactory.quitDriver();
//        EntityManagerUtil.shutdownJpa();
    }
}
