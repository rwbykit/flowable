package rwbykit.flowable.engine.runtime.calculator.approver;

import rwbykit.flowable.engine.Calculator;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.runtime.model.Approver;

import java.util.List;

/**
 * 审批人员计算器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月20日 下午5:58:45
 */
public interface ApproverCalculator extends Calculator<Context, List<Approver>> {

}
