package com.endava.atfproject.pages;

import com.endava.atfproject.steps.Hook;
import org.awaitility.Awaitility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static com.endava.atfproject.WebDriverSingleton.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class DashboardPage extends Hook {

    @FindBy(xpath = "//*[@role='alert']")
    private WebElement loginAlert;

    public DashboardPage() {
        driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    public void assertUserSuccessfullyLoggedIn() {
        Awaitility.await("waiting for login alert to be visible").atMost(30, TimeUnit.SECONDS)
                .until(() -> loginAlert.isDisplayed());

        assertThat(loginAlert.getText()).contains("Hello admin!");
    }
}
