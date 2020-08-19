package rwbykit.flowable.core;

import rwbykit.flowable.core.service.ProcessConfigService;
import rwbykit.flowable.core.service.ProcessEngineService;
import rwbykit.flowable.core.service.ProcessParseService;

public interface FlowableFactory {

    Context getContext();

    ProcessParseService getProcessParserService();

    ProcessEngineService getProcessEngineService();

    default ProcessConfigService getProcessConfigService(String processId) {
        return getProcessConfigService(processId, null);
    }

    ProcessConfigService getProcessConfigService(String processId, String version);

}
