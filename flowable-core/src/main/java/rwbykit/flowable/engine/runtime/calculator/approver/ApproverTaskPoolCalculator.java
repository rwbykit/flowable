package rwbykit.flowable.engine.runtime.calculator.approver;

import rwbykit.flowable.engine.Calculator;
import rwbykit.flowable.engine.runtime.model.Approver;

import java.util.List;

/**
 * 审批池的计算器
 * 
 * @author Cytus_
 * @since 2018年12月27日 上午9:59:26
 * @version 1.0
 */
public interface ApproverTaskPoolCalculator extends Calculator<ApprovalProcess, List<Approver>> {

}
