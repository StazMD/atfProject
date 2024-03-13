package stepDefinition.ui;

import config.PropertyReader;
import config.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.ExceptionUtils;

public class HomePageTest {

    private final WebDriver driver;
    private final String homePageUrl = PropertyReader.getProperty("browser.homepage-url");
    private final Logger log = LogManager.getLogger(HomePageTest.class);
    private final HomePage homePage;

    public HomePageTest(HomePage homePage) {
        this.driver = WebDriverFactory.getDriver();
        this.homePage = homePage;
    }

    @Given("home page is opened")
    public void mainPageIsOpened() {
        log.info("Navigating to the home page URL: {}", homePageUrl);
        driver.get(homePageUrl);
    }

    @When("adding user page opening")
    public void openAddUserPage() {
        log.info("Opening Add User Page");
        try {
            homePage.openSignUpPage();
            log.debug("Add User page opened successfully");
        } catch (Exception ex) {
            log.error("Failed to open Add User page", ex);
            throw new ExceptionUtils("Failed to open Add User page: " + ex.getMessage(), ex);
        }
    }

}
