package rwbykit.flowable;

import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableFactory;
import rwbykit.flowable.core.cache.CacheManager;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.service.ProcessConfigService;
import rwbykit.flowable.core.service.ProcessEngineService;
import rwbykit.flowable.core.service.ProcessParseService;
import rwbykit.flowable.extension.service.ProcessConfigServiceImpl;

public class FlowableFactoryImpl implements FlowableFactory {

    private Context context;
    private ProcessParseService processParseService;
    private ProcessEngineService processEngineService;

    private FlowableFactoryImpl(Context context, ProcessParseService processParseService, ProcessEngineService processEngineService) {
        this.context = context;
        this.processParseService = processParseService;
        this.processEngineService = processEngineService;
    }

    public static FlowableFactoryImpl of(Context context, ProcessParseService processParseService, ProcessEngineService processEngineService) {
        return new FlowableFactoryImpl(context, processParseService, processEngineService);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public ProcessParseService getProcessParserService() {
        return processParseService;
    }

    @Override
    public ProcessEngineService getProcessEngineService() {
        return processEngineService;
    }

    @Override
    public ProcessConfigService getProcessConfigService(String processId, String version) {
        Process process = CacheManager.getProcess(processId, version);
        return new ProcessConfigServiceImpl(process);
    }
}
