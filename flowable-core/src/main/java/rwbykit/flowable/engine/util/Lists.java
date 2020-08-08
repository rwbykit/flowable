package rwbykit.flowable.engine.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lists {

    public final static <E> List<E> newArrayList(E... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }

    public final static <E> List<E> immutableList(List<E> list) {
        if (Collections.isEmpty(list)) {
            return java.util.Collections.emptyList();
        } else if (list.size() == 1) {
            return java.util.Collections.singletonList(list.get(0));
        } else {
            return java.util.Collections.unmodifiableList(list);
        }
    }

    public final static <E> List<E> immutableList(E... elements) {
        if (Collections.isEmpty(elements)) {
            return java.util.Collections.emptyList();
        } else if (elements.length == 1) {
            return java.util.Collections.singletonList(elements[0]);
        } else {
            return java.util.Collections.unmodifiableList(newArrayList(elements));
        }
    }

}
