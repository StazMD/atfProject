package utils;

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
        try {
            Properties properties = new Properties();
            String path = "src/test/resources/reportportal.properties";
            FileInputStream in = new FileInputStream(path);
            properties.load(in);
            in.close();

            log.info("Loaded reportportal.properties");

            String currentDate = new SimpleDateFormat("dd-MMM-yyyy, HH:mm:ss").format(new Date());
            properties.setProperty("rp.launch", "AtfUpskilling-Tests " + currentDate);

            log.debug("Updated rp.launch with current date format {}", currentDate);

            FileOutputStream out = new FileOutputStream(path);
            properties.store(out, null);
            out.close();

            log.debug("Saved reportportal.properties");
        } catch (Exception ex) {
            log.warn("Error updating reportportal.properties: " + ex.getMessage());
        }
    }
}


