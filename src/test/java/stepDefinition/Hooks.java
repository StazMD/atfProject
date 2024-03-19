package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.config.Configurator;
import utils.ReportPortalUtils;
import utils.TestDataGeneratorUtils;


public class Hooks {
    static {
//        ThreadContext.put("scenarioName", "");
//        ThreadContext.clearMap();
    }

    //    private static final DbTest dbDbTest = new DbTest();
    private static final TestDataGeneratorUtils generateUserDate = new TestDataGeneratorUtils();
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void setUpBeforeAll() {
        ReportPortalUtils.updatePropertiesTestLaunchName();
    }

    @Before(order = 1)
    public void beforeEach(Scenario scenario) {
        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9\\-_]+", "_");
        ThreadContext.put("scenarioName", scenarioName);
        Configurator.reconfigure();
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

//    @After("@UI")
//    public void afterEachUi() {
//        ReportPortalUtils.sendScreenshotToReportPortal();
//    }

    @AfterAll
    public static void tearDownAfterAll() {
        WebDriverFactory.quitDriver();
//        EntityManagerUtil.shutdownJpa();
    }
}
