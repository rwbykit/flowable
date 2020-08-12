package rwbykit.flowable.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public final class Lists {

    public static <E> List<E> newArrayList(E... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }

    public static <E> List<E> newArrayList(Collection<E> collection) {
        if (Collections.isEmpty(collection)) {
            return new ArrayList<>(0);
        }
        return new ArrayList<>(collection);
    }

    public static <E> List<E> newLinkedList() {
        return new LinkedList<>();
    }


    public static <E> List<E> immutable(List<E> list) {
        if (Collections.isEmpty(list)) {
            return java.util.Collections.emptyList();
        } else if (list.size() == 1) {
            return java.util.Collections.singletonList(list.get(0));
        } else {
            return java.util.Collections.unmodifiableList(list);
        }
    }

    public static <E> List<E> immutable(E... elements) {
        if (Collections.isEmpty(elements)) {
            return java.util.Collections.emptyList();
        } else if (elements.length == 1) {
            return java.util.Collections.singletonList(elements[0]);
        } else {
            return java.util.Collections.unmodifiableList(newArrayList(elements));
        }
    }

    public static <E> List<E> emptyList() {
        return java.util.Collections.emptyList();
    }


}
