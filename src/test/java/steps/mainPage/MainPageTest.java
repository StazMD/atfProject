package steps.mainPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.MainPage;

public class MainPageTest {

    private final MainPage mainPage;

    public MainPageTest(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    @Given("main page is opened")
    public void mainPageIsOpened() {
        mainPage.assertHeadText();
    }

    @And("add user button is clicked")
    public void addUserButtonIsClicked() {
        mainPage.signUpButton();
    }

}
