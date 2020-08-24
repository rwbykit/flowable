package rwbykit.flowable.engine.service;

import rwbykit.flowable.core.model.runtime.ApprovalInstance;
import rwbykit.flowable.core.model.runtime.NodeInstance;
import rwbykit.flowable.core.model.runtime.ParameterInstance;
import rwbykit.flowable.core.model.runtime.ProcessInstance;
import rwbykit.flowable.core.model.runtime.TaskInstance;
import rwbykit.flowable.core.service.ApprovalService;
import rwbykit.flowable.core.service.NodeService;
import rwbykit.flowable.core.service.ParameterService;
import rwbykit.flowable.core.service.ProcessService;
import rwbykit.flowable.core.service.RuntimeService;
import rwbykit.flowable.core.service.TaskService;

@SuppressWarnings("unchecked")
public class RuntimeServiceImpl implements RuntimeService {

    private final ApprovalService<?> approvalService;

    private final ParameterService<?> parameterService;

    private final ProcessService<?> processService;

    private final NodeService<?> nodeService;

    private final TaskService<?> taskService;

    public RuntimeServiceImpl(ApprovalService<?> approvalService, ParameterService<?> parameterService,
                              ProcessService<?> processService, NodeService<?> nodeService, TaskService<?> taskService) {
        this.approvalService = approvalService;
        this.parameterService = parameterService;
        this.processService = processService;
        this.nodeService = nodeService;
        this.taskService = taskService;
    }

    @Override
    public <T extends ApprovalInstance> ApprovalService<T> getApprovalService() {
        return (ApprovalService<T>) this.approvalService;
    }

    @Override
    public <T extends ParameterInstance> ParameterService<T> getParameterService() {
        return (ParameterService<T>) this.parameterService;
    }

    @Override
    public <T extends ProcessInstance> ProcessService<T> getProcessService() {
        return (ProcessService<T>) this.processService;
    }

    @Override
    public <T extends NodeInstance> NodeService<T> getNodeService() {
        return (NodeService<T>) this.nodeService;
    }

    @Override
    public <T extends TaskInstance> TaskService<T> getTaskService() {
        return (TaskService<T>) this.taskService;
    }
}
