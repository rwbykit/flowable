package rwbykit.flowable.engine.factory;

import rwbykit.flowable.engine.FlowableRuntimeException;
import rwbykit.flowable.model.enumeration.ExecuteMode;
import rwbykit.flowableTemp.core.util.Beans;
import rwbykit.flowableTemp.core.util.SpringContexts;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象创建工厂类
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午2:49:39
 * @version 1.0
 */
public class ObjectFactory {

    private final static Map<String, Object> OBJECT_MAP = new ConcurrentHashMap<>();
    
    private static class ObjectFactoryHolder {
        private static final ObjectFactory FACTORY = new ObjectFactory();
    }
    
    private ObjectFactory() {}

    public final static ObjectFactory factory() {
        return ObjectFactoryHolder.FACTORY;
    }
    
    public <T> T create(ExecuteMode executeMode, String value) {
        T t = null;
        switch (executeMode) {
            case BEAN : t = getBeanTask(value); break;
            case INVOKE : t = getClassTask(value); break;
            default : break;
        }
        return t;
    }
    
    @SuppressWarnings("unchecked")
    protected <T> T getBeanTask(String value) {
        return (T) SpringContexts.getBean(value);
    }
    
    @SuppressWarnings("unchecked")
    protected <T> T getClassTask(String value) {
        
        try {
            return (T) Beans.newInstance(Class.forName(value));
        } catch (ClassNotFoundException e) {
            throw new FlowableRuntimeException("");
        }
        
    }

    public <T> T getObject(String value) {
        if (OBJECT_MAP.containsKey(value)) {
            OBJECT_MAP.computeIfAbsent(value, (key) -> getClassTask(key));
        }
        return (T) OBJECT_MAP.get(value);
    }
    
}
