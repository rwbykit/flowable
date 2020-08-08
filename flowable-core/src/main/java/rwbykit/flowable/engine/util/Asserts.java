package rwbykit.flowable.engine.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * 断言工具 类
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午1:58:27
 * @version 1.0
 */
public final class Asserts {

    private static final String DEFAULT_NULL = "the element is non-null";

    private static final String DEFAULT_NON_NULL = "the element is null";

    private static final String DEFAULT_MIN_NUMBER = "the value is greater than min value";

    private static final String DEFAULT_MAX_NUMBER = "the value is smaller than max value";

    private static final String DEFAULT_TRUE = "the expression is false";

    private static final String DEFAULT_FALSE = "the expression is true";

    private static final String DEFAULT_EQUALS = "the two objects are not equal";

    private static final String DEFAULT_NON_EQUALS = "the two objects are equal";

    private static final String DEFAULT_EMPTY = "the string is not empty";

    private static final String DEFAULT_NOT_EMPTY = "the string is empty";

    /**
     * utility class cannot be instantiated
     */
    private Asserts() {
    }

    /**
     * assert whether the object is non-null, and throw an exception if it is not with default error message
     *
     * @param t   object element
     * @param <T> object type
     * @return object or exception
     */
    public static <T> T nonNull(T t) {
        return nonNull(t, DEFAULT_NON_NULL);
    }

    /**
     * assert whether the object is non-null, and throw an exception if it is not with default error message
     *
     * @param t   object element
     * @param <T> object type
     * @return object or exception
     */
    public static <T> Collection<T> nonEmpty(Collection<T> t) {
        return nonEmpty(t, DEFAULT_NON_NULL);
    }

    /**
     * assert whether the object is non-null, and throw an exception if it is not with default error message
     *
     * @param t   object element
     * @param <T> object type
     * @return object or exception
     */
    public static <T> T isNull(T t) {
        return isNull(t, DEFAULT_NULL);
    }

    /**
     * assert whether the object is non-null, and throw an exception if it is not with customize error message
     *
     * @param t            object element
     * @param errorMessage error message while occur exception
     * @param <T>          object type
     * @return object or throw an exception
     * @throws IllegalStateException throws an illegal state exception while the t is null
     */
    public static <T> T nonNull(T t, String... errorMessage) {
        if (Objects.isNull(t)) {
            throw new NullPointerException(formatErrorMessage(DEFAULT_NON_NULL, errorMessage));
        }
        return t;
    }

    /**
     * assert whether the object is non-null, and throw an exception if it is not with customize error message
     *
     * @param t            object element
     * @param errorMessage error message while occur exception
     * @param <T>          object type
     * @return object or throw an exception
     * @throws IllegalStateException throws an illegal state exception while the t is null
     */
    public static <T> Collection<T> nonEmpty(Collection<T> t, String... errorMessage) {
        if (Objects.isNull(t)) {
            throw new NullPointerException(formatErrorMessage(DEFAULT_NON_NULL, errorMessage));
        }
        return t;
    }

    /**
     * assert whether the object is non-null, and throw an exception if it is not with customize error message
     *
     * @param t            object element
     * @param errorMessage error message while occur exception
     * @param <T>          object type
     * @return object or throw an exception
     * @throws IllegalStateException throws an illegal state exception while the t is non-null
     */
    public static <T> T isNull(T t, String... errorMessage) {
        if (Objects.nonNull(t)) {
            throw new IllegalStateException(formatErrorMessage(DEFAULT_NULL, errorMessage));
        }
        return null;
    }

    /**
     * assert whether the <code>value</code> is greater than <code>minVal</code>, and throw an exception if it is not
     *
     * @param value        value
     * @param minVal       min value
     * @param errorMessage customize error message
     * @see Number
     */
    public static void minNumber(Number value, Number minVal, String... errorMessage) {
        if (value.doubleValue() > minVal.doubleValue()) {
            throw new IllegalArgumentException(formatErrorMessage(DEFAULT_MIN_NUMBER, errorMessage));
        }
    }

    /**
     * assert whether the <code>value</code> is smaller than <code>maxVal</code>, and throw an exception if it is not
     *
     * @param value        value
     * @param maxVal       max value
     * @param errorMessage customize error message
     * @see Number
     */
    public static void maxNumber(Number value, Number maxVal, String... errorMessage) {
        if (value.doubleValue() < maxVal.doubleValue()) {
            throw new IllegalArgumentException(formatErrorMessage(DEFAULT_MAX_NUMBER, errorMessage));
        }
    }

    /**
     * assert whether the <code>expression</code> is true, and throw an exception if it is not
     *
     * @param expression   expression
     * @param errorMessage customize exception message
     * @throws IllegalStateException exception with customize error message or default message
     */
    public static void isTrue(boolean expression, String... errorMessage) {
        if (!expression) {
            throw new IllegalStateException(formatErrorMessage(DEFAULT_TRUE, errorMessage));
        }
    }

    /**
     * assert whether the <code>expression</code> is false, and throw an exception if it is not
     *
     * @param expression   expression
     * @param errorMessage customize exception message
     * @throws IllegalStateException exception with customize error message or default message
     */
    public static void isFalse(boolean expression, String... errorMessage) {
        if (expression) {
            throw new IllegalStateException(formatErrorMessage(DEFAULT_FALSE, errorMessage));
        }
    }

    /**
     * assert whether the two objects are not equal, and throw an exception if it is not
     *
     * @param obj1         object
     * @param obj2         object
     * @param errorMessage customize error message
     * @throws IllegalStateException exception with customize error message or default message
     */
    public static void notEquals(Object obj1, Object obj2, String... errorMessage) {
        if (Objects.equals(obj1, obj2)) {
            throw new IllegalStateException(formatErrorMessage(DEFAULT_NON_EQUALS, errorMessage));
        }
    }

    /**
     * assert whether the two objects are not equal, and throw an exception if it is not
     *
     * @param obj1         object
     * @param obj2         object
     * @param errorMessage customize error message
     * @throws IllegalStateException exception with customize error message or default message
     */
    public static void isEquals(Object obj1, Object obj2, String... errorMessage) {
        if (!Objects.equals(obj1, obj2)) {
            throw new IllegalStateException(formatErrorMessage(DEFAULT_EQUALS, errorMessage));
        }
    }

    /**
     * assert whether the <code>cs</code> is not null or it's length is greater than zero, and throw an exception if it is not
     *
     * @param cs           CharSequence
     * @param errorMessage customize error message
     * @see rwbykit.flowableTemp.core.util.Strings#isEmpty(CharSequence)
     */
    public static void nonEmpty(CharSequence cs, String... errorMessage) {
        if (Strings.isEmpty(cs)) {
            throw new IllegalStateException(formatErrorMessage(DEFAULT_NOT_EMPTY, errorMessage));
        }
    }

    /**
     * assert whether the <code>cs</code> is null or it's length is zero, and throw an exception if it is not
     *
     * @param cs           CharSequence
     * @param errorMessage customize error message
     * @see rwbykit.flowableTemp.core.util.Strings#nonEmpty(CharSequence)
     */
    public static void isEmpty(CharSequence cs, String... errorMessage) {
        if (Strings.nonEmpty(cs)) {
            throw new IllegalStateException(formatErrorMessage(DEFAULT_EMPTY, errorMessage));
        }
    }

    /**
     * format the customize error message form array or return the default message<br/>
     * if the length of <code>errorMessage<code/> is greater than zero or it's not null, we connect every elements<br/>
     * by "," exclude null element
     *
     * @param defaultMessage default error message
     * @param errorMessage   customize error message
     * @return error message
     */
    private static String formatErrorMessage(String defaultMessage, String[] errorMessage) {
        if (Collections.isEmpty(errorMessage)) {
            return defaultMessage;
        } else if (errorMessage.length == 1){
            return errorMessage[0];
        } else {
            return Utils.formatMessage(errorMessage[0], Arrays.copyOfRange(errorMessage, 1, errorMessage.length));
        }
    }

}
