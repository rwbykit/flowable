package rwbykit.flowable.engine.runtime.calculator.approver.assignee;


/**
 * 人员角色相关计算器
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午4:45:45
 * @version 1.0
 */
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
