package rwbykit.flowable.core.service;

import rwbykit.flowable.core.model.runtime.ApprovalInstance;
import rwbykit.flowable.core.model.runtime.NodeInstance;
import rwbykit.flowable.core.model.runtime.ParameterInstance;
import rwbykit.flowable.core.model.runtime.ProcessInstance;
import rwbykit.flowable.core.model.runtime.TaskInstance;

public interface RuntimeService {

    <T extends ApprovalInstance> ApprovalService<T> getApprovalService();

    <T extends ParameterInstance> ParameterService<T> getParameterService();

    <T extends ProcessInstance> ProcessService<T> getProcessService();

    <T extends NodeInstance> NodeService<T> getNodeService();

    <T extends TaskInstance> TaskService<T> getTaskService();

}
