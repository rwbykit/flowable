package rwbykit.flowable;

import rwbykit.flowable.core.Configuration;
import rwbykit.flowable.core.factory.ObjectFactory;
import rwbykit.flowable.core.factory.ObjectFactoryAware;

public class ConfigurationImpl implements Configuration<ConfigurationImpl> {



    @Override
    public Configuration<?> buildObjectFactory(ObjectFactory objectFactory) {
        return null;
    }

    @Override
    public Configuration<?> buildObjectFactoryAware(ObjectFactoryAware factoryAware) {
        return null;
    }
}
