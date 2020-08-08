package rwbykit.flowableTemp.model.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 执行模式
 * 
 * @author Cytus_
 * @since 2018年12月27日 上午9:48:43
 * @version 1.0
 */
public enum ExecuteMode {

    /**
     * spring bean 类型的调度执行模式
     */
    BEAN, 
    
    /**
     * class 反射类型的调度执行模式
     */
    INVOKE, 
    
    /**
     * 自定义脚本 类型的调度执行模式
     */
    @Deprecated
    SCRIPT, 
    
    /**
     * 表达式类型的调度执行模式
     */
    EXPRESSION, 
    
    /**
     * 自定义类型的调度执行模式
     */
    CUSTOM;
    
    private final static Map<String, ExecuteMode> types = new ConcurrentHashMap<String, ExecuteMode>(ExecuteMode.values().length);
    
    static {
        Arrays.stream(ExecuteMode.values()).forEach(s -> {
            types.put(s.toString(), s);
            types.put(s.toString().toLowerCase(), s);
        });
    }
    
    public final static boolean compare(ExecuteMode executeMode, String value) {
        return executeMode.equals(types.getOrDefault(value, null));
    }
    
    public final static ExecuteMode get(String value) {
        return types.getOrDefault(value, null);
    }
}
