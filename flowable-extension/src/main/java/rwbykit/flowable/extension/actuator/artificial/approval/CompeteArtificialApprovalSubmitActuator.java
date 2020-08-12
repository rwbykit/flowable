package rwbykit.flowable.extension.actuator.artificial.approval;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.AbstractArtificialApprovalSubmitActuator;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ArtificialApprovalSubmitResult;
import rwbykit.flowable.engine.runtime.model.ApprovalInstance;
import rwbykit.flowable.core.model.ArtifactNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 竞办人工审批提交
 * 
 * @author Cytus_
 * @since 2018年12月28日 下午3:16:46
 * @version 1.0
 */
public class CompeteArtificialApprovalSubmitActuator extends AbstractArtificialApprovalSubmitActuator {
    
    private final static Logger logger = LoggerFactory.getLogger(CompeteArtificialApprovalSubmitActuator.class);

    @Override
    protected ArtificialApprovalSubmitResult doSubmit(ArtifactNode node, List<ApprovalInstance> listApprovalInstance) {
        long assignQuantity = node.getAssignment().getAssignQuantity();
        List<ApprovalInstance> hasSubmitted = listApprovalInstance.parallelStream()
                .filter(approvalInstance -> Constants.COMMMON_YESNO_YES.equals(approvalInstance.getSubmitted()))
                .collect(Collectors.toList());
        if (hasSubmitted.size() == assignQuantity) {
            long refuseAprvResult = listApprovalInstance.parallelStream().filter(s -> Constants.ARRV_RESULT_REFUSE.equals(s.getAprvResult())).count();
            logger.info("节点实例[{}], 节点[{}], 为审批提交，审批结果为：{}", listApprovalInstance.get(0).getNodeInstanceId(), node.getId(), refuseAprvResult > 0 ? "拒绝" : "通过");
            return refuseAprvResult > 0 ? ArtificialApprovalSubmitResult.REFUSE : ArtificialApprovalSubmitResult.PASS;
        } else {
            logger.warn("节点实例[{}], 节点[{}]审批人尚未完全审批结束!", listApprovalInstance.get(0).getNodeInstanceId(), node.getId());
            return ArtificialApprovalSubmitResult.UNCOMPLETED;
        }
    }
}
