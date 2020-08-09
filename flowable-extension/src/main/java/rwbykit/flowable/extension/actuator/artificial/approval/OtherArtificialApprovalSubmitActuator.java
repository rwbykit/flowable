package rwbykit.flowable.extension.actuator.artificial.approval;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.AbstractArtificialApprovalSubmitActuator;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ArtificialApprovalSubmitResult;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.DefaultArtificialApprovalSubmit;
import rwbykit.flowable.engine.runtime.model.ApprovalInstance;
import rwbykit.flowable.model.ArtifactNode;

import java.util.List;

/**
 * 其他类型审批提交
 * 
 * @author Cytus_
 * @since 2018年12月28日 下午3:16:58
 * @version 1.0
 */
@DefaultArtificialApprovalSubmit
public class OtherArtificialApprovalSubmitActuator extends AbstractArtificialApprovalSubmitActuator {

    private final static Logger logger = LoggerFactory.getLogger(OtherArtificialApprovalSubmitActuator.class);
    
    @Override
    protected ArtificialApprovalSubmitResult doSubmit(ArtifactNode node, List<ApprovalInstance> listApprovalInstance) {
        long notApprovalCount = listApprovalInstance.parallelStream().filter(s -> !Constants.COMMMON_YESNO_YES.equals(s.getSubmitted())).count();
        if (notApprovalCount > 0) {
            logger.warn("节点实例[{}], 节点[{}]审批人尚未完全审批结束!", listApprovalInstance.get(0).getNodeInstanceId(), node.getId());
            return ArtificialApprovalSubmitResult.UNCOMPLETED;
        }

        long refuseAprvResult = listApprovalInstance.parallelStream().filter(s -> Constants.ARRV_RESULT_REFUSE.equals(s.getAprvResult())).count();
        logger.info("节点实例[{}], 节点[{}], 为审批提交，审批结果为：{}", listApprovalInstance.get(0).getNodeInstanceId(), node.getId(), refuseAprvResult > 0 ? "拒绝" : "通过");
        return refuseAprvResult > 0 ? ArtificialApprovalSubmitResult.REFUSE : ArtificialApprovalSubmitResult.PASS;
    }
}
