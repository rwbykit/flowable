package rwbykit.flowable.engine.runtime.calculator.approver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.factory.CalculatorFactory;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.model.ArtifactNode;

import java.util.List;

/**
 * 竞办
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月28日 下午1:31:43
 */
public class CompeteApproverCalculator extends GenericApproverCalculator {

    private final static Logger logger = LoggerFactory.getLogger(CompeteApproverCalculator.class);

    @Override
    protected List<Approver> afterCalculatorSet(Context context, List<List<Approver>> listApprovers) throws FlowableException {
        ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
        ApproverPolymerizationCalculator setCalculator = CalculatorFactory.factory().getApproverPolymerizationCalculator(node.getAssignment().getPolymerizationType());
        List<Approver> approvers = super.executeCustomizedApproverAuthority(context, setCalculator.calculate(listApprovers));
        logger.info("节点实例[{}], 节点[{}]竞办计算当前处理人结束, 审批人信息为:{}", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
        return approvers;
    }

}
