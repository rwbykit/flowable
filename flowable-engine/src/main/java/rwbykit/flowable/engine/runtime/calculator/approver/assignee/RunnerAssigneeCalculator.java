package rwbykit.flowable.engine.runtime.calculator.approver.assignee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.FlowableRuntimeException;
import rwbykit.flowable.engine.factory.GenericObjectFactory;
import rwbykit.flowable.core.current.Initiator;
import rwbykit.flowable.core.model.runtime.Approver;
import rwbykit.flowable.engine.runtime.runner.Runner;
import rwbykit.flowable.core.model.parser.ArtifactNode;

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
            Runner<AssigneeParameter, AssignedResult> runner = GenericObjectFactory.factory().getRunner(executeMode);
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
