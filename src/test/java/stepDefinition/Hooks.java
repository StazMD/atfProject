package stepDefinition;

import config.WebDriverFactory;
import context.ScenarioContext;
import io.cucumber.java.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.ReportPortalUtils;
import utils.TestDataGeneratorUtils;


public class Hooks {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    public static void setUpBeforeAll() {
        ReportPortalUtils.updatePropertiesTestLaunchName();
        emf = Persistence.createEntityManagerFactory("testPU");
        em = emf.createEntityManager();
    }

    @Before("@UI")
    public void setUp(Scenario scenario) {
        ScenarioContext.INSTANCE.setScenario(scenario);
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

    @AfterAll
    public static void afterAll() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
