package rwbykit.flowableTemp.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CollectionHelper {

    public static <E> List<E> newArrayList(E... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }


    public static boolean nonEmpty(Collection<?> collection) {
        return Objects.nonNull(collection) && collection.size() > 0;
    }

}
