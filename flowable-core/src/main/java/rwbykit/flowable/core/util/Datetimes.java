package rwbykit.flowable.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 字符串工具类
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午3:11:35
 * @version 1.0
 */
public class Datetimes {

    public static String formatByDefault() {
        return DateTimeFormatter.ofPattern("").format(LocalDateTime.now());
    }

}


