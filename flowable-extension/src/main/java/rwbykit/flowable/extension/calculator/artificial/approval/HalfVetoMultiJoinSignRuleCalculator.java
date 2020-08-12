package rwbykit.flowable.extension.calculator.artificial.approval;

import rwbykit.flowable.engine.Result;
import rwbykit.flowable.engine.runtime.model.ApprovalInstance;
import rwbykit.flowable.extension.actuator.artificial.approval.MultiJoinSignParameter;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowable.extension.BooleanResult;
import rwbykit.flowableTemp.core.util.Numbers;

import java.math.BigDecimal;
import java.util.List;

/**
 * 半数否决
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月28日 下午6:02:15
 */
public class HalfVetoMultiJoinSignRuleCalculator implements CustomizedMultiJoinSignRuleCalculator {


    @Override
    public Result<Boolean> calculate(MultiJoinSignParameter parameter) {
        List<ApprovalInstance> approvals = parameter.getApprovals();
        long refuseNum = approvals.parallelStream().filter(s -> ProcessConstants.ARRV_RESULT_REFUSE.equals(s.getAprvResult())).count();
        return BooleanResult.createSuccess(Numbers.divide(refuseNum, approvals.size(), 2).compareTo(new BigDecimal(0.5)) < 0);
    }
}
