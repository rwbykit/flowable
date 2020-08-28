package rwbykit.flowable.engine.runtime.actuator.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.model.parser.ArtifactNode;
import rwbykit.flowable.core.model.runtime.ApprovalInstance;
import rwbykit.flowable.core.model.runtime.Approver;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Utils;
import rwbykit.flowable.engine.factory.GenericObjectFactory;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ApprovalSubmitPostProcessor;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ArtificialApprovalSubmitActuator;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ArtificialApprovalSubmitResult;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ArtificialApprovalSubmitServiceFactory;
import rwbykit.flowable.engine.runtime.calculator.approver.ApproverCalculator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 人工节点执行器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月13日 上午8:39:34
 */
@Type(category = Constants.CATEGORY_ACTUATOR_NODE, type = Constants.TYPE_NODE_ARTIFICIAL)
public class ArtificialNodeActuator extends AbstractNodeActuator {

    private final static Logger logger = LoggerFactory.getLogger(ArtificialNodeActuator.class);

    @Override
    public Context doExecute(Context context) throws FlowableException {
        try {

            List<ApprovalInstance> approvalInstances = context.getRuntimeService()
                    .getApprovalService().getAllApprovalInstance(context.getCurrentInstance().getNodeInstanceId());

            // 第一次进入人工处理，仅用计算审批人员信息即可
            if (Collections.isEmpty(approvalInstances)) {
                return this.handleApprover(context);
            }

            logger.info("节点实例[{}], 节点[{}], 进入人工审批提交处理", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
            ApprovalInstance approvalInstance = context.getParam(Constants.INSTANCE_APPROVAL);
            if (Objects.nonNull(approvalInstance)) {
                context.getRuntimeService().getApprovalService().update(approvalInstance);
            }

            ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
            String assignType = node.getAssignment().getAssignType();
            ArtificialApprovalSubmitActuator submitActuator = ArtificialApprovalSubmitServiceFactory.factory().getArtificialApprovalSubmitService(assignType);
            ArtificialApprovalSubmitResult submitResult = submitActuator.execute(context);

            List<ApprovalSubmitPostProcessor> approvalSubmitPostProcessors =
                    ArtificialApprovalSubmitServiceFactory.factory().getApprovalSubmitPostProcessors(context.getCurrentInstance().getNodeId());

            approvalSubmitPostProcessors = Optional.ofNullable(approvalSubmitPostProcessors).orElse(Lists.emptyList());
            for (ApprovalSubmitPostProcessor approvalSubmitPostProcessor : approvalSubmitPostProcessors) {
                context = approvalSubmitPostProcessor.postProcessorApprovalSubmit(context, submitResult);
            }
            logger.info("节点实例[{}], 节点[{}], 人工审批提交处理结束", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
        } catch (Exception e) {
            String errorMessage = Utils.formatMessage("节点实例[{}], 节点[{}]处理异常! cause by:{}",
                    context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId(), e.getMessage());
            context.getCurrentInstance().setNodeStatus(Constants.STATUS_EXCEPTION);
            throw handleException(e, errorMessage);
        }
        return context;
    }

    /**
     * 处理审批人
     *
     * @param context
     * @return
     */
    protected Context handleApprover(Context context) throws FlowableException {
        context.getCurrentInstance().setNodeStatus(Constants.STATUS_APPROVAL);
        ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
        ApproverCalculator approverCalculator = GenericObjectFactory.factory().getApproverCalculator(node.getAssignment().getAssigneeType());
        List<Approver> approvers = approverCalculator.calculate(context);
        context.getRuntimeService().getApprovalService().initialize(context.getCurrentInstance().getProcessInstanceId(), context.getCurrentInstance().getNodeInstanceId(), approvers);
        context.addParam("nextApprovers", approvers);
        logger.info("节点实例[{}], 节点[{}], 计算审批人员信息结束!", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
        return context;
    }

}
