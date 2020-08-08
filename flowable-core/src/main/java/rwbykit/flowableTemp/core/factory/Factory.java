package rwbykit.flowableTemp.core.factory;

import rwbykit.flowable.engine.FlowableRuntimeException;
import rwbykit.flowableTemp.core.util.FlowableHelper;
import rwbykit.flowableTemp.core.util.SpringContexts;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 映射工厂类
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午2:49:25
 * @version 1.0
 */
class Factory {
    
    private static Map<String, Map<String, Object>> REGISTER_MAPPER = new ConcurrentHashMap<>();

    private static Factory factory;
    
    public final static Factory factory() {
        if (Objects.isNull(factory)) {
            synchronized (Factory.class) {
                if (Objects.isNull(factory)) {
                    factory = SpringContexts.getBean(Factory.class);
                }
            }
        }
        return factory;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(String type, String key) {
        if (REGISTER_MAPPER.containsKey(type)) {
            Map<String, Object> objectMap = REGISTER_MAPPER.get(type);
            if (objectMap.containsKey(key)) {
                return (T) objectMap.get(key);
            }
        }
        throw new FlowableRuntimeException(FlowableHelper.formatMessage("Type[], Key[] not found!", type, key));
    }


}
