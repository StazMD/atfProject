package steps.common;

import config.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private volatile static WebDriver driver;

    public static WebDriver setupDriver() {
        if (driver == null) {
            System.out.println("Opening WebDriver");
            String browserType = PropertyReader.getProperty("browser");
//            String browserUrl = PropertyReader.getProperty("url");

            switch (browserType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserType);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//            driver.get(browserUrl);
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return setupDriver();
    }

    public static void quitDriver() {
        if (driver != null) {
            System.out.println("Quitting WebDriver");
            driver.quit();
            driver = null;
        }
    }
}