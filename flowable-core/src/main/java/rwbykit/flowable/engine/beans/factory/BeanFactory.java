package rwbykit.flowable.engine.beans.factory;

/**
 * 运行时对象工厂
 */
public interface BeanFactory {

    <T> T getBean(String category, String type);
}
