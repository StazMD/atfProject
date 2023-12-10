package com.endava.atfproject.steps;

import com.endava.atfproject.WebDriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;

public class Hook {
    protected static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverSingleton.getDriver();
    }

    @AfterAll
    public static void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}
