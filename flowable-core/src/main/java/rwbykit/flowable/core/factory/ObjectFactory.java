package rwbykit.flowable.core.factory;

import java.util.List;

/**
 * 运行时对象工厂
 */
public interface ObjectFactory {

    <T> T getObject(String category, String type);

    <T> List<T> getAllType(String category);
}
