package com.endava.atfproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("src/test/resources/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.endava.atfproject.steps")
public class BaseRunner {


    @BeforeEach
    public void setUp() {
        WebDriverSingleton.getDriver();
    }

    @AfterEach
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

}
