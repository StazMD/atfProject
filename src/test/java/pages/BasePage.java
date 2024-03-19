package pages;

import config.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CustomException;
import utils.WaitUtils;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BasePage {

    private static final Logger log = LogManager.getLogger(BasePage.class);

    protected WebDriver driver;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;

    public BasePage() {
        this.driver = WebDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void clickSubmitButton() {
        log.info("Clicking [{}] button...", submitButton.getText());
        try {
            WaitUtils.waitForButton(submitButton).click();
        } catch (RuntimeException ex) {
            throw new CustomException(ex.getMessage(), true, ex);
        }
    }

    public WebElement getHeaderElement() {
        throw new CustomException("getHeaderElement must be overwrite");
    }

    public void assertHeader(String headerText) {
        //TODO: wait for element and get text after, then assert
        WaitUtils.waitForTextInElement(getHeaderElement(), headerText);
        assertThat(headerText).isEqualTo(getHeaderElement().getText());
        log.info("'{}' header is displaying", headerText);
    }
}
