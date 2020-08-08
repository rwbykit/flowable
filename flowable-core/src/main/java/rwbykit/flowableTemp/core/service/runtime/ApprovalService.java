package rwbykit.flowableTemp.core.service.runtime;

import rwbykit.flowableTemp.core.runtime.model.ApprovalInstance;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowableTemp.core.beans.Approval;
import rwbykit.flowableTemp.core.util.CollectionHelper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 审批人信息服务
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午2:53:58
 * @version 1.0
 */
public interface ApprovalService {
    
    /**
     * 插入
     * @param insApproval
     * @return
     */
    int insert(ApprovalInstance insApproval);

    /**
     * 初始化审批人信息
     * @param approval
     */
    default void initialize(Approval approval) {
        initialize(CollectionHelper.newArrayList(approval));
    }

    void initialize(List<Approval> approvers);

    default void initialize(final String processInstanceId, final String nodeInstanceId, List<Approver> approvers) {
        List<Approval> approvals = approvers.parallelStream().map(approver -> {
            Approval approval = new Approval();
            approval.setApprovers(approver);
            approval.setProcessInstanceId(processInstanceId);
            approval.setNodeInstanceId(nodeInstanceId);
            return approval;
        }).collect(Collectors.toList());
        initialize(approvals);
    }

    default void initSystemAutoApproval(String processInstId, String nodeInstanceId, String opinion, String conclusion) {

        /*ApprovalInstance approval = createPassApproval(ProcessConstants.SYSTEM_USER,
                ProcessConstants.SYSTEM_USER_NAME, ProcessConstants.SYSTEM_ORG, ProcessConstants.SYSTEM_ORG_NAME, ProcessConstants.SYSTEM_INST_ORG);
        approval.setNodeInstanceId(nodeInstanceId);
        approval.setProcessInstId(processInstId);
        approval.setSubmitted(Constants.COMMMON_YESNO_YES);
        insApprovalService.insert(approval);*/
    }

    /**
     * 统计当前节点实例已存在的审批人信息
     * @param nodeInstanceId
     * @return
     */
    int countApprover(String nodeInstanceId);


    /**
     * 批量插入
     * @param insApprovals
     * @return
     */
    int addApprovers(List<ApprovalInstance> insApprovals);
    
    /**
     * 更新
     * @param insApproval
     * @return
     */
    int updateApproval(ApprovalInstance insApproval);
    
    /**
     * 根据节点实例号查询所有信息
     * @param nodeInstanceId
     * @return
     */
    List<ApprovalInstance> getAllByNodeInstId(String nodeInstanceId);

    List<Approval> getAllByNodeInstanceId(String nodeInstanceId);
    
    /**
     * 删除节点实例下除传入的approvalids以外的审批信息
     * @param nodeInstId 节点实例
     * @param notDelApprovalId 不需要删除的approvalid
     */
    int deleteOtherAprvIdByNodeInstId(String nodeInstId, List<String> notDelApprovalId);

    /**
     * 更新提交状态
     * @param nodeInstanceId
     * @param submittedStatus
     */
    void updateSubmittedStatus(String nodeInstanceId, String submittedStatus);

}
