package pages;

import config.WebDriverFactory;
import context.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public abstract class BasePage {

    Logger log = LoggerFactory.getLogger(BasePage.class);

    private final ScenarioContext scenarioContext = ScenarioContext.INSTANCE;
    protected final WebDriver driver = WebDriverFactory.getDriver();
    //TODO what is the difference when logger is in every class or only in one class

    //TODO wtf Annotations?
    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@id='logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//body//h1")
    private WebElement header;

    public BasePage() {
        //TODO read about abstract classes
        //TODO read about PO pattern
        PageFactory.initElements(driver, this);
    }

    public void submitButton() {
        WaitUtils.waitForElement(submitButton).click();
    }

    public void assertHeader(String headerText) {
        WaitUtils.waitForTextInElement(header, headerText);
        log.info(headerText + " header is displaying");
    }

}
