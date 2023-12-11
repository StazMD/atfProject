package com.endava.atfproject.pages;

import com.endava.atfproject.steps.Hook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.endava.atfproject.WebDriverSingleton.getDriver;

public class LoginPage extends Hook {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    public LoginPage() {
        driver = getDriver();
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
