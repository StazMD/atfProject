package utils;

import config.PropertyReader;
import config.WebDriverFactory;
import context.ScenarioContext;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    private static final String screenshotsFilePath = PropertyReader.getProperty("reports.screenshot.folder");
    private static final Logger log = LogManager.getLogger(ScreenshotUtils.class);

    public static void takeScenarioScreenshot() {
        String scenarioName = ScenarioContext.getScenario().getName().replaceAll("[^a-zA-Z0-9-_]", "");
        String screenshotFormat = scenarioName + "_" + System.currentTimeMillis();
        File scrFile = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotsFilePath + screenshotFormat + ".png");
        log.info("Screenshot {} saved", destFile);
        try {
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            log.warn("Failed to take screenshot");
        }
    }
}
