package base;

import java.util.HashMap;
import java.util.Map;

/**
 * Placeholder to share objects between steps
 */
public class BaseStep {

    private static final ThreadLocal<Map<String, Object>> testsData = ThreadLocal.withInitial(HashMap::new);

    public static void saveData(String key, Object value) {
        testsData.get().put(key, value);
    }

    public static Object getData(String key) {
        return testsData.get().getOrDefault(key, "no data");
    }

    public static void clearData() {
        testsData.get().clear();
    }
}
