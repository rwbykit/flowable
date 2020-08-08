package rwbykit.flowableTemp.core.actuator.node.artificialapproval;

import rwbykit.flowableTemp.core.runtime.model.ApprovalInstance;
import com.war3.nova.beans.NvNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowableTemp.Constants;
import rwbykit.flowableTemp.annotation.NovaMapper;
import rwbykit.flowableTemp.core.ProcessConfigContext;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowableTemp.core.enumeration.ApproverType;
import rwbykit.flowableTemp.core.service.util.Approvals;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 竞办人工审批提交
 * 
 * @author Cytus_
 * @since 2018年12月28日 下午3:16:46
 * @version 1.0
 */
@NovaMapper(enumClass = ApproverType.class, enumValue = "COMPETE", mapperName = Constants.ARTIFICIAL_APPROVAL_SUBMIT_SERVICE)
public class CompeteArtiAprvSubmitService implements ArtificialApprovalSubmitService {
    
    private final static Logger logger = LoggerFactory.getLogger(CompeteArtiAprvSubmitService.class);

    @Override
    public ArtificialApprovalSubmitResult doSubmit(String processInstId, String nodeInstId) {
        NvNode nvNode = ProcessConfigContext.getContext().getCurrentNode();
        int approverNum = nvNode.getApproval().getApproverNum();
        List<ApprovalInstance> insApprovals = Approvals.getNodeAllApproval(nodeInstId);
        List<ApprovalInstance> hasSubmitted = insApprovals.parallelStream().filter(s -> Constants.COMMMON_YESNO_YES.equals(s.getSubmitted())).collect(Collectors.toList());
        if (hasSubmitted.size() == approverNum) {
            
            Approvals.delOtherApprovalRecored(nodeInstId, hasSubmitted.stream().map(s -> s.getApprovalId()).collect(Collectors.toList()));
            long refuseAprvResult = insApprovals.parallelStream().filter(s -> ProcessConstants.ARRV_RESULT_REFUSE.equals(s.getAprvResult())).count();
            logger.info("节点实例[{}], 节点[{}], 为审批提交，审批结果为：{}", nodeInstId, nvNode.getId(), refuseAprvResult > 0 ? "拒绝" : "通过");
            return refuseAprvResult > 0 ? ArtificialApprovalSubmitResult.REFUSE : ArtificialApprovalSubmitResult.PASS;
            
        } else {
            logger.warn("节点实例[{}], 节点[{}]审批人尚未完全审批结束!", nodeInstId, nvNode.getId());
            return ArtificialApprovalSubmitResult.UNCOMPLETED;
        }
    }

}
