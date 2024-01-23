package steps.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.MainPage;
import pages.SignUpPage;

public class MainPageTest {

    private final MainPage mainPage;
    private final SignUpPage signUpPage;

    public MainPageTest(MainPage mainPage, SignUpPage signUpPage) {
        this.mainPage = mainPage;
        this.signUpPage = signUpPage;
    }

    @Given("main page is opened")
    public void mainPageIsOpened() {
        mainPage.openMainPage();
        mainPage.assertMainPageHeadText();
    }

    @When("adding user page opening")
    public void openAddUserPage() {
        mainPage.clickSignUpButton();
        signUpPage.assertSignUpPage();
    }

}
