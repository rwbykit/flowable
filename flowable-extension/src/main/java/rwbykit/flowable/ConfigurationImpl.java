package rwbykit.flowable;

import rwbykit.flowable.core.Configuration;
import rwbykit.flowable.core.factory.ObjectFactory;
import rwbykit.flowable.core.factory.ObjectFactoryAware;
import rwbykit.flowable.core.factory.support.GenericObjectFactory;

import java.util.Map;

class ConfigurationImpl implements Configuration<ConfigurationImpl> {

    private ObjectFactory objectFactory;
    private Map<String, Map<String, Object>> registerObject;

    ConfigurationImpl(Map<String, Map<String, Object>> registerObject) {
        this.registerObject = registerObject;
    }

    @Override
    public Configuration<?> byDefaultObjectFactory() {
        this.objectFactory = new GenericObjectFactory(registerObject);
        return this;
    }

    @Override
    public Configuration<?> buildObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
        return this;
    }

    @Override
    public Configuration<?> buildObjectFactoryAware(ObjectFactoryAware factoryAware) {
        factoryAware.setObjectFactory(objectFactory);
        return this;
    }
}
