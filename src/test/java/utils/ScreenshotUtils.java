package utils;

import config.PropertyReader;
import config.WebDriverFactory;
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

    public static File takeScreenshot() {
        File scrFile = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotsFilePath + "screenshot.png");
        try {
            FileUtils.copyFile(scrFile, destFile);
            return destFile;
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("Failed to get screenshot");
            return null;
        }
    }
}
