package com.endava.atfproject;

import com.endava.atfproject.config.YamlReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public volatile static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            YamlReader yamlReader = new YamlReader();
            String browserType = yamlReader.getProperty("browser");
            String browserUrl = yamlReader.getProperty("url");

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