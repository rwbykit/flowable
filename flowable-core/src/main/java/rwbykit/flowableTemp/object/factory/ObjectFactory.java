package rwbykit.flowableTemp.object.factory;

/**
 * 对象工厂
 */
public interface ObjectFactory {

    <T> T getObject(String type, String key);
}
