package rwbykit.flowable.engine.factory.support;

import rwbykit.flowable.engine.factory.ObjectFactory;
import rwbykit.flowable.engine.factory.ObjectFactoryAware;

public class GenericFactoryAware implements ObjectFactoryAware {

    private ObjectFactory objectFactory;

    @Override
    public void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    public ObjectFactory getObjectFactory() {
        return this.objectFactory;
    }

}
