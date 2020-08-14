package rwbykit.flowable.engine.runtime.actuator.node.artificial.approval;

import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.engine.runtime.model.ApprovalInstance;
import rwbykit.flowable.core.model.ArtifactNode;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractArtificialApprovalSubmitActuator implements ArtificialApprovalSubmitActuator {

    @Override
    public ArtificialApprovalSubmitResult execute(Context context) throws FlowableException {
        List<ApprovalInstance> approvalInstances = context.getRuntimeService().getApprovalService().getAllApprovalInstance(context.getCurrentInstance().getNodeInstanceId());
        ArtificialApprovalSubmitResult result = doSubmit(context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId()), approvalInstances);
        afterSubmitSet(context, approvalInstances);
        return result;
    }

    protected abstract ArtificialApprovalSubmitResult doSubmit(ArtifactNode node, List<ApprovalInstance> listApprovalInstance);

    protected void afterSubmitSet(Context context, List<ApprovalInstance> listApprovalInstance) {
        List<ApprovalInstance> nonSubmitted = listApprovalInstance.stream()
                .filter(instance -> Constants.COMMON_YESNO_NO.equals(instance.getSubmitted()))
                .collect(Collectors.toList());
        context.getRuntimeService().getApprovalService().exclude(nonSubmitted);
    }

}
