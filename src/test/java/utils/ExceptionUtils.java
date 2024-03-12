package utils;

public class ExceptionUtils extends RuntimeException {

    public ExceptionUtils(String message) {
        super(message);
        takeScreenshot();
    }

    private void takeScreenshot() {
        ReportPortalUtils.sendScreenshotToReportPortal();
    }
    
}
