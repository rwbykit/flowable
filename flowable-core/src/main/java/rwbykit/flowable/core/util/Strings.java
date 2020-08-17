package rwbykit.flowable.core.util;


import java.util.stream.Stream;

public class Strings {

    public final static String[] EMPTY_ARRAY = new String[0];

    public final static String EMPTY = "";

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }


    public static boolean nonEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String[] safeSplit(String cs, String regex) {
        if (nonEmpty(cs)) {
            return cs.split(regex);
        }
        return EMPTY_ARRAY;
    }

    public static String replace(String cs, CharSequence target, CharSequence replace) {
        if (nonEmpty(cs)) {
            return cs.replace(target, replace);
        }
        return cs;
    }

    public static String formatMessage(String messageTemplate, Object... objects) {

        if (isEmpty(messageTemplate)) {
            return EMPTY;
        }

        if (Collections.isEmpty(objects)) {
            return messageTemplate;
        }

        return String.format(messageTemplate, objects);
    }

    public static String builder(Object... objects) {
        StringBuilder builder = new StringBuilder();
        if (Objects.nonNull(objects)) {
            Stream.of(objects).forEach(builder::append);
        }
        return builder.toString();
    }

    public static String buffer(Object... objects) {
        StringBuffer buffer = new StringBuffer();
        if (Objects.nonNull(objects)) {
            Stream.of(objects).forEach(buffer::append);
        }
        return buffer.toString();
    }

    public static String upperIndexChar(String value, int index) {
        if (isEmpty(value) || value.length() < index) {
            return value;
        }
        StringBuilder builder = new StringBuilder(value);
        builder.setCharAt(index, Character.toUpperCase(builder.charAt(index)));
        return builder.toString();
    }

}
