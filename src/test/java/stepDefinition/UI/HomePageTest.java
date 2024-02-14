package stepDefinition.UI;

import config.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SignUpPage;

public class HomePageTest {

    protected WebDriver driver;
    private final HomePage homePage;
    private final SignUpPage signUpPage;


    public HomePageTest(HomePage homePage, SignUpPage signUpPage) {
        this.driver = WebDriverFactory.getDriver();
        this.homePage = homePage;
        this.signUpPage = signUpPage;
    }

    @Given("home page is opened")
    public void mainPageIsOpened() {
        homePage.assertHeader("Contact List App");
    }

    @When("adding user page opening")
    public void openAddUserPage() {
        homePage.clickSignUpButton();
        signUpPage.assertHeader("Add User");
    }

}
