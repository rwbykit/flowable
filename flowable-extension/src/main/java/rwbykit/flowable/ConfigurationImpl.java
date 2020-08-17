package rwbykit.flowable;

import rwbykit.flowable.core.Configuration;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableFactory;
import rwbykit.flowable.core.factory.ObjectFactory;
import rwbykit.flowable.core.factory.ObjectFactoryAware;
import rwbykit.flowable.core.factory.support.RegisteredObjectFactory;
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
        processPaths = new String[]{"classpath*:process/*.xml"};
        return this;
    }

    @Override
    public FlowableFactory getFlowableFactory() {
        Context context = Context.of(new RuntimeServiceImpl(), new HistoryServiceImpl());
        return FlowableFactoryImpl.of(context, new ParserServiceImpl(processPaths), new ProcessEngineServiceImpl());
    }
}
