package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    //TODO volatile wtf?
    private volatile static WebDriver driver = null;
    private static final Logger log = LogManager.getLogger(WebDriverFactory.class);

    public static synchronized WebDriver setupDriver() {

        if (driver == null) {
            log.info("Opening WebDriver");
            String browserType = PropertyReader.getProperty("browser.type");
            String headlessProperty = PropertyReader.getProperty("browser.headless");
            boolean headless = Boolean.parseBoolean(headlessProperty);

            switch (browserType) {
                case "chrome":
                    driver = setupChromeDriver(headless);
                    break;
                case "firefox":
                    driver = setupFirefoxDriver(headless);
                    break;
                default:
                    log.error("Unsupported browser type: {}", browserType);
                    throw new IllegalArgumentException("Unsupported browser: " + browserType);
            }

            driver.manage().window().maximize();
            log.debug("Browser window maximized");
            log.info("WebDriver for {} has been successfully set up", browserType);
        }
        log.debug("Reusing existing WebDriver instance");
        return driver;
    }

    public static WebDriver setupChromeDriver(boolean headless) {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();

            chromeOptions.addArguments("--disable-popup-blocking");
            if (headless) {
                chromeOptions.addArguments("--headless=new");
                log.info("Chrome is set to run in headless mode");
            }
            log.info("Setting ChromeDriver with options: {}", chromeOptions);
            return new ChromeDriver(chromeOptions);
        } catch (Exception e) {
            log.error("Error initializing WebDriver: " + e.getMessage());
            throw new RuntimeException("Error initializing WebDriver", e);
        }
    }

    public static WebDriver setupFirefoxDriver(boolean headless) {
        try {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            if (headless) {
                firefoxOptions.addArguments("--headless");
                log.info("Firefox is set to run in headless mode");
            }
            log.info("Setting FirefoxDriver with options: {}", firefoxOptions);
            return new FirefoxDriver(firefoxOptions);
        } catch (Exception e) {
            log.error("Error initializing WebDriver: " + e.getMessage());
            throw new RuntimeException("Error initializing WebDriver", e);
        }
    }

    public static WebDriver getDriver() {
        return setupDriver();
    }

    public static void quitDriver() {
        if (driver != null) {
            log.info("Shutting down WebDriver");
            driver.quit();
            driver = null;
        } else {
            log.info("No WebDriver instance to quit");
        }
    }
}
