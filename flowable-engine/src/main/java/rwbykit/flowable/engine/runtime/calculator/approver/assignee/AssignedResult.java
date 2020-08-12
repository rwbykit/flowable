package rwbykit.flowable.engine.runtime.calculator.approver.assignee;

import rwbykit.flowable.engine.AbstractResult;

import java.util.List;

public class AssignedResult extends AbstractResult<List<AssigneeInformation>> {

    private List<AssigneeInformation> assignees;

    private AssignedResult(List<AssigneeInformation> assignees) {
        super(true, null, null);
        this.assignees = assignees;
    }

    private AssignedResult(String errorCode, String errorMessage) {
        super(false, errorCode, errorMessage);
    }

    public static AssignedResult success(List<AssigneeInformation> assignees) {
        return new AssignedResult(assignees);
    }

    public static AssignedResult failure(String errorCode, String errorMessage) {
        return new AssignedResult(errorCode, errorMessage);
    }

    @Override
    public List<AssigneeInformation> getResult() {
        return this.assignees;
    }
}
