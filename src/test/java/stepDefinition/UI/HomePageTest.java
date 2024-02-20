package stepDefinition.UI;

import config.PropertyReader;
import config.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class HomePageTest {

    protected WebDriver driver;
    private final String homePageUrl = PropertyReader.getProperty("browser.homepage-url");
    private final Logger log = LogManager.getLogger(HomePageTest.class);
    private final HomePage homePage;

    public HomePageTest(HomePage homePage) {
        this.driver = WebDriverFactory.getDriver();
        this.homePage = homePage;
    }

    @Given("home page is opened")
    public void mainPageIsOpened() {
        driver.get(homePageUrl);
        try {
            homePage.assertHeader("Contact List App");
        } catch (Exception ex) {
            String exceptionMessage = "Missing expected header text";
//            ReportPortalUtils.sendScreenshotToReportPortal(exceptionMessage);
            throw new RuntimeException(exceptionMessage, ex);
        }
        log.info("Navigated to the home page URL: {}", homePageUrl);
    }

    @When("adding user page opening")
    public void openAddUserPage() {
        homePage.openSignUpPage();
    }

}
