package rwbykit.flowable.engine.runtime.calculator.approver.assignee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.FlowableRuntimeException;
import rwbykit.flowable.engine.factory.support.Factory;
import rwbykit.flowable.engine.runtime.current.Initiator;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.engine.runtime.runner.Runner;
import rwbykit.flowable.core.model.ArtifactNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义的审批人员计算
 * 
 * @author Cytus_
 * @since 2018年12月17日 下午4:15:52
 * @version 1.0
 */
@Type(category = Constants.TYPE_CALCULATOR_ASSIGNEE, type = "Runner")
public class RunnerAssigneeCalculator extends GenericAssigneeCalculator {

    private final static Logger logger = LoggerFactory.getLogger(RunnerAssigneeCalculator.class);

    @Override
    public List<Approver> calculate(Context context) {
        ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
        Initiator initiator = context.getCurrentInstance().getInitiator();
        final AssigneeParameter assigneeParameter = AssigneeParameter.builder()
                .initiator(initiator)
                .processId(context.getCurrentInstance().getProcessId())
                .processInstanceId(context.getCurrentInstance().getProcessInstanceId())
                .nodeId(context.getCurrentInstance().getNodeId())
                .nodeInstanceId(context.getCurrentInstance().getNodeInstanceId())
                .build();
        return node.getAssignment().getAssignees().stream()
                .map(assignee -> execute(assignee.getType(), assignee.getValue(), assigneeParameter))
                .flatMap(List::parallelStream)
                .map(this::toApprover)
                .collect(Collectors.toList());
    }


    @Override
    public AssigneeService getAssigneeService() {
        return null;
    }

    protected final static List<AssigneeInformation> execute(String executeMode, String value, AssigneeParameter parameter) {
        try {
            Runner<AssigneeParameter, AssignedResult> runner = Factory.factory().getRunner(executeMode);
            AssignedResult result = runner.run(value, parameter);
            if (result.isSuccess()) {
                return result.getResult();
            } else {
                throw new FlowableRuntimeException(result.errorCode(), result.errorMessage());
            }
        } catch (FlowableException e) {
            logger.error("当前路由条件执行异常!", e);
            throw new FlowableRuntimeException(e.getErrorCode(), e.getErrorMessage(), e);
        }
    }
}
