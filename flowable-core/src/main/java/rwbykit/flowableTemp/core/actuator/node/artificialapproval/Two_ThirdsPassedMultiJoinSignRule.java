package rwbykit.flowableTemp.core.actuator.node.artificialapproval;

import org.springframework.stereotype.Service;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.Result;
import rwbykit.flowable.engine.runtime.model.ApprovalInstance;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowableTemp.core.customized.BooleanResult;
import rwbykit.flowableTemp.core.util.Numbers;

import java.util.List;

/**
 * 三分之二通过
 * 
 * @author Cytus_
 * @since 2018年12月28日 下午6:03:35
 * @version 1.0
 */
@Service
public class Two_ThirdsPassedMultiJoinSignRule implements CustomizedMultiJoinSignRule {

    @Override
    public Result<Boolean> calculate(MultiJoinSignParameter parameter) throws FlowableException {
        List<ApprovalInstance> approvals = parameter.getApprovals();
        long refuseNum = approvals.parallelStream().filter(s -> ProcessConstants.ARRV_RESULT_REFUSE.equals(s.getAprvResult())).count();
        return BooleanResult.createSuccess(Numbers.divide(refuseNum, approvals.size(), 2).compareTo(Numbers.divide(1, 3, 2)) < 0);
    }
}
