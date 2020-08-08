package rwbykit.flowable.model.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 聚合类型
 * @author Cytus_
 */
public enum PolymerizationType {

    /**
     * 交集
     */
    MIXED,

    /**
     * 并集
     */
    UNION;

    private final static Map<String, PolymerizationType> types = new ConcurrentHashMap<String, PolymerizationType>(PolymerizationType.values().length);

    static {
        Arrays.stream(PolymerizationType.values()).forEach(s -> {types.put(s.toString(), s);});
    }

    public final static boolean compare(PolymerizationType approverSetType, String value) {
        return approverSetType.equals(types.getOrDefault(value, null));
    }

    public final static PolymerizationType get(String value) {
        return types.getOrDefault(value, null);
    }

}
