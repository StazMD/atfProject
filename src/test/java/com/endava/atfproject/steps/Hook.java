package com.endava.atfproject.steps;

import com.endava.atfproject.WebDriverFactory;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class Hook {
    protected static org.openqa.selenium.WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverFactory.getDriver();
    }

    @AfterAll
    public static void tearDown() {
        WebDriverFactory.closeDriver();
    }
}
