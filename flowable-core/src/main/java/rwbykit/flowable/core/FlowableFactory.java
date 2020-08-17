package rwbykit.flowable.core;

import rwbykit.flowable.core.service.ProcessEngineService;
import rwbykit.flowable.core.service.ProcessParseService;

public interface FlowableFactory {

    Context getContext();

    ProcessParseService getProcessParserService();

    ProcessEngineService getProcessEngineService();

}
