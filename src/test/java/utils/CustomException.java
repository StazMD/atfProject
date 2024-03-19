package utils;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message, boolean isScreenshot) {
        super(message);
        if (isScreenshot) {
            ReportPortalUtils.sendScreenshotToReportPortal();
        }
    }

    public CustomException(String message, boolean isScreenshot, Throwable cause) {
        super(message, cause);
        if (isScreenshot) {
            ReportPortalUtils.sendScreenshotToReportPortal();
        }
    }
}
