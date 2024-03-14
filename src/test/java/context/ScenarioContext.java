package context;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CustomException;

import java.util.HashMap;
import java.util.Map;

public enum ScenarioContext {
    INSTANCE;
    //TODO context values by ENUM
    private final Map<String, Object> contextData;
    private Scenario scenario;
    private final Logger log = LogManager.getLogger(ScenarioContext.class);

    ScenarioContext() {
        contextData = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        contextData.put(key, value);
    }

    public Object getContext(String key) {
        if (!contextData.containsKey(key)) {
            log.error("Context for key '{}' not found", key);
            throw new CustomException("Context for key '" + key + "' not found");
        }
        return contextData.get(key);
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void clearContext() {
        contextData.clear();
        log.info("Scenario Context cleared successfully");
    }
}
