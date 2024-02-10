package context;

import java.util.HashMap;
import java.util.Map;

public enum ScenarioContext {
    INSTANCE;

    private final Map<String, Object> contextData;

    ScenarioContext() {
        contextData = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        contextData.put(key, value);
    }

    public Object getContext(String key) {
        return contextData.get(key);
    }

    public boolean isContains(String key) {
        return contextData.containsKey(key);
    }

    public void clearContext() {
        contextData.clear();
    }
}



