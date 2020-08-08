package rwbykit.flowableTemp.core.runtime;

import rwbykit.flowableTemp.core.config.ProcessConfigService;
import rwbykit.flowableTemp.core.runtime.instance.Instance;
import rwbykit.flowableTemp.core.service.HistoryService;
import rwbykit.flowableTemp.core.service.runtime.RuntimeService;
import rwbykit.flowable.engine.util.Maps;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 流程上下文执行对象
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月17日 上午10:18:44
 */
public class Context implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private RuntimeService runtimeService;

    private HistoryService historyService;

    private ProcessConfigService configService;

    private Context parentContext;

    private Map<String, Object> params;

    private Context currentContext;

    private Instance currentInstance;

    private Context() {
    }

    private Context(RuntimeService runtimeService, HistoryService historyService) {
        this.parentContext = new Context();
        this.parentContext.runtimeService = runtimeService;
        this.parentContext.historyService = historyService;
        this.currentContext = new Context();
    }

    public final static Context of(RuntimeService runtimeService, HistoryService historyService) {
        return new Context(runtimeService, historyService);
    }

    public RuntimeService getRuntimeService() {
        return this.parentContext.runtimeService;
    }

    public HistoryService getHistoryService() {
        return this.parentContext.historyService;
    }

    public ProcessConfigService getConfigService() {
        return this.currentContext.configService;
    }

    public Instance getCurrentInstance() {
        return this.currentContext.currentInstance;
    }

    public <T> T getContextParam(String key) {
        return getParam(this.params, key);
    }

    public <T> T getParam(String key) {
        return this.currentContext.getContextParam(key);
    }

    private <T> T getParam(Map<String, Object> params, String key) {
        if (Objects.isNull(params)) {
            params = new ConcurrentHashMap<>(8);
        }
        return (T) params.get(key);
    }

    public Context newContext(ProcessConfigService configService, Map<String, Object> params) {
        return newContext(configService, params);
    }

    public Context newContext(ProcessConfigService configService, Instance instance, Map<String, Object> params) {
        Context context = of(this.getRuntimeService(), this.getHistoryService());
        context.configService = configService;
        context.currentContext.params = params;
        context.currentContext.currentInstance = instance;
        return context;
    }

    public void addParam(String key, Object value) {
        this.currentContext.params.put(key, value);
    }

    public void addContextParam(String key, Object value) {
        this.addContextParam(this.params, key, value);
    }

    private void addContextParam(Map<String, Object> params, String key, Object value) {
        if (Objects.isNull(params)) {
            params = new ConcurrentHashMap<>(8);
        }
        params.put(key, value);
    }

    public void removeParam(String key) {
        this.currentContext.params.remove(key);
    }

    public void setCurrentInstance(Instance instance) {
        if (Objects.isNull(getCurrentInstance())) {
            this.currentContext.currentInstance = instance;
        }
    }

    public Context copyContext() {
        Context context = newContext(this.configService, this.getCurrentInstance().copyCurrentInstance(), Maps.cloneMap(this.currentContext.params));
        context.params = Maps.cloneMap(this.params);
        return context;
    }

}
