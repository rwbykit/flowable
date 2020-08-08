package rwbykit.flowableTemp.core.actuator.node.artificialapproval;

import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowable.engine.Result;
import rwbykit.flowableTemp.core.runtime.model.ApprovalInstance;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowableTemp.core.customized.BooleanResult;
import rwbykit.flowableTemp.core.util.Numbers;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 半数否决
 * 
 * @author Cytus_
 * @since 2018年12月28日 下午6:02:15
 * @version 1.0
 */
@Service
public class HalfVetoMultiJoinSignRule implements CustomizedMultiJoinSignRule {

    @Override
    public Result<?> execute(MultiJoinSignParameter parameter) throws FlowableException {
        List<ApprovalInstance> approvals = parameter.getApprovals();
        long refuseNum = approvals.parallelStream().filter(s -> ProcessConstants.ARRV_RESULT_REFUSE.equals(s.getAprvResult())).count();
        return BooleanResult.createSuccess(Numbers.divide(refuseNum, approvals.size(), 2).compareTo(new BigDecimal(0.5)) < 0);
        
    }

}
