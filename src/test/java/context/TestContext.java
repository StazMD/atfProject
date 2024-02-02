package context;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private static Map<String, Object> scenarioContext;

    private TestContext() {
    }

    public static Map<String, Object> getScenarioContext() {
        if (scenarioContext == null) {
            scenarioContext = new HashMap<>();
        }
        return scenarioContext;
    }

    public static void setContext(String key, Object value) {
        getScenarioContext().put(key, value);
    }

    public static Object getContext(String key) {
        return getScenarioContext().get(key);
    }


//    public static Boolean isContains(String key) {
//        return getScenarioContext().containsKey(key);
//    }
}


