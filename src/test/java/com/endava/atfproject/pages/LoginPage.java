package com.endava.atfproject.pages;

import com.endava.atfproject.WebDriverSingleton;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    public LoginPage() {
        driver = WebDriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void loginWithCredentials(String username, String password) {
        try {
            usernameField.sendKeys(username);
            passwordField.sendKeys(password);
            passwordField.sendKeys(Keys.RETURN);
        } catch (Exception e) {
        }
    }

}
