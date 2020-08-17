package rwbykit.flowable.extension.calculator.approver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.engine.runtime.calculator.approver.GenericApproverCalculator;
import rwbykit.flowable.core.model.runtime.Approver;

import java.util.List;

/**
 * 竞办
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月28日 下午1:31:43
 */
@Type(category = Constants.TYPE_CALCULATOR_APPROVER, type = "Compete")
public class CompeteApproverCalculator extends GenericApproverCalculator {

    private final static Logger logger = LoggerFactory.getLogger(CompeteApproverCalculator.class);

    @Override
    protected List<Approver> afterCalculatorSet(Context context, List<Approver> listApprovers) {
        List<Approver> approvers = super.executeCustomizedApproverAuthority(context, listApprovers);
        logger.info("节点实例[{}], 节点[{}]竞办计算当前处理人结束, 审批人信息为:{}", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
        return approvers;
    }

}
