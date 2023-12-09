package com.endava.atfproject;

import com.endava.atfproject.utils.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {

    private volatile static WebDriver driver;

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            PropertyReader config = new PropertyReader();
            config.getProperty("chrome");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(

            );
        } else if (driver == null) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            System.out.println("Unsupported browser!");
        }
//        driver.manage().window().maximize();
//        driver.get("https://demo.defectdojo.org/");
//        return driver;
        return null;
    }

    public static void closeDriver() {
        driver.quit();
    }

}


