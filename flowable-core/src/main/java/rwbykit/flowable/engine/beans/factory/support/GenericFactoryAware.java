package rwbykit.flowable.engine.beans.factory.support;

import rwbykit.flowable.engine.beans.factory.BeanFactory;
import rwbykit.flowable.engine.beans.factory.BeanFactoryAware;

public class GenericFactoryAware implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

}
