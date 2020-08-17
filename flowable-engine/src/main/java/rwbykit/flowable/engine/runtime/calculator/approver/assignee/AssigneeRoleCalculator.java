package rwbykit.flowable.engine.runtime.calculator.approver.assignee;

import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.Constants;

/**
 * 人员角色相关计算器
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午4:45:45
 * @version 1.0
 */
@Type(category = Constants.TYPE_CALCULATOR_ASSIGNEE, type = "Role")
public class AssigneeRoleCalculator extends GenericAssigneeCalculator {

    AssigneeService assigneeService;

    public AssigneeRoleCalculator(AssigneeService assigneeService) {
        this.assigneeService = assigneeService;
    }

    @Override
    public AssigneeService getAssigneeService() {
        return this.assigneeService;
    }
}
