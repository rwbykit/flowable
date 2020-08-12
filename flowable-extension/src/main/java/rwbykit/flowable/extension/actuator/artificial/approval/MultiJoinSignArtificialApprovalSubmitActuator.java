package rwbykit.flowable.extension.actuator.artificial.approval;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ArtificialApprovalSubmitActuator;
import rwbykit.flowable.engine.runtime.actuator.node.artificial.approval.ArtificialApprovalSubmitResult;
import rwbykit.flowable.engine.runtime.model.ApprovalInstance;
import rwbykit.flowable.core.model.ArtifactNode;

import java.util.List;


/**
 * 会签审批提交
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月28日 下午3:17:16
 */
public class MultiJoinSignArtificialApprovalSubmitActuator implements ArtificialApprovalSubmitActuator {

    private final static Logger logger = LoggerFactory.getLogger(MultiJoinSignArtificialApprovalSubmitActuator.class);

    @Override
    public ArtificialApprovalSubmitResult execute(Context context) throws FlowableException {
        List<ApprovalInstance> approvalInstances = context.getRuntimeService().getApprovalService().getAllApprovalInstance(context.getCurrentInstance().getNodeInstanceId());
        ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
        MultiJoinSignParameter parameter = MultiJoinSignParameter.builder()
                .approvalInstances(approvalInstances)
                .nodeId(context.getCurrentInstance().getNodeId())
                .nodeInstanceId(context.getCurrentInstance().getNodeInstanceId())
                .processId(context.getCurrentInstance().getProcessId())
                .processInstanceId(context.getCurrentInstance().getProcessInstanceId())
                .build();


        /*if (MultiRuleType.compare(MultiRuleType.SYSTEM, null)) {
            CustomizedMultiJoinSignRuleCalculator rule = SpringContexts.getBean(beanName, CustomizedMultiJoinSignRuleCalculator.class);
            logger.debug("节点实例[{}], 节点[{}]使用系统预定义会签规则执行开始", nodeInstId, nvNode.getId());
            runnerResult = null;//rule.execute(parameter);

        } else {
            logger.debug("节点实例[{}], 节点[{}]自定义会签规则执行开始", nodeInstId, nvNode.getId());
            Runner<MultiJoinSignParameter, Result<?>> runner = RunnerFactory.factory().getRunner(nvMulti.getExecutionMode());
            runnerResult = runner.run(nvMulti.getValue(), parameter);
        }

        if (!runnerResult.isSuccess()) {
            throw new FlowableRuntimeException(runnerResult.errorCode(), runnerResult.errorMessage());
        } else {
            boolean result = Boolean.valueOf(Strings.replaceNullByObj(runnerResult.getResult()));
            logger.info("节点实例[{}], 节点[{}], 为审批提交，审批结果为：{}", nodeInstId, nvNode.getId(), result ? "拒绝" : "通过");
            return result ? ArtificialApprovalSubmitResult.PASS : ArtificialApprovalSubmitResult.REFUSE;
        }
    */
        return null;
    }

}
