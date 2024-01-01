package com.endava.atfproject.pages;

import com.endava.atfproject.WebDriverFactory;
import com.endava.atfproject.config.PropertyReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProductPage {

    WebDriver driver;
    PropertyReader url = new PropertyReader();

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    public AddProductPage() {
        driver = WebDriverFactory.getDriver();
        String newUrl = url.getProperty("url");
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
