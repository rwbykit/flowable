package rwbykit.flowable.core;

import rwbykit.flowable.core.factory.ObjectFactory;
import rwbykit.flowable.core.factory.ObjectFactoryAware;

public interface Configuration<T extends Configuration<T>> {

    Configuration<?> buildObjectFactory(ObjectFactory objectFactory);

    Configuration<?> buildObjectFactoryAware(ObjectFactoryAware factoryAware);

}
