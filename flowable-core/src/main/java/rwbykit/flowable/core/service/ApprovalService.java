package rwbykit.flowable.core.service;

import rwbykit.flowable.core.model.runtime.ApprovalInstance;
import rwbykit.flowable.core.model.runtime.Approver;
import rwbykit.flowable.core.util.Lists;

import java.util.List;

public interface ApprovalService<T extends ApprovalInstance> {

    default void initialize(String processInstanceId, String nodeInstanceId, Approver approver) {
        initialize(processInstanceId, nodeInstanceId, Lists.immutable(approver));
    }

    void initialize(String processInstanceId, String nodeInstanceId, List<Approver> approvers);


    void initSystemAutoApproval(String processInstId, String nodeInstanceId, String opinion, String conclusion);

    int countApprover(String nodeInstanceId);

    void update(ApprovalInstance approvalInstance);

    List<ApprovalInstance> getAllApprovalInstance(String nodeInstanceId);

    void exclude(List<ApprovalInstance> nonSubmitted);
}
