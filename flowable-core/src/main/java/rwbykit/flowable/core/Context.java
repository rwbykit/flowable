package rwbykit.flowable.core;

import rwbykit.flowable.core.current.CurrentInstance;
import rwbykit.flowable.core.service.ProcessConfigService;
import rwbykit.flowable.core.service.HistoryService;
import rwbykit.flowable.core.service.RuntimeService;
import rwbykit.flowable.core.util.Maps;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class Context {

    private RuntimeService runtimeService;

    private HistoryService historyService;

    private ProcessConfigService processConfigService;

    private Context temporaryContext;

    private Context eternalContext;

    private Map<String , Object> params;

    private CurrentInstance currentInstance;

    private Context() {}

    private Context(RuntimeService runtimeService, HistoryService historyService) {
        this.eternalContext = new Context();
        this.eternalContext.runtimeService = runtimeService;
        this.eternalContext.historyService = historyService;
        this.temporaryContext = new Context();
        this.temporaryContext.params = new ConcurrentHashMap<>(8);
        this.params = new ConcurrentHashMap<>(8);
    }

    public static Context of(RuntimeService runtimeService, HistoryService historyService) {
        return new Context(runtimeService, historyService);
    }

    public RuntimeService getRuntimeService() {
        return this.eternalContext.runtimeService;
    }

    public HistoryService getHistoryService() {
        return this.eternalContext.historyService;
    }

    public ProcessConfigService getProcessConfigService() {
        return this.temporaryContext.processConfigService;
    }

    public CurrentInstance getCurrentInstance() {
        return this.temporaryContext.currentInstance;
    }

    public <T> T getContextParam(String key) {
        return getParam(this.params, key);
    }

    public <T> T getParam(String key) {
        return this.temporaryContext.getContextParam(key);
    }

    @SuppressWarnings("unchecked")
    private <T> T getParam(Map<String, Object> params, String key) {
        if (Objects.isNull(params)) {
            params = new ConcurrentHashMap<>(8);
        }
        return (T) params.get(key);
    }

    public Context newContext(ProcessConfigService processConfigService, Map<String, Object> params) {
        return newContext(processConfigService, params);
    }

    public Context newContext(ProcessConfigService processConfigService, CurrentInstance currentInstance, Map<String, Object> params) {
        Context context = of(this.getRuntimeService(), this.getHistoryService());
        context.temporaryContext.currentInstance = currentInstance;
        context.temporaryContext.processConfigService = processConfigService;

        return context;
    }

    public void addParam(String key, Object value) {
        this.temporaryContext.params.put(key, value);
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
        this.temporaryContext.params.remove(key);
    }

    public void setCurrentInstance(CurrentInstance currentInstance) {
        if (Objects.isNull(getCurrentInstance())) {
            this.temporaryContext.currentInstance = currentInstance;
        }
    }

    public Context cloneContext() {
        Context context = newContext(this.processConfigService, this.getCurrentInstance().cloneCurrentInstance(), Maps.cloneMap(this.temporaryContext.params));
        context.params = Maps.cloneMap(this.params);
        return context;
    }

}
