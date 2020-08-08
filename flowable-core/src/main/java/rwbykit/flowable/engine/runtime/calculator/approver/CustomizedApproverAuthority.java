package rwbykit.flowable.engine.runtime.calculator.approver;

import rwbykit.flowable.engine.runtime.model.Approver;

import java.util.List;

/**
 * 自定义的审批流程信息
 * 
 * @author Cytus_
 * @since 2018年12月20日 下午4:53:15
 * @version 1.0
 */
public interface CustomizedApproverAuthority {
    
    List<Approver> doCustomizedAuthority(ApprovalProcess approvalProcess, List<Approver> approvers);

}
