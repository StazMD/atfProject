package utils;

public class ExceptionUtils extends RuntimeException {

    public ExceptionUtils(String message) {
        super(message);
        takeScreenshot();
    }

    public ExceptionUtils(String message, Throwable cause) {
        super(message, cause);
        takeScreenshot();
    }

    private void takeScreenshot() {
        ReportPortalUtils.sendScreenshotToReportPortal();
    }

}
