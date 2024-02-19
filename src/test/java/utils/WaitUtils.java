package utils;

import config.PropertyReader;
import config.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final long timeout = Long.parseLong(PropertyReader.getProperty("element.wait.timeout"));

    //TODO reverse waiter?
    public static WebElement waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public static void waitForTextInElement(WebElement element, String expectedText) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    public static WebElement waitForButton(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }
}
