package utils;

import config.PropertyReader;
import config.WebDriverFactory;
import enums.ConfigKeys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final long timeout = Long.parseLong(PropertyReader.getProperty(ConfigKeys.TIMEOUT.getKey()));
    private static final Logger log = LogManager.getLogger(WaitUtils.class);
    private static final WebDriver driver = WebDriverFactory.getDriver();

    public static WebElement waitForElement(WebElement element) {
        log.debug("Waiting for visibility of element '{}'", element.toString());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        log.info("Element is visible: " + element);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public static void waitForTextInElement(WebElement element, String expectedText) {
        try {
            log.debug("Waiting for text '{}' in element '{}'", expectedText, element.toString());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(driver -> element.getText().equals(expectedText));
        } catch (TimeoutException ex) {
            throw new CustomException(String.format("Actual text '%s' does not match the expected text: '%s'", element.getText(), expectedText), true);
        }
    }

    public static WebElement waitForButton(WebElement button) {
        log.info("Waiting for button to be visible and clickable");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            WebElement clickableButton = wait.until(ExpectedConditions.elementToBeClickable(waitForElement(button)));
            log.info("Button is now visible and clickable");
            return clickableButton;
        } catch (Exception ex) {
            log.error("Failed to wait for button to be clickable");
            throw new CustomException(ex.getMessage());
        }
    }

    public static void sendKeysUtil(WebElement field, String textToPopulate) {
        log.info("Clearing field '{}'", field);
        waitForElement(field).clear();
        log.info("Sending string '{}' in field '{}'", textToPopulate, field);
        waitForElement(field).sendKeys(textToPopulate);
    }
}
