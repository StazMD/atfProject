package config;

import enums.ConfigKeys;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.CustomException;

public class WebDriverFactory {

    private static WebDriver driver = null;
    private static final Logger log = LogManager.getLogger(WebDriverFactory.class);

    private static WebDriver setupDriver() {

        if (driver == null) {
            log.info("Opening WebDriver...");

            var browserType = PropertyReader.getProperty(ConfigKeys.BROWSER_TYPE);
            switch (browserType) {
                case "chrome" -> getChromeDriver();
                case "firefox" -> getFirefoxDriver();
                case "edge" -> getEdgeDriver();
                default -> throw new CustomException("Unsupported browser: " + browserType);
            }

            driver.manage().window().maximize();
            log.debug("Browser window maximized");
            log.info("WebDriver for {} has been successfully set up", browserType);
        }
        log.debug("Reusing existing WebDriver instance");
        return driver;
    }

    private static void getChromeDriver() {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();

            if (Boolean.parseBoolean(PropertyReader.getProperty(ConfigKeys.BROWSER_HEADLESS))) {
                chromeOptions.addArguments("--headless=new");
                log.info("Chrome is set to run in headless mode");
            }
            log.info("Setting ChromeDriver with options: {}", chromeOptions);
            driver = new ChromeDriver(chromeOptions);
        } catch (Exception ex) {
            log.error("Error initializing WebDriver: " + ex.getMessage());
            throw new CustomException("Error initializing WebDriver", ex);
        }
    }

    private static void getFirefoxDriver() {
        try {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            if (Boolean.parseBoolean(PropertyReader.getProperty(ConfigKeys.BROWSER_HEADLESS))) {
                firefoxOptions.addArguments("--headless");
                log.info("Firefox is set to run in headless mode");
            }
            log.info("Setting FirefoxDriver with options: {}", firefoxOptions);
            driver = new FirefoxDriver(firefoxOptions);
        } catch (Exception ex) {
            log.error("Error initializing WebDriver: " + ex.getMessage());
            throw new CustomException("Error initializing WebDriver", ex);
        }
    }

    private static void getEdgeDriver() {
        try {
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();

            if (Boolean.parseBoolean(PropertyReader.getProperty(ConfigKeys.BROWSER_HEADLESS))) {
                edgeOptions.addArguments("--headless");
                log.info("Edge is set to run in headless mode");
            }
            log.info("Setting EdgeDriver with options: {}", edgeOptions);
            driver = new EdgeDriver(edgeOptions);
        } catch (Exception ex) {
            log.error("Error initializing WebDriver: " + ex.getMessage());
            throw new CustomException("Error initializing WebDriver", ex);
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
