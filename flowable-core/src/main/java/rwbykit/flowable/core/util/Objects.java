package rwbykit.flowable.core.util;

import org.slf4j.LoggerFactory;

public class Objects {

    public static final <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            LoggerFactory.getLogger(Objects.class).error("Class类对象:"+ clazz.getName() +"实例化时出现异常!", e);
        }
        return null;
    }

    public static final <T> T newInstance(String className) {
        try {
            return (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            LoggerFactory.getLogger(Objects.class).error("Class类对象:"+ className +"实例化时出现异常!", e);
        }
        return null;
    }

}
