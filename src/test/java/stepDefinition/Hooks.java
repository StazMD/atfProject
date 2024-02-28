package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import db.util.JPAUtil;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ReportPortalUtils;
import utils.TestDataGeneratorUtils;


public class Hooks {

    private static final Logger log = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void setUpBeforeAll() {
        ReportPortalUtils.updatePropertiesTestLaunchName();
    }

    @Before("@UI")
    public void setUp(Scenario scenario) {
        ScenarioContext.INSTANCE.setScenario(scenario);
        new TestDataGeneratorUtils().generateUserCredentials();
        new TestDataGeneratorUtils().generateContactCredentials();
    }

    @Before("@DB")
    public void setUpDatabase() {
        try {
            JPAUtil.getEntityManagerFactory().createEntityManager();
            log.info("EntityManager was successfully initialized.");
        } catch (Exception e) {
            log.error("Error initializing EntityManager: " + e.getMessage());
            throw e;
        }
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

    @AfterAll
    public static void afterAll() {
        JPAUtil.shutdownJpa();
    }
}
