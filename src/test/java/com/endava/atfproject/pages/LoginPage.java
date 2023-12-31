package com.endava.atfproject.pages;

import com.endava.atfproject.WebDriverFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    org.openqa.selenium.WebDriver driver;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    public LoginPage() {
        driver = WebDriverFactory.getDriver();
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
