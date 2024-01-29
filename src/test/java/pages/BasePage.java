package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.common.WebDriverFactory;
import utils.WaitUtils;

public class BasePage {

    protected WebDriver driver;
    protected static Logger log;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//body//h1")
    private WebElement header;

    public BasePage() {
        this.driver = WebDriverFactory.getDriver();
        log = LoggerFactory.getLogger(this.getClass());
        PageFactory.initElements(driver, this);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void assertHeader(String headerText) {
        WaitUtils.waitForTextInElement(header, headerText, 10);
        log.info(headerText + " header is displaying");
    }

    public static void sendKeysUtils(WebElement locator, String keysToSend) {
        WebElement element = WaitUtils.waitForElement(locator, 10);
        log.info("Field [" + locator + "]: " + keysToSend);
        element.sendKeys(keysToSend);
    }
}
