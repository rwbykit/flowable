package rwbykit.flowableTemp.core.util;

import rwbykit.flowable.engine.util.Asserts;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Collections utility
 *
 * @author tangxb
 * @author zhaosj3
 * @since 1.0.0
 */
public class Collections {

    /**
     * utility class cannot be instantiated
     */
    private Collections() throws IllegalAccessException {
        throw new IllegalAccessException("Utils not create");
    }

    /**
     * cast iterable to collection
     *
     * @param iterable iterable
     * @param <T>      element type
     * @return collection
     */
    public static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection<T>) iterable;
    }

    /**
     * add all <code>elements</code> into <code>collection</code>, if any element fails to add return false<br/>
     * this method will not rollback while any element add failed
     *
     * @param collection collection
     * @param elements   elements
     * @param <T>        element type
     * @return true while all elements add to collection, otherwise false
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean addAll(Collection<? super T> collection, T... elements) {
        boolean result = true;
        for (T element : elements) {
            result &= collection.add(element);
        }
        return result;
    }

    /**
     * add all <code>iterable</code> into <code>collection</code>, if any element fails to add return false<br/>
     * this method will not rollback while any element add failed
     *
     * @param collection collection
     * @param iterable   iterable
     * @param <T>        element type
     * @return true while all iterable add to collection, otherwise false
     */
    public static <T> boolean addAll(Collection<T> collection, Iterable<? extends T> iterable) {
        Asserts.nonNull(collection, "Collection must not null");
        Asserts.nonNull(iterable, "Iterable element must not null!");
        if (iterable instanceof Collection) {
            Collection<? extends T> c = Collections.cast(iterable);
            return collection.addAll(c);
        } else {
            return addAll(collection, iterable.iterator());
        }
    }

    /**
     * add all <code>iterator</code> into <code>collection</code>, if any element fails to add return false<br/>
     * this method will not rollback while any element add failed
     *
     * @param collection collection
     * @param iterator   iterator
     * @param <T>        element type
     * @return true while all elements add to collection, otherwise false
     */
    public static <T> boolean addAll(Collection<T> collection, Iterator<? extends T> iterator) {
        Asserts.nonNull(collection, "Collection must not null");
        Asserts.nonNull(iterator, "Iterator must not null");
        boolean wasModified = true;
        while (iterator.hasNext()) {
            wasModified &= collection.add(iterator.next());
        }
        return wasModified;
    }

    /**
     * 判断集合是否为null或空
     *
     * @param collection
     * @return
     */
    public final static boolean isNullOrEmpty(Collection<?> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    /**
     * 当集合为null抛出异常
     *
     * @param collection 集合.
     */
    public static void requiredAnyNotNull(Collection<?> collection) {
        if (existsAnyNull(collection)) {
            throw new NullPointerException();
        }
    }

    /**
     * 是否集合为null或者集合存在null元素.
     *
     * @param collection 集合对象.
     * @return 是否存在空值.
     */
    public static boolean existsAnyNull(Collection<?> collection) {
        return Objects.isNull(collection) || collection.stream().anyMatch(Objects::isNull);
    }


    /**
     * 判断集合不为null和空
     *
     * @param collection
     * @return
     */
    public final static boolean nonNullOrEmpty(Collection<?> collection) {
        return Objects.nonNull(collection) && !collection.isEmpty();
    }

    /**
     * 判断MAP是否为null或空
     *
     * @param map
     * @return
     */
    public final static boolean isNullOrEmpty(Map<?, ?> map) {
        return Objects.isNull(map) || map.isEmpty();
    }

    /**
     * 判断Map不为null和空
     *
     * @param map
     * @return
     */
    public final static boolean nonNullOrEmpty(Map<?, ?> map) {
        return Objects.nonNull(map) && !map.isEmpty();
    }
}
