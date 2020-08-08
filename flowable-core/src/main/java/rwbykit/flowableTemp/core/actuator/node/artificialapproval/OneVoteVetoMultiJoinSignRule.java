package rwbykit.flowableTemp.core.actuator.node.artificialapproval;

import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.Result;
import rwbykit.flowable.engine.runtime.model.ApprovalInstance;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowableTemp.core.customized.BooleanResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 一票否决
 * 
 * @author Cytus_
 * @since 2018年12月28日 下午5:54:40
 * @version 1.0
 */
@Service
public class OneVoteVetoMultiJoinSignRule implements CustomizedMultiJoinSignRule {


    @Override
    public Result<Boolean> calculate(MultiJoinSignParameter parameter) throws FlowableException {
        List<ApprovalInstance> approvals = parameter.getApprovals();
        long refuseNum = approvals.parallelStream().filter(s -> ProcessConstants.ARRV_RESULT_REFUSE.equals(s.getAprvResult())).count();
        return BooleanResult.createSuccess(refuseNum > 0);
    }
}
