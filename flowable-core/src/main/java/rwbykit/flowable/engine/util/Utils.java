package rwbykit.flowable.engine.util;

import org.slf4j.helpers.MessageFormatter;

public class Utils {

    /**
     * 格式化字符串，采用slf4j的方式
     * @param messagePattern
     * @param objects
     * @return
     */
    public final static String formatMessage(String messagePattern, Object... objects) {
        return MessageFormatter.arrayFormat(messagePattern, objects).getMessage();
    }

}
