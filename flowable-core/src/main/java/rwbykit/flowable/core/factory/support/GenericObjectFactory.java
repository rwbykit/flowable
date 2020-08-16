package rwbykit.flowable.core.factory.support;

import rwbykit.flowable.core.FlowableRuntimeException;
import rwbykit.flowable.core.factory.ObjectFactory;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Maps;
import rwbykit.flowable.core.util.Strings;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class GenericObjectFactory implements ObjectFactory {

    private static Map<String, Map<String, Object>> REGISTER_OBJECT = null;

    public GenericObjectFactory(Map<String, Map<String, Object>> registerObject) {
        REGISTER_OBJECT = Maps.immutable(registerObject);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(String category, String type) {
        if (REGISTER_OBJECT.containsKey(category)) {
            Map<String, Object> objectMap = REGISTER_OBJECT.get(category);
            if (objectMap.containsKey(type)) {
                return (T) objectMap.get(type);
            }
        }
        throw new FlowableRuntimeException(Strings.formatMessage("Type[], Key[] not found!", category, type));
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAllType(String category) {
        Map<String, Object> typeMap = REGISTER_OBJECT.get(category);
        return Collections.nonEmpty(typeMap) ? (List<T>) Lists.immutable(Lists.newArrayList(typeMap.values())) : Lists.emptyList();
    }

}
