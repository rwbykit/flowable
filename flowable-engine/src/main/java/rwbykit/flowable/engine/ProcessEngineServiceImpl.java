package rwbykit.flowable.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.FlowableFactory;
import rwbykit.flowable.core.cache.CacheManager;
import rwbykit.flowable.core.current.CurrentInstance;
import rwbykit.flowable.core.current.Initiator;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.model.runtime.ApprovalInstance;
import rwbykit.flowable.core.model.runtime.Approver;
import rwbykit.flowable.core.model.runtime.NodeInstance;
import rwbykit.flowable.core.model.runtime.ProcessInstance;
import rwbykit.flowable.core.service.ProcessEngineService;
import rwbykit.flowable.core.service.ProcessResult;
import rwbykit.flowable.core.util.Asserts;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Objects;
import rwbykit.flowable.core.util.Strings;
import rwbykit.flowable.engine.factory.GenericObjectFactory;
import rwbykit.flowable.engine.runtime.actuator.porcess.AbstractProcessActuator;

import java.util.List;
import java.util.Map;

public class ProcessEngineServiceImpl implements ProcessEngineService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessEngineServiceImpl.class);

    private final FlowableFactory flowableFactory;

    public ProcessEngineServiceImpl(FlowableFactory flowableFactory) {
        this.flowableFactory = flowableFactory;
    }

    @Override
    public ProcessResult initialize(String processId, String bizNo, Initiator initiator, Map<String, Object> params) {
        Context context = this.flowableFactory.getContext().newContext(this.flowableFactory.getProcessConfigService(processId), params);
        ProcessResult result;
        try {
            Asserts.nonEmpty(processId, "尚未传入流程编号!");
            Process process = CacheManager.getProcess(processId);
            Asserts.nonNull(process, "流程[{}]未获取到最新的流程配置信息!", processId);
            ProcessInstance processInstance = context.getRuntimeService().getProcessService().initialize(process, initiator, bizNo);
            if (Collections.nonEmpty(params)) {
                context.getRuntimeService().getParameterService().initialize(processInstance.getProcessInstanceId(), params);
            }
            logger.info("流程[{}]实例化成功, 实例号[{}], 版本号[{}]对应的业务流水号为:{}",
                    processId, processInstance.getProcessInstanceId(), processInstance.getVersion(), bizNo);
            result = ProcessResult.success().processInstanceId(context.getCurrentInstance().getProcessInstanceId()).build();
        } catch (Exception e) {
            logger.error("初始化流程出现异常!", e);
            result = ProcessResult.failure(Constants.SYSTEM_ERROR_CODE, e.getMessage()).build();
        }
        return result;
    }

    @Override
    public ProcessResult initializeAndSubmit(String processId, String bizNo, Initiator initiator, Map<String, Object> params) {
        ProcessResult result = this.initialize(processId, bizNo, initiator, params);
        if (!result.isSuccess()) {
            return result;
        }
        return null;
    }

    @Override
    public ProcessResult submit(String processInstanceId, String nodeInstanceId, String nextNodeId, List<Approver> nextApprovers,
                                ApprovalInstance currentApprovalInstance, Map<String, Object> params) throws FlowableException {
        ProcessInstance processInstance = this.flowableFactory.getContext().getRuntimeService().getProcessService().getByProcessInstanceId(processInstanceId);
        Asserts.nonNull(processInstance, "Not found current process instance by instance id[{}]", processInstanceId);
        Context context = this.flowableFactory.getContext().newContext(this.flowableFactory.getProcessConfigService(processInstance.getProcessId()), params);
        context.setCurrentInstance(CurrentInstance.of(processInstance.getInitiator(), processInstance.getBizNo(), processInstance));
        NodeInstance nodeInstance;
        if (Strings.nonEmpty(nodeInstanceId)) {
            nodeInstance = this.flowableFactory.getContext().getRuntimeService().getNodeService().getByNodeInstanceId(nodeInstanceId);

        } else {
            nodeInstance = this.flowableFactory.getContext().getRuntimeService().getNodeService().getByProcessInstanceId(processInstanceId);
        }

        if (Objects.nonNull(nodeInstance)) {
            context.getCurrentInstance().setCurrentNode(nodeInstance.getNodeId(), nodeInstance.getNodeInstanceId(), nodeInstance.getNodeStatus());
        }

        if (Strings.nonEmpty(nextNodeId)) {
            context.addParam(Constants.NEXT_NODE_ID, nextNodeId);
            if (Collections.nonEmpty(nextApprovers)) {
                context.addParam(Constants.NEXT_APPROVERS, nextApprovers);
            }
        }

        if (Objects.nonNull(currentApprovalInstance)) {
            context.addParam(Constants.CURRENT_APPROVAL_INSTANCE, currentApprovalInstance);
        }

        AbstractProcessActuator processActuator = GenericObjectFactory.factory().getProcessActuator(Constants.DEFAULT);
        context = processActuator.execute(context);
        return null;
    }

    @Override
    public ProcessResult continueProcess(String processInstanceId, String nodeInstanceId) throws FlowableException {
        return null;
    }

    @Override
    public ProcessResult submitSubProcess(String processInstanceId, String subProcessId, Initiator initiator, Map<String, Object> params) throws FlowableException {
        return null;
    }

    @Override
    public ProcessResult reApproval(String processInstanceId, String nodeInstanceId, Approver approver) throws FlowableException {
        return null;
    }

    @Override
    public ProcessResult revocation(String processInstanceId, String nodeInstanceId, Approver approver) throws FlowableException {
        return null;
    }

    @Override
    public ProcessResult transferTransact(String processInstanceId, String nodeInstanceId, Approver srcApprover, Approver operateApprover, Approver targetApprover) throws FlowableException {
        return null;
    }

    @Override
    public ProcessResult takeBack(String processInstanceId, String takeBackNodeInstanceId, Approver takeBackApprover) throws FlowableException {
        return null;
    }

    @Override
    public ProcessResult returnBack(String processInstanceId, String returnBackNodeInstanceId, Approver returnBackApprover) throws FlowableException {
        return null;
    }

}
