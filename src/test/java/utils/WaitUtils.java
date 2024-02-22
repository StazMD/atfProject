package utils;

import config.PropertyReader;
import config.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final long timeout = Long.parseLong(PropertyReader.getProperty("element.wait.timeout"));
    private static final Logger log = LogManager.getLogger(WaitUtils.class);
    private static final WebDriver driver = WebDriverFactory.getDriver();

    //TODO reverse waiter?
    public static WebElement waitForElement(WebElement element) {
        log.info("Waiting for visibility of element: " + element.toString());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            WebElement visibleElement = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
            log.info("Element is visible: " + element);
            return visibleElement;
        } catch (TimeoutException ex) {
            log.error("Element is not visible after " + timeout + " seconds: " + element);
            throw ex;
        }
    }

//    public static void waitForTextInElement(WebElement element, String expectedText) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
//        assertEquals(element.getText(), expectedText);
//        try {
//            wait.until(driver -> element.getText().equals(expectedText));
//        } catch (Exception ex) {
//            throw new ExceptionUtils(String.format("Actual text '%s' does not match the expected text: '%s'", element.getText(), expectedText));
//        }
//    }

    //TODO: return?
    public static WebElement waitForButton(WebElement button) {
        WebElement visibleButton = waitForElement(button);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(visibleButton));
    }
}
