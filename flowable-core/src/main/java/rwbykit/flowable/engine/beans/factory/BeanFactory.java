package rwbykit.flowable.engine.beans.factory;

import java.util.List;

/**
 * 运行时对象工厂
 */
public interface BeanFactory {

    <T> T getBean(String category, String type);

    <T> List<T> getAllType(String category);
}
