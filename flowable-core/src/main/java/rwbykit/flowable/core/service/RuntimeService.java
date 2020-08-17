package rwbykit.flowable.core.service;

public interface RuntimeService {

    ApprovalService getApprovalService();

    ParameterService getParameterService();

    ProcessService getProcessService();

    NodeService getNodeService();

    TaskService getTaskService();

}
