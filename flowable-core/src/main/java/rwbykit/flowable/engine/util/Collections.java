package rwbykit.flowable.engine.util;

import java.util.Collection;
import java.util.Objects;

public class Collections {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return Objects.isNull(collection) || !collection.isEmpty();
    }

    public static <T> boolean nonEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static <T> boolean isEmpty(T... elements) {
        return Objects.isNull(elements) || elements.length == 0;
    }

}
