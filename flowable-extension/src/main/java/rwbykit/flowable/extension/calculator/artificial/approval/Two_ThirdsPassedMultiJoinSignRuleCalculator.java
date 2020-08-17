package rwbykit.flowable.extension.calculator.artificial.approval;

import rwbykit.flowable.core.Result;
import rwbykit.flowable.core.model.runtime.ApprovalInstance;
import rwbykit.flowable.extension.BooleanResult;
import rwbykit.flowable.extension.actuator.artificial.approval.MultiJoinSignParameter;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowableTemp.core.util.Numbers;

import java.util.List;

/**
 * 三分之二通过
 * 
 * @author Cytus_
 * @since 2018年12月28日 下午6:03:35
 * @version 1.0
 */
public class Two_ThirdsPassedMultiJoinSignRuleCalculator implements CustomizedMultiJoinSignRuleCalculator {

    @Override
    public Result<Boolean> calculate(MultiJoinSignParameter parameter) {
        List<ApprovalInstance> approvals = parameter.getApprovalInstances();
        long refuseNum = approvals.parallelStream().filter(s -> ProcessConstants.ARRV_RESULT_REFUSE.equals(s.getAprvResult())).count();
        return BooleanResult.createSuccess(Numbers.divide(refuseNum, approvals.size(), 2).compareTo(Numbers.divide(1, 3, 2)) < 0);
    }
}
