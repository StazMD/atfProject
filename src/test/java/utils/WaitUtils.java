package utils;

import config.PropertyReader;
import config.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.core.IsEqual.equalTo;

public class WaitUtils {

    private static final long timeout = Long.parseLong(PropertyReader.getProperty("element.wait.timeout.seconds"));

    //TODO reverse waiter?
    public static WebElement waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForTextInElement(WebElement element, String expectedText) {
        await().atMost(timeout, SECONDS).until(element::getText, equalTo(expectedText));
    }
}
