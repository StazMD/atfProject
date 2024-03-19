package enums;

public enum ConfigKeys {
    BROWSER_TYPE("browser.type"),
    BROWSER_HEADLESS("browser.headless"),
    SERVER_URL("browser.homepage-url"),
    TIMEOUT("element.wait.timeout"),
    SCREENSHOT_FOLDER("reports.screenshot.folder");

    private final String key;

    ConfigKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
