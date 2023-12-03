package com.endava.atfproject.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.endava.atfproject.WebDriverSingleton.getDriver;

public class DashboardPage {
    WebDriver driver;

    public DashboardPage() {
        this.driver = getDriver();
    }

    private final By userMenu = By.xpath("//*[@aria-label='User Menu']");

    public void asserUserMenuFromTopBarNavigation() {
        try {
            WebElement userMenuElement = driver.findElement(userMenu);
            Assert.assertNotNull("Element should be present", userMenuElement);
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }
}
