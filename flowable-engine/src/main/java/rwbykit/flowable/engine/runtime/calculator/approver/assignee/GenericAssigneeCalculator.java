package rwbykit.flowable.engine.runtime.calculator.approver.assignee;

import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.runtime.calculator.approver.AssigneeCalculator;
import rwbykit.flowable.engine.runtime.current.CurrentInstance;
import rwbykit.flowable.engine.runtime.current.Initiator;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.core.util.Strings;
import rwbykit.flowable.core.model.ArtifactNode;
import rwbykit.flowable.core.model.Assignee;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 审批人员计算
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月17日 下午3:19:29
 */
@Type(category = Constants.TYPE_CALCULATOR_ASSIGNEE, type = "Default")
public class GenericAssigneeCalculator implements AssigneeCalculator {

    private final static AssigneeService DEFAULT_ASSIGNEE_SERVICE = new DefaultAssigneeService();

    @Override
    public List<Approver> calculate(Context context) {
        ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
        Initiator initiator = context.getCurrentInstance().getInitiator();
        CurrentInstance instance = context.getCurrentInstance().cloneCurrentInstance();
        return node.getAssignment().getAssignees().stream()
                .map(assignee -> doCalculate(assignee, initiator, instance))
                .flatMap(List::parallelStream)
                .collect(Collectors.toList());
    }

    public List<Approver> doCalculate(Assignee assignee, Initiator initiator, CurrentInstance currentInstance) {
        AssigneeService assigneeService = this.getAssigneeService();
        if (Objects.nonNull(assigneeService)) {
            List<AssigneeInformation> assigneeInformationList = assigneeService.getAssigneeInformation(Strings.safeSplit(assignee.getValue(), ";"));
            return assigneeInformationList.parallelStream().map(this::toApprover).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public AssigneeService getAssigneeService() {
        return DEFAULT_ASSIGNEE_SERVICE;
    }

    /**
     * user to Approver
     *
     * @param assigneeInformation
     * @return
     */
    protected Approver toApprover(AssigneeInformation assigneeInformation) {
        return Approver.of(assigneeInformation.getCode(), assigneeInformation.getName(), assigneeInformation.getDetails());
    }

    static class DefaultAssigneeService implements AssigneeService {

        @Override
        public List<AssigneeInformation> getAssigneeInformation(String... ids) {
            return Collections.emptyList();
        }
    }
}
