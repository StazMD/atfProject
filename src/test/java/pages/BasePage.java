package pages;

import config.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public abstract class BasePage {

    //TODO read about abstract classes
    //TODO read about PO pattern

    //TODO read about encapsulation
    protected WebDriver driver;
    //TODO what is the difference when logger is in every class or only in one class
    protected static Logger logger;

    //TODO wtf Annotations?
    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@id='logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//body//h1")
    private WebElement header;

    public BasePage() {
        this.driver = WebDriverFactory.getDriver();
        logger = LoggerFactory.getLogger(this.getClass());
        PageFactory.initElements(driver, this);
    }

    public void clickSubmitButton() {
        WaitUtils.waitForElement(submitButton, 10).click();
    }

    public void clickLogoutButton() {
        WaitUtils.waitForElement(logoutButton, 10).click();
    }

    public void assertHeader(String headerText) {
        WaitUtils.waitForTextInElement(header, headerText, 10);
        logger.info(headerText + " header is displaying");
    }

}
