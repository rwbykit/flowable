package rwbykit.flowable.extension.calculator.approver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.factory.CalculatorFactory;
import rwbykit.flowable.engine.runtime.calculator.approver.ApproverPolymerizationCalculator;
import rwbykit.flowable.engine.runtime.calculator.approver.GenericApproverCalculator;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.model.ArtifactNode;

import java.util.List;
import java.util.stream.Collectors;

public class MultiApproverCalculator extends GenericApproverCalculator {

    private final static Logger logger = LoggerFactory.getLogger(MultiJointlySignApproverCalculator.class);

    @Override
    protected List<Approver> afterCalculatorSet(Context context, List<List<Approver>> listApprovers) throws FlowableException {
        ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
        ApproverPolymerizationCalculator polymerizationCalculator = CalculatorFactory.factory().getApproverPolymerizationCalculator(node.getAssignment().getPolymerizationType());
        List<Approver> approvers = polymerizationCalculator.calculate(listApprovers);
        approvers = super.executeCustomizedApproverAuthority(context, approvers.parallelStream().limit(node.getAssignment().getAssignQuantity()).collect(Collectors.toList()));
        logger.info("节点实例[{}], 节点[{}]计算当前处理人结束, 审批人信息为:{}", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
        return approvers;
    }

}
