package utils;

import com.epam.reportportal.service.ReportPortal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ReportPortalUtils {
    private static final Logger log = LogManager.getLogger(ReportPortalUtils.class);

    public static void updateLaunchName() {
        String path = "src/test/resources/reportportal.properties";
        Properties properties = new Properties();

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
            log.error("Error updating reportportal.properties: " + ex.getMessage(), ex);
        }
    }

    public static void sendScreenshotToReportPortal(String message) {
        try {
            log.info("Creating screenshot for ReportPortal with message: {}", message);
            ReportPortal.emitLog(message, "INFO", new Date(), ScreenshotUtils.takeScreenshot());
        } catch (Exception ex) {
            log.error("Failed to send screenshot to ReportPortal: {}", ex.getMessage());
        }
    }
}
