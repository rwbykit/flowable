package rwbykit.flowable.engine.runtime.actuator.node.artificial.approval;

import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.Supported;

public interface ApprovalSubmitPostProcessor extends Supported<String> {

    Context postProcessorApprovalSubmit(Context context, ArtificialApprovalSubmitResult submitResult);

}
