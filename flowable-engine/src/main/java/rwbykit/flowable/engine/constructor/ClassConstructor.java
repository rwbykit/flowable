package rwbykit.flowable.engine.constructor;

import rwbykit.flowable.core.Constructor;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.FlowableRuntimeException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Type(category = Constants.TYPE_CONSTRUCTOR, type = "Class")
public class ClassConstructor<T> implements Constructor<T> {

    private final Map<String, Object> CACHEABLE_OBJECT_MAP = new ConcurrentHashMap<>();

    @Override
    public T build(String value) {
        if (!CACHEABLE_OBJECT_MAP.containsKey(value)) {
            doCreate(value);
        }
        return (T) CACHEABLE_OBJECT_MAP.get(value);
    }

    private synchronized void doCreate(String value) {
        if (!CACHEABLE_OBJECT_MAP.containsKey(value)) {
            try {
                java.lang.reflect.Constructor constructor = Class.forName(value).getConstructor();
                Object object = constructor.newInstance();
                CACHEABLE_OBJECT_MAP.put(value, object);
            } catch (Exception e) {
                throw new FlowableRuntimeException(e.getMessage(), e);
            }
        }
    }
}
