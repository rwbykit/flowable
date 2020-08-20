package rwbykit.flowable.core;

public interface GenericBootstrap {

    Configuration<?> configure();

    GenericBootstrap register(Object object);

    GenericBootstrap register(Class<?> classType);

    GenericBootstrap register(String category, String type, Object object);

    GenericBootstrap register(String category, String type, Class<?> classType);

}
