package rwbykit.flowable.engine.runtime.calculator.approver;

import rwbykit.flowable.core.Calculator;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.model.runtime.Approver;

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
