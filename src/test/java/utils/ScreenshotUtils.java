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
    private static final ScenarioContext scenarioContext = ScenarioContext.INSTANCE;

    public static File takeScenarioScreenshot() {
        String screenshotName = scenarioContext
                .getScenario()
                .getName()
                .replaceAll("\\s+", "") + System.currentTimeMillis() + ".png";
        try {
            File scrFile = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotsFilePath + screenshotName);
            log.info("Screenshot {} saved", screenshotName);
            FileUtils.copyFile(scrFile, destFile);
            return destFile;
        } catch (IOException ex) {
            ex.printStackTrace();
            log.warn("Failed to take screenshot");
            return null;
        }
    }
}
