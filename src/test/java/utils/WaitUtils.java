package utils;

import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.common.WebDriverFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WaitUtils {

    public static WebElement waitForElement(By locator, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForTextInElement(By locator, String expectedText) {
        Awaitility.await().atMost(10, TimeUnit.SECONDS).until(() -> {
            WebElement element = WebDriverFactory.getDriver().findElement(locator);
            return element.getText().contains(expectedText);
        });
    }
}

