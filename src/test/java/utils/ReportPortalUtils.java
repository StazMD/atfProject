package utils;

import com.epam.reportportal.service.ReportPortal;
import context.ScenarioContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ReportPortalUtils {
    private static final Logger log = LogManager.getLogger(ReportPortalUtils.class);
    private final static ScenarioContext scenarioContext = ScenarioContext.INSTANCE;

    public static void updatePropertiesTestLaunchName() {
        String path = "src/test/resources/reportportal.properties";
        Properties properties = new Properties();
        log.info("Loading reportportal.properties");

        try (FileInputStream in = new FileInputStream(path)) {
            properties.load(in);
            log.info("Loaded reportportal.properties");

            String currentDateTime = new SimpleDateFormat("dd-MMM-yyyy, HH:mm:ss").format(new Date());
            properties.setProperty("rp.launch", "AtfTests " + currentDateTime);
            log.info("Updated rp.launch with current datetime format {}", currentDateTime);

            try (FileOutputStream out = new FileOutputStream(path)) {
                properties.store(out, null);
                log.info("Saved reportportal.properties");
            }
        } catch (Exception ex) {
            log.error("Error updating reportportal.properties: {}", ex.getMessage(), ex);
            throw new CustomException(ex.getMessage(), ex);
        }
    }

    public static void sendScreenshotToReportPortal() {
        log.info("Sending screenshot to ReportPortal");
        try {
            ReportPortal.emitLog(scenarioContext.getScenario().getName(), "INFO", new Date(), ScreenshotUtils.takeScenarioScreenshot());
            log.info("Screenshot was sent");
        } catch (RuntimeException ex) {
            log.error("Failed to send screenshot to ReportPortal: {}", ex.getMessage());
            throw new CustomException(ex.getMessage(), ex);
        }
    }
}
