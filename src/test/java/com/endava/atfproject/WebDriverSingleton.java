package com.endava.atfproject;

import com.endava.atfproject.config.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {

    public volatile static WebDriver driver;
    public volatile static PropertyReader propertyReader;

    public WebDriverSingleton(PropertyReader propertyReader) {
        this.propertyReader = propertyReader;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserType = propertyReader.getProperty("browser");
            String browserUrl = propertyReader.getProperty("url");

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
            driver.get(browserUrl);
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}