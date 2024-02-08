package utils;

import config.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.core.IsEqual.equalTo;

public class WaitUtils {

    //TODO reverse waiter?
    public static WebElement waitForElement(WebElement element, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public static void waitForTextInElement(WebElement element, String expectedText, long timeoutInSeconds) {
        await().atMost(timeoutInSeconds, SECONDS).until(element::getText, equalTo(expectedText));
    }


}
