package rwbykit.flowable.core;

import rwbykit.flowable.core.cache.ProcessCache;
import rwbykit.flowable.core.factory.ObjectFactory;
import rwbykit.flowable.core.factory.ObjectFactoryAware;
import rwbykit.flowable.core.service.HistoryService;
import rwbykit.flowable.core.service.RuntimeService;

public interface Configuration<T extends Configuration<T>> {

    Configuration<?> buildObjectFactory(ObjectFactory objectFactory);

    Configuration<?> buildObjectFactoryAware(ObjectFactoryAware factoryAware);

    Configuration<?> setFlowPath(String... path);

    Configuration<?> byDefaultConfiguration();

    FlowableFactory getFlowableFactory();

    Configuration<?> setProcessCache(ProcessCache processCache);

    Configuration<?> buildContext(RuntimeService runtimeService, HistoryService historyService);

}
