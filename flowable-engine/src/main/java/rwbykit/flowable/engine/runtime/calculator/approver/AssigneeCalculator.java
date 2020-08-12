package rwbykit.flowable.engine.runtime.calculator.approver;

import rwbykit.flowable.engine.Calculator;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.runtime.model.Approver;

import java.util.List;

/**
 * 审批人员范围计算器
 * 
 * @author Cytus_
 * @since 2018年12月17日 下午4:14:25
 * @version 1.0
 */
public interface AssigneeCalculator extends Calculator<Context, List<Approver>> {


    String ASSIGNEES = "Assignees";
}
