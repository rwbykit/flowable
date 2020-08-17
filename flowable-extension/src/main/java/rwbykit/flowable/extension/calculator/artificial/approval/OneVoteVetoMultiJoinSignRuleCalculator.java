package rwbykit.flowable.extension.calculator.artificial.approval;

import rwbykit.flowable.core.Result;
import rwbykit.flowable.core.model.runtime.ApprovalInstance;
import rwbykit.flowable.extension.actuator.artificial.approval.MultiJoinSignParameter;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowable.extension.BooleanResult;

import java.util.List;

/**
 * 一票否决
 * 
 * @author Cytus_
 * @since 2018年12月28日 下午5:54:40
 * @version 1.0
 */
public class OneVoteVetoMultiJoinSignRuleCalculator implements CustomizedMultiJoinSignRuleCalculator {


    @Override
    public Result<Boolean> calculate(MultiJoinSignParameter parameter) {
        List<ApprovalInstance> approvals = parameter.getApprovalInstances();
        long refuseNum = approvals.parallelStream().filter(s -> ProcessConstants.ARRV_RESULT_REFUSE.equals(s.getAprvResult())).count();
        return BooleanResult.createSuccess(refuseNum > 0);
    }
}
