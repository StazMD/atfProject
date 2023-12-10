package com.endava.atfproject.pages;

import com.endava.atfproject.BaseRunner;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static com.endava.atfproject.WebDriverSingleton.getDriver;

public class LoginPage extends BaseRunner {

    public LoginPage() {
        driver = getDriver();
    }

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");

    public void loginWithCredentials(String username, String password) {

        WebElement usernameFieldElement = driver.findElement(usernameField);
        usernameFieldElement.sendKeys(username);
        WebElement passwordFieldElement = driver.findElement(passwordField);
        passwordFieldElement.sendKeys(password, Keys.RETURN);

    }
}
