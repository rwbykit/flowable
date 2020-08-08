package rwbykit.flowableTemp.core.service.runtime;

public interface RuntimeService {

    ApprovalService getApprovalService();

    NodeService getNodeService();

    TaskService getTaskService();

    ParameterService getParameterService();

    ProcessService getProcessService();

}
