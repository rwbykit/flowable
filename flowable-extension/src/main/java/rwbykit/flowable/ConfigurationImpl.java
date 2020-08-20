package rwbykit.flowable;

import rwbykit.flowable.core.Configuration;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableFactory;
import rwbykit.flowable.core.cache.CacheManager;
import rwbykit.flowable.core.cache.MemoryProcessCache;
import rwbykit.flowable.core.cache.ProcessCache;
import rwbykit.flowable.core.factory.ObjectFactory;
import rwbykit.flowable.core.factory.ObjectFactoryAware;
import rwbykit.flowable.core.factory.support.RegisteredObjectFactory;
import rwbykit.flowable.core.service.HistoryService;
import rwbykit.flowable.core.service.RuntimeService;
import rwbykit.flowable.core.util.Objects;
import rwbykit.flowable.engine.ProcessEngineServiceImpl;
import rwbykit.flowable.engine.factory.GenericObjectFactory;
import rwbykit.flowable.extension.service.HistoryServiceImpl;
import rwbykit.flowable.extension.service.RuntimeServiceImpl;
import rwbykit.flowable.parser.ParserFactory;
import rwbykit.flowable.parser.ParserServiceImpl;

import java.util.Map;

class ConfigurationImpl implements Configuration<ConfigurationImpl> {

    private ObjectFactory objectFactory;
    private Map<String, Map<String, Object>> registerObject;
    private String[] processPaths;
    private ProcessCache processCache;
    private Context context;

    ConfigurationImpl(Map<String, Map<String, Object>> registerObject) {
        this.registerObject = registerObject;
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

    @Override
    public Configuration<?> setFlowPath(String... path) {
        processPaths = path;
        return this;
    }

    @Override
    public Configuration<?> byDefaultConfiguration() {
        this.objectFactory = new RegisteredObjectFactory(registerObject);
        GenericObjectFactory.factory().setObjectFactory(this.objectFactory);
        ParserFactory.factory().setObjectFactory(this.objectFactory);
        this.processPaths = new String[]{"classpath*:process/*.xml"};
        this.processCache = new MemoryProcessCache();
        return this;
    }

    @Override
    public FlowableFactory getFlowableFactory() {
        CacheManager.setProcessCache(processCache);
        if (Objects.isNull(context)) {
            context = Context.of(new RuntimeServiceImpl(), new HistoryServiceImpl());
        }
        return FlowableFactoryImpl.of(context, new ParserServiceImpl(processPaths), new ProcessEngineServiceImpl());
    }

    @Override
    public Configuration<?> setProcessCache(ProcessCache processCache) {
        this.processCache = processCache;
        return this;
    }

    @Override
    public Configuration<?> buildContext(RuntimeService runtimeService, HistoryService historyService) {
        this.context = Context.of(runtimeService, historyService);
        return this;
    }
}
