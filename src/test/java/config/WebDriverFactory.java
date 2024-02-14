package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFactory {

    //TODO volatile wtf?
    private volatile static WebDriver driver = null;
    private static final Logger log = LoggerFactory.getLogger(WebDriverFactory.class);

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
                    //TODO define exception
                    throw new IllegalArgumentException("Unsupported browser: " + browserType);
            }
            //            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver setupChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--disable-popup-blocking");
        //TODO chromeoptions for headless
        if (headless) {
            chromeOptions.addArguments("--headless=new");
            log.warn("Chrome is set to run in headless mode");
        }
        return new ChromeDriver(chromeOptions);
    }

    public static WebDriver setupFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if (headless) {
            firefoxOptions.addArguments("--headless");
            log.warn("Firefox is set to run in headless mode");
        }
        return new FirefoxDriver(firefoxOptions);
    }

    public static WebDriver getDriver() {
        return setupDriver();
    }

    public static void quitDriver() {
        if (driver != null) {
            log.info("Quitting WebDriver");
            driver.quit();
            driver = null;
        }
    }
}
