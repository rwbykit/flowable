package rwbykit.flowable.engine.runtime.actuator.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.factory.support.Factory;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ArtificialApprovalSubmitActuator;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ArtificialApprovalSubmitResult;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ArtificialApprovalSubmitServiceFactory;
import rwbykit.flowable.engine.runtime.calculator.approver.ApproverCalculator;
import rwbykit.flowable.engine.runtime.model.ApprovalInstance;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.core.util.Utils;
import rwbykit.flowable.core.model.ArtifactNode;

import java.util.List;
import java.util.Objects;

/**
 * 人工节点执行器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月13日 上午8:39:34
 */
@Type(category = Constants.TYPE_ACTUATOR_NODE, type = "Artificial")
public class ArtificialNodeActuator extends AbstractNodeActuator {

    private final static Logger logger = LoggerFactory.getLogger(ArtificialNodeActuator.class);

    @Override
    public Context nodeExecute(Context context) throws FlowableException {
        String status = context.getCurrentInstance().getNodeStatus();
        try {
            // 第一次进入人工处理，仅用计算审批人员信息即可
            if (Constants.STATUS_RUNNING.equals(status)) {
                return this.handleApprover(context);
            }

            int approverNum = context.getRuntimeService().getApprovalService().countApprover(context.getCurrentInstance().getNodeInstanceId());
            // 处理异常状况
            if (Constants.STATUS_EXCEPTION.equals(status)) {
                // 未计算出审批人员信息
                if (approverNum <= 0) {
                    return this.handleApprover(context);
                } else { // 提交异常
                    context.getCurrentInstance().setNodeStatus(Constants.STATUS_APPROVAL);
                }
            }

            //  人工审批提交
            if (Constants.STATUS_APPROVAL.equals(status)) {
                logger.info("节点实例[{}], 节点[{}], 进入人工审批提交处理", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
                ApprovalInstance approvalInstance = context.getParam("Approval");
                if (Objects.nonNull(approvalInstance)) {
                    context.getRuntimeService().getApprovalService().update(approvalInstance);
                }

                ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
                String assignType = node.getAssignment().getAssignType();
                ArtificialApprovalSubmitActuator submitActuator = ArtificialApprovalSubmitServiceFactory.factory().getArtificialApprovalSubmitService(assignType);
                ArtificialApprovalSubmitResult submitResult = submitActuator.execute(context);
                if (ArtificialApprovalSubmitResult.UNCOMPLETED.equals(submitResult)) {
                    logger.warn("节点实例[{}], 节点[{}]审批人尚未完全审批结束!", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
                    context.getCurrentInstance().setNodeStatus(Constants.STATUS_APPROVAL);
                    return context;
                } else {
                    context.getCurrentInstance().setNodeStatus(ArtificialApprovalSubmitResult.REFUSE.equals(submitResult) ? Constants.STATUS_REFUSE : Constants.STATUS_END);
                }
                logger.info("节点实例[{}], 节点[{}], 人工审批提交处理结束", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
            }


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
        ApproverCalculator approverCalculator = Factory.factory().getApproverCalculator(node.getAssignment().getAssigneeType());
        List<Approver> approvers = approverCalculator.calculate(context);
        context.getRuntimeService().getApprovalService().initialize(context.getCurrentInstance().getProcessInstanceId(), context.getCurrentInstance().getNodeInstanceId(), approvers);
        context.addParam("nextApprovers", approvers);
        logger.info("节点实例[{}], 节点[{}], 计算审批人员信息结束!", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
        return context;
    }

}
