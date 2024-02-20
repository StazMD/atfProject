package context;

import config.PropertyReader;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public enum ScenarioContext {
    INSTANCE;

    private final Map<String, Object> contextData;
    private static final Logger log = LogManager.getLogger(PropertyReader.class);
    private static Scenario scenario;

    ScenarioContext() {
        contextData = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        contextData.put(key, value);
    }

    public Object getContext(String key) {
        if (!contextData.containsKey(key)) {
            log.error("Context for key '{}' not found", key);
            throw new IllegalArgumentException("Context for key '" + key + "' not found");
        }
        return contextData.get(key);
    }

    public static void setScenario(Scenario scenario) {
        ScenarioContext.scenario = scenario;
        log.info("Scenario name set");
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public void clearContext() {
        contextData.clear();
        log.info("Scenario Context cleared successfully");
    }
}
