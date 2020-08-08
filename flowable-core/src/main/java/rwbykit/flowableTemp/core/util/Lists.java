package rwbykit.flowableTemp.core.util;

import rwbykit.flowable.engine.util.Asserts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.isNull;

/**
 * Lists utility
 *
 * @author tangxb
 * @author zhaosj3
 * @since 1.0.0
 */
public class Lists {

    /**
     * utility class cannot be instantiated
     */
    private Lists() {
    }

    /**
     * obtain a new {@link ArrayList} instance
     *
     * @param <E> element type
     * @return ArrayList instance
     */
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * obtain a new {@link ArrayList} instance with initial element
     * if the <code>elements</code> is null throws an exception
     *
     * @param elements elements
     * @param <E>      element type
     * @return ArrayList instance
     * @see Asserts
     * @see Collections#addAll(Collection, Object[])
     */
    @SafeVarargs
    public static <E> ArrayList<E> newArrayList(E... elements) {
        Asserts.nonNull(elements, "Element must not null!");
        int capacity = computeArrayListCapacity(elements.length);
        ArrayList<E> arrayList = new ArrayList<>(capacity);
        java.util.Collections.addAll(arrayList, elements);
        return arrayList;
    }

    /**
     * compute the initial capacity of {@link ArrayList}
     *
     * @param arraySize arraySize
     * @return capacity
     * @see Numbers#saturatedCastInt(long)
     */
    private static int computeArrayListCapacity(int arraySize) {
        Asserts.maxNumber(arraySize, 0, "Array size must greater than or equal to zero!");
        return Numbers.saturatedCastInt(arraySize + 1);
    }

    /**
     * obtain a new {@link ArrayList} instance with initial element
     * if the <code>elements</code> is null throws an exception
     *
     * @param elements elements
     * @param <E>      element type
     * @return ArrayList instance
     * @see Collections#addAll(Collection, Iterable)
     */
    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
        ArrayList<E> list = newArrayList();
        Collections.addAll(list, elements);
        return list;
    }

    /**
     * obtain a new {@link ArrayList} instance with initial element
     * if the <code>elements</code> is null throws an exception
     *
     * @param elements elements
     * @param <E>      element type
     * @return ArrayList instance
     * @see Collections#addAll(Collection, Iterator)
     */
    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
        ArrayList<E> list = newArrayList();
        Collections.addAll(list, elements);
        return list;
    }

    /**
     * obtain a new {@link ArrayList} instance with capacity, the <code>arraySize</code> can not be smaller than zero
     *
     * @param arraySize initial capacity
     * @param <E>       element type
     * @return ArrayList instance
     */
    public static <E> ArrayList<E> newArrayListWithCapacity(int arraySize) {
        Asserts.maxNumber(arraySize, 0, "Init array size must greater than or equal to zero!");
        return new ArrayList<>(arraySize);
    }

    /**
     * obtain a new {@link LinkedList} instance
     *
     * @param <E> element type
     * @return LinkedList instance
     */
    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    /**
     * obtain a new {@link LinkedList} instance with initial element<br/>
     * if the <code>elements</code> is null throws an exception
     *
     * @param elements elements array
     * @param <E>      element type
     * @return LinkedList instance
     * @see Asserts
     * @see Collections#addAll(Collection, Object[])
     */
    @SuppressWarnings("unchecked")
    public static <E> LinkedList<E> newLinkedList(E... elements) {
        Asserts.nonNull(elements, "Element must not null!");
        LinkedList<E> arrayList = new LinkedList<>();
        java.util.Collections.addAll(arrayList, elements);
        return arrayList;
    }

    /**
     * obtain a new {@link LinkedList} instance with initial element<br/>
     * if the <code>elements</code> is null throws an exception
     *
     * @param elements elements
     * @param <E>      element type
     * @return LinkedList instance
     * @see Collections#addAll(Collection, Iterable)
     */
    public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> elements) {
        LinkedList<E> linkedList = new LinkedList<>();
        Collections.addAll(linkedList, elements);
        return linkedList;
    }

    /**
     * obtain a new {@link LinkedList} instance with initial element<br/>
     * if the <code>elements</code> is null throws an exception
     *
     * @param elements elements
     * @param <E>      element type
     * @return LinkedList instance
     * @see Collections#addAll(Collection, Iterator)
     */
    public static <E> LinkedList<E> newLinkedList(Iterator<? extends E> elements) {
        LinkedList<E> linkedList = new LinkedList<>();
        Collections.addAll(linkedList, elements);
        return linkedList;
    }

    /**
     * obtain a new {@link CopyOnWriteArrayList}
     *
     * @param <E> element type
     * @return CopyOnWriteArrayList instance
     */
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    /**
     * obtain a new {@link CopyOnWriteArrayList} instance with initial element<br/>
     * if the <code>elements</code> is null throws an exception
     *
     * @param elements elements array
     * @param <E>      element type
     * @return CopyOnWriteArrayList instance
     */
    @SuppressWarnings("unchecked")
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(E... elements) {
        Asserts.nonNull(elements, "Element must not null!");
        return new CopyOnWriteArrayList<>(elements);
    }

    /**
     * obtain a new {@link CopyOnWriteArrayList} instance with initial element<br/>
     * if the <code>elements</code> is null throws an exception
     *
     * @param elements elements
     * @param <E>      elements type
     * @return CopyOnWriteArrayList instance
     * @see Collections#addAll(Collection, Iterable)
     */
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterable<? extends E> elements) {
        CopyOnWriteArrayList<E> list = new CopyOnWriteArrayList<>();
        Collections.addAll(list, elements);
        return list;
    }

    /**
     * obtain a new {@link CopyOnWriteArrayList} instance with initial element<br/>
     * if the <code>elements</code> is null throws an exception
     *
     * @param elements elements
     * @param <E>      elements type
     * @return CopyOnWriteArrayList instance
     * @see Collections#addAll(Collection, Iterator)
     */
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterator<? extends E> elements) {
        CopyOnWriteArrayList<E> list = new CopyOnWriteArrayList<>();
        Collections.addAll(list, elements);
        return list;
    }

    /**
     * intercept sublist from collection({@link Set},{@link List},{@link Queue} et.) by assign <code>startIndex</code>, it will return an array
     * list include all elements after <code>startIndex</code>
     *
     * @param collection collection
     * @param startIndex index of element, start from zero
     * @param <E>        element type
     * @return subList of collection
     */
    public static <E> ArrayList<E> subArrayList(Collection<E> collection, int startIndex) {
        return subArrayList(collection, startIndex, collection.size() - startIndex);
    }

    /**
     * intercept sublist from collection({@link Set},{@link List},{@link Queue} et.) by assign <code>startIndex</code> and sublist's
     * <code>length</code>, this method will return an {@link ArrayList}
     *
     * @param collection collection
     * @param startIndex index of element, start from zero
     * @param length     substring's length
     * @param <E>        element type
     * @return subList of collection
     * @throws IllegalArgumentException  while collection is null or empty, throw an IllegalArgumentException
     * @throws IndexOutOfBoundsException while the <code>startIndex</code> is smaller than zero or <code>startIndex+length</code>
     *                                   is greater than collection's size
     * @see List#subList(int, int)
     */
    public static <E> ArrayList<E> subArrayList(Collection<E> collection, int startIndex, int length) {
        if (isNull(collection) || collection.isEmpty()) {
            throw new IllegalArgumentException("the collection can not be null or empty");
        }
        if (startIndex < 0) {
            throw new IndexOutOfBoundsException("start index is smaller than zero");
        }
        int endIndex = startIndex + length;
        if (endIndex > collection.size()) {
            throw new IndexOutOfBoundsException("end index is greater than max index");
        }
        /*Asserts.minNumber(endIndex, collection.size(), "end index is greater than max index");*/
        if (collection instanceof List) {
            // subList(fromIndex,toIndex) fromIndex include first element, toIndex not include the last element
            return new ArrayList<>(((List<E>) collection).subList(startIndex, endIndex));
        } else {
            ArrayList<E> result = new ArrayList<>();
            Iterator<E> iterator = collection.iterator();
            int tempIndex = 0, count = 1;
            while (iterator.hasNext()) {
                E e = iterator.next();
                if (tempIndex >= startIndex && count <= length) {
                    result.add(e);
                    count++;
                }
                tempIndex++;
            }
            return result;
        }
    }

}
