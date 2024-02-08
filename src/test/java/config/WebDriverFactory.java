package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    //TODO volatile wtf?
    private volatile static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    public static WebDriver setupDriver() {
        if (driver == null) {
            //TODO add logger instead of sout
            logger.info("Opening WebDriver");
            String browserType = PropertyReader.getProperty("browser");
            String headlessProperty = PropertyReader.getProperty("headless");
            boolean headless = Boolean.parseBoolean(headlessProperty);

            switch (browserType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) {
                        chromeOptions.addArguments("--headless");
                        logger.debug("Chrome is set to run in headless mode");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    //TODO define exception
                    throw new IllegalArgumentException("Unsupported browser: " + browserType);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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