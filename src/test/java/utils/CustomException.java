package utils;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, boolean isScreenshot) {
        super(message);
        if (isScreenshot) {
            ReportPortalUtils.sendScreenshotToReportPortal();
        }
    }
}
