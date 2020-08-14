package rwbykit.flowable.core.factory.support;

import rwbykit.flowable.core.factory.ObjectFactory;
import rwbykit.flowable.core.factory.ObjectFactoryAware;

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
