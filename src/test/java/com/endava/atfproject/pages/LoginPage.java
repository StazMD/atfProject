package com.endava.atfproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static com.endava.atfproject.WebDriverSingleton.getDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage() throws IOException {
        this.driver = getDriver();
    }

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");

    public void loginWithCredentials(String username, String password) {

        WebElement usernameFieldElement = driver.findElement(usernameField);
        usernameFieldElement.sendKeys(username);
        WebElement passwordFieldElement = driver.findElement(passwordField);
        passwordFieldElement.sendKeys(password, Keys.RETURN);

    }

//    public String alertMessage() {
//        WebElement alertMessageText = driver.findElement(alertMessage);
//        if (alertMessageText.isDisplayed()) {
//            return alertMessageText.getText();
//        } else {
//            return null;
//        }
//    }

}
