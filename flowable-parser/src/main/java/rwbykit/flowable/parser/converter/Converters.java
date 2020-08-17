package rwbykit.flowable.parser.converter;

import rwbykit.flowable.core.util.Maps;
import rwbykit.flowable.core.util.Objects;
import rwbykit.flowable.parser.Converter;
import rwbykit.flowableTemp.core.util.Numbers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Converters {

    private static Map<Class<?>, Converter<?>> DEFAULT_CONVERTER = null;

    static {
        Map<Class<?>, Converter<?>> register = new ConcurrentHashMap<>();
        register(int.class, Numbers::toInteger, register);
        register(Integer.class, Numbers::toInteger, register);
        register(float.class, Numbers::toFloat, register);
        register(Float.class, Numbers::toFloat, register);
        register(double.class, Numbers::toDouble, register);
        register(Double.class, Numbers::toDouble, register);
        DEFAULT_CONVERTER = Maps.immutable(register);
    }

    private Converters() {
    }

    private static void register(Class<?> classType, Converter<?> converter, Map<Class<?>, Converter<?>> converterMap) {
        converterMap.putIfAbsent(classType, converter);
    }

    @SuppressWarnings("unchecked")
    public static <T> Converter<T> getDefaultConverter(Class<?> classType) {
        return (Converter<T>) DEFAULT_CONVERTER.get(classType);
    }

    public static <T> Converter<T> getConverter(Converter<T> converter, Class<?> classType) {
        return Objects.nonNull(converter) ? converter : getDefaultConverter(classType);
    }

}
