package context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    //TODO fix to be a classic singleton implementation
    private static Map<String, Object> scenarioData;

    private ScenarioContext() {
    }

    //TODO key in hashmap to force people using the same name using Java collections
    public static Map<String, Object> getScenarioData() {
        if (scenarioData == null) {
            scenarioData = new HashMap<>();
            //TODO read about collection Maps, why using it
        }
        return scenarioData;
    }

    public static void setContext(String key, Object value) {
        getScenarioData().put(key, value);
        //TODO map's methods why put, replace
    }

    public static Object getContext(String key) {
        return getScenarioData().get(key);
        //TODO add exception and logger
    }

}


