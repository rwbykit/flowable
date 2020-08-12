package rwbykit.flowable.engine.runtime.service;

public interface RuntimeService {

    ApprovalService getApprovalService();

    ParameterService getParameterService();

    ProcessService getProcessService();

    NodeService getNodeService();

    TaskService getTaskService();

}
