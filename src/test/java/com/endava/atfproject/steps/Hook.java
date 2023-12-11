package com.endava.atfproject.steps;

import com.endava.atfproject.WebDriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hook {
    protected static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverSingleton.getDriver();
    }

    @After
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}
