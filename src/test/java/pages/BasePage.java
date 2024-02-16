package pages;

import api.Assertions;
import config.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

//TODO read about abstract classes
public abstract class BasePage {

    private static final Logger log = LogManager.getLogger(Assertions.class);

    protected WebDriver driver;
    //TODO what is the difference when logger is in every class or only in one class

    //TODO wtf Annotations?
    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//h1")
    private WebElement header;

    public BasePage() {
        this.driver = WebDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void submitButton() {
        WaitUtils.waitForButton(submitButton).click();
    }

    public void assertHeader(String headerText) {
        WaitUtils.waitForTextInElement(header, headerText);
        log.info(headerText + " header is displaying");
    }

}
