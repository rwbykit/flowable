package rwbykit.flowable.engine.runtime.calculator.approver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.factory.CalculatorFactory;
import rwbykit.flowable.engine.factory.ObjectFactory;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.engine.util.Asserts;
import rwbykit.flowable.engine.util.Collections;
import rwbykit.flowable.engine.util.Utils;
import rwbykit.flowable.model.ArtifactNode;
import rwbykit.flowable.model.Assignee;
import rwbykit.flowable.model.enumeration.ExecuteMode;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 审批人员计算器抽象类
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月17日 下午3:49:45
 */
public class GenericApproverCalculator implements ApproverCalculator {

    private final static Logger logger = LoggerFactory.getLogger(GenericApproverCalculator.class);
    private final static ExecuteMode[] CUSTOMIZED_APPROVER_AUTH_EXECUTION_MODES = new ExecuteMode[]{ExecuteMode.BEAN, ExecuteMode.INVOKE};

    public List<Approver> calculate(Context context, Assignee assignee) {
        try {
            AssigneeCalculator calculator = CalculatorFactory.factory().getApproverRangeCalculator(assignee.getType());
            context.addParam(AssigneeCalculator.ASSIGNEES, assignee);
            List<Approver> approvers = calculator.calculate(context);
            context.removeParam(AssigneeCalculator.ASSIGNEES);
            return approvers;
        } catch (FlowableException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Approver> calculate(Context context) throws FlowableException {
        logger.info("节点实例[{}], 节点[{}]计算当前处理人开始", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());

        ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getProcessId());
        List<Assignee> assignees = node.getAssignment().getAssignees();
        if (Collections.isEmpty(assignees)) {
            handleNonApprover(context.getCurrentInstance().getNodeId(), context.getCurrentInstance().getNodeInstanceId());
            return java.util.Collections.emptyList();
        }

        List<List<Approver>> listApprovers = assignees.parallelStream().map(s -> this.calculate(context, s)).collect(Collectors.toList());

        ApproverPolymerizationCalculator approverPolymerizationCalculator = CalculatorFactory.factory().getApproverPolymerizationCalculator(node.getAssignment().getPolymerizationType());
        List<Approver> approvers = approverPolymerizationCalculator.calculate(listApprovers);
        approvers = afterCalculatorSet(context, approvers);
        logger.info("节点实例[{}], 节点[{}]计算当前处理人结束, 审批人信息为:{}", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId(), approvers.toString());
        return approvers;
    }

    /**
     * 进行业务自定义审批人员计算扩展
     *
     * @param approvers
     * @param context
     * @return
     */
    public List<Approver> executeCustomizedApproverAuthority(Context context, List<Approver> approvers) {
        ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
        if (Objects.isNull(node.getAssignment().getAfterAssignmentMode())) {
            return approvers;
        }
        ExecuteMode executeMode = ExecuteMode.get(node.getAssignment().getAfterAssignmentMode().getRunMode());
        Asserts.isEquals(CUSTOMIZED_APPROVER_AUTH_EXECUTION_MODES, executeMode, "Unsupported Execution Mode[{}]!", executeMode.toString());
        CustomizedApproverAuthority customizedApproverAuthority = ObjectFactory.factory().create(executeMode, node.getAssignment().getAfterAssignmentMode().getRunValue());
        return customizedApproverAuthority.doCustomizedAuthority(
                ApprovalProcess.builder()
                        .bizNo(context.getCurrentInstance().getBizNo())
                        .processId(context.getCurrentInstance().getProcessId())
                        .nodeId(context.getCurrentInstance().getNodeId())
                        .build(),
                approvers);
    }

    /**
     * 自定义后置处理模式
     *
     * @param context
     * @param listApprovers
     * @return
     */
    protected List<Approver> afterCalculatorSet(Context context, List<Approver> listApprovers) {
        return listApprovers;
    }

    /**
     * 异常处理
     *
     * @param nodeId
     * @param nodeInstanceId
     * @throws FlowableException
     */
    protected void handleNonApprover(String nodeId, String nodeInstanceId) throws FlowableException {
        String errorMsg = Utils.formatMessage("节点实例[{}], 节点[{}]当前配置定的审批人信息为空!", nodeInstanceId, nodeId);
        logger.error(errorMsg);
        throw new FlowableException(errorMsg);
    }

}
