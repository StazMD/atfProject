package steps.tests;

import config.PropertyReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.SignUpPage;
import steps.common.WebDriverFactory;

public class MainPageTest {

    protected WebDriver driver;
    private final MainPage mainPage;
    private final SignUpPage signUpPage;

    private final String browserUrl = PropertyReader.getProperty("url");

    public MainPageTest(MainPage mainPage, SignUpPage signUpPage) {
        this.driver = WebDriverFactory.getDriver();
        this.mainPage = mainPage;
        this.signUpPage = signUpPage;
    }

    @Given("main page is opened")
    public void mainPageIsOpened() {
        driver.get(browserUrl);
        mainPage.assertHeader("Contact List App");
    }

    @When("adding user page opening")
    public void openAddUserPage() {
        mainPage.clickSignUpButton();
        signUpPage.assertHeader("Add User");
    }

}
