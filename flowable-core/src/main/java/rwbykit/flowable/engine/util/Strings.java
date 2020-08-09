package rwbykit.flowable.engine.util;


public class Strings {

    public final static String[] EMPTY_ARRAY = new String[0];

    public final static String EMPTY = "";

    public final static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }


    public final static boolean nonEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public final static String[] safeSplit(String cs, String regex) {
        if (nonEmpty(cs)) {
            return cs.split(regex);
        }
        return  EMPTY_ARRAY;
    }

    public final static String formatMessage(String messageTemplate, Object... objects) {

        if (isEmpty(messageTemplate)) {
            return EMPTY;
        }

        if (Collections.isEmpty(objects)) {
            return messageTemplate;
        }

        return String.format(messageTemplate, objects);
    }

}
