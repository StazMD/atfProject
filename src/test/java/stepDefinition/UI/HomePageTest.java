package stepDefinition.UI;

import config.PropertyReader;
import config.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SignUpPage;

public class HomePageTest {

    protected WebDriver driver;
    private final String homePageUrl = PropertyReader.getProperty("browser.homepage-url");
    private final Logger log = LogManager.getLogger(HomePageTest.class);
    private final HomePage homePage;
    private final SignUpPage signUpPage;

    public HomePageTest(HomePage homePage, SignUpPage signUpPage) {
        this.driver = WebDriverFactory.getDriver();
        this.homePage = homePage;
        this.signUpPage = signUpPage;
    }

    @Given("home page is opened")
    public void mainPageIsOpened() {
        driver.get(homePageUrl);
        log.info("Navigated to the home page URL: {}", homePageUrl);
    }

    @When("adding user page opening")
    public void openAddUserPage() {
        homePage.openSignUpPage();
    }

}
