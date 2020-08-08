package rwbykit.flowableTemp.core.service.util;

import rwbykit.flowableTemp.Constants;
import com.war3.nova.beans.Approver;
import rwbykit.flowableTemp.core.runtime.model.ApprovalInstance;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowableTemp.core.service.runtime.ApprovalService;
import rwbykit.flowableTemp.core.util.Dates;
import rwbykit.flowableTemp.core.util.SpringContexts;
import rwbykit.flowableTemp.core.util.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务伴生操作工具类
 * @author Cytus_
 * @since 2018年12月14日 上午10:19:05
 * @version 1.0
 */
public final class Approvals {

    private final static ApprovalService insApprovalService = SpringContexts.getBean(ApprovalService.class);
    
    /**
     * 初始化系统审批人信息
     * @param processInstId
     * @param nodeInstanceId
     * @param aprvResult
     * @param aprvComment
     */
    public final static void initSystemAutoApproval(String processInstId, String nodeInstanceId, String aprvResult, String aprvComment) {
        ApprovalInstance approval = createPassApproval(ProcessConstants.SYSTEM_USER,
                ProcessConstants.SYSTEM_USER_NAME, ProcessConstants.SYSTEM_ORG, ProcessConstants.SYSTEM_ORG_NAME, ProcessConstants.SYSTEM_INST_ORG);
        approval.setNodeInstanceId(nodeInstanceId);
        approval.setProcessInstId(processInstId);
        approval.setSubmitted(Constants.COMMMON_YESNO_YES);
        insApprovalService.insert(approval);
    }
    
    /**
     * 更新审批信息
     * @param insApproval
     */
    public final static void updateApproval(ApprovalInstance insApproval) {
        insApprovalService.updateApproval(insApproval);
    }
    
    /**
     * 查询实例节点下所有的审批信息
     * @param nodeInstanceId
     * @return
     */
    public final static List<ApprovalInstance> getNodeAllApproval(String nodeInstanceId) {
        return insApprovalService.getAllByNodeInstId(nodeInstanceId);
    }
    
    /**
     * 创建通过的审批人信息
     * @param aprvUser
     * @param aprvOrg
     * @param aprvInstOrg
     * @return
     */
    public final static ApprovalInstance createPassApproval(String aprvUser, String aprvUserName, String aprvOrg, String aprvOrgName, String aprvInstOrg) {
        ApprovalInstance approval = new ApprovalInstance();
        approval.setApprovalId(Strings.getSequence());
        approval.setAprvResult(ProcessConstants.ARRV_RESULT_PASS);
        approval.setAprvComment("通过");
        approval.setAprvInstOrg(aprvInstOrg);
        approval.setAprvOrg(aprvOrg);
        approval.setAprvUser(aprvUser);
        approval.setAprvTime(Dates.formatDateTimeByDef());
        approval.setAprvUserName(aprvUserName);
        approval.setAprvOrgName(aprvOrgName);
        return approval;
    }
    
    /**
     * 批量插入审批人信息
     * @param processInstId
     * @param nodeInstId
     * @param approvers
     */
    public final static void addApprovers(String processInstId, String nodeInstId, List<Approver> approvers) {
        List<ApprovalInstance> approvals = new ArrayList<>(approvers.size());
        for (Approver approver : approvers) {
            ApprovalInstance approval = new ApprovalInstance();
            approval.setApprovalId(Strings.getSequence());
            approval.setAprvInstOrg(approver.getAprvInstOrg());
            approval.setAprvOrgName(approver.getAprvOrgName());
            approval.setAprvOrg(approver.getAprvOrg());
            approval.setAprvUser(approver.getAprvUser());
            approval.setAprvUserName(approver.getAprvUserName());
            approval.setNodeInstanceId(nodeInstId);
            approval.setProcessInstId(processInstId);
            approval.setSubmitted(Constants.COMMON_YESNO_NO);
            approvals.add(approval);
        }
        insApprovalService.addApprovers(approvals);
    }
    
    public final static void delOtherApprovalRecored(String nodeInstId, List<String> notDelApprovalId) {
        insApprovalService.deleteOtherAprvIdByNodeInstId(nodeInstId, notDelApprovalId);
    }
    
    public final static int countApprover(String nodeInstId) {
        return 0;
    }
    
    
}
