package rwbykit.flowable.engine.runtime.service;

import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.engine.util.Lists;

import java.util.List;

public interface ApprovalService {

    default void initialize(String processInstanceId, String nodeInstanceId, Approver approver) {
        initialize(processInstanceId, nodeInstanceId, Lists.immutableList(approver));
    }

    void initialize(String processInstanceId, String nodeInstanceId, List<Approver> approvers);


    void initSystemAutoApproval(String processInstId, String nodeInstanceId, String opinion, String conclusion);

    int countApprover(String nodeInstanceId);

}
