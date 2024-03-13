package config;

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
import utils.ExceptionUtils;

public class WebDriverFactory {

    private static WebDriver driver = null;
    private static final Logger log = LogManager.getLogger(WebDriverFactory.class);

    private static WebDriver setupDriver() {
        if (driver == null) {
            log.info("Opening WebDriver...");

            String browserType = PropertyReader.getProperty("browser.type").toLowerCase();
            switch (browserType) {
                case "chrome" -> getChromeDriver();
                case "firefox" -> getFirefoxDriver();
                case "edge" -> getEdgeDriver();
                default -> throw new ExceptionUtils("Unsupported browser: " + browserType);
            } //TODO properly handle default driver

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

            if (Boolean.parseBoolean(PropertyReader.getProperty("browser.headless"))) {
                chromeOptions.addArguments("--headless=new");
                log.info("Chrome is set to run in headless mode");
            }
            log.info("Setting ChromeDriver with options: {}", chromeOptions);
            driver = new ChromeDriver(chromeOptions);
        } catch (Exception e) {
            log.error("Error initializing WebDriver: " + e.getMessage());
            throw new ExceptionUtils("Error initializing WebDriver");
        }
    }

    private static void getFirefoxDriver() {
        try {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            if (Boolean.parseBoolean(PropertyReader.getProperty("browser.headless"))) {
                firefoxOptions.addArguments("--headless");
                log.info("Firefox is set to run in headless mode");
            }
            log.info("Setting FirefoxDriver with options: {}", firefoxOptions);
            driver = new FirefoxDriver(firefoxOptions);
        } catch (Exception e) {
            log.error("Error initializing WebDriver: " + e.getMessage());
            throw new ExceptionUtils("Error initializing WebDriver");
        }
    }

    private static void getEdgeDriver() {
        try {
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();

            if (Boolean.parseBoolean(PropertyReader.getProperty("browser.headless"))) {
                edgeOptions.addArguments("--headless");
                log.info("Edge is set to run in headless mode");
            }
            log.info("Setting EdgeDriver with options: {}", edgeOptions);
            driver = new EdgeDriver(edgeOptions);
        } catch (Exception e) {
            log.error("Error initializing WebDriver: " + e.getMessage());
            throw new ExceptionUtils("Error initializing WebDriver");
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
